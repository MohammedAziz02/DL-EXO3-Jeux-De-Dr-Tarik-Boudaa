package com.web.servlets;

import com.bo.User;
import com.web.helpers.IGameDataManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "bestScore",value = "/bestscore")
public class BestScoreServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        IGameDataManagement contextGame = (IGameDataManagement) request.getServletContext().getAttribute("gameData");

        // On récupére tous les Users
        List<User> users = contextGame.getAllUsers();

        // On stocke dans la requete (comme attribut) les Users. Cette
        // liste a une durée de vie = à la durée de vie de la requete. Donc elle
        // n'existera plus à la fin du cycle de vie de la requete
        request.setAttribute("users", users);

        // On redirige vers la vue (redirection coté serveur)
        request.getServletContext().getRequestDispatcher("/WEB-INF/vues/back/bestScore.jsp").forward(request, response);

    }
}
