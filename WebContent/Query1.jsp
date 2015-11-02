<%@page import="edu.stonybrook.cs.ccdb.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="index.jsp"%>
        <form method="get" action="GetAccountServlet">
            <h1>Quries</h1>
            <label>Query1</label>
            <input type="text" name="q1" id="q1" value="select * from Account" />
            <input type="submit" value="submit">
        </form>
    </body>
</html>