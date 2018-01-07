package controllers.gui.teacher;

import dao.HomeworkDao;
import dao.ScoreDao;
import data.forms.score.EditForm;
import models.Score;
import models.construct.HCT;
import models.construct.HS;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.File;
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

    public Result download(long id) {
        Score score = scoreDao.findById(id);
        File file = new File("public/" + score.getStudentHomeworkPath());
        return ok(file , /*inline = */false);
    }

    public Result edit(long id) {
        Score score = scoreDao.findById(id);
        EditForm formData = new EditForm();
        formData.id = id;
        formData.score = String.valueOf(score.getScore());
        formData.homeworkId = score.getHomeworkId();
        formData.studentId = score.getStudentId();
        Form<EditForm> form = formFactory.form(EditForm.class).fill(formData);
        return ok(views.html.teacher.score.edit.render(form));
    }

    public Result update() {

        Form<EditForm> formData = formFactory.form(EditForm.class).bindFromRequest();
        if (formData.hasErrors()) {
            return badRequest(views.html.teacher.score.edit.render(formData));
        } else {
            EditForm editForm = formData.get();
            Score score = scoreDao.findById(editForm.id);
            score.setScore(Double.parseDouble(editForm.score));
            score.update();
            return redirect("/teacher/score/list");
        }
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
