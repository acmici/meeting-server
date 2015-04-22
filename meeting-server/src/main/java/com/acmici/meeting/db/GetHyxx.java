package com.acmici.meeting.db;
import java.sql.*;
public class GetHyxx {

	
		//private String dbURL="jdbc:microsoft:sqlserver://202.115.26.181:1433";	// ���ݿ��ʶ��
		//private String user="devon";	// ���ݿ��û�
		//private String password="book";	// ���ݿ��û�����
		
		
		private static String drivername="oracle.jdbc.driver.OracleDriver";                        //mysql���ݿ�����
		private static String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";                     //���ӵ����ݿ��ַ
		private static String username="system";                                           //�������ݿ��û���
		private static String password="123";
		
		
		public GetHyxx(){
			try	{
				Class.forName(drivername);  
				Connection con=DriverManager.getConnection(url,username,password); //��ȡ����
				String sqlStr="select * from T_hyxx where 1=1"; //SQL��ѯ���
				Statement st=con.createStatement(); //��ȡStatement����
				ResultSet rs=st.executeQuery(sqlStr); //ִ�в�ѯ
				String yhry,hyzt,hysj,hyjly; //��ѯ���
				int zj;
				while (rs.next()){ //����ResultSet
					yhry=rs.getString("Yhry"); //ȡ�ò�ѯ���
					hyzt=rs.getString("Hyzt");
					hysj=rs.getString("Hysj");
					hyjly=rs.getString("Hyjly");
					zj=rs.getInt("Zj");
					System.out.println("yhry:"+yhry); //��ʾ��ѯ���
				}			
				con.close(); //�ر�����
			}
			catch(Exception ex){
				ex.printStackTrace(); //���������Ϣ
			}
		}
		
		public static void main(String[] args){
			new GetHyxx();
		}
	}

