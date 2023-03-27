<%--
  Created by IntelliJ IDEA.
  User: Mohammed Aziz
  Date: 27/03/2023
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link href="<%=request.getServletContext().getContextPath()%>/style/bootstrap.min.css"  rel="stylesheet">
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">--%>
</head>
<body>
<div class="min-vw-100 min-vh-100 d-flex flex-column align-items-center justify-content-around bg-success">
    <%--    // titre--%>
    <div class="col-12 d-flex flex-column justify-content-center align-items-center">


        <div class="title">
            <h3 class="text-light"> Inscription pour un nouveau <span class="badge bg-danger">jeux</span></h3>
        </div>


        <%--    // formulaire de jeux--%>
        <div class="container-fluid w-75 p-4 shadow bg-light">
            <div>
                <a href="<%=request.getServletContext().getContextPath()%>/login"><span class="badge bg-info"> go to login</span></a>
            </div>
            <form action="<%=request.getServletContext().getContextPath()%>/inscription" method="POST">
                <div class="form-group">
                    <label for="nom">nom </label>
                    <input type="text" class="form-control" id="nom" placeholder="Enter nom"  name="nom">
                </div>
                <div class="form-group">
                    <label for="prenom">prenom </label>
                    <input type="text" class="form-control" id="prenom" placeholder="Enter prenom"  name="prenom">
                </div>

                <div class="form-group">
                    <label for="login">login </label>
                    <input type="text" class="form-control" id="login" placeholder="Enter login"  name="login">
                </div>

                <div class="form-group">
                    <label for="password">password </label>
                    <input type="password" class="form-control" id="password" placeholder="Enter password"  name="password">
                </div>
               
                <button type="submit" class="btn btn-primary col-12 mt-2">S'inscrire</button>
            </form>
        </div>

    </div>

</div>

</div>

</body>
</html>
