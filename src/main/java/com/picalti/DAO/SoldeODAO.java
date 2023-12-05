package com.picalti.DAO;

import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.SoldeOBean;

public interface SoldeODAO {

    void create(SoldeOBean soldeO) throws DAOException;

    SoldeOBean findById(Long id) throws DAOException;

    List<SoldeOBean> findAll() throws DAOException;

    void update(SoldeOBean soldeO) throws DAOException;

    void delete(Long id) throws DAOException;

    // Autres méthodes nécessaires
}
