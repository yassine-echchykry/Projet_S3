package com.picalti.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.SoldeDAO;
import com.picalti.beans.SoldeBean;

public class SoldeDAOImpl implements SoldeDAO {

    private DAOFactory daoFactory;

    public SoldeDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(SoldeBean solde) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "INSERT INTO Solde (Amount, Update_date, User_Id) VALUES (?, ?, ?)";
            preparedStatement = initRequestPrepare(connexion, sql,
                    solde.getAmount(), solde.getUpdateDate(), solde.getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }

    @Override
    public SoldeBean findById(Long id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SoldeBean soldeBean = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Solde WHERE Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                soldeBean = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return soldeBean;
    }

    @Override
    public List<SoldeBean> findByUserId(Long userId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<SoldeBean> userSoldes = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Solde WHERE User_Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SoldeBean soldeBean = map(resultSet);
                userSoldes.add(soldeBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return userSoldes;
    }

    @Override
    public List<SoldeBean> findAll() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<SoldeBean> allSoldes = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Solde";
            preparedStatement = initRequestPrepare(connexion, sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                SoldeBean soldeBean = map(resultSet);
                allSoldes.add(soldeBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return allSoldes;
    }

    @Override
    public void update(SoldeBean solde) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "UPDATE Solde SET Amount=?, Update_date=?, User_Id=? WHERE Id=?";
            preparedStatement = initRequestPrepare(connexion, sql,
                    solde.getAmount(), solde.getUpdateDate(), solde.getUserId(), solde.getId());

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
            String sql = "DELETE FROM Solde WHERE Id=?";
            preparedStatement = initRequestPrepare(connexion, sql, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }

    // ...

    private static SoldeBean map(ResultSet resultSet) throws SQLException {
        SoldeBean soldeBean = new SoldeBean();
        soldeBean.setId(resultSet.getLong("Id"));
        soldeBean.setAmount(resultSet.getDouble("Amount"));
        soldeBean.setUpdateDate(resultSet.getDate("Update_date"));
        soldeBean.setUserId(resultSet.getLong("User_Id"));
        return soldeBean;
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
