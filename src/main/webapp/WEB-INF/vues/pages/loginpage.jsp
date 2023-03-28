<%@ page import="com.bo.Message" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Mohammed Aziz
  Date: 28/03/2023
  Time: 00:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
    <title>Title</title>
    <link href="<%=request.getServletContext().getContextPath()%>/style/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="min-vh-100 d-flex flex-column align-items-center  bg-success">
    <%--    // titre--%>
    <div class="title">
        <h3 class="text-light"> LOGIN DE <span class="badge bg-danger">jeux</span></h3>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <%--                      Recevoir la liste des messages deja créer lors du traitement dans le servlet--%>
                <% if (request.getAttribute("messages") != null) {%>
                <% List<Message> listMessages = (List<Message>) request.getAttribute("messages");%>
                <% for (Message m : listMessages) {%>
                <% if (m.getType() == 0) {%>
                <div class="alert-primary alert text-center">
                    <%=m%>
                </div>
                <%}%>
                <% if (m.getType() == 1) {%>
                <div class="alert-warning alert text-center">
                    <%=m%>
                </div>
                <%}%>
                <% if (m.getType() == 2) {%>
                <div class="alert-danger alert text-center">
                    <%=m%>
                </div>
                <%}%>
                <%}%>
                <%}%>

            </div>
        </div>


        <%--    // formulaire de jeux--%>



        <div class="row">
            <div class="col-12 shadow bg-light p-4">
                <a href="<%=request.getServletContext().getContextPath()%>/inscription?create=yes"><span
                        class="badge bg-info"> go to la page d'inscription</span></a>
                <form action="<%=request.getServletContext().getContextPath()%>/login" method="POST">
                    <div class="form-group">
                        <label for="login">login </label>
                        <input type="text" class="form-control" id="login" placeholder="Enter login" name="login">
                    </div>

                    <div class="form-group">
                        <label for="password">password </label>
                        <input type="password" class="form-control" id="password" placeholder="Enter password"
                               name="password">
                    </div>

                    <button type="submit" class="btn btn-primary col-12 mt-1">Login</button>
                </form>
            </div>
        </div>

    </div>

</div>


</body>
</html>
