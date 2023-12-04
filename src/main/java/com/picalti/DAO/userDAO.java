package com.picalti.DAO;

import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.UserBean;

public interface userDAO {
	 void create( UserBean user ) throws DAOException;

	 UserBean find( String nom ) throws DAOException;
	 public List<UserBean> all()throws DAOException;
	}

