package models;

import javax.persistence.*;

import io.ebean.*;
import play.data.validation.*;

@Entity
public class Teacher extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String password;

    public static final Finder<Long, Teacher> find = new Finder<>(Teacher.class);
}