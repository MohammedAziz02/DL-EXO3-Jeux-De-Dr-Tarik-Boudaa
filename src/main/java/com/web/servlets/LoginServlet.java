package com.web.servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.bo.GameState;
import com.bo.Message;
import com.bo.User;
import com.web.helpers.IGameDataManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.log4j.Logger;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass());

    String loginjsp="/WEB-INF/vues/pages/loginpage.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(loginjsp).forward(req,resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String gamePage = "/WEB-INF/vues/back/game.jsp";
        String loginForm = "/WEB-INF/vues/pages/loginpage.jsp";


        String login = req.getParameter("login");
        String password = req.getParameter("password");

        // On instancie la liste que nous utiliserons pour stocker les messages
        // à passer à la vue
        List<Message> messages = new ArrayList<Message>();

        IGameDataManagement gameContext = (IGameDataManagement) getServletContext().getAttribute("gameData");

        // On recherche l'User par login
        User user = gameContext.getUserByLogin(login.trim());
        // Si un User existe
        if (user != null) {

            // On vérifie que les mots de passe sont identiques
            if (user.getPassword() != null && user.getPassword().equals(password)) {

                // On stocke l'objet stockant l'état de jeu dans la session
                GameState gameSate = new GameState(user);
                req.getSession().setAttribute("gameState", gameSate);

                // On stocke l'User authentifié dans la session
                req.getSession().setAttribute("user", user);

                // On envoie une vue qu'est la page home comme résultat
                getServletContext().getRequestDispatcher(gamePage).forward(req, resp);

                // Fin
                return;
            } else {

                // On ajoute un message
                messages.add(new Message("Login ou mot de passe incorrect", Message.ERROR));

                // Mettre la la liste des messages dans les attributs de la requête
                req.setAttribute("messages", messages);

                // on affiche le formulaire d'authentification avec des
                // messages d'erreur
                getServletContext().getRequestDispatcher(loginForm).forward(req, resp);

                return;
            }

        } else {
            messages.add(new Message("User n'existe Pas Déja .... créer un compte puis essayer", Message.WARN));

            req.setAttribute("messages", messages);

            // de meme si l'User est introuvable avec une recherche par
            // login
            getServletContext().getRequestDispatcher(loginForm).forward(req, resp);

            // Fin
            return;
        }

    }

    public void destroy() {
    }

}


