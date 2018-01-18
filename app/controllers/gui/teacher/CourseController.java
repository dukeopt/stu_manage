package controllers.gui.teacher;

import dao.CourseTeacherDao;
import models.Course;
import models.CourseTeacher;
import models.construct.CT;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class CourseController extends Controller {

    CourseTeacherDao ctDao;

    @Inject
    public CourseController(CourseTeacherDao ctDao) {
        this.ctDao = ctDao;
    }

    public Result list() {
        long tId =  Long.parseLong(session().get("tid"));
        List<CT> cts = ctDao.optList(tId);
        return ok(views.html.teacher.course.list.render(cts));
    }

    public Result add() {
        List<Course> courses = ctDao.list(Long.parseLong(session().get("tid")));
        return ok(views.html.teacher.course.add.render(courses));
    }

    public Result save(long cId) {
        long tId = Long.parseLong(session().get("tid"));
        CourseTeacher ct = new CourseTeacher();
        ct.setTeacherId(tId);
        ct.setCourseId(cId);
        ct.save();
        return redirect("/teacher/course/list");
    }

    public Result del(long id) {
        ctDao.delete(id);
        return redirect("/teacher/course/list");
    }
}
