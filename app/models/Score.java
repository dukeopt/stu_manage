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

    private Double score;

    private String studentHomeworkPath;

    private long studentId;

    private long homeworkId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getStudentHomeworkPath() {
        return studentHomeworkPath;
    }

    public void setStudentHomeworkPath(String studentHomeworkPath) {
        this.studentHomeworkPath = studentHomeworkPath;
    }

}
