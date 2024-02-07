package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.SoldeDAO;
import com.picalti.DAOImpl.Session_DAO_Impl;
import com.picalti.DAOImpl.SoldeDAOImpl;
import com.picalti.DAOImpl.TempSoldeDAOImpl;
import com.picalti.DAOImpl.UserDAOImpl;
import com.picalti.beans.TempSoldeBean;
import com.picalti.beans.UserBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/VerifyCode")
public class ConfirmSoldeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TempSoldeDAOImpl tempSoldeDAO;
    private SoldeDAOImpl soldeDAO;
    private Session_DAO_Impl sessionDAO;

    public void init() throws ServletException {
    	DAOFactory daoFactory = DAOFactory.getInstance();
        this.tempSoldeDAO = daoFactory.getTempSoldeDAO(); 
        this.soldeDAO = daoFactory.getSoldeDAO();
        this.sessionDAO = daoFactory.getSessionDAO();
        // Remplacez cela avec l'injection de dépendance appropriée
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }

        String jsonString = requestBody.toString();

        String token = getValueFromJsonString(jsonString, "token");
        String userCode = getValueFromJsonString(jsonString, "code");
        System.out.println("token: " + token);
        System.out.println("userCode: " + userCode);

        // Get the stored code from tempsolde table
        TempSoldeBean tempsolde = tempSoldeDAO.findByToken(token);
        System.out.println("userCodeini: " +tempsolde.getCode());
        // Compare the codes
        if (userCode.trim().equals(tempsolde.getCode().trim())) {
            // Codes match, add solde to the main solde table
            int soldeToAdd = tempsolde.getAmount();

            try {
                // Add solde to the main solde table
            	UserBean user =sessionDAO.getUserByToken(token);
                soldeDAO.updateAmount(soldeToAdd, user.getId());

                // Remove the entry from tempsolde table

          
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle database exception
                sendResponse(response, "error", "Database error");
                return;
            }

            sendResponse(response, "success", "Solde added successfully");
        } else {
            // Codes do not match
            sendResponse(response, "error", "Verification code is incorrect");
        }
    }

    private void sendResponse(HttpServletResponse response, String status, String message) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{\"status\": \"" + status + "\", \"message\": \"" + message + "\"}");
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
