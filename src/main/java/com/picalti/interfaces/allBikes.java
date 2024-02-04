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

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.beans.BikeBean;



import com.picalti.DAO.BikeDAO;
import com.picalti.DAOImpl.BikeDAOImpl;
/**
 * Servlet implementation class allBikes
 */
@WebServlet("/allbike")
public class allBikes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BikeDAOImpl bikeDAO;
	
	
	public void init() throws ServletException {
	    System.out.println("Init method called!");  // Ajoutez cette ligne pour le débogage
	    DAOFactory daoFactory = DAOFactory.getInstance();
	    this.bikeDAO = daoFactory.getBikeDAO();
	   
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "POST");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	System.out.println("Naaaaah");
    	
    	try {
            // Retrieve all posts from the database
            ArrayList<BikeBean> bikes = bikeDAO.all();
           

            // Set the content type of the response
            response.setContentType("application/json");

            // Get the PrintWriter object to write the response
            PrintWriter out = response.getWriter();

         // Write the JSON response manually (without using Gson)
            out.print("[");
            for (int i = 0; i < bikes.size(); i++) {
                BikeBean post = bikes.get(i);
                
                User_Bean user = userDAO.getUserById(post.getUserID());
                String user1=user.getName();
                out.print("{");
                out.print("\"id\": " + post.getID() + ",");
                out.print("\"userId\": " + post.getUserID() + ",");
                out.print("\"title\": \"" + post.getTitle() + "\",");
                out.print("\"auther\": \"" + user1  + "\",");
                out.print("\"description\": \"" + post.getDescription() + "\",");
                out.print("\"img\": \"" + post.getImage() + "\",");
                out.print("\"date\": \"" + post.getDate() + "\"");
                out.print("}");
                
                // Add a comma if it's not the last element
                if (i < posts.size() - 1) {
                    out.print(",");
                }
                
            }
            out.print("]");

            out.flush();
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching posts");
        }
    }
}
