package invoice;


public class Main {

	public static void main(String[] args) throws Exception {

		String testCSV =
		"2021-10-01;1;1000,00;комментарий\n" +
		"2021-10-26;2;2035,56;без комментарев :)\n" +
		"2021-11-25;3;23045,40;\n" +
		"2021-11-03;3;93,99;и вот ещё\n";

		InvoiceController invoiceController = new InvoiceController(testCSV, "\n");
		invoiceController.parseCsv();
		invoiceController.saveToDB();
	}
}
