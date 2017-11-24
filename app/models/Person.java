package models;

import io.ebean.*;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends Model {

    @Id
    public Long id;

    public String name;

    public String password;
}
