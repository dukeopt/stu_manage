package controllers.admin;

import play.mvc.Controller;
import play.mvc.Result;

public class AdminController extends Controller {

    public Result index() {
        return ok(views.html.index.render());
    }
}
