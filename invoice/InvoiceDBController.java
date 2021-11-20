package invoice.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class InvoiceDBController extends DAO {

	InvoiceDBController() throws ClassNotFoundException {

		super();
	}


	InvoiceDBController(String driver, String url, String username, String password)
		throws ClassNotFoundException {

		super(driver, url, username, password);
	}

}
