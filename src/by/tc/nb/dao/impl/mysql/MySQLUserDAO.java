package by.tc.nb.dao.impl.mysql;

import by.tc.nb.dao.UserDAO;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.dao.impl.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLUserDAO implements UserDAO {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet result;
    private static String checkLoginQuery;

    @Override
    public int logging(String login, String password) throws DAOException {
        checkLoginQuery = "select * from users where (login, pass)=('" + login + "', '" + password + "');";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(checkLoginQuery);
            if (result.next()) {
                return result.getInt("users_id");
            } else {
                System.out.println("There are no user with current data!");
                return 0;
            }
        } catch (InterruptedException | SQLException ex) {
            throw new DAOException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    ConnectionPool.getInstance().returnConnection(connection);
                } catch (InterruptedException | SQLException ex) {
                    throw new DAOException(ex);
                }
            }
        }
    }

    @Override
    public boolean registration(String login, String password) throws DAOException {
        checkLoginQuery = "select * from users where login = '" + login + "';";
        String registrationQuery = "insert into users (login, pass) values ('" + login + "', '" + password + "');";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(checkLoginQuery);
            if (!result.next()) {
                statement.executeUpdate(registrationQuery);
                return true;
            } else {
                return false;
            }
        } catch (InterruptedException | SQLException ex) {
            throw new DAOException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    ConnectionPool.getInstance().returnConnection(connection);
                } catch (InterruptedException | SQLException ex) {
                    throw new DAOException(ex);
                }
            }
        }
    }
}
