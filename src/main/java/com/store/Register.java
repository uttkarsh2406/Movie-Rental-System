package com.store;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dbconnection;

import main_class.*;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("Registration.html").include(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String uname=request.getParameter("uname");
		String umob=request.getParameter("mobno");
		String pas1=request.getParameter("psw1");
		String pas2=request.getParameter("psw2");
		user node=new user();
		node.setName(name);
		node.setIsadmin(false);
		node.setUsername(uname);
		
		node.setContact_no(umob);
		node.setPassword(pas1);
		System.out.print(name+" "+uname+" "+umob+" "+pas1+" "+pas2);
		int s=0;
		if(pas1.equals(pas2)) {
            node.signup(node);
		    request.getRequestDispatcher("index.html").include(request, response);
		}
		else {
		    out.println("<h1>!! Please Enter Password And Confirm Password Same !!</h1>");
			request.getRequestDispatcher("Registration.html").include(request, response);
		}
		
		
	}

}
