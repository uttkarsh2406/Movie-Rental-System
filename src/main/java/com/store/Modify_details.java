package com.store;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Modify_details
 */
@WebServlet("/Modify_details")
public class Modify_details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify_details() {
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
			
		
		
			request.getRequestDispatcher("modifydetails.html").include(request, response);
	}
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mid=request.getParameter("mid");
		System.out.println(mid+"   1345");
//		if(request.getParameter("mname")!=null) {
          Cookie co=new Cookie("mid",mid+"");
//        int mt=(int) ses.getAttribute("mid");
//        System.out.print(mt);
//        System.out.print("HEllo NOoooooooooooo");
          response.addCookie(co);
         response.sendRedirect("Mod_det");
//		}
//		if(request.getParameter("mq")!=null) {
//		int quantity=Integer.parseInt(request.getParameter("mq"));
//		}
//		if(request.getParameter("mp")!=null) {
//		Float mprice=Float.parseFloat(request.getParameter("mp"));
//		}
//		if(request.getParameter("ml")!=null) {
//		String lang=request.getParameter("ml");
//		}
//		if(request.getParameter("mc")!=null) {
//		String chara=request.getParameter("mc");
//		}
		
		
	}

}
