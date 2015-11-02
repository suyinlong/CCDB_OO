<%@page import="edu.stonybrook.cs.ccdb.*"%>
<%@ page import="java.util.ArrayList" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
                <thead>
                    <tr>
                        <th>Number</th>
                        <th>Balance</th>
                        <th>Limit</th>
                    </tr>
                </thead>
                <% 
                
                List<Account> results = (List<Account>)request.getAttribute("result");
                for (Account iter : results) {
	                if(results != null && !results.isEmpty()){
	                       out.println("<TR align='left'>");
	                       out.println("<TD>"+iter.GetNumber()+"</TD>");
	                       out.println("<TD>"+String.valueOf(iter.GetBalance())+"</TD>");
	                       out.println("<TD>"+String.valueOf(iter.GetLimit())+"</TD>");
	                       out.println("</TR>");
	                }
                }
            %>
            </table>
    </body>
</html>