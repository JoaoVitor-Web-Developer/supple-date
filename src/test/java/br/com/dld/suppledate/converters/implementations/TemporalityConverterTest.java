package br.com.dld.suppledate.converters.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TemporalityConverterTest {
	private StringConverter converter;
	private final ZoneId zoneId = ZoneId.systemDefault();

	@BeforeEach
	void setup() {converter = new StringConverter();}

	@Nested
	class LocalDateTimeTests {
		@Test
		void givenInvalidPattern_whenFromLocalDateTime_thenShouldThrowException() {
			LocalDateTime inputDate = LocalDateTime.now();
			String pattern = "invalid pattern";

			assertThrows(Exception.class, () -> converter.fromLocalDateTime(inputDate, pattern, zoneId));
		}

		@Test
		void givenNull_whenFromLocalDateTime_thenShouldThrowException() {
			LocalDateTime inputDate = null;
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";

			assertThrows(NullPointerException.class, () -> converter.fromLocalDateTime(inputDate, pattern, zoneId));
		}

		@Test
		void givenNull_whenToLocalDateTime_thenShouldThrowException() {
			String inputDate = null;
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(inputDate, pattern, zoneId));
		}

		@Test
		void givenNullable_whenToLocalDateTime_thenShouldThrowException() {
			String inputDate = null;
			String pattern = null;

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(inputDate, pattern, zoneId));
		}

		@Test
		void givenStrighlyInvalidPattern_whenFromLocalDateTime_thenShouldThrowException() {
			LocalDateTime inputDate = LocalDateTime.now();
			String pattern = "invalid pattern";

			assertThrows(Exception.class, () -> converter.fromLocalDateTime(inputDate, pattern, zoneId));
		}

		@Test
		void givenInvalidInputString_whenToLocalDateTime_thenShouldThrowException() {
			String inputDate = "invalid date";
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";

			assertThrows(Exception.class, () -> converter.toLocalDateTime(inputDate, pattern, zoneId));
		}

		@Test
		void givenDifferentZoneId_whenToLocalDateTime_thenShouldThrowException() {
			LocalDateTime inputDate = LocalDateTime.now();
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";
			ZoneId differentZoneId = ZoneId.of("America/Sao_Paulo");

			assertThrows(Exception.class, () -> converter.toLocalDateTime(String.valueOf(inputDate), pattern, differentZoneId));
		}

		@Test
		void givenNullZoneId_whenFromLocalDateTime_thenShouldThrowException() {
			LocalDateTime inputDate = LocalDateTime.now();
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";
			ZoneId zoneId = null;

			assertThrows(NullPointerException.class, () -> converter.fromLocalDateTime(inputDate, pattern, zoneId));
		}

		@Test
		void givenNullZoneId_whenToLocalDateTime_thenShouldThrowException() {
			String inputDate = "2022-12-13T00:00:00";
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";
			ZoneId zoneId = null;

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(inputDate, pattern, zoneId));
		}

		@Test
		void givenInvalidHour_whenToLocalDateTime_thenShouldThrowException() {
			String inputDate = "2022-12-13T25:00:00";
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";

			assertThrows(Exception.class, () -> converter.toLocalDateTime(inputDate, pattern, zoneId));
		}

		@Test
		void givenInvalidMinute_whenToLocalDateTime_thenShouldThrowException() {
			String inputDate = "2022-12-13T00:60:00";
			String pattern = "yyyy-MM-dd'T'HH:mm:ss";

			assertThrows(Exception.class, () -> converter.toLocalDateTime(inputDate, pattern, zoneId));
		}
	}
}
