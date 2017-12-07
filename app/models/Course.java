package models;

import javax.persistence.*;

import io.ebean.*;

/**
 * 课程
 */
@Entity
public class Course extends Model {

    @Id
    private Long id;

    private String name;

    private Boolean vaild;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVaild() {
        return vaild;
    }

    public void setVaild(Boolean vaild) {
        this.vaild = vaild;
    }
}
