package com.picalti.DAO;

import java.sql.SQLException;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.UserBean;



public interface userDAO {


	public void register(String fullName, String cin, Integer age, String sexe, String email, String password, String tele) throws SQLException;;
	
	public UserBean login(String email, String password) throws SQLException;
	
    UserBean findById(int id) throws DAOException;

    UserBean findByEmail(String email) throws DAOException;

    List<UserBean> findAll() throws DAOException;

	

    // Autres méthodes nécessaires
}

