package com.picalti.DAOImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.userDAO;
import com.picalti.beans.UserBean;


public class UserDAOImpl implements userDAO {

	private DAOFactory          daoFactory;

    public UserDAOImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
	@Override
	public void create(UserBean person) throws DAOException {
		// TODO Auto-generated method stub
		
	}
	private static UserBean map( ResultSet resultSet ) throws SQLException {
		UserBean userBean = new UserBean();
		userBean.setId( resultSet.getLong( "id" ) );
		userBean.setNom( resultSet.getString( "nom" ) );
		userBean.setEmail( resultSet.getString( "email" ) );
		userBean.setPrenom( resultSet.getString( "prenom" ) );
		userBean.setPassword( resultSet.getString( "password" ));
		System.out.println(userBean.getPassword());
		return userBean;
		}
	public static PreparedStatement initRequestPrepare( Connection connexion, String sql, Object... objets ) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement( sql );
	    for ( int i = 0; i < objets.length; i++ ) {
	        preparedStatement.setObject( i + 1, objets[i] );
	    }
	    return preparedStatement;
	}

	@Override
	public UserBean find(String nom) throws DAOException {
		// TODO Auto-generated method stub
		
		
		    final String SQL_SELECT_PAR_NOM = "SELECT id, nom, prenom,email FROM person WHERE nom = ?";
		    Connection connexion = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;
		    UserBean personBean = null;

		    try {
		        /* Récupération d'une connexion depuis la Factory */
		        connexion = daoFactory.getConnection();
		        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_PAR_NOM, nom );
		        resultSet = preparedStatement.executeQuery();
		        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
		        if ( resultSet.next() ) {
		            personBean = map( resultSet );
		        }
		    } catch ( SQLException e ) {
		        throw new DAOException( e );
		    } finally {
		        //ClosingAll( resultSet, preparedStatement, connexion );
		    }

		    return personBean;
		}
	
	@Override
	public List<UserBean> all() throws DAOException {
		
		final String SQL_SELECT_ALL = "SELECT id, nom, prenom,email,age FROM person";
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    UserBean personBean = null;
	    List<UserBean> allPerson = new ArrayList<UserBean>();
	    
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_ALL);
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        while ( resultSet.next() ) {
	            personBean = map( resultSet );
	            allPerson.add(personBean);
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

		
		return allPerson;
	}
}

