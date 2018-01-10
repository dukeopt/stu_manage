package dao;

import io.ebean.Ebean;
import io.ebean.Finder;
import io.ebean.SqlRow;
import models.Course;
import models.CourseStudent;
import models.construct.CS;

import java.util.ArrayList;
import java.util.List;

public class CourseStudentDao {

    public static final Finder<Long, CourseStudent> find = new Finder<>(CourseStudent.class);

    /**
     * 学生已经选择的课程
     * @param sId
     * @return
     */
    public List<CS> optList(long sId) {
        String sql = " SELECT CS.ID AS CS_ID, C.ID AS C_ID, C.NAME AS C_NAME, S.ID AS S_ID, S.NAME AS S_NAME " +
                " FROM COURSE_STUDENT CS " +
                " INNER JOIN COURSE C ON CS.COURSE_ID = C.ID" +
                " INNER JOIN STUDENT S ON CS.STUDENT_ID = S.ID " +
                " WHERE S.ID = " + sId;

        List<SqlRow> rows = Ebean.createSqlQuery(sql).findList();
        List<CS> csList = new ArrayList<>();

        for (SqlRow row: rows) {
            CS cs = new CS();
            cs.setId(row.getLong("CS_ID"));
            cs.setCourseId(row.getLong("C_ID"));
            cs.setCourseName(row.getString("C_NAME"));
            cs.setStudentId(row.getLong("S_ID"));
            cs.setStudentName(row.getString("S_NAME"));
            csList.add(cs);
        }

        return csList;
    }

    /**
     * 学生还未经选择的课程
     * @param sId
     * @return
     */
    public List<Course> list(long sId) {
        String sql = " SELECT C.ID AS C_ID, C.NAME AS C_NAME " +
                " FROM COURSE C " +
                " WHERE NOT EXISTS (" +
                "   SELECT * " +
                "   FROM COURSE C1 " +
                "     INNER JOIN COURSE_STUDENT CS " +
                "     ON C1.ID=CS.COURSE_ID" +
                "     WHERE C.ID=C1.ID " +
                "     AND CS.STUDENT_ID = " + sId + ")";
        
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
