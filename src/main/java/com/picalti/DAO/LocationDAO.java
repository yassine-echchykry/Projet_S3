package com.picalti.DAO;

import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.LocationBean;

public interface LocationDAO {

    void create(LocationBean location) throws DAOException;

    LocationBean findById(Long id) throws DAOException;

    List<LocationBean> findByUserId(Long userId) throws DAOException;

    List<LocationBean> findByStationSId(Long stationSId) throws DAOException;

    List<LocationBean> findByStationFId(Long stationFId) throws DAOException;

    List<LocationBean> findByBikeId(Long bikeId) throws DAOException;

    List<LocationBean> findAll() throws DAOException;

    void update(LocationBean location) throws DAOException;

    void delete(Long id) throws DAOException;

    // Autres méthodes nécessaires
}
