package controllers.gui.student;

import dao.CourseStudentDao;
import models.Course;
import models.CourseStudent;
import models.construct.CS;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class CourseController extends Controller {

    CourseStudentDao csDao;

    @Inject
    public CourseController(CourseStudentDao csDao) {
        this.csDao = csDao;
    }

    public Result list() {
        long tId =  Long.parseLong(session().get("id"));
        List<CS> csList = csDao.optList(tId);
        return ok(views.html.student.course.list.render(csList));
    }

    public Result add() {
        List<Course> courses = csDao.list(Long.parseLong(session().get("id")));
        return ok(views.html.student.course.add.render(courses));
    }

    public Result save(long cId) {
        long sId = Long.parseLong(session().get("id"));
        CourseStudent cs = new CourseStudent();
        cs.setStudentId(sId);
        cs.setCourseId(cId);
        cs.save();
        return redirect("/student/course/list");
    }

    public Result del(long id) {
        csDao.delete(id);
        return redirect("/student/course/list");
    }
}
