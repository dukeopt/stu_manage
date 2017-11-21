package models;

import javax.persistence.*;

import io.ebean.*;
import play.data.validation.*;

@Entity
public class Student extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String password;

    public static final Finder<Long, Student> find = new Finder<>(Student.class);
}
