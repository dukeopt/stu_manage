package data.forms.teacher;

import play.Play;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import service.MessageService;

import java.util.ArrayList;
import java.util.List;

@Constraints.Validate
public class AddForm implements Constraints.Validatable<List<ValidationError>> {

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String password;

    @Constraints.Required
    public String cPassword;

    @Override
    public List<ValidationError> validate() {

        final List<ValidationError> errors = new ArrayList<>();
        MessageService messageService = Play.application().injector().instanceOf(MessageService.class);

        if (!cPassword.equals(password)) {


            errors.add(new ValidationError("cPassword", messageService.at("error.confirm.password")));

        }
        return errors.isEmpty() ? null : errors;
    }
}
