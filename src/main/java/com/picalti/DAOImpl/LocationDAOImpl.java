package com.picalti.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.LocationDAO;
import com.picalti.beans.LocationBean;

public class LocationDAOImpl implements LocationDAO {

    private DAOFactory daoFactory;

    public LocationDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(LocationBean location) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "INSERT INTO Location (DateD, DateF, HourD, HourF, Cost, EtatD, EtatF, User_Id, StationS_Id, StationF_Id, Bike_Id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = initRequestPrepare(connexion, sql,
                    location.getDateD(), location.getDateF(), location.getHourD(), location.getHourF(),
                    location.getCost(), location.getEtatD(), location.getEtatF(),
                    location.getUserId(), location.getStationSId(), location.getStationFId(), location.getBikeId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }

    @Override
    public LocationBean findById(Long id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        LocationBean locationBean = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Location WHERE Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                locationBean = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return locationBean;
    }

    @Override
    public List<LocationBean> findByUserId(Long userId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<LocationBean> userLocations = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Location WHERE User_Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LocationBean locationBean = map(resultSet);
                userLocations.add(locationBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return userLocations;
    }

    @Override
    public List<LocationBean> findByStationSId(Long stationSId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<LocationBean> stationSLocations = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Location WHERE StationS_Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, stationSId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LocationBean locationBean = map(resultSet);
                stationSLocations.add(locationBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return stationSLocations;
    }

    @Override
    public List<LocationBean> findByStationFId(Long stationFId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<LocationBean> stationFLocations = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Location WHERE StationF_Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, stationFId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LocationBean locationBean = map(resultSet);
                stationFLocations.add(locationBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return stationFLocations;
    }

    @Override
    public List<LocationBean> findByBikeId(Long bikeId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<LocationBean> bikeLocations = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Location WHERE Bike_Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, bikeId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LocationBean locationBean = map(resultSet);
                bikeLocations.add(locationBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return bikeLocations;
    }

    @Override
    public List<LocationBean> findAll() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<LocationBean> allLocations = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Location";
            preparedStatement = initRequestPrepare(connexion, sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LocationBean locationBean = map(resultSet);
                allLocations.add(locationBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return allLocations;
    }

    @Override
    public void update(LocationBean location) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "UPDATE Location SET DateD=?, DateF=?, HourD=?, HourF=?, Cost=?, EtatD=?, EtatF=?, User_Id=?, StationS_Id=?, StationF_Id=?, Bike_Id=? WHERE Id=?";
            preparedStatement = initRequestPrepare(connexion, sql,
                    location.getDateD(), location.getDateF(), location.getHourD(), location.getHourF(),
                    location.getCost(), location.getEtatD(), location.getEtatF(),
                    location.getUserId(), location.getStationSId(), location.getStationFId(), location.getBikeId(),
                    location.getId());

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
            String sql = "DELETE FROM Location WHERE Id=?";
            preparedStatement = initRequestPrepare(connexion, sql, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }

    // ...

    private static LocationBean map(ResultSet resultSet) throws SQLException {
        LocationBean locationBean = new LocationBean();
        locationBean.setId(resultSet.getLong("Id"));
        locationBean.setDateD(resultSet.getDate("DateD"));
        locationBean.setDateF(resultSet.getDate("DateF"));
        locationBean.setHourD(resultSet.getTime("HourD"));
        locationBean.setHourF(resultSet.getTime("HourF"));
        locationBean.setCost(resultSet.getDouble("Cost"));
        locationBean.setEtatD(resultSet.getString("EtatD"));
        locationBean.setEtatF(resultSet.getString("EtatF"));
        locationBean.setUserId(resultSet.getLong("User_Id"));
        locationBean.setStationSId(resultSet.getLong("StationS_Id"));
        locationBean.setStationFId(resultSet.getLong("StationF_Id"));
        locationBean.setBikeId(resultSet.getLong("Bike_Id"));
        return locationBean;
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
