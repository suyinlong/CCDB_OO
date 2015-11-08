<%@page import="edu.stonybrook.cs.ccdb.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
        <title>CSE 532 Project 2 Home Page</title>
        <script language="JavaScript">
        	function query(id) {
        		resulttable.innerHTML = "<iframe src=\"QueryProcessingServlet?Query=" + id + "\" scrolling=\"no\"></iframe>";
        	}
        </script>
    </head>
    <body>
    <div class="wrapper">
    	<h1>CSE 532 Project 2 <a href="index.jsp">[Home]</a></h1>
    </div>
    <div class="content">
    	<div class="query">
    	<h1>Queries</h1>
    	<ul class="submenu">
    		<li><a href="#" onclick="javascript: query(1);">Query 1</a></li>
    		<li><a href="#" onclick="javascript: query(2);">Query 2</a></li>
    		<li><a href="#" onclick="javascript: query(3);">Query 3</a></li>
    		<li><a href="#" onclick="javascript: query(4);">Query 4</a></li>
    		<li><a href="#" onclick="javascript: query(5);">Query 5</a></li>
    	</ul>
    	</div>
    	<div class="result">
    	<h1>Result</h1>
    	<span id="resulttable">&nbsp;</span>
    	</div>
    </div>
</body>
</html>