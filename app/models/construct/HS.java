package models.construct;

/**
 * 作业 成绩
 */
public class HS {

    private long id;

    private double score;

    private long courseId;

    private String courseName;

    private long homeworkId;

    private String homeworkName;

    private long teacherId;

    private String teacherName;

    private String teacherHomeWorkPath;

    private long studentId;

    private String studentName;

    private String studentHomeWorkPath;

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

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherHomeWorkPath() {
        return teacherHomeWorkPath;
    }

    public void setTeacherHomeWorkPath(String teacherHomeWorkPath) {
        this.teacherHomeWorkPath = teacherHomeWorkPath;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentHomeWorkPath() {
        return studentHomeWorkPath;
    }

    public void setStudentHomeWorkPath(String studentHomeWorkPath) {
        this.studentHomeWorkPath = studentHomeWorkPath;
    }
}
