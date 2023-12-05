package com.picalti.DAO;

import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.MaintenanceBean;

public interface MaintenanceDAO {

    void create(MaintenanceBean maintenance) throws DAOException;

    List<MaintenanceBean> findByBikeId(Long bikeId) throws DAOException;

    List<MaintenanceBean> findByCauseUserId(Long causeUserId) throws DAOException;

    List<MaintenanceBean> findByStaffId(Long staffId) throws DAOException;

    List<MaintenanceBean> findAll() throws DAOException;

    // Autres méthodes nécessaires
}
