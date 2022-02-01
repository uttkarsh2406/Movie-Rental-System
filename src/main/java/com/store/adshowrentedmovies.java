package com.store;
import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dbconnection;

import java.sql.*;
/**
 * Servlet implementation class adshowrentedmovies
 */
@WebServlet("/adshowrentedmovies")
public class adshowrentedmovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adshowrentedmovies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setHeader("Cache-Control", "no-cache,no-store,,must-revalidate");
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
		ResultSet rs=null;
		try {
			Connection con=Dbconnection.get_connection();
		    PreparedStatement ps= con.prepareStatement("Select *from person Where isadmin=?");
		    ps.setBoolean(1, false);
		    rs=ps.executeQuery();
		    
		    boolean flag=false;
			out.print("<html>");
			
			out.print("<style>table, th,td{ border: 1px solid black; }"
					+ "       #c2{\r\n"
					+ "      float:right;\r\n"
					+ "      border: 5px;\r\n"
					+ "      border-style: groove;\r\n"
					+ "      border-radius: 6px;\r\n"
					+ "      text-decoration: none;\r\n"
					+ "      color: black;\r\n"
					+ "    }</style>");
			out.print("<body>");
			out.print("<table>");
			out.print("<a id='c2' href=\"logout\">Logout</a>");
		    while(rs.next()) {
			    if(flag==false) {
			    	
			    	out.print("<tr><th>User ID</th><th>User Name</th><th>User Username</th><th>User Contact Number</th><th>Books on rent</th></tr>");
			        flag=true;
			    }
			    out.print("<tr><td>"+rs.getInt("pid")+"</td><td>"+rs.getString("pname")+"</td><td>"+rs.getString("puname")+"</td><td>"+rs.getString("pmob")+"</td><td><a href='rentedmovies?id="+rs.getInt("pid")+"'<button>Click here</button></a></td></tr>");
			    
		   }
		    out.print("<table>");
		    out.print("</body>");
		    out.print("</html>");	
		    
		    
		}
		catch(Exception e) {
			System.out.print(e);
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
