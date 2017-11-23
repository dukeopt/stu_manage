package models;

import javax.persistence.*;

import io.ebean.*;
import play.data.validation.*;

@Entity
public class Teacher extends Model {

    @Id
    public Long id;

    public String name;

    public String password;

    public static final Finder<Long, Teacher> find = new Finder<>(Teacher.class);
}