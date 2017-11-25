package models;

import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 成绩
 */
@Entity
public class Score extends Model {
    @Id
    private long id;

    private double score;

    private long studentId;

    private long courseId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
