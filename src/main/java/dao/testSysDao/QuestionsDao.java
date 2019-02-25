package dao.testSysDao;

import dao.BaseDao;
import pojo.testSys.Questions;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDao extends BaseDao{
    public List<Questions> findQuestion(int typeId,int rowbum){
        String sql="select a.question_id from (select * from t_questions where type_id=? order by dbms_random.random) a where rownum<=?";
        ResultSet set=select(sql,typeId,rowbum);
        List<Questions> list=new ArrayList<>();
        try {
            while (set.next()){
                Questions q=new Questions();
                q.setQuestionId(set.getInt("Question_id"));
//                q.setQuestion(set.getString("question"));
//                q.setTypeId(set.getInt("type_id"));
                list.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
