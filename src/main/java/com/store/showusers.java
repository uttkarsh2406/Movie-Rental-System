package com.store;
import java.sql.*;
import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dbconnection;

/**
 * Servlet implementation class showusers
 */
@WebServlet("/showusers")
public class showusers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showusers() {
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
		PrintWriter out=response.getWriter();
//		out.print("12343243453");
		int mid=Integer.parseInt(request.getParameter("id"));
		ResultSet res=null;
		try {
		    Connection con=Dbconnection.get_connection();
		    PreparedStatement ps=con.prepareStatement("select person.pid, person.pname, rentmovie.bdate , rentmovie.rdate From person INNER JOIN rentmovie ON rentmovie.uid=person.pid Where mid=?");
		    ps.setInt(1, mid);
		    res=ps.executeQuery();
		    boolean flag=false;
			out.print("<html>");
			
			out.print("<style>table, th,td{ border: 1px solid black; }        #c2{\r\n"
					+ "      float:right;\r\n"
					+ "      border: 5px;\r\n"
					+ "      border-style: groove;\r\n"
					+ "      border-radius: 20px;\r\n"
					+ "      text-decoration: none;\r\n"
					+ "      color: black;\r\n"
					+ "    }</style>");
			out.print("<body>");
			out.print("<table>");
			out.print("   <a id='c2' href=\"logout\">Logout</a>");
		    while(res.next()) {
			    if(flag==false) {
			    	
			    	out.print("<tr><th>User ID</th><th>User Name</th><th>Buy Date</th><th>Return Date</th><th>Click Here To Return</th></tr>");
			        flag=true;
			    }
			    out.print("<tr><td>"+res.getInt("pid")+"</td><td>"+res.getString("pname")+"</td><td>"+res.getString("bdate")+"</td><td>"+res.getString("rdate")+"</td><td><a href='return?id="+mid+"'><button>Click Here</button></a></td></tr>");
			    
		   }
		    out.print("</table>");
		    out.print("</body>");
		    out.print("</html>");	
		}
		catch(Exception e) {
			
		}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		PrintWriter out=response.getWriter();
		out.print("123456765456787654");
	}

}
