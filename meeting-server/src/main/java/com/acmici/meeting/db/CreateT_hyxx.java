package com.acmici.meeting.db;
import java.sql.*;
public class CreateT_hyxx {
	private static String drivername="oracle.jdbc.driver.OracleDriver";
	private static String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private static String username="meeting";
	private static String password="123";
	public CreateT_hyxx(){
		try	{
			Class.forName(drivername);//连接驱动
			Connection con=DriverManager.getConnection(url,username,password);//连接数据库
			Statement st=con.createStatement();
			
			String sqlStr="create table meeting(Id int,Theme varchar(100),Time date,Recorder varchar(10),Members varchar(200))";//创建会议信息表
                       //String sqlStr="create table people(Id int not null,Numbers varchar(20))";//创建与会人员表
			st.executeUpdate(sqlStr);
			
			//��������
			//sqlStr="insert into products(Name,Price,Provider,Count) values(\'���\',2.5,\'�Ϻ�\',20)";
			//st.executeUpdate(sqlStr); //ִ�в���
			//sqlStr="insert into products(Name,Price,Provider,Count) values(\'����\',5.5,\'����\',13)";
			//st.executeUpdate(sqlStr);			
			
			//��ʾ����
			/*sqlStr="select * from T_yhry";
			ResultSet rs=st.executeQuery(sqlStr);
			String yhry; 
			int zj;
			while (rs.next()){
				yhry=rs.getString("Yhry");
				zj=rs.getInt("Zj");
				System.out.println("yhry:"+yhry+"  zj:"+zj);
			}*/
			
			
			//System.out.println("ɾ����products"); 
			//sqlStr="drop table products";
			//st.executeUpdate(sqlStr);

			con.close();
		}
		catch (Exception ex)	{
			ex.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		new CreateT_hyxx();
	}
}
