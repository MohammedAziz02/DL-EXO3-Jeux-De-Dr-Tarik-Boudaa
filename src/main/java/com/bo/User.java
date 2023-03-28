package com.bo;

import java.util.ArrayList;
import java.util.List;
public class User {
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private double score;
    private double bestScore;
    private List<Integer> listplayedde;
    public User() {
    }
    public User(String nom, String prenom, String login, String password, double score, double bestScore) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.score = score;
        this.bestScore = bestScore;
        this.listplayedde=new ArrayList<>();
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public double getBestScore() {
        return bestScore;
    }
    public void setBestScore(double bestScore) {
        this.bestScore = bestScore;
    }
    public List<Integer> getplayedde(){
        return  this.listplayedde;
    }
    public void resetplayedde(){
        this.listplayedde.clear();
    }
    public void addplayedde(int n){
        this.listplayedde.add(n);
    }
    @Override
    public String toString() {
        return "User [ nom=" + nom + ", prenom=" + prenom + ", login="
                + login + ", password=" + password + ", score=" + score + ", bestScore=" + bestScore
                +  "]";
    }
}
