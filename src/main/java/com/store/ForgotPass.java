package com.store;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Dbconnection;

/**
 * Servlet implementation class ForgotPass
 */
@WebServlet("/ForgotPass")
public class ForgotPass extends HttpServlet {


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		response.setHeader("Cache-Control", "no-cache,no-store,,must-revalidate");
		
		request.getRequestDispatcher("forgotpass.html").include(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String uname=request.getParameter("uname");
		String mob=request.getParameter("mobno");
		String np1=request.getParameter("np1");
		String np2=request.getParameter("np2");
		
        Boolean flag=false;
        if(np1.equals(np2)) {
        	
        
		try {
        	Connection con=Dbconnection.get_connection();
        	PreparedStatement ps=con.prepareStatement("Select *from person Where pname=? and puname=? and pmob=? ");
        	ps.setString(1, name);
        	ps.setString(2, uname);
        	ps.setString(3, mob);
        	ResultSet rs=ps.executeQuery();
        	if(rs.next()) {
        		PreparedStatement nps=con.prepareStatement("update person SET ppass=? Where pname=? and puname=? and pmob=? ");
        		nps.setString(1, np1);
        		nps.setString(2, name);
            	nps.setString(3, uname);
            	nps.setString(4, mob);
            	int rsq=nps.executeUpdate();
            	flag=true;
        	}
        	else {
    		    out.println("<h1>!! Wrong Details !!</h1>");
    		    request.getRequestDispatcher("forgotpass.html").include(request, response);
        	}
        	
        }
        catch(Exception e) {
             System.out.print("Error while Updating Pass");
             System.out.print(e);
        }
		if(flag==true) {
			request.getRequestDispatcher("index.html").include(request, response);
		}
        }
        else {
        	out.println("<h1>!! Please Enter Password and confirm Password Same !!</h1>");
        	request.getRequestDispatcher("forgotpass.html").include(request, response);
        }
	}

}
