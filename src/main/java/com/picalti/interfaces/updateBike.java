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
 * Servlet implementation class updateBike
 */
public class updateBike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateBike() {
        super();
        try {
            // Retrieve a bike by ID
        	DAOFactory DAOFactory;
    		DAOFactory = com.JDBC.DAO.DAOFactory.getInstance();
            BikeDAO bikeDAO = new BikeDAOImpl(DAOFactory);
            BikeBean bikeToUpdate = bikeDAO.findById(1L); // Assuming bike ID is 1

            // Update bike properties
            bikeToUpdate.setModel("Updated Model");
            bikeToUpdate.setDescription("Updated description");
            // Update other properties as needed

            // Perform the update
            bikeDAO.update(bikeToUpdate);
            System.out.println("Bike updated successfully!");

        } catch (DAOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        // TODO Auto-generated constructor stub
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
