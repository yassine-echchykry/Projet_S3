package com.picalti.DAO;

import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.BikeBean;

public interface BikeDAO {

    void create(BikeBean bike) throws DAOException;

    BikeBean findById(Long id) throws DAOException;

    List<BikeBean> findAll() throws DAOException;

    List<BikeBean> findByOwnerId(Long ownerId) throws DAOException;

    void update(BikeBean bike) throws DAOException;

    void delete(Long id) throws DAOException;

    // Autres méthodes nécessaires
}
