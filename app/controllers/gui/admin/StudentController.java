package controllers.gui.admin;

import dao.StudentDao;
import data.forms.teacher.AddForm;
import data.forms.teacher.EditForm;
import models.Student;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class StudentController  extends Controller {

    private FormFactory formFactory;
    private StudentDao studentDao;

    @Inject
    public StudentController(FormFactory formFactory,
                             StudentDao studentDao) {
        this.formFactory = formFactory;
        this.studentDao = studentDao;
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public Result add() {
        Form<AddForm> formData = formFactory.form(AddForm.class);
        return ok(views.html.admin.student.add.render(formData));
    }

    public Result save() {

        Form<AddForm> formData = formFactory.form(AddForm.class).bindFromRequest();
        if (formData.hasErrors()) {
            return badRequest(views.html.admin.student.add.render(formData));
        } else {
            AddForm addForm = formData.get();
            Student student = new Student();
            student.setName(addForm.name);
            student.setPassword(addForm.password);
            student.save();
            return redirect("/admin/student/list");
        }
    }

    public Result edit(long id) {

        EditForm formData = new EditForm();
        formData.id = id;
        Form<EditForm> form = formFactory.form(EditForm.class).fill(formData);
        return ok(views.html.admin.student.edit.render(form));
    }

    public Result update() {

        Form<EditForm> formData = formFactory.form(EditForm.class).bindFromRequest();
        if (formData.hasErrors()) {
            return badRequest(views.html.admin.student.edit.render(formData));
        } else {
            EditForm editForm = formData.get();
            Student student = studentDao.findById(editForm.id);
            student.setPassword(editForm.password);
            student.update();
            return redirect("/admin/student/list");
        }
    }

    public Result list() {
        List<Student> students = studentDao.list();
        return ok(views.html.admin.student.list.render(students));
    }

    public Result delete(long id) {
        studentDao.delete(id);
        return redirect("/admin/student/list");
    }
}
