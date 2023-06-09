<%@ page import="com.web.helpers.GameContextManagement" %>
<%@ page import="com.bo.User" %>
<%@ page import="com.bo.GameState" %>
<%@ page import="com.bo.Message" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 3/18/2023
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>
    <title>Game Party</title>
    <link href="<%=request.getServletContext().getContextPath()%>/style/bootstrap.min.css" rel="stylesheet">
</head>
<body>


<%--    <%--%>
<%--        // On récupére de la session, les informations du joueur en cours--%>
<%--        User user = (User) session.getAttribute("user"); %>--%>
<%--    <%=user.getLogin()%>--%>

<%-- <div class="d-flex col-12 justify-content-center align-items-center bg-gradient">

     <%
         var sessuser = (User) session.getAttribute("user");
         var sessgame = (GameState) session.getAttribute("gameState");
         if (sessgame != null && sessuser != null) {
     %>
     <span class="m-1"> Nom : <%=sessuser.getNom()%></span>
     <span class="m-1"> Prenom : <%=sessuser.getPrenom()%></span>
     <span class="m-1"> Score :  <%=sessuser.getScore()%></span>
     <span class="m-1"> BestScore :  <%=sessuser.getBestScore()%></span>
     <%}%>

 </div>
 &lt;%&ndash;    // titre&ndash;%&gt;
 <div class="col-12 d-flex flex-column justify-content-center align-items-center">


     <% if (request.getAttribute("message") != null) {%>


     &lt;%&ndash;    selon le type de Message une Alert s'affiche au-dessous de la formulaire (Error==> Rouge, info==> blue, Warning ==> yellow)&ndash;%&gt;
     <%  Message m=(Message) request.getAttribute("message");%>

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
--%>

<div class="container-fluid min-vh-100 d-flex flex-column align-items-center  bg-success">
    <div class="container">
        <div class="row d-flex justify-content-around">
            <div class="col-2">
                <%
                    User user = (User) session.getAttribute("user");
                %>
                Nom :<span class="badge bg-success"> <%=user.getNom()%> </span>
            </div>
            <div class="col-2">
                Prénom :<span class="badge bg-success"> <%=user.getPrenom()%></span>
            </div>
            <div class="col-2">
                Score :<span class="badge bg-success"> <%=user.getScore()%></span>
            </div>


            <div class="col-2">
                Best Score of others Users :<a href="<%=request.getServletContext().getContextPath()%>/bestscore"><span class="badge bg-success"> Go gO </span></a>
            </div>

        </div>
    </div>

    <div class="container text-center">
        <h3 class="text-light"> bienvenue au jeux de <span class="badge bg-danger">dées</span></h3>
    </div>
    <%--    // formulaire de jeux--%>
    <div class="container w-75 p-4 shadow bg-light">
        <form action="<%=request.getServletContext().getContextPath()%>/game" method="POST">
            <div class="mb-3">
                <label>Numéro de dé :</label>
                <%--                    <input type="text" class="form-control" placeholder="N° de Dé" name="numerode">--%>
                <select class="form-select" name="numerode">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>

            </div>
            <button type="submit" class="btn btn-success col-12">Play</button>
        </form>

        <form class="mt-2" action="<%=request.getServletContext().getContextPath()%>/reinitgame" >
            <button type="submit" class="btn btn-danger col-12">Reinit</button>
        </form>


    </div>
    <div class="container">
        <div class="row">
            <%if(session.getAttribute("informations_dee")!=null){%>
                <div class="alert-success mt-3 text-center text-light">
                    <%=session.getAttribute("informations_dee")%>
                </div>
                <%}%>
        </div>
    </div>


</div>


</body>
</html>
