package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

import com.JDBC.DAO.DAOFactory;
import com.picalti.DAOImpl.Session_DAO_Impl;



/**
 * Servlet implementation class User_Infos_Servlet
 */
public class User_Infos_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Session_DAO_Impl sessionDAO;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.sessionDAO = daoFactory.getSessionDAO();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "POST");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        String token = stringBuilder.toString();
        String username= sessionDAO.getUsernameByToken(token);
		
		// Envoyer le résultat directement dans la réponse HTTP en tant que texte brut
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().write(username);
		
    
	}

}
