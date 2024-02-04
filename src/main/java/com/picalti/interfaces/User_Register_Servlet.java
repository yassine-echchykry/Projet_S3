package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

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
    private DAOFactory daoFactory; 
    // Ajoutez un attribut pour stocker l'instance de DAOFactory
    
    public User_Register_Servlet() {
        // Initialisez DAOFactory
        this.daoFactory = DAOFactory.getInstance();
        // Initialisez userDAO avec DAOFactory
        this.userDAO = new UserDAOImpl(daoFactory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérez les paramètres du formulaire
    	
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "POST");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	 // Read the JSON data from the request body
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }
        
     // Parse JSON data
        String jsonString = requestBody.toString();
        
        
        String fullName = getValueFromJsonString(jsonString, "fullName");
        String cin = getValueFromJsonString(jsonString, "cin");
        String ageStr = getValueFromJsonString(jsonString, "age");
        Integer age = Integer.parseInt(ageStr);
        String sexe = getValueFromJsonString(jsonString, "sexe");
        String email = getValueFromJsonString(jsonString, "email");
        String password = getValueFromJsonString(jsonString, "password");
        String tele = getValueFromJsonString(jsonString, "tele");
        // ... autres paramètres

        

        try {
			userDAO.register(fullName, cin, age,sexe,email,password,tele);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Perform any necessary backend logic with the received data

		// Send a response back to the client
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		out.print("{\"status\": \"success\"}");
		out.flush();
        }
        
        private String getValueFromJsonString(String jsonString, String key) {
            int startIndex = jsonString.indexOf("\"" + key + "\":") + key.length() + 3; // 3 accounts for ": and optional space
            int endIndex = jsonString.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = jsonString.indexOf("}", startIndex);
            }

            return jsonString.substring(startIndex, endIndex).replace("\"", "");
        
    }
}
