package dao;

import io.ebean.Finder;
import models.CourseTeacher;

import java.util.List;

public class CourseTeacherDao {

    public static final Finder<Long, CourseTeacher> find = new Finder<>(CourseTeacher.class);

    public List<CourseTeacher> list() {
        return find.all();
    }

    public void delete(long id) {
        find.deleteById(id);
    }
}
