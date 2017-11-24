package dao;

import io.ebean.Finder;
import models.Admin;

import java.util.List;

public class AdminDao implements Dao {

    public static final Finder<Long, Admin> find = new Finder<>(Admin.class);

    @Override
    public List<Admin> all() {
        return find.all();
    }

    @Override
    public Admin findById(long id) {
        return find.byId(id);
    }

    @Override
    public Admin findByName(String name) {
        return find.query().where()
                .eq("name", name)
                .findUnique();
    }

    @Override
    public void delete(long id) {
        find.deleteById(id);
    }
}
