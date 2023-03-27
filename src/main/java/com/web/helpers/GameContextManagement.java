package com.web.helpers;
import com.bo.User;
import jakarta.servlet.ServletContext;

import java.util.ArrayList;
import java.util.List;

public class GameContextManagement implements IGameDataManagement {
    private ServletContext context;
    private static GameContextManagement instance;

    private GameContextManagement(ServletContext context) {
        this.context = context;
        this.context.setAttribute("users",new  ArrayList<User>());
    }

    synchronized public static final GameContextManagement getInstance(ServletContext context) {
        if (instance == null) {
            instance = new GameContextManagement(context);

        }
        return instance;
    }

    public List<User> getAllUsers() {
        return (List<User>) this.context.getAttribute("users");

    }

    public void updateScore(User user) {
        List<User> users = this.getAllUsers();
        for (User it : users) {
            if (it.getLogin().equals(user.getLogin())) {
                it.setBestScore(user.getBestScore());
                break;
            }
        }
    }

    public void insertUser(User user) {
        List<User> userList = (List<User>) context.getAttribute("users");
        userList.add(user);

    }

    public User getUserByLogin(String login) {
        List<User> users = getAllUsers();
        for (User it : users) {
            System.out.println(it);
            if (it.getLogin().equals(login)) {
                return it;
            }
        }

        return null;
    }

}
