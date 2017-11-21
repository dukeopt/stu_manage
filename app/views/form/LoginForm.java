package views.form;

import play.data.validation.ValidationError;
import play.i18n.Messages;

import java.util.ArrayList;
import java.util.List;

import static play.mvc.Controller.session;

public class LoginForm {
    public String name = "";
    public String password = "";

    public LoginForm() {
    }

    public LoginForm(String name,
                     String password) {
        this.name = name;
        this.password = password;
    }

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();

        if (name == null || name.length() == 0) {
//            errors.add(new ValidationError("email", Messages.get("error.emptyUserMail")));
        }
        if (password == null || password.length() == 0) {
//            errors.add(new ValidationError("password", Messages.get("error.emptyPassword")));
        }
        if (name != null && name.length() != 0 && password != null && password.length() != 0) {

            session().put("user", "1");
        }

        return errors.isEmpty() ? null : errors;
    }
}
