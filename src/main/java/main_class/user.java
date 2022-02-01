package main_class;
import java.sql.*;

import com.database.Dbconnection;
public class user extends person{
	int user_id;
	String contact_no;
	
	public void signup(user node) {
		try {
			
			Connection con=Dbconnection.get_connection();
			PreparedStatement ps=con.prepareStatement("Insert into person(pname,puname,pmob,ppass,isadmin) Values(?,?,?,?,?)");
			ps.setString(1, node.name);
			ps.setString(2, node.username);
			ps.setString(3, node.contact_no);
			ps.setString(4, node.password);
			ps.setBoolean(5, node.isadmin);
			int s=ps.executeUpdate();
			
		}
		catch(Exception e) {
			System.out.print(e);
			System.out.print("I am error in user class signup method");
		}
	}
	void rented_movies() {
		
	}
	void change_password() {
		
	}
	void transaction() {
		
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	
}
