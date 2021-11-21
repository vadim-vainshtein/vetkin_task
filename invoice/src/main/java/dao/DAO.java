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
 * Имеет методы для выполнения запросов executeUpdate() и executeQuery()
 */

// TODO: обработка исключений и логирование - ?..

	// адрес БД, пользователь, пароль
	private final String url;
	private final String username;
	private final String password;


	// значения по умолчанию
	private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/invoice";
	private static final String DEFAULT_USER = "mysql";
	private static final String DEFAULT_PASSWORD = "123456";

	public DAO() {

		this(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
	}

	public DAO(String url, String username, String password) {

		this.url = url;
		this.username = username;
		this.password = password;
	}


	protected int executeUpdate(String queryStr) {

		int result = 0;

		try(Connection connection =
				DriverManager.getConnection(url, username, password)) {

			Statement statement = connection.createStatement();
			result = statement.executeUpdate(queryStr);
			connection.close();

		} catch(SQLException e) {
//TODO: выбросить своё исключение?..
			printExceptionInfo(e);
		}

		return result;
	}


	protected ResultSet executeQuery(String queryStr) {
		/*
		 * отправляет SQL-запрос queryStr в базу,
		 * возвращает результат в resultSet
		 */

		ResultSet resultSet = null;

		// говорят, в таком варианте connection закроется автоматически в случае исключения
		try(Connection connection =
				DriverManager.getConnection(url, username, password)) {

			PreparedStatement statement = connection.prepareStatement(queryStr);
			resultSet = statement.executeQuery();
			connection.close();

		} catch(SQLException e) {

//TODO: выбросить своё исключение?..
			printExceptionInfo(e);
		}

		return resultSet;
	}


	protected void printExceptionInfo(SQLException e) {

		e.printStackTrace(System.err);
                System.err.println("SQLState: " +
                    ((SQLException)e).getSQLState());

                System.err.println("Error Code: " +
                    ((SQLException)e).getErrorCode());

                System.err.println("Message: " + e.getMessage());
	}
}
