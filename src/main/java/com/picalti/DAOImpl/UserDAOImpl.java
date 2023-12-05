package com.picalti.DAOImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.userDAO;
import com.picalti.beans.UserBean;


//... (Autres imports)

public class UserDAOImpl implements userDAO {

 private DAOFactory daoFactory;

 public UserDAOImpl(DAOFactory daoFactory) {
     this.daoFactory = daoFactory;
 }

 @Override
 public void create(UserBean user) throws DAOException {
     Connection connexion = null;
     PreparedStatement preparedStatement = null;

     try {
         connexion = daoFactory.getConnection();
         String sql = "INSERT INTO User (Full_name, CIN, Age, Sexe, Email, Password, Tele) VALUES (?, ?, ?, ?, ?, ?, ?)";
         preparedStatement = initRequestPrepare(connexion, sql,
                 user.getFullName(), user.getCin(), user.getAge(), user.getSexe(), user.getEmail(),
                 user.getPassword(), user.getTele());

         preparedStatement.executeUpdate();
     } catch (SQLException e) {
         throw new DAOException(e);
     } finally {
         // ClosingAll(preparedStatement, connexion);
     }
 }

 @Override
 public UserBean findById(Long id) throws DAOException {
     Connection connexion = null;
     PreparedStatement preparedStatement = null;
     ResultSet resultSet = null;
     UserBean userBean = null;

     try {
         connexion = daoFactory.getConnection();
         String sql = "SELECT * FROM User WHERE Id = ?";
         preparedStatement = initRequestPrepare(connexion, sql, id);
         resultSet = preparedStatement.executeQuery();

         if (resultSet.next()) {
             userBean = map(resultSet);
         }
     } catch (SQLException e) {
         throw new DAOException(e);
     } finally {
         // ClosingAll(resultSet, preparedStatement, connexion);
     }

     return userBean;
 }

 @Override
 public UserBean findByEmail(String email) throws DAOException {
     Connection connexion = null;
     PreparedStatement preparedStatement = null;
     ResultSet resultSet = null;
     UserBean userBean = null;

     try {
         connexion = daoFactory.getConnection();
         String sql = "SELECT * FROM User WHERE Email = ?";
         preparedStatement = initRequestPrepare(connexion, sql, email);
         resultSet = preparedStatement.executeQuery();

         if (resultSet.next()) {
             userBean = map(resultSet);
         }
     } catch (SQLException e) {
         throw new DAOException(e);
     } finally {
         // ClosingAll(resultSet, preparedStatement, connexion);
     }

     return userBean;
 }

 @Override
 public List<UserBean> findAll() throws DAOException {
     Connection connexion = null;
     PreparedStatement preparedStatement = null;
     ResultSet resultSet = null;
     List<UserBean> allUsers = new ArrayList<>();

     try {
         connexion = daoFactory.getConnection();
         String sql = "SELECT * FROM User";
         preparedStatement = initRequestPrepare(connexion, sql);
         resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {
             UserBean userBean = map(resultSet);
             allUsers.add(userBean);
         }
     } catch (SQLException e) {
         throw new DAOException(e);
     } finally {
         // ClosingAll(resultSet, preparedStatement, connexion);
     }

     return allUsers;
 }

 // Ajoutez d'autres méthodes si nécessaire

 private static UserBean map(ResultSet resultSet) throws SQLException {
     UserBean userBean = new UserBean();
     userBean.setId(resultSet.getLong("Id"));
     userBean.setFullName(resultSet.getString("Full_name"));
     userBean.setCin(resultSet.getString("CIN"));
     userBean.setAge(resultSet.getInt("Age"));
     userBean.setSexe(resultSet.getString("Sexe"));
     userBean.setEmail(resultSet.getString("Email"));
     userBean.setPassword(resultSet.getString("Password"));
     userBean.setTele(resultSet.getString("Tele"));
     return userBean;
 }

 private static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objects) throws SQLException {
     PreparedStatement preparedStatement = connexion.prepareStatement(sql);
     for (int i = 0; i < objects.length; i++) {
         preparedStatement.setObject(i + 1, objects[i]);
     }
     return preparedStatement;
 }

 // Ajoutez la méthode de fermeture des ressources (ClosingAll) ici
}
