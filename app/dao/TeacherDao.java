package dao;

import io.ebean.Finder;
import models.Teacher;

import java.util.List;

public class TeacherDao extends PersonDao {
    public static final Finder<Long, Teacher> find = new Finder<>(Teacher.class);

    @Override
    public List<Teacher> all() {
        return find.all();
    }

    @Override
    public Teacher findById(long id) {
        return find.byId(id);
    }

    @Override
    public Teacher findByName(String name) {
        return find.query().where()
                .eq("name", name)
                .findUnique();
    }

    @Override
    public void delete(long id) {
        find.deleteById(id);
    }
}
