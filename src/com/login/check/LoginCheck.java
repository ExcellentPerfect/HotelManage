package com.login.check;
import com.hotel.dao.*;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="LoginCheck",urlPatterns={"/LoginCheck"})
public class LoginCheck extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//1.��ȡhtmlҳ���е�username��password
		String username = req.getParameter("Name");
		String password = req.getParameter("password");
//		System.out.println(username);
//		System.out.println(password);
		//2.��ȡ���ݿ��е�username��password
		//����Connection����
		Connection con =  Connect.getCon();
		PreparedStatement pstat =null;
		ResultSet result =null;
		try{
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			
				//����
//				pstat = con.prepareStatement("create table user ("
//						+ "id int not null primary key auto_increment,"
//						+ "username varchar(20) not null,"
//						+ "password varchar(20) not null)");
//				pstat.execute();
				
				//�����¼
//				pstat = con.prepareStatement("insert into user values(?,?,?)");
//				pstat.setInt(1, 1);
//				pstat.setString(2, "david");
//				pstat.setString(3,"liujianjy");
//				pstat.executeUpdate();
				
				//��ȡ��¼
			pstat = con.prepareStatement("select count(*) from Manager where Name=? and Password=?");
			pstat.setString(1, username);
			pstat.setString(2, password);
			result = pstat.executeQuery();
				
			//3.�Աȣ����ؽ��
			if(result.next()){
				int i = result.getInt(1);
				if(i>0){
//					out.print("hello  ,"+ username);
					resp.sendRedirect("/HotelManagement/Manage.html");
				}else{
					out.print("sorry  ,"+username+"! Your name or password is incorrect!");
				}
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("������ݴ��󣡣���");
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			System.out.println("���ݿ�ɹ���ȡ����");
			Connect.close3p(result,con, pstat);
		}
	
	}

}
