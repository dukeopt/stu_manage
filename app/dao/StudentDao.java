package dao;

import io.ebean.Finder;
import models.Student;

import java.util.List;

public class StudentDao extends PersonDao {

    public static final Finder<Long, Student> find = new Finder<>(Student.class);

    @Override
    public List<Student> all() {
        return find.all();
    }

    @Override
    public Student findById(long id) {
        return find.byId(id);
    }

    @Override
    public Student findByName(String name) {
        return find.query().where()
                .eq("name", name)
                .findUnique();
    }

    @Override
    public void delete(long id) {
        find.deleteById(id);
    }
    
}
