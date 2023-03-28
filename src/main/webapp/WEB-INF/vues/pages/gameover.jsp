<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>GameOver</title>
    <link href="<%=request.getServletContext().getContextPath()%>/style/bootstrap.min.css" rel="stylesheet">
</head>
<body class="min-vh-100 bg-success">

<div class="container mt-5">

    <div class="row ">
        <div class="text-center">
            <%=session.getAttribute("user")%>
        </div>

    </div>

    <div class="row">
        <%if (request.getAttribute("message") != null) {%>
        <div class="alert alert-primary text-center">
            <span><%=request.getAttribute("message")%></span>
        </div>
        <%}%>
    </div>
    <div class="row mt-2">
        <div class="col-12 row shadow bg-light p-1">
            <div class="text-center ">
                <form action="<%=request.getServletContext().getContextPath()%>/game">

                    <input type="submit" class="btn-outline-primary col-12" value="jouer encore fois"/>
                </form>
            </div>
        </div>
    </div>
</div>


</body>
</html>