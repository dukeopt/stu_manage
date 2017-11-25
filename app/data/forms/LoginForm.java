package data.forms;

import dao.AdminDao;
import dao.Dao;
import dao.StudentDao;
import dao.TeacherDao;
import models.Person;
import play.Play;
import play.data.validation.Constraints;
import play.data.validation.Constraints.*;
import play.data.validation.ValidationError;
import service.MessageService;

import java.util.*;

import static play.mvc.Controller.session;

@Validate
public class LoginForm implements Validatable<List<ValidationError>> {
    @Constraints.Required
    public String name;
    @Constraints.Required
    public String password;
    public String type;

    public LoginForm() {
    }

    public LoginForm(String name,
                     String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public List<ValidationError> validate() {

        final List<ValidationError> errors = new ArrayList<>();
        MessageService messageService = Play.application().injector().instanceOf(MessageService.class);
        Dao dao;
        Person person = null;

        if (name != null && name.length() != 0 && password != null && password.length() != 0) {

            if (type.equals("student")) {
                dao = Play.application().injector().instanceOf(StudentDao.class);
                person = (Person) dao.findByName(name);
            } else if (type.equals("teacher")) {
                dao = Play.application().injector().instanceOf(TeacherDao.class);
                person = (Person) dao.findByName(name);
            } else {
                dao = Play.application().injector().instanceOf(AdminDao.class);
                person = (Person) dao.findByName(name);
            }
            if (person == null) {
                errors.add(new ValidationError("name", messageService.at("error.emptyName")));
            } else {
                if (!person.getPassword().equals(password)) {
                    errors.add(new ValidationError("password", messageService.at("error.password")));
                } else {
                    session().put("id", String.valueOf(person.getId()));
                    session().put("name", String.valueOf(person.getName()));
                    session().put("type", type);
                }
            }
        }
        return errors.isEmpty() ? null : errors;
    }
}
