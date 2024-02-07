package com.picalti.DAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.SoldeBean;

public interface SoldeDAO {

    void create(int amount,Date updateDate,int userId) throws SQLException;
    
    public void updateAmount(int amount, int userId) throws SQLException;

    SoldeBean findById(int id) throws DAOException;

    SoldeBean findByUserId(int userId) throws DAOException;

    List<SoldeBean> findAll() throws DAOException;

    void update(SoldeBean solde) throws DAOException;

    void delete(Long id) throws DAOException;

    // Autres méthodes nécessaires
}
