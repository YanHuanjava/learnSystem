package dao.testSysDao;

import dao.BaseDao;
import pojo.Page;
import pojo.testSys.PaperInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaperInfoDao extends BaseDao {
    //查找
    public List<PaperInfo> findPaper(){
        String sql="select * from t_paper_info";
        ResultSet set=select(sql);
        List<PaperInfo> list=new ArrayList<>();
        try {
            while (set.next()){
                PaperInfo p=new PaperInfo();
                p.setPaperid(set.getInt("paper_id"));
                p.setPaperName(set.getString("paper_name"));
                p.setUserName(set.getString("user_Name"));
                p.setStartTime(set.getDate("start_time"));
                p.setEndTime(set.getDate("end_time"));
                p.setPaperTime(set.getInt("paper_time"));
                p.setPaperStart(set.getDate("paper_start"));
                p.setScore(set.getInt("score"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<PaperInfo> findPaper(int paperid){
        String sql="select * from t_paper_info where paper_id=?";
        ResultSet set=select(sql,paperid);
        List<PaperInfo> list=new ArrayList<>();
        try {
            while (set.next()){
                PaperInfo p=new PaperInfo();
                p.setPaperid(set.getInt("paper_id"));
                p.setPaperName(set.getString("paper_name"));
                p.setUserName(set.getString("user_Name"));
                p.setStartTime(set.getDate("start_time"));
                p.setEndTime(set.getDate("end_time"));
                p.setPaperTime(set.getInt("paper_time"));
                p.setPaperStart(set.getDate("paper_start"));
                p.setScore(set.getInt("score"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public PaperInfo findPaperInfo(int paperid){
        String sql="select * from t_paper_info where paper_id=?";
        ResultSet set=select(sql,paperid);
        try {
            while (set.next()){
                PaperInfo p=new PaperInfo();
                p.setPaperid(set.getInt("paper_id"));
                p.setPaperName(set.getString("paper_name"));
                p.setUserName(set.getString("user_Name"));
                p.setStartTime(set.getDate("start_time"));
                p.setEndTime(set.getDate("end_time"));
                p.setPaperTime(set.getInt("paper_time"));
                p.setPaperStart(set.getDate("paper_start"));
                p.setScore(set.getInt("score"));
               return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //增加
    public int addPaper(PaperInfo paperInfo){
        String sql="insert into t_paper_info values(seq_t_paper_info.nextval,?,?,?,?,?,?,systimestamp)";
        int row=update(sql,paperInfo.getPaperName(),paperInfo.getUserName(),paperInfo.getStartTime(),paperInfo.getEndTime(),paperInfo.getPaperTime(),paperInfo.getScore());
        return row;
    }

    //删除
    public int delPaper(int id){
        String sql="delete from t_paper_info where paper_id=?";
        int row=update(sql,id);
        return row;
    }
    //修改
    public int update(PaperInfo paperInfo){
        String sql="update t_paper_info set paper_name=?,user_name=?,start_time=?,end_time=?,paper_time=? where paper_id=?";
        return update(sql,paperInfo.getPaperName(),paperInfo.getUserName(),paperInfo.getStartTime(),paperInfo.getEndTime(),paperInfo.getPaperTime(),paperInfo.getPaperid());
    }
    public Page<PaperInfo> findPaperByPage(Page<PaperInfo> page){
            if(page!=null){
                StringBuffer sb=new StringBuffer();
                sb.append(Page.PAGE_START);
                sb.append("select * from t_paper_info where 1=1");
                if (page.getCondition() != null) {
                    if (page.getCondition().getPaperName() != null) {
                        sb.append("and paper_name like '%" + page.getCondition().getPaperName() + "%'");
                    }
                }
                sb.append(Page.PAGE_END);
                ResultSet set=select(sb.toString(),page.getEnd(),page.getStart());
                List<PaperInfo> list=new ArrayList<>();
                try {
                    while (set.next()){
                        PaperInfo p=new PaperInfo();
                        p.setPaperid(set.getInt("paper_id"));
                        p.setPaperName(set.getString("paper_name"));
                        p.setUserName(set.getString("user_Name"));
                        p.setStartTime(set.getDate("start_time"));
                        p.setEndTime(set.getDate("end_time"));
                        p.setPaperTime(set.getInt("paper_time"));
                        p.setPaperStart(set.getDate("paper_start"));
                        p.setScore(set.getInt("score"));
                        list.add(p);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                page.setList(list);
                closeAll();
                sb = new StringBuffer("select count(*) from t_paper_info where 1=1");
                if (page.getCondition() != null) {
                    if (page.getCondition().getPaperName() != null) {
                        sb.append("and paper_name like '%" + page.getCondition().getPaperName() + "%'");
                    }
                }
                set = select(sb.toString());
                try {
                    while (set.next()) {
                        page.setTotal(set.getLong(1));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                closeAll();
            }
        return page;
    }
    //根据标题查询id
    public int findPaperId(String paperName){
        String sql="select paper_id from t_Paper_Info where paper_name=?";
        ResultSet set=select(sql,paperName);
        int paperid=0;
        try {
            while (set.next()){
                paperid=set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paperid;
    }
}
