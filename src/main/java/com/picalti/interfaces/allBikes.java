package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOFactory;
import com.fasterxml.jackson.databind.*;
import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.beans.BikeBean;
import com.picalti.beans.OwnerBean;


import com.picalti.DAO.BikeDAO;
import com.picalti.DAOImpl.*;
/**
 * Servlet implementation class allBikes
 */
@WebServlet("/allbike")
public class allBikes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BikeDAOImpl bikeDAO;
	private OwnerDAOImpl ownerDAO;
	
	
	public void init() throws ServletException {
	    DAOFactory daoFactory = DAOFactory.getInstance();
	    this.bikeDAO = daoFactory.getBikeDAO();
	    this.ownerDAO = daoFactory.getOwnerDAO();
	   
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        // Code qui peut lever une exception (mais pas nécessairement une SQLException)
	        // ...
	        
	        ArrayList<BikeBean> bikes = bikeDAO.all();

	        // Set the content type of the response
	        response.setContentType("application/json");

	        // Get the PrintWriter object to write the response
	        PrintWriter out = response.getWriter();

	        out.print("[");

	        // Iterate through bikes and generate JSON objects
	        for (int i = 0; i < bikes.size(); i++) {
	            BikeBean bike = bikes.get(i);
	            
	            OwnerBean owner = ownerDAO.findById(bike.getOwnerId());
                String owner_name=owner.getFullName();

	            out.print("{");
	            out.print("\"id\": " + bike.getId() + ",");
	            out.print("\"model\": \"" + bike.getModel() + "\",");
	            out.print("\"state\": \"" + bike.getState() + "\",");
	            out.print("\"hourlyPrice\": " + bike.getHourlyPrice() + ",");
	            out.print("\"name\": \"" + bike.getName() + "\",");
	            out.print("\"description\": \"" + bike.getDescription() + "\",");
	            out.print("\"imagePath\": \"" + bike.getImagePath() + "\",");
	            out.print("\"owner\": " + owner_name + ",");
	            out.print("\"type\": \"" + bike.getType() + "\",");
	            out.print("\"speed\": " + bike.getSpeed() + ",");
	            out.print("\"station\": \"" + bike.getStation() + "\"");
	            out.print("}");
	            System.out.println("type: " + bike.getType());

	            // Add a comma if it's not the last element
	            if (i < bikes.size() - 1) {
	                out.print(",");
	            }
	        }

	        out.print("]");

	        out.flush();

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching bikes");
	    }
	}

}