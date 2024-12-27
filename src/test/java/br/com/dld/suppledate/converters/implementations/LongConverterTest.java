package br.com.dld.suppledate.converters.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

public class LongConverterTest {

	private LongConverter converter;
	private final ZoneId zoneId = ZoneId.systemDefault();

	@BeforeEach
	void setup() {
		converter = new LongConverter();
	}

	@Nested
	class LocalDateTimeTests {
		@Test
		void givenActualDate_whenToLocalDateTime_thenShouldReturnLocalDateTime() {
			Long inputDate = 20221213091530L;
			String pattern = "yyyyMMddHHmmss";

			LocalDateTime result = converter.toLocalDateTime(inputDate, pattern, zoneId);

			assertNotNull(result);
			assertEquals(2022, result.getYear());
			assertEquals(12, result.getMonthValue());
			assertEquals(13, result.getDayOfMonth());
			assertEquals(9, result.getHour());
			assertEquals(15, result.getMinute());
			assertEquals(30, result.getSecond());
		}

		@Test
		void givenActualLocalDateTime_whenFromLocalDateTime_thenShouldReturnLong() {
			LocalDateTime inputDate = LocalDateTime.of(2022, 12, 13, 9, 15, 30);
			String pattern = "yyyyMMddHHmmss";

			Long result = converter.fromLocalDateTime(inputDate, pattern, zoneId);

			assertNotNull(result);
			assertEquals(20221213091530L, result);
		}

		@Test
		void givenNullInput_whenToLocalDateTime_thenShouldThrowException() {
			Long invalidDate = null;
			String pattern = "yyyyMMddHHmmss";

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(invalidDate, pattern, zoneId));
		}

		@Test
		void givenNullInput_whenFromLocalDateTime_thenShouldThrowException() {
			LocalDateTime invalidDate = null;
			String pattern = "yyyyMMddHHmmss";

			assertThrows(NullPointerException.class, () -> converter.fromLocalDateTime(invalidDate, pattern, zoneId));
		}

		@Test
		void givenDifferentPattern_whenToLocalDateTime_thenShouldReturnCorrectLocalDateTime() {
			Long inputDate = 20221213L;
			String pattern = "yyyyMMdd";

			LocalDateTime result = converter.toLocalDateTime(inputDate, pattern, zoneId);

			assertNotNull(result);
			assertEquals(2022, result.getYear());
			assertEquals(12, result.getMonthValue());
			assertEquals(13, result.getDayOfMonth());
			assertEquals(0, result.getHour());
			assertEquals(0, result.getMinute());
			assertEquals(0, result.getSecond());
		}

		@Test
		void givenDifferentPattern_whenFromLocalDateTime_thenShouldReturnCorrectLong() {
			LocalDateTime inputDate = LocalDateTime.of(2022, 12, 13, 0, 0, 0);
			String pattern = "yyyyMMdd";

			Long result = converter.fromLocalDateTime(inputDate, pattern, zoneId);

			assertNotNull(result);
			assertEquals(20221213L, result);
		}

		@Test
		void givenExtremeFutureDate_whenFromLocalDateTime_thenShouldReturnCorrectLong() {
			LocalDateTime inputDate = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
			String pattern = "yyyyMMddHHmmss";

			Long result = converter.fromLocalDateTime(inputDate, pattern, zoneId);

			assertNotNull(result);
			assertEquals(99991231235959L, result);
		}

		@Test
		void givenExtremePastDate_whenFromLocalDateTime_thenShouldReturnCorrectLong() {
			LocalDateTime inputDate = LocalDateTime.of(1, 1, 1, 0, 0, 0);
			String pattern = "yyyyMMddHHmmss";

			Long result = converter.fromLocalDateTime(inputDate, pattern, zoneId);

			assertNotNull(result);
			assertEquals(10101000000L, result);
		}


		@Test
		void givenInvalidLongDate_whenToLocalDateTime_thenShouldThrowException() {
			Long invalidDate = 99999999999999L;
			String pattern = "yyyyMMddHHmmss";

			assertThrows(Exception.class, () -> converter.toLocalDateTime(invalidDate, pattern, zoneId));
		}
	}
}
