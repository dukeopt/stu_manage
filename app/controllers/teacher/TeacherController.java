package controllers.teacher;

import play.mvc.Controller;
import play.mvc.Result;

public class TeacherController extends Controller {

    public Result index() {
        return ok(views.html.index.render());
    }
}
