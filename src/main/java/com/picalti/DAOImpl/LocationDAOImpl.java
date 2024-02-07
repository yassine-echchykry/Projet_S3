package com.picalti.DAOImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
    public void create(String dateD,String hourD,String hourF,String etatD,int userId,int stationSId,int bikeId,String code) throws SQLException {
    	Connection conn = daoFactory.getConnection();

    	String sql = "INSERT INTO Location (DateD, HourD, HourF, EtatD, User_Id, StationF_Id, Bike_Id, code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    	PreparedStatement statement = conn.prepareStatement(sql);
    	statement.setString(1, dateD);
    	statement.setString(2, hourD);
    	statement.setString(3, hourF);
    	statement.setString(4, etatD);
    	statement.setInt(5, userId);
    	statement.setInt(6, stationSId);
    	statement.setInt(7, bikeId);
    	statement.setString(8, code);

    		
    		statement.execute();
    		
    		statement.close();
    		conn.close();
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
    public List<LocationBean> findByUserId(int userId) throws DAOException {
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
    public List<LocationBean> findByBikeId(int bikeId) throws DAOException {
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
        locationBean.setId(resultSet.getInt("Id"));
        locationBean.setDateD(resultSet.getDate("DateD"));
        locationBean.setDateF(resultSet.getDate("DateF"));
        locationBean.setHourD(resultSet.getTime("HourD"));
        locationBean.setHourF(resultSet.getTime("HourF"));
        locationBean.setEtatD(resultSet.getString("EtatD"));
        locationBean.setEtatF(resultSet.getString("EtatF"));
        locationBean.setUserId(resultSet.getInt("User_Id"));
        locationBean.setStationSId(resultSet.getInt("StationS_Id"));
        locationBean.setStationFId(resultSet.getInt("StationF_Id"));
        locationBean.setBikeId(resultSet.getInt("Bike_Id"));
        locationBean.setCode(resultSet.getString("code"));
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
