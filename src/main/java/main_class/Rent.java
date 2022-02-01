package main_class;
import java.sql.*;

import com.database.Dbconnection;

public class Rent {
    int movieid;
    int userid;
    String buydate;
    String retdate;
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getBuydate() {
		return buydate;
	}
	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}
	public String getRetdate() {
		return retdate;
	}
	public void setRetdate(String retdate) {
		this.retdate = retdate;
	}
	
	void borrow() {
		
	}
	void returnmovie() {
		try {
			Connection con=Dbconnection.get_connection();
			PreparedStatement ps=con.prepareStatement("Delete From rentmovie Where  ");
			
		}
		catch(Exception e) {
			System.out.print(e);
		}
	}
	
    
    
}
