package controllers.student;

import play.mvc.Controller;
import play.mvc.Result;

public class StudentController extends Controller {

    public Result index() {
        return ok(views.html.index.render());
    }
}
