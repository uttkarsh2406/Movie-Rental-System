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
 * Servlet implementation class buymovie
 */
@WebServlet("/buy")
public class buymovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buymovie() {
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
//		String id=request.getParameter("id");
//		System.out.print(id);
//		Movie node=new Movie();
//		Movie node2=null;
//		node.setId(Integer.parseInt(id));
//		node2=node.showdetails(node);
//		System.out.print(node2.getMovie_name());
//		System.out.print(node2.getMovie_price());
//		System.out.print(node2.getMovie_language());
//		System.out.print(node2.getMovie_characherstics());
//		System.out.print(node2.getMovie_name());
//        request.setAttribute("obj", node2);
//		Cookie cook=new Cookie("id",node2.getId()+"");
//		Cookie cook1=new Cookie("name",node2.getMovie_name()+"");
//		Cookie cook2=new Cookie("price",node2.getMovie_price()+"");
//		Cookie cook3=new Cookie("language",node2.getMovie_language()+"");
//		
//		Cookie cook4=new Cookie("character",node2.getMovie_characherstics()+"");
//		response.addCookie(cook);
//		response.addCookie(cook1);
//		response.addCookie(cook2);
//		response.addCookie(cook3);
//		response.addCookie(cook4);
			String mid=request.getParameter("id");
	    boolean flag1=false;
		Cookie arr1[]=request.getCookies();
		for(Cookie x:arr1) {
			if(x.getName().equals("dp")) {
				flag=true;
				x.setValue(mid);
			}
		}

//		request.setAttribute("id", mid);
        if(flag1==false) {
		Cookie lol=new Cookie("dp",mid);
		lol.setMaxAge(60*60);
		System.out.println(mid);
		response.addCookie(lol);
        }
		request.getRequestDispatcher("buy_movie.jsp").include(request, response);
		}
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
