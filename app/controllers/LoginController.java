package controllers;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import views.form.LoginForm;
import views.html.login;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class LoginController extends Controller {

    private FormFactory formFactory;
    @Inject
    public LoginController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result index() {
        Form<LoginForm> formData = formFactory.form(LoginForm.class);
        return ok(login.render(formData, ""));
    }

}
