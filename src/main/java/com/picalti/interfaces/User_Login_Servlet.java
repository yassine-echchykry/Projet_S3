package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.UUID;

import com.JDBC.DAO.DAOFactory;

import com.picalti.DAO.*;
import com.picalti.beans.*;

import com.picalti.DAOImpl.*;

/**
 * Servlet implementation class User_Login_Servlet
 */
@WebServlet("/login")
public class User_Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAOImpl userDAO;
	private Session_DAO_Impl sessionDAO;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
		this.sessionDAO = daoFactory.getSessionDAO();
		
	} 
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

    	
    	StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }
        
        // Parse JSON data
        String jsonString = requestBody.toString();
        
        System.out.printf("Name: %s%n", jsonString);

        String email = getValueFromJsonString(jsonString, "email");
        String password = getValueFromJsonString(jsonString, "password");
        
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        // Perform any necessary backend logic with the received data

        // Send a response back to the client
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try {
			UserBean userBean = userDAO.login(email, password);
			
			if(userBean == null) {
				request.setAttribute("form_error", "Unvalid credentails");
            
				return;
			}
			String token = generateToken();
			response.setContentType("text/plain"); // Set the content type to plain text
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(token);
			
			// Store the session information in the Session table
            Session_Bean sessionBean = new Session_Bean();
            sessionBean.setUserId(userBean.getId());
            sessionBean.setToken(token);
            sessionDAO.insertSession(sessionBean);

			
		} catch (SQLException e) {
			e.printStackTrace();
			
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
    
        
    }
	
	
	private String getValueFromJsonString(String jsonString, String key) {
        int startIndex = jsonString.indexOf("\"" + key + "\":") + key.length() + 3; // 3 accounts for ": and optional space
        int endIndex = jsonString.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = jsonString.indexOf("}", startIndex);
        }

        return jsonString.substring(startIndex, endIndex).replace("\"", "");
    }
		
		
	private String generateToken() {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();
        
        // Convert the UUID to a string and remove hyphens
        String token = uuid.toString().replace("-", "");
        
        return token;
    }
	

    

}

