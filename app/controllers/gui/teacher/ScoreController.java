package controllers.gui.teacher;

import dao.HomeworkDao;
import dao.ScoreDao;
import models.construct.HCT;
import models.construct.HS;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class ScoreController extends Controller {

    private HomeworkDao homeworkDao;
    private ScoreDao scoreDao;
    private FormFactory formFactory;

    @Inject
    public ScoreController(HomeworkDao homeworkDao, ScoreDao scoreDao, FormFactory formFactory) {
        this.homeworkDao = homeworkDao;
        this.scoreDao = scoreDao;
        this.formFactory = formFactory;
    }

    public Result list() {
        long tId =  Long.parseLong(session().get("id"));
        Map<Long, List<HCT>> hctMap = homeworkDao.map(tId);
        List<HS> homeworkScores = scoreDao.list(tId, -1,-1);
        return ok(views.html.teacher.score.list.render(hctMap, homeworkScores));
    }

    public Result homeworklist(Long cId) {
        long tId =  Long.parseLong(session().get("id"));
        Map<Long, List<HCT>> hctMap = homeworkDao.map(tId);
        return ok(Json.toJson(hctMap.get(cId)));
    }

    public Result search(long cId, long hId) {
        long tId =  Long.parseLong(session().get("id"));
        Map<Long, List<HCT>> hctMap = homeworkDao.map(tId);
        List<HS> homeworkScores = scoreDao.list(tId, cId, hId);
        return ok(views.html.teacher.score.list.render(hctMap, homeworkScores));
    }

//    public Result add() {
//        List<Course> courses = ctDao.list(Long.parseLong(session().get("id")));
//        return ok(views.html.teacher.course.add.render(courses));
//    }
//
//    public Result save(long cId) {
//        long tId = Long.parseLong(session().get("id"));
//        CourseTeacher ct = new CourseTeacher();
//        ct.setTeacherId(tId);
//        ct.setCourseId(cId);
//        ct.save();
//        return redirect("/teacher/course/list");
//    }


}
