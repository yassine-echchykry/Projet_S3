package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import com.JDBC.DAO.DAOFactory;
import com.JDBC.DAO.DAOFactory;

import com.picalti.beans.LocationBean;
import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.LocationDAO;
import com.picalti.DAOImpl.LocationDAOImpl;
/**
 * Servlet implementation class updateLocation
 */
public class updateLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateLocation() {
        super();
        // TODO Auto-generated constructor stub
        try {
            // Retrieve a location by ID
        	DAOFactory DAOFactory;
    		DAOFactory = com.JDBC.DAO.DAOFactory.getInstance();
            LocationDAO locationDAO = new LocationDAOImpl(DAOFactory);
            LocationBean locationToUpdate = locationDAO.findById(1L); // Replace with the location ID

            // Update location properties
            locationToUpdate.setDateD(Date.valueOf("2023-02-01")); // Replace with your desired date
            locationToUpdate.setDateF(Date.valueOf("2023-02-10")); // Replace with your desired date
            // Update other properties as needed

            // Perform the update
            locationDAO.update(locationToUpdate);
            System.out.println("Location updated successfully!");

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
