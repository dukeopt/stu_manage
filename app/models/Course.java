package models;

import javax.persistence.*;

import io.ebean.*;
import play.data.validation.*;

@Entity
public class Course extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String name;

    public static final Finder<Long, Course> find = new Finder<>(Course.class);
}
