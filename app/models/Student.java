package models;

import javax.persistence.*;

import io.ebean.*;
import play.data.validation.*;

@Entity
public class Student extends Model {

    @Id
    public Long id;

    public String name;

    public String password;

    public static final Finder<Long, Student> find = new Finder<>(Student.class);
}
