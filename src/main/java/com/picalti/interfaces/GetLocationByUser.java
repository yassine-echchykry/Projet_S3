package com.picalti.interfaces;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.JDBC.DAO.DAOFactory;
import com.JDBC.DAO.DAOException;
import com.picalti.beans.LocationBean;
import com.picalti.DAO.LocationDAO;
import com.picalti.DAOImpl.LocationDAOImpl;

/**
 * Servlet implementation class UserLocations
 */
@WebServlet("/userlocations")
public class GetLocationByUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private LocationDAO locationDAO;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.locationDAO = daoFactory.getLocationDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        String userId = stringBuilder.toString();
        System.out.println("userId: " + userId);
        try {
            // Récupérez l'ID de l'utilisateur à partir des paramètres de la requête
            int userid1=Integer.parseInt(userId);

            // Récupérez les locations de l'utilisateur avec l'ID correspondant
            List<LocationBean> userLocations = locationDAO.findByUserId(userid1);

            // Set the content type of the response
            response.setContentType("application/json");

            // Get the PrintWriter object to write the response
            PrintWriter out = response.getWriter();

            out.print("[");

            // Iterate through user locations and generate JSON objects
            for (int i = 0; i < userLocations.size(); i++) {
                LocationBean location = userLocations.get(i);

                out.print("{");
                out.print("\"id\": " + location.getId() + ",");
                out.print("\"dateD\": \"" + location.getDateD() + "\",");
                out.print("\"hourD\": \"" + location.getHourD() + "\",");
                out.print("\"hourF\": \"" + location.getHourF() + "\",");
                out.print("\"etatD\": \"" + location.getEtatD() + "\",");
                out.print("\"code\": \"" + location.getCode() + "\",");
                out.print("\"userId\": " + location.getUserId() + ",");
                out.print("\"stationSId\": " + location.getStationSId() + ",");
                out.print("\"bikeId\": " + location.getBikeId());
                out.print("}");

                // Add a comma if it's not the last element
                if (i < userLocations.size() - 1) {
                    out.print(",");
                }
            }

            out.print("]");

            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching user locations");
        }
    }
}
