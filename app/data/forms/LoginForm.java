package data.forms;

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

        if (name == null || name.length() == 0) {
            errors.add(new ValidationError("email", messageService.at("error.emptyName")));
        }
        if (password == null || password.length() == 0) {
            System.out.println("111" + messageService);
            errors.add(new ValidationError("password", messageService.at("error.emptyPassword")));
        }
        if (name != null && name.length() != 0 && password != null && password.length() != 0) {
            if (!password.equals("111")) {
                System.out.println("-----------------"+ messageService);
                errors.add(new ValidationError("password", messageService.at("error.emptyPassword")));
            }
            session().put("user", "1");
        }

        return errors.isEmpty() ? null : errors;
    }
}
