package com.picalti.DAO;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.LocationBean;

public interface LocationDAO {

    void create(String dateD,String hourD,String hourF,String etatD,int userId,int stationSId,int bikeId,String code) throws SQLException;

    LocationBean findById(Long id) throws DAOException;

    List<LocationBean> findByUserId(int userId) throws DAOException;

    List<LocationBean> findByBikeId(int bikeId) throws DAOException;

    List<LocationBean> findAll() throws DAOException;


    void delete(Long id) throws DAOException;

    // Autres méthodes nécessaires
}
