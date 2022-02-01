package com.database;
import java.sql.*;

public class Dbconnection {
   public static Connection get_connection() {
	   Connection con=null;
	   String url="jdbc:mysql://localhost:3306/moviestore?useSSL=false";
	   String user="root";
	   String pass="2429";
	   try {
		   Class.forName("com.mysql.jdbc.Driver");
		   con=DriverManager.getConnection(url,user,pass);
	   }
	   catch(Exception e){
		   System.out.print("Error While Getting Connection");
	   }
	   return con;
   }
}
