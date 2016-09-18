package com.hotel.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
public class Connect{
	
		@SuppressWarnings("finally")
		public static Connection getCon(){
			Connection con=null;
			//����������
			String driver = "com.mysql.jdbc.Driver";
			//URLָ��Ҫ���ʵ����ݿ���test
			String url="jdbc:mysql://localhost:3306/hotel_manage";
			//Mysql����ʱ���û���
			String user = "root";
			//Mysql����ʱ������
			String passwd="liujianjy";
			try{
				Class.forName(driver);
				con = DriverManager.getConnection(url, user, passwd);
			}catch(ClassNotFoundException  e){
				System.out.println("Sorry,can`t find the Driver!");
				e.printStackTrace();
			}catch(Exception e){
				System.out.println("��������");
				e.printStackTrace();
			}finally{
				return con;
			}
		}
		
		public static void close2(Connection con , Statement stat){
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void close3(ResultSet rs,Connection con , Statement stat){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void close3p(ResultSet rs,Connection con , PreparedStatement pstat){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
}
		
			

