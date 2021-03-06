package com.hotel.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotel.bean.CustomerBean;
import com.hotel.bean.RoomBean;
import com.hotel.dao.CustomerManage;
import com.hotel.dao.RoomManage;
@WebServlet(name="individualFinish",urlPatterns={"/individualFinish"})
public class individualFinish extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1.添加customer数据库，更改room数据库的IsEmp=0
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("name");
	//	String Cname = new String(name.getBytes(), "utf-8");
	//	System.out.println(name);
		String sex = req.getParameter("sex");
		String IDCard = req.getParameter("IDCard");
		int roomnum = Integer.parseInt(req.getParameter("roomnum"));
		String phone = req.getParameter("phone");
		int OrderNum = 0;
		ArrayList list = (ArrayList)CustomerManage.selectByRoomId(roomnum);
		if(list.size()==0){
			OrderNum = CreateRan.CreateOrderNum();
			System.out.println(OrderNum);
		}else{
			CustomerBean customer = (CustomerBean) list.get(0);
			OrderNum = customer.getOrderNum();
		}
		String StartTime = CreateRan.CreateDate();
		RoomBean room = (RoomBean)RoomManage.selectByRoomId(roomnum);
		int IsEmp = room.getIsEmp();
		if(IsEmp>0){
			//插入住户信息
			CustomerManage.insert(OrderNum, name, sex, IDCard, roomnum, phone, 0, StartTime);
			//设置IsEmp=0
			RoomManage.updateIsEmp(roomnum,0);
		}else{
			resp.sendRedirect("/HotelManagement/errors/error.html");
			return ;
		}
		
		
		int dep=0;
		ArrayList list1 = (ArrayList)CustomerManage.selectRoom(OrderNum);
		Iterator it = list1.iterator();
		while(it.hasNext()){
			int Room =(Integer) it.next();
			
			
			dep += RoomManage.selectByRoomId(Room).getDeposit();
		}
		out.print("<h3>您一共需要缴纳"+dep+"元押金！！</h3>");
	
		
	
	}

}
