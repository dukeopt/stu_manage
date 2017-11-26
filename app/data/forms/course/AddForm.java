package data.forms.course;

import play.data.validation.Constraints;

public class AddForm {

    @Constraints.Required
    public String name;
}
