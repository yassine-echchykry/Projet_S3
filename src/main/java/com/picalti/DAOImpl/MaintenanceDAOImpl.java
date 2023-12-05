package com.picalti.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.MaintenanceDAO;
import com.picalti.beans.MaintenanceBean;

public class MaintenanceDAOImpl implements MaintenanceDAO {

    private DAOFactory daoFactory;

    public MaintenanceDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(MaintenanceBean maintenance) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "INSERT INTO Maintenance (Bike_Id, Date, Description, Observations, Cause_User_Id, Staff_Id) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = initRequestPrepare(connexion, sql,
                    maintenance.getBikeId(), maintenance.getDate(), maintenance.getDescription(),
                    maintenance.getObservations(), maintenance.getCauseUserId(), maintenance.getStaffId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }

    @Override
    public List<MaintenanceBean> findByBikeId(Long bikeId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<MaintenanceBean> bikeMaintenances = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Maintenance WHERE Bike_Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, bikeId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MaintenanceBean maintenanceBean = map(resultSet);
                bikeMaintenances.add(maintenanceBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return bikeMaintenances;
    }

    @Override
    public List<MaintenanceBean> findByCauseUserId(Long causeUserId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<MaintenanceBean> causeUserMaintenances = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Maintenance WHERE Cause_User_Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, causeUserId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MaintenanceBean maintenanceBean = map(resultSet);
                causeUserMaintenances.add(maintenanceBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return causeUserMaintenances;
    }

    @Override
    public List<MaintenanceBean> findByStaffId(Long staffId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<MaintenanceBean> staffMaintenances = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Maintenance WHERE Staff_Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, staffId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MaintenanceBean maintenanceBean = map(resultSet);
                staffMaintenances.add(maintenanceBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return staffMaintenances;
    }

    @Override
    public List<MaintenanceBean> findAll() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<MaintenanceBean> allMaintenances = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Maintenance";
            preparedStatement = initRequestPrepare(connexion, sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MaintenanceBean maintenanceBean = map(resultSet);
                allMaintenances.add(maintenanceBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return allMaintenances;
    }

    // ...

    private static MaintenanceBean map(ResultSet resultSet) throws SQLException {
        MaintenanceBean maintenanceBean = new MaintenanceBean();
        maintenanceBean.setBikeId(resultSet.getLong("Bike_Id"));
        maintenanceBean.setDate(resultSet.getDate("Date"));
        maintenanceBean.setDescription(resultSet.getString("Description"));
        maintenanceBean.setObservations(resultSet.getString("Observations"));
        maintenanceBean.setCauseUserId(resultSet.getLong("Cause_User_Id"));
        maintenanceBean.setStaffId(resultSet.getLong("Staff_Id"));
        return maintenanceBean;
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
