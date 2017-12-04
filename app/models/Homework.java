package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Homework extends Model {

    @Id
    private long id;

    private long courseTacherId;

    private String name;

    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseTacherId() {
        return courseTacherId;
    }

    public void setCourseTacherId(long courseTacherId) {
        this.courseTacherId = courseTacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
