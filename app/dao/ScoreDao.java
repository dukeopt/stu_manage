package dao;

import io.ebean.Ebean;
import io.ebean.Finder;
import io.ebean.SqlRow;
import models.Score;
import models.construct.HS;

import java.util.ArrayList;
import java.util.List;

public class ScoreDao {

    public static final Finder<Long, Score> find = new Finder<>(Score.class);

    /**
     * 教师 根据作业id查询出所有学生的成绩
     * @param tId
     * @param cId
     * @param hId
     * @return
     */
    public List<HS> list(long tId, long cId, long hId) {

        String sql = createSql(tId, cId, hId, true);
        return getList(sql);
    }

    /**
     * 教师 根据作业id查询出所有学生的信息(成绩== null)
     * @param tId
     * @param cId
     * @param hId
     * @return
     */
    public List<HS> noScorelist(long tId, long cId, long hId) {

        String sql = createSql(tId, cId, hId, false);
        return getList(sql);
    }

    /**
     * 学生 根据学生id查询出所有成绩的信息
     * @param sId
     * @return
     */
    public List<HS> studengList(long sId, long cId, long hId) {

        String sql = " SELECT SC.ID AS SC_ID, SC.SCORE AS SCORE, STUDENT_HOMEWORK_PATH AS PATH, " +
                "        H.ID AS H_ID, H.NAME AS H_NAME, ST.ID AS ST_ID, ST.NAME AS ST_NAME," +
                "        C.ID AS C_ID, C.NAME AS C_NAME " +
                " FROM SCORE SC " +
                " INNER JOIN HOMEWORK H ON SC.HOMEWORK_ID = H.ID " +
                " INNER JOIN STUDENT ST ON SC.STUDENT_ID = ST.ID " +
                " INNER JOIN COURSE_TEACHER CT ON H.COURSE_TEACHER_ID = CT.ID" +
                " INNER JOIN COURSE C ON CT.COURSE_ID = C.ID" +
                " INNER JOIN TEACHER T ON CT.TEACHER_ID = T.ID " +
                " WHERE ST.ID = " + sId;

        if (cId != -1) {
            sql +=  " AND C.ID = " + cId;
        }

        if (hId != -1) {
            sql +=  " AND H.ID = " + hId;
        }

        return getList(sql);
    }

    private String createSql (long tId, long cId, long hId, boolean hasScore) {
        String sql = " SELECT SC.ID AS SC_ID, SC.SCORE AS SCORE, STUDENT_HOMEWORK_PATH AS PATH, " +
                "        H.ID AS H_ID, H.NAME AS H_NAME, ST.ID AS ST_ID, ST.NAME AS ST_NAME," +
                "        C.ID AS C_ID, C.NAME AS C_NAME " +
                " FROM SCORE SC " +
                " INNER JOIN HOMEWORK H ON SC.HOMEWORK_ID = H.ID " +
                " INNER JOIN STUDENT ST ON SC.STUDENT_ID = ST.ID " +
                " INNER JOIN COURSE_TEACHER CT ON H.COURSE_TEACHER_ID = CT.ID" +
                " INNER JOIN COURSE C ON CT.COURSE_ID = C.ID" +
                " INNER JOIN TEACHER T ON CT.TEACHER_ID = T.ID " +
                " WHERE T.ID = " + tId;

        if (cId != -1) {
            sql +=  " AND C.ID = " + cId;
        }

        if (hId != -1) {
            sql +=  " AND H.ID = " + hId;
        }

        if (!hasScore) {
            sql +=  " AND SC.SCORE IS NULL";
        }
        sql += " ORDER BY SC.SCORE";
        return sql;
    }

    private List<HS> getList(String sql) {
        List<SqlRow> rows = Ebean.createSqlQuery(sql).findList();
        List<HS> homeworkScores = new ArrayList<>();

        for (SqlRow row: rows) {
            HS hs = new HS();
            hs.setId(row.getLong("SC_ID"));
            hs.setScore(row.getDouble("SCORE"));
            hs.setStudentHomeWorkPath(row.getString("PATH"));
            hs.setHomeworkId(row.getLong("H_ID"));
            hs.setHomeworkName(row.getString("H_NAME"));
            hs.setStudentId(row.getLong("ST_ID"));
            hs.setStudentName(row.getString("ST_NAME"));
            hs.setCourseId(row.getLong("C_ID"));
            hs.setCourseName(row.getString("C_NAME"));
            homeworkScores.add(hs);
        }

        return homeworkScores;
    }

    public Score findById(long id) {
        return find.byId(id);
    }
}
