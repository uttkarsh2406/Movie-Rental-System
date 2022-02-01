package com.store;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main_class.Movie;

/**
 * Servlet implementation class Modify_quantity
 */
@WebServlet("/Modify_quantity")
public class Modify_quantity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify_quantity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		boolean flag=false;
		
		Cookie[] arr=request.getCookies();
		for(Cookie x:arr) {
			if(x.getName().equals("uname")) {
				flag=true;
			}
		}
		if(flag==false) {
			request.getRequestDispatcher("index.html").include(request, response);
		}
		else {
		request.getRequestDispatcher("modmovie.html").include(request, response);
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String quan=request.getParameter("mq");
		Movie node=new Movie();
		node.setMovie_quan(Integer.parseInt(quan));
		javax.servlet.http.Cookie[]arr=request.getCookies();
		String s="";
		for(javax.servlet.http.Cookie x:arr) {
			if(x.getName().equals("mid")) {
				s=x.getValue();
			}
		}
		System.out.println(s);
		node.setId(Integer.parseInt(s));
		node.modify_det(node, 2);
		response.sendRedirect("adminhome.html");
	}

}
