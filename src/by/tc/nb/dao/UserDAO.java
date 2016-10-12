package by.tc.nb.dao;

import by.tc.nb.dao.exception.DAOException;

public interface UserDAO {
	int logging(String login, String password) throws DAOException;
	boolean registration(String login, String password) throws DAOException;
}
