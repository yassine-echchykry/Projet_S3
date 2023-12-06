package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.JDBC.DAO.DAOFactory;

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.beans.BikeBean;
import com.picalti.DAO.BikeDAO;
import com.picalti.DAOImpl.BikeDAOImpl;
/**
 * Servlet implementation class allBikes
 */
public class allBikes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public allBikes() {
        super();
        try {
        	DAOFactory DAOFactory;
    		DAOFactory = com.JDBC.DAO.DAOFactory.getInstance();
            // Retrieve all bikes
            BikeDAO bikeDAO = new BikeDAOImpl(DAOFactory);
            List<BikeBean> allBikes = bikeDAO.findAll();

            // Print or process the retrieved bikes
            for (BikeBean bike : allBikes) {
                System.out.println("Bike ID: " + bike.getId() + ", Model: " + bike.getModel());
                // Add more details as needed
            }

            // Retrieve bikes by owner ID
            List<BikeBean> ownerBikes = bikeDAO.findByOwnerId(1L); // Assuming owner ID is 1
            // Process ownerBikes as needed

            // Retrieve a bike by ID
            BikeBean specificBike = bikeDAO.findById(1L); // Assuming bike ID is 1
            // Process specificBike as needed

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
