package invoice.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class DAO {
/*
 * Базовый класс для непосредственной работы с БД.
 */

	// константные значения по умолчанию

	private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/invoice";
	private static final String DEFAULT_USER = "mysql";
	private static final String DEFAULT_PASSWORD = "123456";

	// адрес БД
	private final String url;
	private final String username;
	private final String password;

	public DAO() {

		this(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
	}

//TODO: Custom exception
	public DAO(String url, String username, String password) {

		this.url = url;
		this.username = username;
		this.password = password;
	}


	public int executeUpdate(String queryStr) throws Exception {

		int result;

		try(Connection connection =
				DriverManager.getConnection(url, username, password)) {

			Statement statement = connection.createStatement();
			result = statement.executeUpdate(queryStr);
			connection.close();

		} catch(SQLException e) {
//TODO: Custom exception
			e.printStackTrace();
			throw new Exception();
		}

		return result;
	}


	public ResultSet executeQuery(String queryStr) throws Exception {
		/*
		 * отправляет SQL-запрос queryStr в базу,
		 * возвращает результат в resultSet
		 */

		ResultSet resultSet;

		// говорят, в таком варианте connection закроется автоматически в случае исключения
		try(Connection connection =
				DriverManager.getConnection(url, username, password)) {

			PreparedStatement statement = connection.prepareStatement(queryStr);
			resultSet = statement.executeQuery();
			connection.close();

		} catch(SQLException e) {
//TODO: Custom exception
			e.printStackTrace();
			throw new Exception();
		}

		return resultSet;
	}
}
