package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import com.picalti.beans.LocationBean;
import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.LocationDAO;
import com.picalti.DAOImpl.LocationDAOImpl;
/**
 * Servlet implementation class createLocation
 */
public class createLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createLocation() {
        super();
        // TODO Auto-generated constructor stub
     // Instantiate a LocationBean with the necessary data
        LocationBean newLocation = new LocationBean();
        newLocation.setDateD(Date.valueOf("2023-01-01")); // Replace with your desired date
        newLocation.setDateF(Date.valueOf("2023-01-10")); // Replace with your desired date
        newLocation.setHourD(Time.valueOf("10:00:00")); // Replace with your desired time
        newLocation.setHourF(Time.valueOf("12:00:00")); // Replace with your desired time
        newLocation.setCost(20.0); // Replace with your desired cost
        newLocation.setEtatD("Start State");
        newLocation.setEtatF("End State");
        newLocation.setUserId(1L); // Replace with the user ID
        newLocation.setStationSId(2L); // Replace with the start station ID
        newLocation.setStationFId(3L); // Replace with the end station ID
        newLocation.setBikeId(4L); // Replace with the bike ID

        try {
            // Create a new Location using the DAO
        	DAOFactory DAOFactory;
    		DAOFactory = com.JDBC.DAO.DAOFactory.getInstance();
            LocationDAO locationDAO = new LocationDAOImpl(DAOFactory);
            locationDAO.create(newLocation);
            System.out.println("Location created successfully!");
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
