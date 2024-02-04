// Session_DAO_Impl.java
package com.picalti.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.JDBC.DAO.DAOFactory;
import com.picalti.beans.*;
import com.picalti.DAO.*;

public class Session_DAO_Impl implements Session_DAO {
    private DAOFactory daoFactory;

    public Session_DAO_Impl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void insertSession(Session_Bean sessionBean) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = daoFactory.getConnection();
            String SQL = "INSERT INTO session (user_id, token) VALUES (?, ?)";
            statement = conn.prepareStatement(SQL);

            statement.setInt(1, sessionBean.getUserId());
            statement.setString(2, sessionBean.getToken());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();}
       
    }
    @Override
    public String getUsernameByToken(String token) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = daoFactory.getConnection();
            String SQL = "SELECT users.name AS username " +
                    "FROM session " +
                    "JOIN users ON session.user_id = users.id " +
                    "WHERE session.token = ?";
            statement = conn.prepareStatement(SQL);

            statement.setString(1, token);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Return the username directly
                return resultSet.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources (Connection, PreparedStatement, ResultSet) in the finally block
            // to ensure they are always closed, even if an exception occurs.
            // Note: You might want to handle these exceptions more gracefully in a production environment.
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null; // Return null if no session found with the given token
    }
    
    
    @Override
    public String getUserIdByToken(String token) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = daoFactory.getConnection();
            String SQL = "SELECT user_id AS userid " +
                    "FROM session " +
                    
                    "WHERE session.token = ?";
            statement = conn.prepareStatement(SQL);

            statement.setString(1, token);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Return the username directly
                return resultSet.getString("userid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources (Connection, PreparedStatement, ResultSet) in the finally block
            // to ensure they are always closed, even if an exception occurs.
            // Note: You might want to handle these exceptions more gracefully in a production environment.
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null; // Return null if no session found with the given token
    }
}
