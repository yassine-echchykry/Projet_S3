package com.picalti.DAOImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.SoldeODAO;
import com.picalti.beans.SoldeOBean;

public class SoldeODAOImpl implements SoldeODAO {

    private DAOFactory daoFactory;

    public SoldeODAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(SoldeOBean soldeO) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "INSERT INTO SoldeO (Amount, Update_date, Owner_Id) VALUES (?, ?, ?)";
            preparedStatement = initRequestPrepare(connexion, sql, soldeO.getAmount(), soldeO.getUpdateDate(), soldeO.getOwner_Id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }

    @Override
    public SoldeOBean findById(Long id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SoldeOBean soldeOBean = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM SoldeO WHERE Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                soldeOBean = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return soldeOBean;
    }

    @Override
    public List<SoldeOBean> findAll() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<SoldeOBean> allSoldeO = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM SoldeO";
            preparedStatement = initRequestPrepare(connexion, sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SoldeOBean soldeOBean = map(resultSet);
                allSoldeO.add(soldeOBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return allSoldeO;
    }

    @Override
    public void update(SoldeOBean soldeO) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "UPDATE SoldeO SET Amount=?, Update_date=?, Owner_Id=? WHERE Id=?";
            preparedStatement = initRequestPrepare(connexion, sql, soldeO.getAmount(), soldeO.getUpdateDate(), soldeO.getOwner_Id(), soldeO.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "DELETE FROM SoldeO WHERE Id=?";
            preparedStatement = initRequestPrepare(connexion, sql, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }

    // ...

    private static SoldeOBean map(ResultSet resultSet) throws SQLException {
        SoldeOBean soldeOBean = new SoldeOBean();
        soldeOBean.setId(resultSet.getLong("Id"));
        soldeOBean.setAmount(resultSet.getBigDecimal("Amount"));
        soldeOBean.setUpdateDate(resultSet.getDate("Update_date"));
        soldeOBean.setOwner_Id(resultSet.getLong("Owner_Id"));
        return soldeOBean;
    }

    private static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }

    // ...
}
