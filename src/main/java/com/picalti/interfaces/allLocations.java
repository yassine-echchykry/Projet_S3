package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.JDBC.DAO.DAOFactory;

import com.picalti.beans.LocationBean;
import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.LocationDAO;
import com.picalti.DAOImpl.LocationDAOImpl;
/**
 * Servlet implementation class allLocations
 */
public class allLocations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public allLocations() {
        super();
        try {
        	DAOFactory DAOFactory;
    		DAOFactory = com.JDBC.DAO.DAOFactory.getInstance();
            // Retrieve all locations
            LocationDAO locationDAO = new LocationDAOImpl(DAOFactory);
            List<LocationBean> allLocations = locationDAO.findAll();

            // Print or process the retrieved locations
            for (LocationBean location : allLocations) {
                System.out.println("Location ID: " + location.getId() + ", Start Date: " + location.getDateD());
                // Add more details as needed
            }

            // Retrieve locations by user ID
            List<LocationBean> userLocations = locationDAO.findByUserId(1L); // Replace with the user ID
            // Process userLocations as needed

            // Retrieve locations by start station ID
            List<LocationBean> startStationLocations = locationDAO.findByStationSId(2L); // Replace with the start station ID
            // Process startStationLocations as needed

            // Retrieve locations by end station ID
            List<LocationBean> endStationLocations = locationDAO.findByStationFId(3L); // Replace with the end station ID
            // Process endStationLocations as needed

            // Retrieve locations by bike ID
            List<LocationBean> bikeLocations = locationDAO.findByBikeId(4L); // Replace with the bike ID
            // Process bikeLocations as needed

            // Retrieve a location by ID
            LocationBean specificLocation = locationDAO.findById(1L); // Replace with the location ID
            // Process specificLocation as needed

        } catch (DAOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
