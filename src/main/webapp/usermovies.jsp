<%@ page  import = "java.io.*" import= "java.util.*" import="main_class.*"   language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<style>
table, th, td {
  border:1px solid black;
  border-collapse: collapse;
}
div.c1{
  box-sizing:border-box;
   height: 500px;
   width: 30%;
   border: 6px solid black;
   float: right;
  
}
.b1{
    margin-top: 100px;
    padding-left: 50%;
    padding-right: 50%;
    margin-top:100px;
}
.b2{
    margin-top: 100px;
    padding-left: 50%;
    padding-right: 50%;
    margin-top:100px;
}
.b3{
    margin-top: 100px;
    padding-left: 50%;
    padding-right: 50%;
    margin-top:100px;
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

<a id='c2' href="logout">Logout</a>
<%
    PrintWriter out1=response.getWriter();
    List<Movie>arr=Movie.listmovie();
    
%>
<table>
    <tr>
    <th>Movie Id</th>
    <th>Movie Name</th>
    <th>Movie Language</th>
    <th>Movie Quantity</th>
    <th>Price</th>
    <th>Movie characteristics</th>
    <th>Buy Link</th>
    </tr>
    
<%
    for(Movie x:arr){

%>     
    <tr>
    <th><%= x.getId()%></th>
    <th><%= x.getMovie_name() %></th>
    <th><%= x.getMovie_language() %></th>
    <th><%= x.getMovie_quan() %></th>
    <th><%= x.getMovie_price() %></th>
    <th><%= x.getMovie_characherstics() %></th>
    <th><a href='buy?id=<%= x.getId() %>'><button type="button">Click Here To Buy</button></a></th>
    </tr>
      
<%
    
    }
    
%>
</table>



</body>
</html>