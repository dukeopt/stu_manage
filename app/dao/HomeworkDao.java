package dao;

import io.ebean.Ebean;
import io.ebean.Finder;
import io.ebean.SqlRow;
import models.Homework;
import models.construct.HCT;

import java.util.ArrayList;
import java.util.List;

public class HomeworkDao {

    public static final Finder<Long, Homework> find = new Finder<>(Homework.class);

    public List<HCT> list(long tId) {
        String sql = " SELECT H.ID AS H_ID, H.NAME AS H_NAME, H.PATH AS H_PATH, H.DATE AS DATE," +
                "        C.ID AS C_ID, C.NAME AS C_NAME, T.ID AS T_ID, T.NAME AS T_NAME" +
                " FROM HOMEWORK H " +
                " INNER JOIN COURSE_TEACHER CT ON H.COURSE_TEACHER_ID = CT.ID" +
                " LEFT JOIN TEACHER T ON CT.TEACHER_ID = T.ID " +
                " LEFT JOIN COURSE C ON CT.COURSE_ID = C.ID " +
                " WHERE T.ID = " + tId +
                " ORDER BY C.ID";

        List<SqlRow> rows = Ebean.createSqlQuery(sql).findList();
        List<HCT> hcts = new ArrayList<>();

        for (SqlRow row: rows) {
            HCT hct = new HCT();
            hct.setId(row.getLong("H_ID"));
            hct.setName(row.getString("H_NAME"));
            hct.setPath(row.getString("H_PATH"));
            hct.setDate(row.getString("DATE"));
            hct.setCourseId(row.getLong("C_ID"));
            hct.setCourseName(row.getString("C_NAME"));
            hct.setTeacherId(row.getLong("T_ID"));
            hct.setTeacherName(row.getString("T_NAME"));
            hcts.add(hct);
        }

        return hcts;
    }

    public Homework findById(long id) {
        return find.byId(id);
    }

    public void delete(long id) {
        find.deleteById(id);
    }

}
