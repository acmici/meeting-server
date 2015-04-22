package com.acmici.meeting.db;
import java.sql.*;
public class TableDemo {
	private static String drivername="oracle.jdbc.driver.OracleDriver";                        //mysql���ݿ�����
	private static String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";                     //���ӵ����ݿ��ַ
	private static String username="system";                                           //�������ݿ��û���
	private static String password="123";
	public TableDemo(){
		try	{
			Class.forName(drivername);	//װ�����ݿ�����
			Connection con=DriverManager.getConnection(url,username,password); //�õ�����
			Statement st=con.createStatement();
			
			//�½���
			System.out.println("�½���T_yhry");  //�����Ϣ������̨
			String sqlStr="create table T_yhry(Yhry varchar(20),Zj int not null)"; //�½����SQL���
			st.executeUpdate(sqlStr); //ִ��SQL���,�½���
			
			//��������
			//sqlStr="insert into products(Name,Price,Provider,Count) values(\'���\',2.5,\'�Ϻ�\',20)"; //��������SQL���
			//st.executeUpdate(sqlStr); //ִ�в���
			//sqlStr="insert into products(Name,Price,Provider,Count) values(\'����\',5.5,\'����\',13)";
			//st.executeUpdate(sqlStr);			
			
			//��ʾ����
			sqlStr="select * from T_yhry"; //��ѯ����SQL���
			ResultSet rs=st.executeQuery(sqlStr); //��ȡ�����
			String yhry; 
			int zj;
			while (rs.next()){
				yhry=rs.getString("Yhry"); //ȡ�ò�ѯ���
				zj=rs.getInt("Zj");
				System.out.println("yhry:"+yhry+"  zj:"+zj); //��ʾ��ѯ���
			}
			
			//ɾ����
			//System.out.println("ɾ����products"); 
			//sqlStr="drop table products"; //ɾ����SQL���
			//st.executeUpdate(sqlStr); //ִ��ɾ��			

			con.close(); //�ر�����			
		}
		catch (Exception ex)	{
			ex.printStackTrace();  //���������Ϣ
		}
	}
	
	public static void main(String args[]){
		new TableDemo();
	}
}
