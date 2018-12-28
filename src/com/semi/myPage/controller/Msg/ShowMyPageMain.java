package com.semi.myPage.controller.Msg;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.admin.user.model.vo.Employee;
import com.semi.myPage.model.Msg.service.MsgService;
import com.semi.myPage.model.Msg.vo.Msg;

@WebServlet("/myPageMain")
public class ShowMyPageMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowMyPageMain() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee loginUser = (Employee) request.getSession().getAttribute("loginUser");
		
		int userId = loginUser.getEmpid();
		
		ArrayList<Msg> list = new MsgService().showMyPageMain(userId);
		
		String page = "";
		request.setAttribute("list", list);
			
		page = "views/myPage/mypageMain.jsp";
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}