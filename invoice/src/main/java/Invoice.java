package invoice;

import java.time.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.ResolverStyle;

public class Invoice {
	/**
	 * Хранит информацию о счёте:
	 * ДатаСчета - ГГГГ-ММ-ДД
	 * НомерСчета - Целое положительное число
	 * СуммаСчета - целое число, равное (сумма * 100) (деньги храним только в int)
	 * Комментарий - Текст до 256 символов.
	 */

	private final int MAX_COMMENT_LENGTH = 256;
	private final String DATE_FORMAT = "yyyy-MM-dd";

	// на всякий случай - immutable
	private final String date;
	private final int number; // возможно, лучше id?..
	private final int sum;
	private final String comment;

	public String getDate() { return date; }
	public int getNumber() { return number; }
	public int getSum() { return sum; }
	public String getComment() { return comment; }

	public Invoice() {

		this.date = "1000-01-01";
		this.number = 1;
		this.sum = 0;
		this.comment = "";
	}

	public Invoice(String date, int number, int sum)
			throws IllegalArgumentException {

		this(date, number, sum, "");
	}

	public Invoice(String date, int number, int sum, String comment)
			throws IllegalArgumentException {

		this.date = date;
		this.number = number;
		this.sum = sum;
		this.comment = comment;

		// выбросит IllegalArgumentException с информацией, в каком поле ошибка
		validateFormat();
	}


	private void validateFormat() throws IllegalArgumentException {
		/**
		 * Проверяет формат даты, номера счёта и длину комментария
		 * date ГГГГ-ММ-ДД
		 * number >0
		 * comment.length() <= 255
		 */

		if(!isDateValid()) {
			throw new IllegalArgumentException("Invalid date format " + date);
		}

		if(number <= 0) {
			throw new IllegalArgumentException(
				"Invoice number should be > 0 but found " + number);
		}

		if(comment.length() > MAX_COMMENT_LENGTH) {
	}


	private boolean isDateValid() {


		DateTimeFormatter formatter =
			DateTimeFormatter.ofPattern( DATE_FORMAT ).
			withResolverStyle( ResolverStyle.STRICT );

		try {
			LocalDate.parse(date , formatter);
		} catch(DateTimeParseException e) {
			return false;
		}

		return true;
	}
}
