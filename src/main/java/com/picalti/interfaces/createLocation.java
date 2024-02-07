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
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import com.picalti.beans.BikeBean;
import com.picalti.beans.SoldeBean;
import com.picalti.beans.UserBean;
import com.picalti.DAO.BikeDAO;
import com.picalti.DAO.LocationDAO;
import com.picalti.DAO.SoldeDAO;
import com.picalti.DAO.userDAO;
import com.picalti.DAOImpl.UserDAOImpl;
import com.JDBC.DAO.*;

/**
 * Servlet implementation class User_Register_Servlet
 */
@WebServlet("/CreateLocation")
public class createLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private userDAO userDAO;
    private DAOFactory daoFactory; 
    private LocationDAO locationDAO ;
    private BikeDAO bikeDAO ;
    private SoldeDAO soldeDAO ;
    // Ajoutez un attribut pour stocker l'instance de DAOFactory
    
    public void init() throws ServletException {
	    DAOFactory daoFactory = DAOFactory.getInstance();
	    this.userDAO = daoFactory.getUserDAO();
	    this.locationDAO = daoFactory.getLocationDAO();
	    this.bikeDAO = daoFactory.getBikeDAO();
	    this.soldeDAO = daoFactory.getSoldeDAO();
	   
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
        
        

        
        String dateD = getValueFromJsonString(jsonString, "dateD");
        String hourD = getValueFromJsonString(jsonString, "hourD");
        String hourF = getValueFromJsonString(jsonString, "hourF");
        String etatD = getValueFromJsonString(jsonString, "etatD");
        int userId = Integer.parseInt(getValueFromJsonString(jsonString, "userId"));
        int stationSId = Integer.parseInt(getValueFromJsonString(jsonString, "stationSId"));
        int bikeId = Integer.parseInt(getValueFromJsonString(jsonString, "bikeId"));
        //...
        System.out.println("dateD"+dateD);
        
        try {
            String code = generateRandomCode();

            // Récupérer le vélo associé à la location
            BikeBean bike = bikeDAO.findById(bikeId);

            // Calculer le coût total de la location
            double totalCost = calculateTotalCost(bike.getHourlyPrice(), hourD, hourF);

            // Récupérer l'utilisateur associé à la location
            SoldeBean solde = soldeDAO.findByUserId(userId);

            // Vérifier si le solde de l'utilisateur est suffisant
            if (solde.getAmount() >= totalCost) {
                // Mettre à jour le solde de l'utilisateur
                soldeDAO.updateAmount((int) -totalCost, userId);

                // Créer la location après avoir vérifié le solde
                locationDAO.create(dateD, hourD, hourF, etatD, userId, stationSId, bikeId, code);

                // Autres opérations si nécessaire

                // Envoyer une réponse JSON de succès
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print("{\"status\": \"success\"}");
                out.flush();
            } else {
                // Envoyer un message indiquant que le solde est insuffisant
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print("{\"status\": \"insufficient_balance\", \"message\": \"Solde insuffisant\"}");
                out.flush();
            }
		        } catch (SQLException e) {
		            // Gérer les exceptions SQL
		            e.printStackTrace();
		            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la création de la location");
		        }
		    }
        
		    private String generateRandomCode() {
		        Random random = new Random();
		        int code = 100000 + random.nextInt(900000);
		        return String.valueOf(code);
		    }
		    private double calculateTotalCost(int hourlyPrice, String hourD, String hourF) {
		        // Séparez les heures, minutes et secondes en utilisant le caractère ":"
		        String[] startTimeArray = hourD.split(":");
		        String[] endTimeArray = hourF.split(":");

		        // Convertissez les parties d'heures en entiers
		        int startHour = Integer.parseInt(startTimeArray[0].trim());
		        int endHour = Integer.parseInt(endTimeArray[0].trim());

		        // Calculez la différence d'heures entre startTime et endTime
		        int hoursDifference = endHour - startHour;

		        // Calculez le coût total
		        double totalCost = hourlyPrice * hoursDifference;

		        return totalCost;
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
