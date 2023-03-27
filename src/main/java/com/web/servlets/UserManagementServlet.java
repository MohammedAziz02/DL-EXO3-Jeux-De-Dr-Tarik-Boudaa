package com.web.servlets;

import com.bo.Message;
import com.bo.User;
import com.web.helpers.IGameDataManagement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Tarik BOUDAA
 * 
 *         Ecole Nationale des Sciences Appliquées Al Hoceima
 *
 */

@WebServlet(name = "inscription",value = "/inscription")
public class UserManagementServlet extends HttpServlet {

	private final Logger LOGGER = Logger.getLogger(getClass());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String createUserFormPage = "/WEB-INF/vues/pages/formInscription.jsp";

		ServletContext cntx = getServletContext();

		// On affiche le formulaire d'ajout
		if (request.getParameter("create") != null) {
			LOGGER.debug("Redirection vers le formulaire de création des Users");
			cntx.getRequestDispatcher(createUserFormPage).forward(request, response);
			// fin
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errorPage = "/WEB-INF/vues/pages/error.jsp";
		String okPage = "/WEB-INF/vues/pages/operationOK.jsp";
		String loginForm = "/WEB-INF/vues/pages/loginForm.jsp";
		ServletContext cntx = getServletContext();

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		User user = new User(nom, prenom, login, password, 0, 0);

		// on reçoit le gameContext qui est stocké dans le context sous le  nom "gameData".
		// il est automatiquement détecté si on va utiliser la base de données ou la tous simplement le context
		// dans la classe ApplicationInitializer dans le package listeners.
		IGameDataManagement gameContext = (IGameDataManagement) getServletContext().getAttribute("gameData");

		// on crée une liste de messages pour passer au vue.
		List<Message> messages = new ArrayList<Message>();

		// On teste si un User existe avec le login choisit

		if (gameContext.getUserByLogin(login) != null) {

			// Ajouter des message d'erreur dans la requete
			messages.add(new Message("Login existe déjà", Message.WARN));
			request.setAttribute("messages", messages);

			cntx.getRequestDispatcher(errorPage).forward(request, response);
			return;

		}

		// On ajoute l'User
		gameContext.insertUser(user);

		// On redirige vers la page login avec un message de succès
		messages.add(new Message("User correctement ajouté", Message.INFO));
		// On enregistre la liste des messages comme attributs de requete
		request.setAttribute("messages", messages);

		// On redirige vers la vue
		cntx.getRequestDispatcher(loginForm).forward(request, response);

	}

}