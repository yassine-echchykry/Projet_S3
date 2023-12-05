package com.picalti.DAO;

import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.SoldeBean;

public interface SoldeDAO {

    void create(SoldeBean solde) throws DAOException;

    SoldeBean findById(Long id) throws DAOException;

    List<SoldeBean> findByUserId(Long userId) throws DAOException;

    List<SoldeBean> findAll() throws DAOException;

    void update(SoldeBean solde) throws DAOException;

    void delete(Long id) throws DAOException;

    // Autres méthodes nécessaires
}
