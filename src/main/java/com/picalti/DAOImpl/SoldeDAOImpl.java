package com.picalti.DAOImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
    public void create(int amount,Date updateDate,int userId) throws DAOException, SQLException {
    	
    	    Connection conn = daoFactory.getConnection();
            String sql = "INSERT INTO Solde (Amount, Update_date, User_Id) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setInt(1, amount);
            statement.setDate(2, updateDate);
    		statement.setInt(3, userId);
    		
    		statement.execute();
    		
    		statement.close();
    		conn.close();

        }
    
    public void updateAmount(int amount, int userId) throws DAOException, SQLException {
        Connection conn = daoFactory.getConnection();
        String sql = "UPDATE Solde SET Amount = Amount + ? WHERE User_Id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, amount);
        statement.setInt(2, userId);

        statement.executeUpdate();

        statement.close();
        conn.close();
    }


    @Override
    public SoldeBean findById(int id) throws DAOException {
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
    public SoldeBean findByUserId(int userId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SoldeBean soldeBean = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Solde WHERE User_Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                soldeBean = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // Fermez les ressources (resultSet, preparedStatement, connexion) ici
        }

        return soldeBean;
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
        soldeBean.setId(resultSet.getInt("Id"));
        soldeBean.setAmount(resultSet.getInt("Amount"));
        soldeBean.setUpdateDate(resultSet.getDate("Update_date"));
        soldeBean.setUserId(resultSet.getInt("User_Id"));
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
