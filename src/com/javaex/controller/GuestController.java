package com.javaex.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestBookVo;

import javax.servlet.RequestDispatcher;

@WebServlet("/GuestController")
public class GuestController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		//post로 해보려함.
		//처음 직면한 문제는 GuestController로 홈페이지에 들어가면
		//doGet 메소드가 불려져서 doPost로 가지 않음. 그래서 위의 함수로 doPost로 가게함
		//그리고 action == null이면 action에 addList의 값을 넣어서 addList가 뜨게 해야함
		
		//아니면 doPost를 지우고 섞어  쓰는 방법도 있다.
		/*
		//이것저것 코드를 더 넣어야 함.
		response.setContentType("text/html;charset=utf-8");
		
		if(action.equals("addList")) {
			RequestDispatcher rd = request.getRequestDispatcher("./addList.jsp");
			rd.forward(request, response);
		}
		 */
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get방식으로 하면 비번의 노출되니까 Post방식을 사용해봄
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action = request.getParameter("action");

		if(action==null) {
			action="addList";
		}
		
		if(action.equals("addList")) {
			GuestBookDao guestBookDao = new GuestBookDao();
			List<GuestBookVo>guestBookList = guestBookDao.getGuestBookList();
			request.setAttribute("guestBookList", guestBookList);
			
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/addList.jsp");
			rd.forward(request, response);
		}else if(action.equals("deleteForm")) {
			
			WebUtil.forward(request, response, "./WEB-INF/deleteForm.jsp");
		}else if(action.equals("delete")) {
			
			String password = request.getParameter("password");
			int deleteNum = Integer.parseInt(request.getParameter("no"));
			GuestBookDao guestBookDao = new GuestBookDao();
			GuestBookVo guestBookVo = guestBookDao.getGuestBook(deleteNum);
			
			if(password.equals(guestBookVo.getPassword())){
				guestBookDao.deleteGuestBook(deleteNum);
				RequestDispatcher rd = request.getRequestDispatcher("./GuestController?action=addList");
				rd.forward(request, response);
			}else{
				PrintWriter out = response.getWriter();
				out.println("<script>"
						+   "alert('아이디 혹은 비밀번호가 잘못 되었습니다.');"
						+   "location.href='./GuestController?action=addList';"
						+   "</script>");
			}
		}else if(action.equals("add")) {
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestBookDao guestBookDao = new GuestBookDao();
			GuestBookVo guestBookVo = new GuestBookVo(name,password,content);
			guestBookDao.guestInsert(guestBookVo);
			
			RequestDispatcher rd = request.getRequestDispatcher("./GuestController?action=addList");
			rd.forward(request, response);
		}
	}

}
