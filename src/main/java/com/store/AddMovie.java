package com.store;

import main_class.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddMovie
 */
@WebServlet("/AddMovie")
public class AddMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		// TODO Auto-generated method stub
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
		     request.getRequestDispatcher("Addmovie.html").include(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		boolean flag=false;
//		Cookie[] arr=request.getCookies();
//		for(Cookie x:arr) {
//			if(x.getName().equals("uname")) {
//				flag=true;
//			}
//		}
//		if(flag==false) {
//			request.getRequestDispatcher("index.html").include(request, response);
//		}
		Movie node=new Movie();
	    String mname=request.getParameter("mname");
	    int mquan=Integer.parseInt(request.getParameter("mq"));
		Float price=Float.parseFloat(request.getParameter("mp"));
		String lang=request.getParameter("ml");
		String mc=request.getParameter("mc");
		node.setMovie_name(mname);
		node.setMovie_quan(mquan);
	    node.setMovie_price(price);
	    node.setMovie_language(lang);
	    node.setMovie_characherstics(mc);
	    node.addmovie(node);
	    request.getRequestDispatcher("adminhome.html").include(request, response);
	}

}
