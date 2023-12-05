package com.picalti.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.StationDAO;
import com.picalti.beans.StationBean;

public class StationDAOImpl implements StationDAO {

    private DAOFactory daoFactory;

    public StationDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(StationBean station) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "INSERT INTO Station (Address, Name) VALUES (?, ?)";
            preparedStatement = initRequestPrepare(connexion, sql, station.getAddress(), station.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }

    @Override
    public StationBean findById(Long id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StationBean stationBean = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Station WHERE Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                stationBean = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return stationBean;
    }

    @Override
    public List<StationBean> findAll() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<StationBean> allStations = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Station";
            preparedStatement = initRequestPrepare(connexion, sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StationBean stationBean = map(resultSet);
                allStations.add(stationBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return allStations;
    }

    @Override
    public void update(StationBean station) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "UPDATE Station SET Address=?, Name=? WHERE Id=?";
            preparedStatement = initRequestPrepare(connexion, sql, station.getAddress(), station.getName(), station.getId());

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
            String sql = "DELETE FROM Station WHERE Id=?";
            preparedStatement = initRequestPrepare(connexion, sql, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }

    // ...

    private static StationBean map(ResultSet resultSet) throws SQLException {
        StationBean stationBean = new StationBean();
        stationBean.setId(resultSet.getLong("Id"));
        stationBean.setAddress(resultSet.getString("Address"));
        stationBean.setName(resultSet.getString("Name"));
        return stationBean;
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
