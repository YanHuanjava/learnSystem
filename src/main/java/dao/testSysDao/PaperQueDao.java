package dao.testSysDao;

import dao.BaseDao;

import java.sql.ResultSet;

public class PaperQueDao extends BaseDao {
    public int addPaperQue(int paperId,int questionId){
        String sql="insert into t_paper_question values(?,?)";
        int row=update(sql,paperId,questionId);
        return row;
    }
}
