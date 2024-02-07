package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.activation.DataHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.JDBC.DAO.DAOFactory;
import com.picalti.DAOImpl.Session_DAO_Impl;
import com.picalti.DAOImpl.SoldeDAOImpl;
import com.picalti.DAOImpl.TempSoldeDAOImpl;
import com.picalti.DAOImpl.UserDAOImpl;
import com.picalti.beans.UserBean;

@WebServlet("/AddSolde")
public class AddSoldeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Session_DAO_Impl sessionDAO;
    private UserDAOImpl userDAO;
    private SoldeDAOImpl soldeDAO;
    private TempSoldeDAOImpl tempSoldeDAO;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.sessionDAO = daoFactory.getSessionDAO();
        this.userDAO = daoFactory.getUserDAO();
        this.soldeDAO = daoFactory.getSoldeDAO();
        this.tempSoldeDAO = daoFactory.getTempSoldeDAO();
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
        String solde = getValueFromJsonString(jsonString, "solde");
        System.out.println("token: " + token);
        System.out.println("solde: " + solde);

        // Get user by token
		UserBean user = sessionDAO.getUserByToken(token);

		if (user != null) {
		    // Get the amount to add from the request
		    Integer soldeAdd = Integer.parseInt(solde.trim());

		    // Generate random code
		    String code = generateRandomCode();

		    try {
		        // Store information in tempsolde table
		        tempSoldeDAO.create(token, soldeAdd, code);

		        // Send email with the generated code
		        sendEmail(user.getEmail(), code);
		    } catch (SQLException e) {
		        e.printStackTrace();
		        // Handle database exception
		    }

		    response.setContentType("application/json");
		    PrintWriter out = response.getWriter();
		    out.print("{\"status\": \"success\"}");
		    out.flush();
		} else {
		    // Handle the case where the user is not found for the given token
		    response.setContentType("application/json");
		    PrintWriter out = response.getWriter();
		    out.print("{\"status\": \"error\", \"message\": \"User not found for the given token\"}");
		    out.flush();
		}
    }
    
    // Generate a random 6-digit code
    private String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
    
    private String getValueFromJsonString(String jsonString, String key) {
        int startIndex = jsonString.indexOf("\"" + key + "\":") + key.length() + 3; // 3 accounts for ": and optional space
        int endIndex = jsonString.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = jsonString.indexOf("}", startIndex);
        }

        return jsonString.substring(startIndex, endIndex).replace("\"", "");
    
}

    // Send email using JavaMail API
    private void sendEmail(String recipientEmail, String code) {
        String senderEmail = "echchykryassine.ensias.it.club@gmail.com"; // Your Gmail address
        String senderPassword = "tlpl simm ribm dgsi"; // Your Gmail password

        // Set the host and port for the SMTP server
        String host = "smtp.gmail.com";
        int port = 587;

        // Get the system properties
        Properties properties = System.getProperties();

        // Configure mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", String.valueOf(port));
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        // Get the default Session object
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header
            message.setFrom(new InternetAddress(senderEmail));

            // Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));

            // Set Subject: header field
            message.setSubject("Verification Code for Solde Addition");

            // Now set the actual message
            message.setText("Your verification code is: " + code);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
