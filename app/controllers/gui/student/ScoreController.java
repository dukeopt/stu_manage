package controllers.gui.student;

import dao.CourseStudentDao;
import dao.HomeworkDao;
import dao.ScoreDao;
import models.Score;
import models.construct.CS;
import models.construct.HCT;
import models.construct.HS;
import org.apache.commons.io.FileUtils;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ScoreController extends Controller {

    private HomeworkDao homeworkDao;
    private ScoreDao scoreDao;
    private CourseStudentDao courseStudentDao;
    private FormFactory formFactory;

    @Inject
    public ScoreController(HomeworkDao homeworkDao, ScoreDao scoreDao, CourseStudentDao courseStudentDao, FormFactory formFactory) {
        this.homeworkDao = homeworkDao;
        this.scoreDao = scoreDao;
        this.courseStudentDao = courseStudentDao;
        this.formFactory = formFactory;
    }

    public Result list() {
        long sId =  Long.parseLong(session().get("sid"));
        List<CS> csList = courseStudentDao.optList(sId);
        List<HS> homeworkScores = scoreDao.studengList(sId, -1, -1);
        return ok(views.html.student.score.list.render(csList, homeworkScores));
    }


    public Result search(long cId, long hId) {
        long sId =  Long.parseLong(session().get("sid"));
        List<CS> csList = courseStudentDao.optList(sId);
        List<HS> homeworkScores = scoreDao.studengList(sId, cId, hId);
        return ok(views.html.student.score.list.render(csList, homeworkScores));
    }

    public Result add() {
        long sId =  Long.parseLong(session().get("sid"));
        List<CS> csList = courseStudentDao.optList(sId);
        List<HCT> hctList = homeworkDao.noScorelist(sId, -1, -1);
        return ok(views.html.student.score.add.render(csList, hctList));
    }

    public Result addSearch(long cId, long hId) {
        long sId =  Long.parseLong(session().get("sid"));
        List<CS> csList = courseStudentDao.optList(sId);
        List<HCT> hctList = homeworkDao.noScorelist(sId, cId, hId);
        return ok(views.html.student.score.add.render(csList, hctList));
    }

    public Result addInit(long hId, String hName) {
        return ok(views.html.student.score.save.render(hId, hName));
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

        String basePath = "student" + "/" + session().get("sid") + "/" + timestamp + "/";
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

        long sId =  Long.parseLong(session().get("sid"));
        Score score = new Score();
        score.setStudentId(sId);
        score.setHomeworkId(Long.parseLong(from.get("hId")));
        score.setStudentHomeworkPath(basePath + fileName);
        score.save();
        return redirect("/student/score/list");
    }

    public Result download(long id) {
        Score score = scoreDao.findById(id);
        File file = new File("public/" + score.getStudentHomeworkPath());
        return ok(file , /*inline = */false);
    }

    public Result homeworklist(Long cId) {
        List<HCT> hctList = homeworkDao.slist(cId);
        return ok(Json.toJson(hctList));
    }
}
