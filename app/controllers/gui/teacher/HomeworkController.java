package controllers.gui.teacher;

import javax.inject.Inject;

import dao.CourseTeacherDao;
import dao.HomeworkDao;
import models.Homework;
import models.construct.CT;
import models.construct.HCT;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.File;
import java.util.List;

public class HomeworkController extends Controller {

    private HomeworkDao homeworkDao;
    private CourseTeacherDao ctDao;

    @Inject
    public HomeworkController(HomeworkDao homeworkDao, CourseTeacherDao ctDao) {
        this.homeworkDao = homeworkDao;
        this.ctDao = ctDao;
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

        play.mvc.Http.MultipartFormData body = request().body().asMultipartFormData();
        play.mvc.Http.MultipartFormData.FilePart file_input = body.getFile("file_input");
        File file = (File) file_input.getFile();
        System.out.println(file.length());

        long tId = Long.parseLong(session().get("id"));
//        Homework homeworks = new Homework();
//        homeworks.setCourseTeacherId(tId);
//        homeworks.save();
        return redirect("/teacher/homework/list");
    }

    public Result download(long id) {
        return ok();
    }

    public Result del(long id) {
        homeworkDao.delete(id);
        return redirect("/teacher/homeworks/list");
    }

}
