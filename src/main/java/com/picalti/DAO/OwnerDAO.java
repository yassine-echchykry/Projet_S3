package com.picalti.DAO;


import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.OwnerBean;

public interface OwnerDAO {

    void create(OwnerBean owner) throws DAOException;

    OwnerBean findById(Long id) throws DAOException;

    OwnerBean findByEmail(String email) throws DAOException;

    List<OwnerBean> findAll() throws DAOException;
    
    void update(OwnerBean owner) throws DAOException;

    void delete(Long id) throws DAOException;

    // Autres méthodes nécessaires
}

