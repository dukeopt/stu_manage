package controllers.gui.teacher;

import javax.inject.Inject;

import dao.CourseTeacherDao;
import dao.HomeworkDao;
import models.Homework;
import models.construct.CT;
import models.construct.HCT;
import org.apache.commons.io.FileUtils;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomeworkController extends Controller {

    private HomeworkDao homeworkDao;
    private CourseTeacherDao ctDao;
    private FormFactory formFactory;

    @Inject
    public HomeworkController(HomeworkDao homeworkDao, CourseTeacherDao ctDao, FormFactory formFactory) {
        this.homeworkDao = homeworkDao;
        this.ctDao = ctDao;
        this.formFactory = formFactory;
    }

    public Result list() {
        long tId =  Long.parseLong(session().get("id"));
        List<HCT> homeworks = homeworkDao.list(tId);
        return ok(views.html.teacher.homework.list.render(homeworks));
    }

    public Result add() {
        List<CT> courses = ctDao.optList(Long.parseLong(session().get("id")));
        return ok(views.html.teacher.homework.add.render(courses));
    }

    public Result save() {

        // 获取文件
        play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
        play.mvc.Http.MultipartFormData.FilePart file_input = body.getFile("file_input");
        File srcFile = (File) file_input.getFile();

        DateFormat formatDir = new SimpleDateFormat("yyyyMMdd-HHmmssSSS");
        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String timestamp = formatDir.format(now);

        String basePath = "teacher" + "/" + session().get("id") + "/" + timestamp + "/";
        String publicPath = "public" + "/" + basePath;
        String fileName = file_input.getFilename();

        // 创建文件夹
        File dirFile = new File(publicPath);
        dirFile.mkdirs();

        // 文件复制到public文件夹下
        try {
            File destFile = new File(publicPath   + fileName);
            FileUtils.copyFile(srcFile, destFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DynamicForm from = formFactory.form().bindFromRequest();

        Homework homework = new Homework();
        homework.setCourseTeacherId(Long.parseLong(from.get("course")));
        homework.setName(from.get("name"));
        homework.setPath(basePath + fileName);
        homework.setDate(formatDate.format(now));
        homework.save();
        return redirect("/teacher/homework/list");
    }

    public Result del(long id) {
        homeworkDao.delete(id);
        return redirect("/teacher/homework/list");
    }
}
