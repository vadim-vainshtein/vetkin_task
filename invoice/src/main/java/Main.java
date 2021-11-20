package invoice;

import invoice.dao.DAO;

public class Main {

	public static void main(String[] args) throws Exception {

		DAO dao = new DAO();

		final String query = "INSERT INTO invoice " +
			"(inv_date, inv_number, inv_sum, inv_comment) " +
			"VALUES ('2021-11-01', 1, 10000, 'Тест')";

		System.out.println(query);
		dao.executeUpdate(query);
	}
}
