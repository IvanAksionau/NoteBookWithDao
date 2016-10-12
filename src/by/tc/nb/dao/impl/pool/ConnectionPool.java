package by.tc.nb.dao.impl.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
	private static final ConnectionPool instance = new ConnectionPool();

	private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(5); // количество всех соединений

	private ConnectionPool(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		String url = "jdbc:mysql://localhost:3306/notebook_db?useSSL=false";
		String login = "root";
		String password = "root";


		for(int i = 0; i< 5; i++){
			try {
				pool.add(DriverManager.getConnection(url, login, password));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection getConnection() throws InterruptedException{
		return pool.take();
	}

	public void returnConnection(Connection connection) throws SQLException, InterruptedException{
		if(connection == null){
			return;
		}
		connection.setAutoCommit(true);
		connection.setReadOnly(true);
		pool.put(connection);
	}


	public void closePool(){ //завершения работы всего приложения

		for(Connection con : pool){
			try {
				con.close();
			} catch (SQLException e) {
			}
		}

	}

	public static ConnectionPool getInstance(){
		return instance;
	}
}
