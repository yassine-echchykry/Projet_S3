package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.JDBC.DAO.DAOFactory;
import com.picalti.DAOImpl.Session_DAO_Impl;
import com.picalti.DAOImpl.SoldeDAOImpl;
import com.picalti.DAOImpl.UserDAOImpl;
import com.picalti.beans.SoldeBean;
import com.picalti.beans.UserBean;



/**
 * Servlet implementation class User_Infos_Servlet
 */
@WebServlet("/GetUser")
public class User_Infos_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Session_DAO_Impl sessionDAO;
    private UserDAOImpl userDAO;
    private SoldeDAOImpl soldeDAO;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.sessionDAO = daoFactory.getSessionDAO();
        this.userDAO = daoFactory.getUserDAO();
        this.soldeDAO = daoFactory.getSoldeDAO();
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
        System.out.println("token: " + token);
        // avoir l utilisateur par token
        UserBean user= sessionDAO.getUserByToken(token);
        
        // avoir le solde par token 
        SoldeBean solde= soldeDAO.findByUserId(user.getId());
		
        response.setContentType("application/json");

        // Get the PrintWriter object to write the response
        PrintWriter out = response.getWriter();

        // Créez le JSON manuellement
        out.print("[");
        out.print("{");
        out.print("\"id\": " + user.getId() + ",");
        out.print("\"fullName\": \"" + user.getFullName() + "\",");
        out.print("\"cin\": \"" + user.getCin() + "\",");
        out.print("\"age\": " + user.getAge() + ",");
        out.print("\"sexe\": \"" + user.getSexe() + "\",");
        out.print("\"email\": \"" + user.getEmail() + "\",");
        out.print("\"password\": \"" + user.getPassword() + "\",");
        out.print("\"tele\": \"" + user.getTele() + "\",");
        out.print("\"solde\": " + solde.getAmount()); // Add this line to include the "Amount" field
        out.print("}");
        out.print("]");


        out.flush();
		
    
	}

}
