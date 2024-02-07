package com.picalti.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.OwnerDAO;
import com.picalti.beans.OwnerBean;
import com.picalti.beans.UserBean;

public class OwnerDAOImpl implements OwnerDAO {

    private DAOFactory daoFactory;

    public OwnerDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void register(String fullName, String cin, Integer age, String sexe, String email, String password, String tele) throws SQLException {
        Connection connexion = daoFactory.getConnection();
        String SQL = "INSERT INTO owner (Full_name, CIN, Age, Sexe, Email, Password, Tele) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connexion.prepareStatement(SQL);

        statement.setString(1, fullName);
        statement.setString(2, cin);
        statement.setInt(3, age);  // Use setInt instead of setString for Integer
        statement.setString(4, sexe);
        statement.setString(5, email);
        statement.setString(6, password);
        statement.setString(7, tele);

        try {
            statement.execute();
        } finally {
            statement.close();
            connexion.close();
        }
    }
    
    private static UserBean getBean(ResultSet res) throws SQLException {
    	UserBean bean = new UserBean();
    	
    	bean.setId(res.getInt("id"));
    	
    	bean.setEmail(res.getString("email"));
    	
    	return bean;
    }
    
    public UserBean login(String email, String password) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "SELECT * FROM owner WHERE email = ? AND password = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setString(1, email);
    	statement.setString(2, password);
    	
    	ResultSet res = statement.executeQuery();
    	UserBean bean = res.next() ? getBean(res) : null;
    	
    	res.close();
    	statement.close();
    	conn.close();
    	
    	return bean;
    }

    @Override
    public OwnerBean findById(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        OwnerBean ownerBean = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Owner WHERE Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ownerBean = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return ownerBean;
    }

    @Override
    public OwnerBean findByEmail(String email) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        OwnerBean ownerBean = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Owner WHERE Email = ?";
            preparedStatement = initRequestPrepare(connexion, sql, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ownerBean = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return ownerBean;
    }

    @Override
    public List<OwnerBean> findAll() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<OwnerBean> allOwners = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Owner";
            preparedStatement = initRequestPrepare(connexion, sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OwnerBean ownerBean = map(resultSet);
                allOwners.add(ownerBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return allOwners;
    }
    @Override
    public void update(OwnerBean owner) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
    
        try {
            connexion = daoFactory.getConnection();
            String sql = "UPDATE Owner SET Full_name=?, CIN=?, Age=?, Sexe=?, Email=?, Password=?, Tele=? WHERE Id=?";
            preparedStatement = initRequestPrepare(connexion, sql,
                    owner.getFullName(), owner.getCin(), owner.getAge(), owner.getSexe(), owner.getEmail(),
                    owner.getPassword(), owner.getTele(), owner.getId());

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
            String sql = "DELETE FROM Owner WHERE Id=?";
            preparedStatement = initRequestPrepare(connexion, sql, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }


    // Ajoutez d'autres méthodes si nécessaire

    private static OwnerBean map(ResultSet resultSet) throws SQLException {
        OwnerBean ownerBean = new OwnerBean();
        ownerBean.setId(resultSet.getInt("Id"));
        ownerBean.setFullName(resultSet.getString("Full_name"));
        ownerBean.setCin(resultSet.getString("CIN"));
        ownerBean.setAge(resultSet.getInt("Age"));
        ownerBean.setSexe(resultSet.getString("Sexe"));
        ownerBean.setEmail(resultSet.getString("Email"));
        ownerBean.setPassword(resultSet.getString("Password"));
        ownerBean.setTele(resultSet.getString("Tele"));
        return ownerBean;
    }

    private static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }

    // Ajoutez la méthode de fermeture des ressources (ClosingAll) ici
}
