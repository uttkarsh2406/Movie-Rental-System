package com.store;
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dbconnection;

import java.io.*;
/**
 * Servlet implementation class userrentedmov
 */
@WebServlet("/rentedmovies")
public class userrentedmov extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userrentedmov() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		int id=Integer.parseInt(request.getParameter("id"));
	    PrintWriter out=response.getWriter();
	    ResultSet rs=null;
	    try {
	    	Connection con=Dbconnection.get_connection();
	    	PreparedStatement ps=con.prepareStatement("Select movie.mid , movie.mname , rentmovie.bdate ,rentmovie.rdate FROM movie INNER JOIN rentmovie ON movie.mid=rentmovie.mid WHERE uid=?");
	        ps.setInt(1, id);
	        rs=ps.executeQuery();
	        boolean flag=false;
			out.print("<html>");
			
			out.print("<style>table, th,td{ border: 1px solid black; }        #c2{\r\n"
					+ "      float:right;\r\n"
					+ "      border: 5px;\r\n"
					+ "      border-style: groove;\r\n"
					+ "      border-radius: 6px;\r\n"
					+ "      text-decoration: none;\r\n"
					+ "      color: black;\r\n"
					+ "    }</style>");
			out.print("<body>");
			out.print("<table>");
			out.print("   <a id='c2' href=\"logout\">Logout</a>");
		    while(rs.next()) {
			    if(flag==false) {
			    	
			    	out.print("<tr><th>Movie ID</th><th>Movie Name</th><th>Buy Date</th><th>Return Date</th></tr>");
			        flag=true;
			    }
			    out.print("<tr><td>"+rs.getInt("mid")+"</td><td>"+rs.getString("mname")+"</td><td>"+rs.getString("bdate")+"</td><td>"+rs.getString("rdate")+"</td></tr>");
			    
		   }
		    out.print("</table>");
		    out.print("</body>");
		    out.print("</html>");	
		    
		    if(flag==false) {
		    	out.println("<h1>No Rented Movie</h1>");
		    	out.print("<a href='adshowrentedmovies'><button type='button'>CLick here to go to Previous page</button></a>");
//		    	request.getRequestDispatcher("adshowrentedmovies").include(request, response);
		    }
		    
	    }
	    catch(Exception e) {
	    	System.out.print(e);
	    }
	}

	}	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
