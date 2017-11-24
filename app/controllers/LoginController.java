package controllers;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import data.forms.LoginForm;
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
        return ok(login.render(formData));
    }

    public Result login() {
        Form<LoginForm> formData = formFactory.form(LoginForm.class).bindFromRequest();
        if (formData.hasErrors()) {
            return badRequest(login.render(formData));
        } else {
            LoginForm loginForm = formData.get();
            if(loginForm.type.equals("student")) {
                return redirect("/student/");
            } else if (loginForm.type.equals("teacher")) {
                return redirect("/teacher");
            } else if (loginForm.type.equals("admin")) {
                return redirect("/admin");
            }
        }
        return ok();
    }

}
