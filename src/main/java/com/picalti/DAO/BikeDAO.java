package com.picalti.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.BikeBean;

public interface BikeDAO {

	public void create(String model,String state,int hourlyPrice,String name,String description,String imagePath,int ownerId,String type,int speed,String station) throws SQLException;

    BikeBean findById(int id) throws DAOException;

    public ArrayList<BikeBean> all() throws SQLException;

    List<BikeBean> findByOwnerId(int ownerId) throws DAOException;

    void update(BikeBean bike) throws DAOException;

    void delete(int id) throws DAOException;

    // Autres méthodes nécessaires
}
