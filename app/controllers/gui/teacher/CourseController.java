package controllers.gui.teacher;

import dao.CourseTeacherDao;
import models.CourseTeacher;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class CourseController extends Controller {

    CourseTeacherDao ctDao;

    @Inject
    public CourseController(CourseTeacherDao ctDao) {

    }

    public Result add(long cId) {
        long tId = Long.getLong(session().get("id"));
        CourseTeacher ct = new CourseTeacher();
        ct.setCourseId(cId);
        ct.setTeacherId(tId);
        ct.save();
        return ok();
    }

    public Result del(long id) {
        ctDao.delete(id);
        return ok();
    }

    public Result list() {
        List<CourseTeacher> cts = ctDao.list();
        return ok();
    }


}
