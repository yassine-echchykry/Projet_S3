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
 // ...

    @Override
    public UserBean getUserByToken(String token) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            conn = daoFactory.getConnection();
            String SQL = "SELECT user.Id AS userid, user.Full_name AS username, user.CIN AS usercin, user.Age AS userage, user.Sexe AS usersexe, user.Email AS useremail, user.Password AS userpass, user.Tele AS usertele " +
                    "FROM session " +
                    "JOIN user ON session.user_id = user.Id " +
                    "WHERE session.token = ? ";
            statement = initRequestPrepare(conn, SQL, token.trim());
            resultSet = statement.executeQuery();

            if (resultSet.next()) 	
            {
                // Map the ResultSet to a UserBean
                UserBean userBean = new UserBean();
                userBean.setId(resultSet.getInt("userid"));
                userBean.setFullName(resultSet.getString("username"));
                userBean.setCin(resultSet.getString("usercin"));
                userBean.setAge(resultSet.getInt("userage"));
                userBean.setSexe(resultSet.getString("usersexe"));
                userBean.setEmail(resultSet.getString("useremail"));
                userBean.setPassword(resultSet.getString("userpass"));
                userBean.setTele(resultSet.getString("usertele"));
                System.out.println("User found with token: " + token);
                return userBean;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the finally block
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("No user found with token: " + token);
        return null; // Return null if no session found with the given token
    }

    private static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }
    // ...
}