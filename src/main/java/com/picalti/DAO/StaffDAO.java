package com.picalti.DAO;

import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.StaffBean;

public interface StaffDAO {

    void create(StaffBean staff) throws DAOException;

    StaffBean findById(Long id) throws DAOException;

    List<StaffBean> findAll() throws DAOException;

    void update(StaffBean staff) throws DAOException;

    void delete(Long id) throws DAOException;

    // Autres méthodes nécessaires
}
