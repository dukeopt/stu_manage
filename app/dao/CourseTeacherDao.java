package dao;

import io.ebean.Ebean;
import io.ebean.Finder;
import io.ebean.SqlRow;
import models.Course;
import models.CourseTeacher;
import models.construct.CT;

import java.util.ArrayList;
import java.util.List;

public class CourseTeacherDao {

    public static final Finder<Long, CourseTeacher> find = new Finder<>(CourseTeacher.class);

    /**
     * 教师已经选择的课程
     * @param tId
     * @return
     */
    public List<CT> optList(long tId) {
        String sql = " SELECT CT.ID AS CT_ID, C.ID AS C_ID, C.NAME AS C_NAME, T.ID AS T_ID, T.NAME AS T_NAME " +
                " FROM COURSE_TEACHER CT " +
                " INNER JOIN COURSE C ON CT.COURSE_ID = C.ID" +
                " INNER JOIN TEACHER T ON CT.TEACHER_ID = T.ID " +
                " WHERE T.ID = " + tId;

        List<SqlRow> rows = Ebean.createSqlQuery(sql).findList();
        List<CT> cts = new ArrayList<>();

        for (SqlRow row: rows) {
            CT ct = new CT();
            ct.setId(row.getLong("CT_ID"));
            ct.setCourseId(row.getLong("C_ID"));
            ct.setCourseName(row.getString("C_NAME"));
            ct.setTeacherId(row.getLong("T_ID"));
            ct.setTeacherName(row.getString("T_NAME"));
            cts.add(ct);
        }

        return cts;
    }

    /**
     * 教师还未经选择的课程
     * @param tId
     * @return
     */
    public List<Course> list(long tId) {
        String sql = " SELECT C.ID AS C_ID, C.NAME AS C_NAME " +
                " FROM COURSE C " +
                " WHERE NOT EXISTS (" +
                "   SELECT * " +
                "   FROM COURSE C1 " +
                "     INNER JOIN COURSE_TEACHER CT " +
                "     ON C1.ID=CT.COURSE_ID" +
                "     WHERE C.ID=C1.ID " +
                "     AND CT.TEACHER_ID = " + tId + ")";

        List<SqlRow> rows = Ebean.createSqlQuery(sql).findList();
        List<Course> courses = new ArrayList<>();

        for (SqlRow row: rows) {
            Course ct = new Course();
            ct.setId(row.getLong("C_ID"));
            ct.setName(row.getString("C_NAME"));
            courses.add(ct);
        }

        return courses;
    }

    public void delete(long id) {
        find.deleteById(id);
    }
}
