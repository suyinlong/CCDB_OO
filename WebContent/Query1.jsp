<%@page import="edu.stonybrook.cs.ccdb.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CSE 532 Project 2 Home Page</title>
    </head>
    <body>
        <%@include file="index.jsp"%>
        <form method="get" action="GetAccountServlet">
            <h1>Quries</h1>
            <input type="radio" name="Query" value="1" checked>Query1
  			<br>
  			<input type="radio" name="Query" value="2">Query2
  			<br>
  			<input type="radio" name="Query" value="3">Query3
  			<br>
  			<input type="radio" name="Query" value="4">Query4
  			<br>
  			<input type="radio" name="Query" value="5">Query5
  			<br>
  			<br>
  			<input type="submit" value="Submit">
        </form>
    </body>
</html>