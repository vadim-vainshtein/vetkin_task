package invoice;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.ResolverStyle;

import java.time.format.DateTimeParseException;

public class Invoice {
	/**
	 * Хранит информацию о счёте:
	 * ДатаСчета - ГГГГ-ММ-ДД
	 * НомерСчета - Целое положительное число
	 * СуммаСчета - целое число, равное (сумма * 100) (деньги храним только в int)
	 * Комментарий - Текст до 256 символов.
	 */

	private final int MAX_COMMENT_LENGTH = 256;
	private final String DATE_FORMAT = "uuuu-MM-dd";

	// на всякий случай - immutable
	private final String date;
	private final int number; // возможно, лучше id?..
	private final long sum;
	private final String comment;

	public String getDate() { return date; }
	public int getNumber() { return number; }
	public long getSum() { return sum; }
	public String getComment() { return comment; }

	public Invoice() {

		this.date = "1000-01-01";
		this.number = 1;
		this.sum = 0;
		this.comment = "";
	}

	public Invoice(String date, int number, long sum)
			throws IllegalArgumentException {

		this(date, number, sum, "");
	}

	public Invoice(String date, int number, long sum, String comment)
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
			throw new IllegalArgumentException(
				"Comment cannot be longer than " + MAX_COMMENT_LENGTH +
				": " + comment);
		}
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


	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;

		result = prime + date.hashCode();
		result = prime * result + number;
		result = prime * result + (int)sum;
		result = prime * result + comment.hashCode();

		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if(obj == null) return false;
		if(this == obj) return true;
		if(this.getClass() != obj.getClass()) return false;

		Invoice other = (Invoice) obj;

		if(number != other.number) return false;
		if(sum != other.sum) return false;
		if(!date.equals(other.date)) return false;
		if(!comment.equals(other.comment)) return false;

		return true;
	}
}
