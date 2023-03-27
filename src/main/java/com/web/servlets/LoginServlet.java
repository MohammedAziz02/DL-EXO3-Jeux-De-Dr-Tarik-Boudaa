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

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

    Logger logger = Logger.getLogger(getClass());

    String loginjsp="/WEB-INF/vues/pages/loginpage.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Informational message mn loginservlet");
        logger.debug("wa hello a weld nass mn login servlet");
        req.getRequestDispatcher(loginjsp).forward(req,resp);

        System.out.println("hamid get");
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        System.out.println("hamid hamid post mn login servlet");
        return;

    }

    public void destroy() {
    }

}


