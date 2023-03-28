<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>App Game</title>
</head>
<body>

<h1>Partie Términé</h1>

<%=session.getAttribute("user")%>

<form action="<%=request.getServletContext().getContextPath()%>/game">

    <input type="submit"  value="jouer encore fois"/>
</form>



</body>
</html>