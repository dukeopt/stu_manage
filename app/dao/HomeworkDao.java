package dao;

import io.ebean.Ebean;
import io.ebean.Finder;
import io.ebean.SqlRow;
import models.Homework;
import models.construct.HCT;
import models.construct.HS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeworkDao {

    public static final Finder<Long, Homework> find = new Finder<>(Homework.class);

    /**
     * 根据教师id选出所有作业
     * @param tId
     * @return list
     */
    public List<HCT> list(long tId) {
        String sql = " SELECT H.ID AS H_ID, H.NAME AS H_NAME, H.PATH AS H_PATH, H.DATE AS DATE," +
                "        C.ID AS C_ID, C.NAME AS C_NAME, T.ID AS T_ID, T.NAME AS T_NAME" +
                " FROM HOMEWORK H " +
                " INNER JOIN COURSE_TEACHER CT ON H.COURSE_TEACHER_ID = CT.ID" +
                " INNER JOIN TEACHER T ON CT.TEACHER_ID = T.ID " +
                " INNER JOIN COURSE C ON CT.COURSE_ID = C.ID " +
                " WHERE T.ID = " + tId +
                " ORDER BY C.ID";

        return getList(sql);
    }

    /**
     * 根据教师id选出所有作业
     * @param tId
     * @return map key:课程id value 作业list
     */
    public Map<Long, List<HCT>> map(long tId) {
        String sql = " SELECT H.ID AS H_ID, H.NAME AS H_NAME, H.PATH AS H_PATH, H.DATE AS DATE," +
                "        C.ID AS C_ID, C.NAME AS C_NAME, T.ID AS T_ID, T.NAME AS T_NAME" +
                " FROM HOMEWORK H " +
                " INNER JOIN COURSE_TEACHER CT ON H.COURSE_TEACHER_ID = CT.ID" +
                " LEFT JOIN TEACHER T ON CT.TEACHER_ID = T.ID " +
                " LEFT JOIN COURSE C ON CT.COURSE_ID = C.ID ";

        if (tId != -1) {
            sql += " WHERE T.ID = " + tId;
        }

        sql += " ORDER BY C.ID";

        List<SqlRow> rows = Ebean.createSqlQuery(sql).findList();
        Map<Long, List<HCT>> hctMap = new HashMap<>();

        for (SqlRow row: rows) {
            Long cId = row.getLong("C_ID");
            HCT hct = new HCT();
            hct.setId(row.getLong("H_ID"));
            hct.setName(row.getString("H_NAME"));
            hct.setPath(row.getString("H_PATH"));
            hct.setDate(row.getString("DATE"));
            hct.setCourseId(cId);
            hct.setCourseName(row.getString("C_NAME"));
            hct.setTeacherId(row.getLong("T_ID"));
            hct.setTeacherName(row.getString("T_NAME"));

            if (hctMap.containsKey(row.getLong("C_ID"))) {
                hctMap.get(cId).add(hct);
            } else {
                List<HCT> hcts = new ArrayList<>();
                hcts.add(hct);
                hctMap.put(cId, hcts);
            }
        }
        return hctMap;
    }

    /**
     * 学生 根据学生id查询出所有学生未提交的作业
     * @param sId
     * @return
     */
    public List<HCT> noScorelist(long sId, long cId, long hId) {

        String sql = " SELECT H.ID AS H_ID, H.NAME AS H_NAME, H.PATH AS H_PATH, H.DATE AS DATE," +
                "        C.ID AS C_ID, C.NAME AS C_NAME, T.ID AS T_ID, T.NAME AS T_NAME" +
                " FROM HOMEWORK H " +
                " INNER JOIN COURSE_TEACHER CT ON H.COURSE_TEACHER_ID = CT.ID" +
                " INNER JOIN TEACHER T ON CT.TEACHER_ID = T.ID " +
                " INNER JOIN COURSE C ON CT.COURSE_ID = C.ID " +
                " INNER JOIN COURSE_STUDENT CS ON C.ID = CS.COURSE_ID" +
                " INNER JOIN STUDENT ST ON CS.STUDENT_ID = ST.ID " +
                " WHERE H.ID NOT IN (" +
                "   SELECT S.HOMEWORK_ID " +
                "   FROM SCORE S )" +
                "  AND ST.ID = " + sId;

        if (cId != -1) {
            sql +=  " AND C.ID = " + cId;
        }

        if (hId != -1) {
            sql +=  " AND H.ID = " + hId;
        }

        return getList(sql);
    }

    public List<HCT> getList(String sql){
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
