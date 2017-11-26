package dao;

import io.ebean.Finder;
import models.Course;

import java.util.List;

public class CourseDao  implements Dao {

    public static final Finder<Long, Course> find = new Finder<>(Course.class);

    @Override
    public List<Course> list() {
        return find.all();
    }

    @Override
    public Course findById(long id) {
        return find.byId(id);
    }

    @Override
    public Course findByName(String name) {
        return find.query().where()
                .eq("name", name)
                .findUnique();
    }

    @Override
    public void delete(long id) {
        find.deleteById(id);
    }
}
