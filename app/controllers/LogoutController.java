package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class LogoutController extends Controller{

    public Result logout() {
        session().clear();
        return redirect("/login");
    }
}
