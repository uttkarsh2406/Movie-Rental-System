<%@page import="java.io.*" import="java.utils.*" import="main_class.*" import="javax.servlet.http.Cookie" import="java.sql.*" %>
<%@page import="com.database.Dbconnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movie Details</title>
<style>
h1{
   text-alng
}
       #c2{
      float:right;
      border: 5px;
      border-style: groove;
      border-radius: 20px;
      text-decoration: none;
      color: black;
      font-size: 25px;
    }
</style>
</head>
<body>




<% 
String id="123";
Cookie []arr=request.getCookies();
for(Cookie x:arr){
	if(x.getName().equals("dp")){
		id=x.getValue();
	}
}
Movie node=new Movie();
node.setId(Integer.parseInt(id));
Movie node1=node.showdetails(node);
int d=node1.getId();
String name=node1.getMovie_name();
Float price=node1.getMovie_price();
String lang=node1.getMovie_language();
String chara=node1.getMovie_characherstics();
Cookie noo=new Cookie("price",price+"");
response.addCookie(noo);
PrintWriter outr=response.getWriter();
//Movie node=request.getAttribute("obj");

//Cookie cookies[]=request.getCookies();
//for(Cookie x:cookies){
////	if(x.getName().equals("id")){
//	  d=Integer.parseInt(x.getValue());	
	  //	}
//	else if(x.getName().equals("name")){
//		name=x.getValue();
		//	}
//	else if(x.getName().equals("price")){
//		price=Float.parseFloat(x.getValue());
//	}
//	else if(x.getName().equals("language")){
//		//		lang=x.getValue();
	//}
	//	else if(x.getName().equals("character")){
	//	chara=x.getValue();
//	}
	//		
//}//

int count=0;
int mc=0;
try{
	Connection con=Dbconnection.get_connection();
	PreparedStatement ps1=con.prepareStatement("select *from movie where mid=?");
	PreparedStatement ps2=con.prepareStatement("Select *from rentmovie Where mid=?");
	ps1.setInt(1,Integer.parseInt(id));
	ps2.setInt(1,Integer.parseInt(id));
	ResultSet rs1=ps1.executeQuery();
	ResultSet rs2=ps2.executeQuery();
	if(rs1.next()){
		mc=rs1.getInt("mquantity");
	}
	while(rs2.next()){
		count++;
	}
	
}
catch(Exception e){
	System.out.println(e);
}

if(mc-count<=0){
%>



<% 
outr.println("<h1>Movie Out Of Stock</h1>");
request.getRequestDispatcher("usermovies.jsp").include(request, response);
}
else{




%>
<a id='c2' href="logout">Logout</a>
<h1>Movie Details</h1>
<br>
<br>
<br>
<h2>Movie id : <%=d%></h2>
<h2>Movie Name : <%=name %></h2>
<h2>Movie Price : <%=price %></h2>
<h2>Movie Language :<%=lang %></h2>

<form action="payment">
          <label for="buydate"><b>Present Day Date</b></label>
          <input id="bd" type="date" name="buydate" >
          <label for="retdate"><b>Buy Till</b></label>
          <input type="date" name="retdate" required>
          <button type='submit'> Make Payment</button>
</form>
        <script type="text/javascript">
          n=new Date();
          y=n.getFullYear();
          m=n.getMonth()+1;
          d=n.getDate();
          document.getElementById("bd").value=y+"-"+m+"-"+d;
        </script>

<%} %>


</body>
</html>







