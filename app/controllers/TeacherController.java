package controllers;

import dao.TeacherDao;
import data.forms.teacher.AddForm;
import data.forms.teacher.EditForm;
import models.Teacher;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class TeacherController extends Controller {

    private FormFactory formFactory;
    private TeacherDao teacherDao;
    @Inject
    public TeacherController(FormFactory formFactory,
                            TeacherDao teacherDao) {
        this.formFactory = formFactory;
        this.teacherDao = teacherDao;
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public Result add() {
        Form<AddForm> formData = formFactory.form(AddForm.class);
        return ok(views.html.teacher.add.render(formData));
    }

    public Result save() {

        Form<AddForm> formData = formFactory.form(AddForm.class).bindFromRequest();
        if (formData.hasErrors()) {
            return badRequest(views.html.teacher.add.render(formData));
        } else {
            AddForm addForm = formData.get();
            Teacher teacher = new Teacher();
            teacher.setName(addForm.name);
            teacher.setPassword(addForm.password);
            teacher.save();
            return redirect("list");
        }
    }

    public Result edit(long id) {

        EditForm formData = new EditForm();
        formData.id = id;
        Form<EditForm> form = formFactory.form(EditForm.class).fill(formData);
        return ok(views.html.teacher.edit.render(form));
    }

    public Result update() {

        Form<EditForm> formData = formFactory.form(EditForm.class).bindFromRequest();
        if (formData.hasErrors()) {
            return badRequest(views.html.teacher.edit.render(formData));
        } else {
            EditForm editForm = formData.get();
            Teacher teacher = teacherDao.findById(editForm.id);
            teacher.setPassword(editForm.password);
            teacher.update();
            return redirect("list");
        }
    }

    public Result list() {
        List<Teacher> teachers = teacherDao.list();
        return ok(views.html.teacher.list.render(teachers));
    }

    public Result delete(long id) {
        teacherDao.delete(id);
        return redirect("/teacher/list");
    }
}
