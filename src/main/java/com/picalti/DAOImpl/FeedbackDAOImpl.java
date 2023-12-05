package com.picalti.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import com.picalti.DAO.FeedbackDAO;
import com.picalti.beans.FeedbackBean;

public class FeedbackDAOImpl implements FeedbackDAO {

    private DAOFactory daoFactory;

    public FeedbackDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(FeedbackBean feedback) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            String sql = "INSERT INTO Feedback (Comment, Declaration, From_User_Id, To_Owner_Id) VALUES (?, ?, ?, ?)";
            preparedStatement = initRequestPrepare(connexion, sql,
                    feedback.getComment(), feedback.getDeclaration(), feedback.getFromUserId(), feedback.getToOwnerId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connexion);
        }
    }

    @Override
    public List<FeedbackBean> findByFromUserId(Long fromUserId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FeedbackBean> userFeedbacks = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Feedback WHERE From_User_Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, fromUserId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FeedbackBean feedbackBean = map(resultSet);
                userFeedbacks.add(feedbackBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return userFeedbacks;
    }

    @Override
    public List<FeedbackBean> findByToOwnerId(Long toOwnerId) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FeedbackBean> ownerFeedbacks = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Feedback WHERE To_Owner_Id = ?";
            preparedStatement = initRequestPrepare(connexion, sql, toOwnerId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FeedbackBean feedbackBean = map(resultSet);
                ownerFeedbacks.add(feedbackBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return ownerFeedbacks;
    }

    @Override
    public List<FeedbackBean> findAll() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FeedbackBean> allFeedbacks = new ArrayList<>();

        try {
            connexion = daoFactory.getConnection();
            String sql = "SELECT * FROM Feedback";
            preparedStatement = initRequestPrepare(connexion, sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FeedbackBean feedbackBean = map(resultSet);
                allFeedbacks.add(feedbackBean);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(resultSet, preparedStatement, connexion);
        }

        return allFeedbacks;
    }

    // ...

    private static FeedbackBean map(ResultSet resultSet) throws SQLException {
        FeedbackBean feedbackBean = new FeedbackBean();
        feedbackBean.setComment(resultSet.getString("Comment"));
        feedbackBean.setDeclaration(resultSet.getString("Declaration"));
        feedbackBean.setFromUserId(resultSet.getLong("From_User_Id"));
        feedbackBean.setToOwnerId(resultSet.getLong("To_Owner_Id"));
        return feedbackBean;
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
