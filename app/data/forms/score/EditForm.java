package data.forms.score;

import play.Play;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import service.MessageService;

import java.util.ArrayList;
import java.util.List;

@Constraints.Validate
public class EditForm implements Constraints.Validatable<List<ValidationError>> {

    @Constraints.Required
    public Long id;

    @Constraints.Required
    public String score;

    @Constraints.Required
    public Long studentId;

    @Constraints.Required
    public Long homeworkId;

    @Override
    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();
        MessageService messageService = Play.application().injector().instanceOf(MessageService.class);

        try {
            System.out.println(Double.parseDouble(score));
        } catch (Exception e) {
            e.printStackTrace();
            errors.add(new ValidationError("score", messageService.at("error.score")));
        }
        return errors.isEmpty() ? null : errors;
    }
}