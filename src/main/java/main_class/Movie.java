package main_class;

import java.util.ArrayList;
import java.util.List;

import com.database.Dbconnection;

import java.sql.*;

public class Movie {
	int id;
	String Movie_name;
	int Movie_quan;
	Float Movie_price;
	String Movie_language;
	String Movie_characherstics;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMovie_name() {
		return Movie_name;
	}
	public void setMovie_name(String movie_name) {
		Movie_name = movie_name;
	}
	public int getMovie_quan() {
		return Movie_quan;
	}
	public void setMovie_quan(int movie_quan) {
		Movie_quan = movie_quan;
	}
	public Float getMovie_price() {
		return Movie_price;
	}
	public void setMovie_price(Float movie_price) {
		Movie_price = movie_price;
	}
	public String getMovie_language() {
		return Movie_language;
	}
	public void setMovie_language(String movie_language) {
		Movie_language = movie_language;
	}
	public String getMovie_characherstics() {
		return Movie_characherstics;
	}
	public void setMovie_characherstics(String movie_characherstics) {
		Movie_characherstics = movie_characherstics;
	}
	
	public void addmovie(Movie node) {
		try {
			Connection con=Dbconnection.get_connection();
			PreparedStatement ps=con.prepareStatement("INSERT INTO movie (mname,mquantity,mprice,mlanguage,mcharacterstic) VALUES(?,?,?,?,?)");
			ps.setString(1, node.Movie_name);
			ps.setInt(2, node.Movie_quan);
			ps.setFloat(3, node.Movie_price);
			ps.setString(4, node.Movie_language);
			ps.setString(5, node.Movie_characherstics);
			int s=ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.print(e);
			System.out.print("i am error in movie class add method");
		}
	}
	public void deletemovie(Movie node) {
		try {
			Connection con=Dbconnection.get_connection();
			PreparedStatement ps=con.prepareStatement("Delete from movie where mid=?");
			ps.setInt(1, node.id);

			int s=ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.print(e);
			System.out.print("i am error in movie class delete method");
		}
		
	}
	public static List<Movie>  listmovie(){
		List<Movie> arr=new ArrayList<>();
		try {
			Connection con=Dbconnection.get_connection();
			PreparedStatement ps=con.prepareStatement("Select *from Movie");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Movie node=new Movie();
				node.id=rs.getInt("mid");
				node.Movie_name=rs.getString("mname");
				node.Movie_quan=rs.getInt("mquantity");
				node.Movie_price=rs.getFloat("mprice");
				node.Movie_language=rs.getString("mlanguage");
				node.Movie_characherstics=rs.getString("mcharacterstic");
				arr.add(node);
			}
			
		}
		catch(Exception e) {
			System.out.print(e);
			System.out.print("i am error in movie class listmovie method");
		}
		
		return arr;
	}
	
	public void modify_det(Movie node,int i) {
		if(i==1) {
			try {
				Connection con=Dbconnection.get_connection();
                PreparedStatement ps= con.prepareStatement("Update movie SET mname=? WHERE mid=?");
                ps.setString(1,node.getMovie_name());
                ps.setInt(2, node.getId());
                int s=ps.executeUpdate();
			}
			catch(Exception e) {
				System.out.print(e);
			}
		}
		else if(i==2) {
			try {
				Connection con=Dbconnection.get_connection();
                PreparedStatement ps= con.prepareStatement("Update movie SET mquantity=? WHERE mid=?");
                ps.setInt(1,node.getMovie_quan());
                ps.setInt(2, node.getId());
                int s=ps.executeUpdate();
			}
			catch(Exception e) {
				System.out.print(e);
			}
		}
		else if(i==3) {
			try {
				Connection con=Dbconnection.get_connection();
                PreparedStatement ps= con.prepareStatement("Update movie SET mprice=? WHERE mid=?");
                ps.setFloat(1, node.Movie_price);
                ps.setInt(2, node.getId());
                int s=ps.executeUpdate();
			}
			catch(Exception e) {
				System.out.print(e);
			}
		}
		else if(i==4) {
			try {
				Connection con=Dbconnection.get_connection();
                PreparedStatement ps= con.prepareStatement("Update movie SET mlanguage=? WHERE mid=?");
                ps.setString(1,node.getMovie_language());
                ps.setInt(2, node.getId());
                int s=ps.executeUpdate();
			}
			catch(Exception e) {
				System.out.print(e);
			}
		}
		else if(i==5) {
			try {
				Connection con=Dbconnection.get_connection();
			
                PreparedStatement ps= con.prepareStatement("Update movie SET mcharacterstic=? WHERE mid=?");
                ps.setString(1,node.getMovie_characherstics());
                ps.setInt(2, node.getId());
                int s=ps.executeUpdate();
			}
			catch(Exception e) {
				System.out.println(e);
				System.out.println("Error in search movie");
			}
		}
	}
	public Movie searchmovie(Movie node){
		ResultSet rs=null;
	    Movie node2=new Movie();
	    node2.setId(-1);
		try {
		
			Connection con=Dbconnection.get_connection();
			System.out.println("      HELLLLLLLLL NOOOOOOOOO");
			PreparedStatement ps=con.prepareStatement("Select *from movie Where mname=?");
			ps.setString(1, node.Movie_name);
		    rs=ps.executeQuery();	
			if(rs.next()) {
				node2.setId(rs.getInt("mid"));
				node2.setMovie_name(rs.getString("mname"));
				node2.setMovie_quan(rs.getInt("mquantity"));
				node2.setMovie_price(rs.getFloat("mprice"));
				node2.setMovie_language(rs.getString("mlanguage"));
				node2.setMovie_characherstics(rs.getString("mcharacterstic"));
				
			}

		}
		catch(Exception e) {
			System.out.print(e);
			 System.out.print("hello i am error");
		}
		return node2; 
	}
	public Movie showdetails(Movie node) {
		ResultSet rs=null;
		Movie node2=new Movie();
		try {
		
			Connection con=Dbconnection.get_connection();
			PreparedStatement ps=con.prepareStatement("Select *from movie Where mid=?");
			ps.setInt(1, node.id);
		    rs=ps.executeQuery();	
			if(rs.next()) {
				node2.setId(rs.getInt("mid"));
				node2.setMovie_name(rs.getString("mname"));
				node2.setMovie_quan(rs.getInt("mquantity"));
				node2.setMovie_price(rs.getFloat("mprice"));
				node2.setMovie_language(rs.getString("mlanguage"));
				node2.setMovie_characherstics(rs.getString("mcharacterstic"));
				
			}
		}
		catch(Exception e) {
			System.out.print(e);           
		}
		return node2;
	}
	public static ResultSet rentedmovies() {
		ResultSet res=null;
		try {
			Connection con=Dbconnection.get_connection();
	    	PreparedStatement ps=con.prepareStatement("select Distinct movie.mid ,movie.mname From movie INNER JOIN rentmovie ON movie.mid=rentmovie.mid ");
	    	res=ps.executeQuery();	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return res;
	}

}
