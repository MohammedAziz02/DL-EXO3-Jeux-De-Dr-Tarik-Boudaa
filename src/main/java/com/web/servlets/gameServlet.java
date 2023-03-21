package com.web.servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bo.GameState;
import com.bo.Message;
import com.bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "game", value = "/game")
public class gameServlet extends HttpServlet {
    //    int score = 0;
//    List<Integer> dejaplay = new ArrayList<>();
    String urlCible = "WEB-INF/vues/back/game.jsp";

    public gameServlet() {
        System.out.println("le constructeur de la servlet gameservlet est appelé");
    }

    public void init() {
        System.out.println("game Servlet est initialisé");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("il est dans game get");
        request.getRequestDispatcher(urlCible).forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var session = request.getSession();
        User user = (User) session.getAttribute("user");
        GameState gameState = (GameState) session.getAttribute("gameState");
//       List<Message> messages=new ArrayList<>();
        System.out.println("il est dans game Post");
        System.out.println(user.getplayedde());


        if (!gameState.isGameOver()) {
            // recevoir le numéro de dé depuis la formulaire
            String numerode = request.getParameter("numerode");

            // if numerode not in [1,2,3] return to game page with message Error

            System.out.println("num de dé ==> " + numerode);
            // créer un nombre aléatoire de dé
            Random random = new Random();
            int resultat = random.nextInt(2) + 1;
            System.out.println("resultat random de==> " + resultat);

            // recevoir la session


            //  condition pour que si le joueur joue deux fois par le meme dé ( on a stocké les valeurs de le dé deja joué dans la liste
            // donc on fait un condition si le numero déja en liste se joue encore une autre fois.
            List<Integer> playeddelist = user.getplayedde();
            System.out.println("True Or False " + playeddelist.contains(Integer.parseInt(numerode)));

            if (playeddelist != null) {
                if (playeddelist.contains(Integer.parseInt(numerode))) {
                    System.out.println("deja played...");
                    user.setScore(-1);
                    request.setAttribute("message", new Message("vous avez deja joué avec ce dé ... Game Over",Message.ERROR));
                    gameState.setGameOver(true);
                    user.resetplayedde();
                    request.getRequestDispatcher(urlCible).forward(request, response);
                }

            }


//         on ajoute le numero de dé joué dans le tableu "dejaplay" dans la session pour la vérifcation après
//        dejaplay.add(Integer.parseInt(numerode));
            user.addplayedde(Integer.parseInt(numerode));
//        session.setAttribute("dejaplaynumero", dejaplay);

            // recevoir le numéro de dé depuis la formulaire et lui affécté un valeur aléatoire et puis le stocke dans la session
            if (numerode.equals("1")) {
                session.setAttribute("resultde1", resultat);
            }
            if (numerode.equals("2")) {
                session.setAttribute("resultde2", resultat);
            }
            if (numerode.equals("3")) {
                session.setAttribute("resultde3", resultat);
            }

            // la logique du jeux
            if (session.getAttribute("resultde1") != null && session.getAttribute("resultde2") != null && session.getAttribute("resultde3") != null) {
                int v1 = (int) session.getAttribute("resultde1");
                int v2 = (int) session.getAttribute("resultde2");
                int v3 = (int) session.getAttribute("resultde3");

                if (v1 < v2 && v2 < v3) {
                    user.setScore(v1 + v2 + v3);
                }
                else if (v1 > v2 && v2 > v3) {
                    user.setScore(v1 * v2 * v3);
                }else{
                    user.setScore(-1);
                    gameState.setGameOver(true);

                }

            }

            System.out.println("score" + user.getScore());

            request.setAttribute("message", new Message("joué encore un fois avec un autre dé",Message.INFO));
            request.getRequestDispatcher(urlCible).forward(request, response);
            return;
        } else {
            request.setAttribute("message", new Message("Game Over ...",Message.INFO));

            request.getRequestDispatcher(urlCible).forward(request, response);
            return;
        }

    }

    public void destroy() {
    }
}