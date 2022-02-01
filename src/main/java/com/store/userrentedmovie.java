package com.store;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import com.database.Dbconnection;
import java.io.*;

import javax.servlet.http.Cookie;
/**
 * Servlet implementation class userrentedmovie
 */
@WebServlet("/usermovies")
public class userrentedmovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		boolean flag1=false;
		Cookie[] arr=request.getCookies();
		for(Cookie x:arr) {
			if(x.getName().equals("uname")) {
				flag1=true;
			}
		}
		if(flag1==false) {
			request.getRequestDispatcher("index.html").include(request, response);
		}
		else {
		PrintWriter out=response.getWriter();
		Cookie cookies[]=request.getCookies();
		String name="";
		for(Cookie x:cookies) {
			if(x.getName().equals("uname")) {
				name=x.getValue();
			}
		}
		System.out.println(name);
		int id=0;
		try {
		     Connection con=Dbconnection.get_connection();
		     PreparedStatement ps=con.prepareStatement("Select *from person where puname=?");
		     ps.setString(1, name);
		     ResultSet s=ps.executeQuery();
		     s.next();
		     id=s.getInt("pid");
			
		}
		catch(Exception e) {
			System.out.print(e);
		}
		ResultSet res=null;
		try {
			Connection con=Dbconnection.get_connection();
			PreparedStatement ps=con.prepareStatement("Select rentmovie.mid,movie.mname,rentmovie.bdate,rentmovie.rdate FROM rentmovie INNER JOIN movie ON rentmovie.mid=movie.mid Where rentmovie.uid=?");
		    ps.setInt(1, id);
		    res=ps.executeQuery();
		}
		catch(Exception e){
		    System.out.println(e);	
		}
		
		try {
			boolean flag=false;
			out.print("<html>");
			
			out.print("<style>table, th,td{ border: 1px solid black; }       #c2{\r\n"
					+ "      float:right;\r\n"
					+ "      border: 5px;\r\n"
					+ "      border-style: groove;\r\n"
					+ "      border-radius: 20px;\r\n"
					+ "      text-decoration: none;\r\n"
					+ "      color: black;\r\n"
					+ "      font-size: 25px;\r\n"
					+ "    }</style>");
			out.print("<body>");
			out.print("<a id='c2' href=\"logout\">Logout</a>");
			out.print("<table>");
		    while(res.next()) {
			    if(flag==false) {
			    	
			    	out.print("<tr><th>Movie ID</th><th>Movie Name</th><th>Buy Date</th><th>Return Date</th></tr>");
			        flag=true;
			    }
			    out.print("<tr><td>"+res.getInt("mid")+"</td><td>"+res.getString("mname")+"</td><td>"+res.getString("bdate")+"</td><td>"+res.getString("rdate")+"</td></tr>");
			    
		   }
		    out.print("<table>");
		    out.print("</body>");
		    out.print("</html>");		
		    }
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
