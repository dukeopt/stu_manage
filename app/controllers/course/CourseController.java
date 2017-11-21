package controllers.course;

import play.mvc.Controller;
import play.mvc.Result;

public class CourseController extends Controller {

    public Result index() {
        return ok(views.html.index.render());
    }
}
