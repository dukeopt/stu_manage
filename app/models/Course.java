package models;

import javax.persistence.*;

import io.ebean.*;

@Entity
public class Course extends Model {

    @Id
    public Long id;

    public String name;
}
