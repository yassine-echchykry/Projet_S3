package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.beans.BikeBean;
import com.picalti.DAO.BikeDAO;
import com.picalti.DAOImpl.BikeDAOImpl;
/**
 * Servlet implementation class addBike
 */
public class addBike extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public addBike() {
    	// Instantiate a BikeBean with the necessary data
    	BikeBean newBike = new BikeBean();
    	newBike.setModel("Mountain Bike");
    	newBike.setState("Available");
    	newBike.setHourlyPrice(10.0);
    	newBike.setName("Bike1");
    	newBike.setDescription("A sturdy mountain bike");
    	newBike.setImagePath("/images/bike1.jpg");
    	newBike.setOwnerId(1L); // Assuming owner ID is 1

    	try {
    		DAOFactory DAOFactory;
    		DAOFactory = com.JDBC.DAO.DAOFactory.getInstance();
    	    // Create a new Bike using the DAO
    	    BikeDAO bikeDAO = new BikeDAOImpl(DAOFactory);
    	    bikeDAO.create(newBike);
    	    System.out.println("Bike created successfully!");
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
