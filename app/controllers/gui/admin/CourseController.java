package controllers.gui.admin;

import dao.CourseDao;
import data.forms.course.AddForm;
import models.Course;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

/**
 * 课程
 */
public class CourseController extends Controller {

    private FormFactory formFactory;
    private CourseDao courseDao;

    @Inject
    public CourseController(FormFactory formFactory,
                            CourseDao courseDao) {
        this.formFactory = formFactory;
        this.courseDao = courseDao;
    }

    public Result add() {
        Form<AddForm> addForm = formFactory.form(AddForm.class);
        return ok(views.html.admin.course.add.render(addForm));
    }

    public Result save() {

        Form<AddForm> formData = formFactory.form(AddForm.class).bindFromRequest();
        if (formData.hasErrors()) {
            return badRequest(views.html.admin.course.add.render(formData));
        } else {
            AddForm addForm = formData.get();
            Course course = new Course();
            course.setName(addForm.name);
            course.save();
            return redirect("/admin/course/list");
        }
    }

    public Result list() {
        List<Course> courses = courseDao.list();
        return ok(views.html.admin.course.list.render(courses));
    }

    public Result delete(long id) {
        courseDao.delete(id);
        return redirect("/admin/course/list");
    }
}
