package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Homework extends Model {

    @Id
    private long id;

    private long courseTeacherId;

    private String name;

    private String path;

    private String date;

    private Boolean vaild;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseTeacherId() {
        return courseTeacherId;
    }

    public void setCourseTeacherId(long courseTeacherId) {
        this.courseTeacherId = courseTeacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getVaild() {
        return vaild;
    }

    public void setVaild(Boolean vaild) {
        this.vaild = vaild;
    }
}
