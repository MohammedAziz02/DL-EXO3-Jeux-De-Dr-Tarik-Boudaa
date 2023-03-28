package com.web.servlets;

import com.bo.GameState;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet(name = "reinitGame",value = "/reinitgame")
public class ReinitGame extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        var gamestate=(GameState)session.getAttribute("gameState");
        if(gamestate!=null){
            System.out.println("c vrai donc game.state raha treinit");
            gamestate.reinit();
        }
        req.getRequestDispatcher("/WEB-INF/vues/back/game.jsp").forward(req, resp);
        return;
    }
}
