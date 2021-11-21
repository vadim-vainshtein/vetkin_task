package invoice;

import org.junit.*;
import static org.junit.Assert.*;

public class InvoiceTest {

	@Test
	public void invoiceTest() {

		assertNotNull(new Invoice("2021-11-15", 243, 197432245, ""));
	}

	@Test(expected = IllegalArgumentException.class)
	public void invoiceDateTestThrows1() {

		new Invoice("15-11-2021", 243, 197432245, "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invoiceDateTestThrows2() {

		new Invoice("2021/11/15", 243, 197432245, "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invoiceDateTestThrows3() {

		new Invoice("2021-15-11", 243, 197432245, "");
	}
}
