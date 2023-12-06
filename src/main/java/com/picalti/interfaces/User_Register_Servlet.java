package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.picalti.beans.UserBean;
import com.picalti.DAO.userDAO;
import com.picalti.DAOImpl.UserDAOImpl;
import com.JDBC.DAO.*;

/**
 * Servlet implementation class User_Register_Servlet
 */
@WebServlet("/register")
public class User_Register_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private userDAO userDAO;
    private DAOFactory daoFactory; // Ajoutez un attribut pour stocker l'instance de DAOFactory

    public User_Register_Servlet() {
        // Initialisez DAOFactory
        this.daoFactory = DAOFactory.getInstance();
        // Initialisez userDAO avec DAOFactory
        this.userDAO = new UserDAOImpl(daoFactory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérez les paramètres du formulaire
        String fullName = request.getParameter("fullName");
        String cin = request.getParameter("cin");
        String ageStr = request.getParameter("age");
        Integer age = Integer.parseInt(ageStr);
        String sexe = request.getParameter("sexe");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String tele = request.getParameter("tele");
        // ... autres paramètres

        // Créez un objet UserBean avec les données du formulaire
        UserBean newUser = new UserBean();
        newUser.setFullName(fullName);
        newUser.setCin(cin);
        newUser.setAge(age);
        newUser.setSexe(sexe);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setTele(tele);
        // ... configurez les autres propriétés

        // Appelez la méthode create de votre UserDAO pour insérer l'utilisateur dans la base de données
        try {
            userDAO.create(newUser);
            // Redirigez l'utilisateur vers une page de succès
            response.sendRedirect("registration-success.jsp");
            
        } catch (DAOException e) {
            // Gérez l'exception, redirigez l'utilisateur vers une page d'échec, etc.
            
        }
    }
}
