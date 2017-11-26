package dao;

import models.Person;

import java.util.List;

public abstract class PersonDao<T extends Person> implements Dao {
    public abstract List<T> list();

    public abstract T findById(long id);
    public abstract T findByName(String name);
    public abstract void delete(long id);
}
