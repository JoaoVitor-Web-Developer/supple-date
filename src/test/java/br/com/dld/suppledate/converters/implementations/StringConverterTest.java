package br.com.dld.suppledate.converters.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringConverterTest {

	private StringConverter converter;
	private final ZoneId zoneId = ZoneId.systemDefault();

	@BeforeEach
	void setup() {
		converter = new StringConverter();
	}

	@Nested
	class LocalDateTimeTests {
		@Test
		void givenInputString_whenToLocalDateTime_thenShouldReturnLocalDateTime() {
			String inputDate = "2022-12-13T00:00:00";
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";

			LocalDateTime result = converter.toLocalDateTime(inputDate, pattern);

			assertEquals(2022, result.getYear());
			assertEquals(12, result.getMonthValue());
			assertEquals(13, result.getDayOfMonth());
			assertEquals(0, result.getHour());
			assertEquals(0, result.getMinute());
			assertEquals(0, result.getSecond());
		}

		@Test
		void givenInvalidInputString_whenToLocalDateTime_thenShouldThrowException() {
			String inputDate = "invalid date";
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";

			assertThrows(Exception.class, () -> converter.toLocalDateTime(inputDate, pattern));
		}

		@Test
		void givenHour_whenToLocalDateTime_thenShouldReturnLocalDateTime() {
			String inputDate = "1970-01-01T12:30:00";
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";


			LocalDateTime result = converter.toLocalDateTime(inputDate, pattern, zoneId);

			assertEquals(12, result.getHour());
			assertEquals(30, result.getMinute());
			assertEquals(0, result.getSecond());
		}


		@Test
		void givenInvalidHour_whenToLocalDateTime_thenShouldThrowException() {
			String inputDate = "25:00:00";
			String pattern = "HH:mm:ss";

			assertThrows(Exception.class, () -> converter.toLocalDateTime(inputDate, pattern));
		}

		@Test
		void givenNullInput_whenToLocalDateTime_thenShouldThrowException() {
			String inputDate = null;
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(inputDate, pattern));
		}

		@Test
		void givenNullPattern_whenToLocalDateTime_thenShouldThrowException() {
			String inputDate = "2022-12-13T00:00:00";
			String pattern = null;

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(inputDate, pattern));
		}

		@Test
		void givenNullZoneId_whenToLocalDateTime_thenShouldThrowException() {
			String inputDate = "2022-12-13T00:00:00";
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";
			ZoneId zoneId = null;

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(inputDate, pattern, zoneId));
		}

		@Test
		void givenNullInput_whenFromLocalDateTime_thenShouldThrowException() {
			LocalDateTime inputDate = null;
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";

			assertThrows(NullPointerException.class, () -> converter.fromLocalDateTime(inputDate, pattern, zoneId));
		}

		@Test
		void givenNullPattern_whenFromLocalDateTime_thenShouldThrowException() {
			LocalDateTime inputDate = LocalDateTime.now();
			String pattern = null;

			assertThrows(NullPointerException.class, () -> converter.fromLocalDateTime(inputDate, pattern, zoneId));
		}

		@Test
		void givenNullZoneId_whenFromLocalDateTime_thenShouldThrowException() {
			LocalDateTime inputDate = LocalDateTime.now();
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";
			ZoneId zoneId = null;

			assertThrows(NullPointerException.class, () -> converter.fromLocalDateTime(inputDate, pattern, zoneId));
		}

		@Test
		void givenForwardSlashPattern_whenFromLocalDateTime_thenShouldReturnCorrectString() {
			LocalDateTime inputDate = LocalDateTime.now();
			String pattern = "yyyy/MM/dd";

			String result = converter.fromLocalDateTime(inputDate, pattern, zoneId);

			assertEquals("2024/12/20", result);
		}

		@Test
		void givenBackSlashPattern_whenFromLocalDateTime_thenShouldReturnCorrectString() {
			LocalDateTime inputDate = LocalDateTime.now();
			String pattern = "yyyy\\MM\\dd";

			String result = converter.fromLocalDateTime(inputDate, pattern, zoneId);

			assertEquals("2024\\12\\20", result);
		}
	}
}
