package com.picalti.DAO;

import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.StationBean;

public interface StationDAO {

    void create(StationBean station) throws DAOException;

    StationBean findById(Long id) throws DAOException;

    List<StationBean> findAll() throws DAOException;

    void update(StationBean station) throws DAOException;

    void delete(Long id) throws DAOException;

    // Autres méthodes nécessaires
}
