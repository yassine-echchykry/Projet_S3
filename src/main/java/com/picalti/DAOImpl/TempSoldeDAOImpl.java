package com.picalti.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.TempSoldeDAO;
import com.picalti.beans.TempSoldeBean;

public class TempSoldeDAOImpl implements TempSoldeDAO {

    private DAOFactory daoFactory;

    public TempSoldeDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(String token, int amount, String code) throws DAOException, SQLException {
        Connection conn = daoFactory.getConnection();
        String sql = "INSERT INTO tempsolde (token, amount, code) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, token);
        statement.setInt(2, amount);
        statement.setString(3, code);

        statement.execute();

        statement.close();
        conn.close();
    }

    @Override
    public TempSoldeBean findByToken(String token) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TempSoldeBean tempSoldeBean = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM tempsolde WHERE token = ?";
            preparedStatement = initRequestPrepare(connexion, sql, token);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tempSoldeBean = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return tempSoldeBean;
    }

    // Other methods...

    private static TempSoldeBean map(ResultSet resultSet) throws SQLException {
        TempSoldeBean tempSoldeBean = new TempSoldeBean();
        tempSoldeBean.setId(resultSet.getInt("Id"));
        tempSoldeBean.setToken(resultSet.getString("token"));
        tempSoldeBean.setAmount(resultSet.getInt("amount"));
        tempSoldeBean.setCode(resultSet.getString("code"));
        return tempSoldeBean;
    }

    private static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }
}
