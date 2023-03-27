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
import org.apache.log4j.Logger;

@WebServlet(name = "game", value = "/game")
public class gameServlet extends HttpServlet {

    Logger logger = Logger.getLogger(getClass());

    String gamejsp="/WEB-INF/vues/back/game.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dkhl l get f line lwl");
        logger.info("Informational message");
        logger.debug("wa hello a weld nass");
        req.getRequestDispatcher(gamejsp).forward(req,resp);

        System.out.println("hamid get");
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post method");
        req.getRequestDispatcher(gamejsp).forward(req,resp);

        System.out.println("hamid hamid post");
        return;

    }

    public void destroy() {
    }

}


