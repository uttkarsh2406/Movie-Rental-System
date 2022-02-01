package main_class;

import java.sql.*;

import com.database.Dbconnection;

public class person {
    String name;
    String username;
    String password;
    boolean isadmin;
    
    public person(){
    	
    }
    
    public person(String name,String username,String password,boolean isadmin){
    	this.name=name;
    	this.username=username;
    	this.password=password;
    	this.isadmin=isadmin;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isIsadmin() {
		return isadmin;
	}
	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}
	
    public int login(person node){
    	ResultSet rs=node.validate(node.username, node.password);
    	int ans=3;
    	try {
    	    if(rs.next()) {
    		     boolean flag=rs.getBoolean("isadmin");
    		     if(flag==true) {
    		    
    		    	 return 1;
    		     }
    		     else {
    		    	 return 2;
    		     }
    	    }
    	}
    	catch(Exception e) {
    		System.out.println(e);
    		System.out.print("I am Error From Person class Login method");
    	}
    	return ans;
    	
    }
    ResultSet validate(String username,String password) {
    	ResultSet rs=null;
    	try {
    		Connection con=Dbconnection.get_connection();
    	    PreparedStatement ps=con.prepareStatement("Select *from person WHERE puname=? and ppass=?");
    	    ps.setString(1, username);
    	    ps.setString(2, password);
    	    rs=ps.executeQuery();
    	}
    	catch(Exception e) {
    		System.out.println(e);
    		System.out.print("I am Error From Person class validate method");
    	}
    	
    	return rs; 
    }
    
    
}
