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

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.beans.BikeBean;



import com.picalti.DAO.BikeDAO;
import com.picalti.DAOImpl.BikeDAOImpl;
/**
 * Servlet implementation class addBike
 */
@WebServlet("/addbike")
public class addBike extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BikeDAOImpl bikeDAO;
	
	public void init() throws ServletException {
	    System.out.println("Init method called!");  // Ajoutez cette ligne pour le débogage
	    DAOFactory daoFactory = DAOFactory.getInstance();
	    this.bikeDAO = daoFactory.getBikeDAO();
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	    System.out.printf("Name: %s%n", jsonString);

	    String model = getValueFromJsonString(jsonString, "authToken");
	    String state = getValueFromJsonString(jsonString, "title");
	    String hourlyPrice = getValueFromJsonString(jsonString, "description");
	    String name = getValueFromJsonString(jsonString, "name");
	    String description = getValueFromJsonString(jsonString, "description");
	    String imagePath = getValueFromJsonString(jsonString, "imagePath");
	    String ownerId = getValueFromJsonString(jsonString, "ownerId");
	    String type = getValueFromJsonString(jsonString, "type");
	    String speed = getValueFromJsonString(jsonString, "speed");
	    String station = getValueFromJsonString(jsonString, "station");
	    
	 
	    
	    
	    try {
	    	bikeDAO.create( model, state, Integer.parseInt(hourlyPrice), name, description, imagePath, Integer.parseInt(ownerId),type,Integer.parseInt(speed),station);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	   

	    // Read the file content into a string (you may want to convert it to Base64)
	   

	   

	    // Your further processing logic here...

	    
	        // Perform any necessary backend logic with the received data
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
