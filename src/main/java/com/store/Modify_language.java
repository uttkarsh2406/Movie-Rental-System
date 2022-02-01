package com.store;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import main_class.Movie;

/**
 * Servlet implementation class Modify_language
 */
@WebServlet("/Modify_language")
public class Modify_language extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify_language() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Cache-Control", "no-cache,no-store,,must-revalidate");
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
        
		String lang=request.getParameter("ml");
		Movie node=new Movie();
		node.setMovie_language(lang);
		Cookie[]arr=request.getCookies();
		String s="";
		for(Cookie x:arr) {
			if(x.getName().equals("mid")) {
				s=x.getValue();
			}
		}
		System.out.println(s);
		node.setId(Integer.parseInt(s));
		System.out.println("sldfjslflskd");
		node.modify_det(node, 4);
		request.getRequestDispatcher("adminhome.html").include(request, response);
	
	}

}
