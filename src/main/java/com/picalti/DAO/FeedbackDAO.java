package com.picalti.DAO;

import java.util.List;

import com.JDBC.DAO.DAOException;
import com.picalti.beans.FeedbackBean;

public interface FeedbackDAO {

    void create(FeedbackBean feedback) throws DAOException;

    List<FeedbackBean> findByFromUserId(Long fromUserId) throws DAOException;

    List<FeedbackBean> findByToOwnerId(Long toOwnerId) throws DAOException;

    List<FeedbackBean> findAll() throws DAOException;

    // Autres méthodes nécessaires
}
