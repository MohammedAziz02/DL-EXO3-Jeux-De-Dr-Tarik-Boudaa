<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 3/18/2023
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game Party</title>
    <link href="<%=request.getServletContext().getContextPath()%>/style/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>
<div class="min-vw-100 min-vh-100 d-flex flex-column align-items-center justify-content-center bg-success">

    <%if (request.getAttribute("message") != null) {%>
    <div class="alert alert-danger"><%=request.getAttribute("message")%>
    </div>
    <%}%>

    // titre
    <div class="title">
        <h3 class="text-light"> bienvenue au jeux de <span class="badge bg-danger">dées</span></h3>
    </div>


    // formulaire de jeux
    <div class="container-fluid w-75 p-4 shadow bg-light">
        <form action="<%=request.getServletContext().getContextPath()%>/game" method="POST">
            <div class="mb-3">
                <label>Numéro de dé :</label>
                <input type="text" class="form-control" placeholder="N° de Dé" name="numerode">

            </div>


            <button type="submit" class="btn btn-success col-12">Play</button>
        </form>
    </div>

</div>

</body>
</html>
