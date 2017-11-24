package dao;

import io.ebean.Model;

import java.util.List;

public interface Dao<T extends Model> {
    List<T> all();

    T findById(long id);
    T findByName(String name);
    void delete(long id);

}
