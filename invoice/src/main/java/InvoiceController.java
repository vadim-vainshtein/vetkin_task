package invoice;

import java.util.ArrayList;

public class InvoiceController {
	/**
	 * - хранит объекты Invoice (класс invoice.Invoice)
	 * - создаёт массив на основе CSV
	 * - формирует CSV из массива
	 * - сохраняет данные в БД, получает их оттуда
	 *
	 * CSV имеет формат:
	 * Дата;НомерСчёта;СуммаСчёта;Комментарий
	 *
	 * ДатаСчета - ГГГГ-ММ-ДД, значения обязательны
	 * НомерСчета - Целое положительное число, значения обязательны
	 * СуммаСчета - число, точность - 2 знака после запятой, значения обязательны
	 * Комментарий - Текст, значения не обязательны, до 256 символов.
	 *
	 **/

	private final static String SEPARATOR = ";";
	private String csvString;
	private ArrayList<Invoice> invoices = new ArrayList<Invoice>();
	// неизвестно, откуда будет CSV, так что лучше хранить lineSeperator
	private String lineSeparator;

	public String getCsvString() { return csvString; }
	public void setCsvString(String csvString) { this.csvString = csvString; }


	public InvoiceController() {

		this(null, System.lineSeparator());
	}

	public InvoiceController(String csvString) {

		this(csvString, System.lineSeparator());
	}

	public InvoiceController(String csvString, String lineSeparator) {

		this.csvString = csvString;
		this.lineSeparator = lineSeparator;
	}


	public void parseCsv() {

		if(csvString == null) {

			IllegalStateException exception = new IllegalStateException(
				"Please pass a CSV using setCsvString() or " +
				"new InvoiceController(String)"
				);
			exception.initCause(new NullPointerException("csvString = null"));
			throw exception;
		}

		// разберём текст на строки
		String[] lines = csvString.split(lineSeparator);

		/* каждую строку преобразуем методом parseInvoiceString()
		 * в объект Invoice, и добавим в массив
		 */
		for(int i = 0; i < lines.length; i++) {

			Invoice invoice;

			try {
				invoice = parseInvoiceString(lines[i]);
			} catch( IllegalArgumentException e ) {

				IllegalArgumentException exception =
					new IllegalArgumentException(
						"Invalid CSV: an error at line " +
						(i + 1) + ":\n" + lines[i] + "\n");
				throw exception;
			}

			invoices.add(invoice);
		}
	}


	private Invoice parseInvoiceString(String str) {

		//делим строку на поля
		String[] fields = str.split(SEPARATOR);

		if(fields.length != 4) {
			throw new IllegalArgumentException(
					"Improper number of parameters to build an Invoice object" +
					+ fields.length );
		}

		// обявим поля, необходимые для инициализации Invoice
		String date = fields[0].trim();
		int number = 0;
		int sum = 0;
		String comment = fields[3].trim();

		//попробуем распарсить числа
		try {
			number = Integer.parseInt(fields[1].trim());
		} catch(NumberFormatException e) {
			IllegalArgumentException exception =
					new IllegalArgumentException(
					"Illegal number format: " + fields[1].trim());
			exception.initCause(e);
		}


		float fSum;

		try {
			// с float нужно ещё заменить десятичный разделитель с ',' на '.'
			fSum = Float.parseFloat(fields[2].replaceAll(",", "."));
		} catch(NumberFormatException e) {
			IllegalArgumentException exception =
					new IllegalArgumentException(
					"Illegal number format: " + fields[2].trim());
			exception.initCause(e);
		}

		/* создадим Invoice
		 * выкинет IllegalArgumentException, если формат данных неверный
		 */
		return new Invoice(date, number, sum, comment);
	}

}
