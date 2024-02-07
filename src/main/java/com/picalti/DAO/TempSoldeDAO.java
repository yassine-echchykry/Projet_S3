package com.picalti.DAO;

import java.sql.SQLException;


import com.JDBC.DAO.DAOException;
import com.picalti.beans.TempSoldeBean;

public interface TempSoldeDAO {

    void create(String token, int amount, String code) throws DAOException, SQLException;

    TempSoldeBean findByToken(String token) throws DAOException;

    // Other necessary methods
}
