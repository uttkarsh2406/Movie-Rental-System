package com.store;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dbconnection;

import main_class.person;

/**
 * Servlet implementation class Login
 */
@WebServlet("/home")
public class Login extends HttpServlet {


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String uname=request.getParameter("uname");
		String upass=request.getParameter("psw");

		person node=new person();
		node.setUsername(uname);
		node.setPassword(upass);
		int flag_val=node.login(node);
		if(flag_val==1) {
			Cookie cook=new Cookie("uname",uname);
		    response.addCookie(cook);
			request.getRequestDispatcher("adminhome").include(request, response);
		}
		else if(flag_val==2) {
			Cookie cook=new Cookie("uname",uname);
		    response.addCookie(cook);
			request.getRequestDispatcher("userhome").include(request, response);
		}
		else {
			out.println("<h1>Wrong Credential</h1>");
			request.getRequestDispatcher("index.html").include(request, response);
		}
		
		
		
	}

}
