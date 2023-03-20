package com.web.servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "gameServlet", value = "/game")
public class gameServlet extends HttpServlet {
    int score = 0;
    List<Integer> dejaplay = new ArrayList<>();
    String urlCible = "WEB-INF/vues/game.jsp";


    public gameServlet() {
        System.out.println("le constructeur de la servlet gameservlet est appelé");
    }

    public void init() {
        System.out.println("game Servlet est initialisé");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String urlCible = "WEB-INF/vues/game.jsp";
        request.getRequestDispatcher(urlCible).forward(request, response);
        System.out.println("il est dans game get");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("il est dans game Post");
        // recevoir le numéro de dé depuis la formulaire
        String numerode = request.getParameter("numerode");
        System.out.println("numde" + numerode);
        // créer un nombre aléatoire de dé
        Random random = new Random();
        int resultat = random.nextInt(6) + 1;
        System.out.println("resulttat random" + resultat);

        // recevoir la session
        var session = request.getSession();

        //  condition pour que si le joueur joue deux fois par le meme dé ( on a stocké les valeurs de le dé deja joué dans une liste dans la sessions
        // donc on fait un condition si le numero déja en session se joue encore une autre fois.
        if (session.getAttribute("dejaplaynumero") != null) {
            ArrayList<Integer> arr = (ArrayList<Integer>) session.getAttribute("dejaplaynumero");
            for (int i : arr) {
                if (i == Integer.parseInt(numerode)) {
                    System.out.println("deja played...");
                    request.setAttribute("message", "deja playiti awldi");
                    request.getRequestDispatcher(urlCible).forward(request, response);

                }
            }
        }


        // on ajoute le numero de dé joué dans le tableu "dejaplay" dans la session pour la vérifcation après
        dejaplay.add(Integer.parseInt(numerode));
        session.setAttribute("dejaplaynumero", dejaplay);

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
                score = v1 + v2 + v3;
            }
            if (v1 > v2 && v2 > v3) {
                score = v1 * v2 * v3;
            }

        }

        System.out.println("score" + score);

        request.setAttribute("message", "jouer encore une fois avec un autre dé");
        request.getRequestDispatcher(urlCible).forward(request, response);
        return;

    }

    public void destroy() {
    }
}