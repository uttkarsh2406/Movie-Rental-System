package com.store;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Cookie;

import com.database.Dbconnection;
import com.mysql.cj.protocol.x.ReusableOutputStream;

/**
 * Servlet implementation class payment
 */
@WebServlet("/payment")
public class payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public payment() {
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
		Cookie[] arr1=request.getCookies();
		for(Cookie x:arr1) {
			if(x.getName().equals("uname")) {
				flag=true;
			}
		}
		if(flag==false) {
			request.getRequestDispatcher("index.html").include(request, response);
		}
		else {
		String buydate=request.getParameter("buydate")+"";
		String returndate=request.getParameter("retdate")+"";
		Cookie bdate=new Cookie("bdate",buydate);
		Cookie rdate=new Cookie("rdate",returndate);
        response.addCookie(bdate);
        response.addCookie(rdate);
		
		String s="";
		int uid=0;
		int mid=0;
		Float mprice=(float)0.0;
		Cookie[] arr=request.getCookies();
		for(Cookie x:arr) {
			if(x.getName().equals("uname")) {
				s=x.getValue();
			}
			if(x.getName().equals("dp")) {
				mid=Integer.parseInt(x.getValue());
			}
			
		} 

		 
        ResultSet rs=null;
		ResultSet rs1=null;
        try {
			Connection con=Dbconnection.get_connection();
			PreparedStatement ps=con.prepareStatement("Select *from person WHERE puname=?");
			PreparedStatement ps1=con.prepareStatement("SELECT *from movie WHERE mid=?");
			ps.setString(1, s);
			ps1.setInt(1, mid);
			rs=ps.executeQuery();
			rs1=ps1.executeQuery();
			rs.next();
			rs1.next();
			uid=rs.getInt("pid");
			mprice=rs1.getFloat("mprice");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	
		Cookie co=new Cookie("iid", uid+"");
		Cookie co1=new Cookie("mp",mprice+"");
	    response.addCookie(co);
	    response.addCookie(co1);
		
		
		request.getRequestDispatcher("buymovie.html").include(request, response);
	}
	
	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie []arr=request.getCookies();
		int uid=0;
		int mid=0;
		Float price=(float)0;
		String bdate="";
		String rdate="";
		
		for(Cookie x:arr) {
			if(x.getName().equals("iid")) {
			    uid=Integer.parseInt(x.getValue());	
			}
			if(x.getName().equals("dp")) {
				mid=Integer.parseInt(x.getValue());
			}
			if(x.getName().equals("mprice")) {
				price=Float.parseFloat(x.getValue());
			}
			if(x.getName().equals("bdate")) {
				bdate=x.getValue();
			}
			if(x.getName().equals("rdate")) {
				rdate=x.getValue();
			}
			
		}
		System.out.println(mid+" "+uid+" "+" "+bdate+" "+rdate+" "+price);
		try {
			
			Connection con=Dbconnection.get_connection();
			PreparedStatement ps1=con.prepareStatement("Insert INTO rentmovie VALUES(?,?,?,?)");
			PreparedStatement ps2=con.prepareStatement("Insert INTO payment (mid,uid,amount) VALUES(?,?,?)");
			PreparedStatement ps3=con.prepareStatement("SELECT *from movie where mid=?");
			ps3.setInt(1, mid);
			
			ResultSet rs=ps3.executeQuery();
			rs.next();
			price=rs.getFloat("mprice");
			ps1.setInt(1, mid);
			ps1.setInt(2,uid);
			ps1.setString(3,bdate);
			ps1.setString(4,rdate);
			ps2.setInt(1, mid);
			ps2.setInt(2, uid);
			ps2.setFloat(3, price);
			int s=ps1.executeUpdate();
			int q=ps2.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.print(e);
		}
		request.getRequestDispatcher("usermovies.jsp").include(request, response);
		
		
	}

}
