package models;

import javax.persistence.*;

import io.ebean.*;

@Entity
public class Admin extends Model {

    @Id
    public Long id;

    public String name;

    public String password;

    public static final Finder<Long, Admin> find = new Finder<>(Admin.class);
}
