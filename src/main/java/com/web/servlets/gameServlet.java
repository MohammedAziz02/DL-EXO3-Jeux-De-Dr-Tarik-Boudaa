package com.web.servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
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

@WebServlet(name = "game", value = "/game")
public class gameServlet extends HttpServlet {

    Logger logger = Logger.getLogger(getClass());
    String gamejsp = "/WEB-INF/vues/back/game.jsp";
    String gameover = "/WEB-INF/vues/pages/gameover.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(gamejsp).forward(req, resp);
        return;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // On récupére la session de l'User en cours
        HttpSession session = req.getSession();
        // On récupére de la session, les informations du joueur en cours
        User user = (User) session.getAttribute("user");
        // Pour gérer les données de l'application dans le ServletContext
        IGameDataManagement gameContext = (IGameDataManagement) getServletContext().getAttribute("gameData");
        // Cette objet déjà inséré dans la session au moment de login
        GameState gameSate = (GameState) session.getAttribute("gameState");
        // pour tester
        // recevoir la valeur du dee a partir de la formulaire et le caster en Int pour pouvoir l'utilisé après
        Integer numerodee = Integer.parseInt(req.getParameter("numerode"));

        if (session.getAttribute("informations_dee") != null) {
            // casting
            HashMap<Integer, Integer> informations_dee = (HashMap<Integer, Integer>) session.getAttribute("informations_dee");
            // si le n° dée joué par l'user est déja exist donc gameover
            if (informations_dee.containsKey(numerodee)) {
                // on donne -1 au score d l'user et on supprime les des jouée dans la sessiosn
                user.setScore(-1);
                gameSate.setGameOver(true);
                session.removeAttribute("informations_dee");
                req.getRequestDispatcher(gameover).forward(req, resp);
                return;
            }
            // sinon on stock les information de dé dans un dictionnaire N°=> Valeur
            else {
                // génerer un nombre aléatoire between 1 and 6;
                Random random = new Random();
                int resultat_dee = random.nextInt(6) + 1;
                informations_dee.put(numerodee, resultat_dee);

            }

            //condition sur si ....

            if (informations_dee.size() < 3) {
                if (informations_dee.get(2) == 1 && informations_dee.get(2) == 6) {
                    user.setScore(0);
                    gameSate.setGameOver(true);
                    session.removeAttribute("informations_dee");
                    req.getRequestDispatcher(gameover).forward(req, resp);
                    return;
                }
            }
            // on commence par la logique de jeux si le dictionnaire a 3 element (3 dee)

            if (informations_dee.size() == 3) {
                System.out.println("valeur dans le de 1==>"+informations_dee.get(1));
                System.out.println("valeur dans le de 2==>"+informations_dee.get(2));
                System.out.println("valeur dans le de 3==>"+informations_dee.get(3));
                // on commence par donner le score à 0 puis si les conditions sont  vrai le score va etre changé
                user.setScore(0);
                if (informations_dee.get(1) < informations_dee.get(2) && informations_dee.get(2) < informations_dee.get(3)) {
                    System.out.println("score qui va dans user"+informations_dee.get(1) + informations_dee.get(2) + informations_dee.get(3));
                    user.setScore(informations_dee.get(1) + informations_dee.get(2) + informations_dee.get(3));
                }
                if (informations_dee.get(1) > informations_dee.get(2) && informations_dee.get(2) > informations_dee.get(3)) {
                    System.out.println("score qui va dans user"+informations_dee.get(1) * informations_dee.get(2) * informations_dee.get(3));
                    user.setScore(informations_dee.get(1) * informations_dee.get(2) * informations_dee.get(3));
                }

                gameSate.setGameOver(true);
                session.removeAttribute("informations_dee");
                req.getRequestDispatcher(gameover).forward(req, resp);
                return;
            }

        } else {
            HashMap<Integer, Integer> informations_dee = new HashMap<Integer, Integer>();
            Random random = new Random();
            int resultat_dee = random.nextInt(6) + 1;
            informations_dee.put(numerodee, resultat_dee);
            session.setAttribute("informations_dee", informations_dee);
        }


        req.getRequestDispatcher(gamejsp).forward(req, resp);
        return;
    }

    public void destroy() {
    }

}


