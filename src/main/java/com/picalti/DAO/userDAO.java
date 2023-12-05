package com.picalti.DAO;

import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.UserBean;

public interface userDAO {

    void create(UserBean user) throws DAOException;

    UserBean findById(Long id) throws DAOException;

    UserBean findByEmail(String email) throws DAOException;

    List<UserBean> findAll() throws DAOException;

    // Autres méthodes nécessaires
}

