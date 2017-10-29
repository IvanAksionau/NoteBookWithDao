package by.tc.nb.dao.impl.mysql;

import by.tc.nb.dao.NoteBookDAO;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.dao.impl.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class MySQLNoteBookDAO implements NoteBookDAO {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    @Override
    public void addNote(String note, int userID) throws DAOException {
        String noteDate = LocalDate.now().toString();
        String addNoteQuery =
                "insert into notes (users_id, note, date) values('" + userID + "', '" + note + "', '" + noteDate + "');";
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(addNoteQuery);
            //statement.close(); обязательно ли это, если я закрываю в finally ?
        } catch (InterruptedException | SQLException ex) {
            throw new DAOException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    ConnectionPool.getInstance().returnConnection(connection);
                } catch (InterruptedException | SQLException ex) {
                    throw new DAOException(ex.getMessage());
                }
            }
        }
    }

    @Override
    public void createNewNoteBook(int userID) throws DAOException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("delete from notes where users_id =" + userID + ";");
            //statement.close(); обязательно ли это, если я закрываю в finally ?
        } catch (InterruptedException | SQLException ex) {
            throw new DAOException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    ConnectionPool.getInstance().returnConnection(connection);
                } catch (InterruptedException | SQLException ex) {
                    throw new DAOException(ex.getMessage());
                }
            }
        }
    }

    @Override
    public ArrayList findNoteByContent(String content, int userID) throws DAOException {
        ArrayList<String> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from notes where users_id="
                    + userID + " AND note LIKE '%" + content.trim() + "%';");////if trim needed??
            if (!resultSet.next()) {
                System.out.println("Notes were not founded !");
            } else while (resultSet.next()) {
                list.add(resultSet.getString("note") + "(" + resultSet.getString("date") + ")");
            }
            return list;
            //statement.close(); обязательно ли это, если я закрываю в finally ?
        } catch (InterruptedException | SQLException ex) {
            throw new DAOException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    ConnectionPool.getInstance().returnConnection(connection);
                } catch (InterruptedException | SQLException ex) {
                    throw new DAOException(ex.getMessage());
                }
            }
        }
    }

    @Override
    public ArrayList findNotesByDate(String date, int userID) throws DAOException {
        ArrayList<String> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from notes where users_id="
                    + userID + " AND date='" + date + "';");
            if (!resultSet.next()) {
                System.out.println("Notes were not founded !");
            } else while (resultSet.next()) {
                list.add(resultSet.getString("note") + "(" + resultSet.getString("date") + ")");
            }
            System.out.println("count of founded notes is " + list.size());
            return list;
            //statement.close(); обязательно ли это, если я закрываю в finally ?
        } catch (InterruptedException | SQLException ex) {
            throw new DAOException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    ConnectionPool.getInstance().returnConnection(connection);
                } catch (InterruptedException | SQLException ex) {
                    throw new DAOException(ex.getMessage());
                }
            }
        }
    }

    @Override
    public ArrayList showAllNotes(int userID) throws DAOException {
        ArrayList<String> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from notes where users_id=" + userID + ";");
            if (!resultSet.next()) {
                System.out.println("Notes were not founded !");
            } else while (resultSet.next()) {
                list.add(resultSet.getString("note") + "(" + resultSet.getString("date") + ")");
            }
            return list;
            //statement.close(); обязательно ли это, если я закрываю в finally ?
        } catch (InterruptedException | SQLException ex) {
            throw new DAOException(ex.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                    ConnectionPool.getInstance().returnConnection(connection);
                } catch (InterruptedException | SQLException ex) {
                    throw new DAOException(ex.getMessage());
                }
            }
        }
    }
}
