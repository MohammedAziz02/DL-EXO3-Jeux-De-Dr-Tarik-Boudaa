package com.web.dao;

import com.bo.User;
import com.web.helpers.GameException;
import com.web.helpers.IGameDataManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GameDatabaseManagement implements IGameDataManagement {

	private static GameDatabaseManagement instance;

	private String connexionString = "jdbc:mysql://localhost/gamedb?user=root&password=";

	private GameDatabaseManagement() throws GameException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			throw new GameException(e);
		}
	}
	synchronized public static final GameDatabaseManagement getInstance() throws GameException {
		if (instance == null) {
			instance = new GameDatabaseManagement();
		}
		return instance;
	}

	public List<User> getAllUsers() throws GameException {
		Connection con = null;
		List<User> list = new ArrayList<User>();

		try {
			con = DriverManager.getConnection(connexionString);

			try {
				Statement st = con.createStatement();

				ResultSet rs = st.executeQuery("select * from User");

				while (rs.next()) {
					User u = new User(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"),
							rs.getString("password"), rs.getDouble("score"), rs.getDouble("bestScore"));
					list.add(u);

				}
			} finally {
				if (con != null) {
					con.close();
				}
			}
		} catch (SQLException e) {
			throw new GameException(e);
		}
		return list;

	}

	public void updateScore(User user) throws GameException {

		Connection con = null;
		try {
			con = DriverManager.getConnection(connexionString);

			try {

				PreparedStatement pstmt = con.prepareStatement("UPDATE User set bestScore = ? where login=?");
				pstmt.setDouble(1, user.getBestScore());
				pstmt.setString(2, user.getLogin());
				pstmt.executeUpdate();

			} finally {
				if (con != null) {
					con.close();
				}
			}
		} catch (SQLException e) {
			throw new GameException(e);
		}

	}

	public void insertUser(User user) throws GameException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(connexionString);

			try {

				PreparedStatement pstmt = con.prepareStatement(
						"insert into User ( nom,  prenom,  login,  password,  score,  bestScore) values ( ?,  ?,  ?,  ?,  ?,  ?)");
				pstmt.setString(1, user.getNom());
				pstmt.setString(2, user.getPrenom());
				pstmt.setString(3, user.getLogin());
				pstmt.setString(4, user.getPassword());
				pstmt.setDouble(5, user.getScore());
				pstmt.setDouble(6, user.getBestScore());

				pstmt.executeUpdate();

			} finally {
				if (con != null) {
					con.close();
				}
			}
		} catch (SQLException e) {
			throw new GameException(e);
		}

	}

	public User getUserByLogin(String login) throws GameException {
		Connection con = null;
		List<User> list = new ArrayList<User>();

		try {
			con = DriverManager.getConnection(connexionString);

			try {


				PreparedStatement pstmt = con.prepareStatement("select * from User where login =?");
				pstmt.setString(1, login);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					User u = new User(rs.getString("nom"), rs.getString("prenom"), rs.getString("login"),
							rs.getString("password"), rs.getDouble("score"), rs.getDouble("bestScore"));
					list.add(u);

				}

			} finally {
				if (con != null) {
					con.close();
				}
			}
		} catch (SQLException e) {
			throw new GameException(e);
		}
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}
