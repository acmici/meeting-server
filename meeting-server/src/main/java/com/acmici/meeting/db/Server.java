package com.acmici.meeting.db;
import com.acmici.meeting.db.PeopleVo;
import com.acmici.meeting.db.MeetingVo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Server {
    
private static String drivername="oracle.jdbc.driver.OracleDriver"; 
private static String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
private static String username="meeting";
private static String password="123";

/***
 * 获取所有会议信息
 * @return 
 */
    public List GetAllMeeting(){
         List<MeetingVo> list = new ArrayList<MeetingVo>();//新建List
        try{
            Class.forName(drivername);//连接驱动
            Connection con=DriverManager.getConnection(url,username,password); //连接数据库
            String sqlStr="select * from meeting where 1=1";//查询数据的SQL语句
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sqlStr);//获得结果集
            while (rs.next()){
                MeetingVo meeting = new MeetingVo();
                    meeting.setId(rs.getInt("Id"));//将结果集中的主键放入meeting中
                    meeting.setTheme(rs.getString("Theme"));//将结果集中的会议主题放入meeting中
                    meeting.setTime(rs.getString("Time"));//将结果集中的会议时间放入meeting中
                    meeting.setRecorder(rs.getString("Recorder"));//将结果集中的会议记录员放入meeting中
                    meeting.setNumbers(rs.getString("Numbers"));//将结果集中的与会人员放入meeting中
                    list.add(meeting);
            }
            con.close();//关闭连接
    }
    catch(Exception ex){
            ex.printStackTrace();
    }
    return list;
}
    
/***
 * 通过时间查询会议信息
 * @param startTime
 * @param endTime
 * @return 
 */
    public List GetMeetingByTime(String startTime,String endTime){
         List<MeetingVo> list = new ArrayList<MeetingVo>();
        try{
            Class.forName(drivername);//连接驱动
            Connection con=DriverManager.getConnection(url,username,password); //连接数据库
            String sqlStr="select * from meeting where time >= to_date('"+startTime+"','yyyy-MM-dd') and time <=  to_date('"+endTime+"','yyyy-MM-dd')";//按时间查询的sql语句
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sqlStr);//获得结果集
            while (rs.next()){
                MeetingVo meeting = new MeetingVo();
                    meeting.setId(rs.getInt("Id"));//将结果集中的主键放入meeting中
                    meeting.setTheme(rs.getString("Theme"));//将结果集中的会议主题放入meeting中
                    meeting.setTime(rs.getString("Time"));//将结果集中的会议时间放入meeting中
                    meeting.setRecorder(rs.getString("Recorder"));//将结果集中的会议记录员放入meeting中
                    meeting.setNumbers(rs.getString("Numbers"));//将结果集中的与会人员放入meeting中
                    list.add(meeting);
            }
            con.close();//关闭连接
    }
    catch(Exception ex){
            ex.printStackTrace();
    }
    return list;
}    
 /***
 * 通过会议主题，会议记录员，与会人员查询会议信息
 * @param startTime
 * @param endTime
 * @return 
 */
    public List GetMeetingByInformation(String information){
         List<MeetingVo> list = new ArrayList<MeetingVo>();
        try{
            Class.forName(drivername);//连接驱动
            Connection con=DriverManager.getConnection(url,username,password); //连接数据库
            String sqlStr="select * from meeting where theme like '%"+information+"%' or recorder = '"+information+"' or numbers like '%"+information+"%'";//通过会议主题，会议记录员，与会人员查询的sql语句
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sqlStr);//获得结果集
            while (rs.next()){
                MeetingVo meeting = new MeetingVo();
                    meeting.setId(rs.getInt("Id"));//将结果集中的主键放入meeting中
                    meeting.setTheme(rs.getString("Theme"));//将结果集中的会议主题放入meeting中
                    meeting.setTime(rs.getString("Time"));//将结果集中的会议时间放入meeting中
                    meeting.setRecorder(rs.getString("Recorder"));//将结果集中的会议记录员放入meeting中
                    meeting.setNumbers(rs.getString("Numbers"));//将结果集中的与会人员放入meeting中
                    list.add(meeting);
            }
            con.close();
    }
    catch(Exception ex){
            ex.printStackTrace();
    }
    return list;
}  
    
    
    
    
/***
 * 修改会议信息
 * @param id
 * @param Theme
 * @param Time
 * @param recorder
 * @param numbers
 * @return 
 */
    public void ChangeMeetingById(Integer id,String theme,String time,String recorder,String numbers){
        try{
            Class.forName(drivername);//连接驱动
            Connection con=DriverManager.getConnection(url,username,password); //连接数据库
            String sqlStr="update meeting set theme = '"+theme+"',time = to_date('"+time+"','yyyy-MM-dd'),recorder = '"+recorder+"',numbers = '"+numbers+"'  where id = '"+id+"'";//修改会议信息的SQL语句
            System.out.println(sqlStr);
            Statement st=con.createStatement();
            st.executeUpdate(sqlStr);
            con.close();//关闭连接
    }
    catch(Exception ex){
            ex.printStackTrace();
    }
}
    
    /***
 * 保存会议信息
 * @param id
 * @param Theme
 * @param Time
 * @param recorder
 * @param numbers
 * @return 
 */
    public void SaveMeeting(Integer id,String theme,String time,String recorder,String numbers){
        
        try{
            Class.forName(drivername);//连接驱动
            Connection con=DriverManager.getConnection(url,username,password); //连接数据库
            String sqlStr="insert into meeting(id,theme,time,recorder,numbers) values("+id+",'"+theme+"',to_date('"+time+"','yyyy-MM-dd'),'"+recorder+"','"+numbers+"')";//保存会议的SQL语句
            Statement st=con.createStatement();
            st.executeUpdate(sqlStr);
            con.close();//关闭连接
    }
    catch(Exception ex){
            ex.printStackTrace();
    }
}

/***
 * 获取所有与会人员信息
 * @return 
 */
    public List GetAllPeople(){
         List<PeopleVo> list = new ArrayList<PeopleVo>();
        try{
            Class.forName(drivername);//连接驱动
            Connection con=DriverManager.getConnection(url,username,password); //连接数据库
            String sqlStr="select * from people where 1=1";//查询与会人员数据的SQL语句
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sqlStr);//获的结果集
            while (rs.next()){
                PeopleVo people = new PeopleVo();
                    people.setId(rs.getInt("Id"));
                    people.setNumbers(rs.getString("Numbers"));
                    list.add(people);
            }
            con.close();//关闭连接
    }
    catch(Exception ex){
            ex.printStackTrace();
    }
    return list;
}    

}

