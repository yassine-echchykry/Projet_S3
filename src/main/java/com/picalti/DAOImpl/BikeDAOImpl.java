package com.picalti.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.BikeDAO;
import com.picalti.beans.BikeBean;

public class BikeDAOImpl implements BikeDAO {

    private DAOFactory daoFactory;

    public BikeDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(BikeBean bike) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "INSERT INTO Bike (Model, State, Hourly_price, Name, Description, Image_path, Owner_Id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = initRequestPrepare(connexion, sql,
                    bike.getModel(), bike.getState(), bike.getHourlyPrice(), bike.getName(),
                    bike.getDescription(), bike.getImagePath(), bike.getOwnerId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }

    @Override
    public BikeBean findById(Long id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BikeBean bikeBean = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Bike WHERE Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                bikeBean = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return bikeBean;
    }

    @Override
    public List<BikeBean> findAll() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<BikeBean> allBikes = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Bike";
            preparedStatement = initRequestPrepare(connexion, sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BikeBean bikeBean = map(resultSet);
                allBikes.add(bikeBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return allBikes;
    }

    @Override
    public List<BikeBean> findByOwnerId(Long ownerId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<BikeBean> ownerBikes = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Bike WHERE Owner_Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, ownerId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BikeBean bikeBean = map(resultSet);
                ownerBikes.add(bikeBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return ownerBikes;
    }

    @Override
    public void update(BikeBean bike) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "UPDATE Bike SET Model=?, State=?, Hourly_price=?, Name=?, Description=?, Image_path=?, Owner_Id=? WHERE Id=?";
            preparedStatement = initRequestPrepare(connexion, sql,
                    bike.getModel(), bike.getState(), bike.getHourlyPrice(), bike.getName(),
                    bike.getDescription(), bike.getImagePath(), bike.getOwnerId(), bike.getId());

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
            String sql = "DELETE FROM Bike WHERE Id=?";
            preparedStatement = initRequestPrepare(connexion, sql, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }

    // ...

    private static BikeBean map(ResultSet resultSet) throws SQLException {
        BikeBean bikeBean = new BikeBean();
        bikeBean.setId(resultSet.getLong("Id"));
        bikeBean.setModel(resultSet.getString("Model"));
        bikeBean.setState(resultSet.getString("State"));
        bikeBean.setHourlyPrice(resultSet.getDouble("Hourly_price"));
        bikeBean.setName(resultSet.getString("Name"));
        bikeBean.setDescription(resultSet.getString("Description"));
        bikeBean.setImagePath(resultSet.getString("Image_path"));
        bikeBean.setOwnerId(resultSet.getLong("Owner_Id"));
        return bikeBean;
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

          