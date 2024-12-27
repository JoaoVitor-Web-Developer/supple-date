package br.com.dld.suppledate.converters.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocalDateTimeConverterTest {
	private LocalDateTimeConverter converter;

	@BeforeEach
	void setup() {
		converter = new LocalDateTimeConverter();
	}

	@Nested
	class toLocalDateTime {
		@Test
		void givenValidDateAndSameZone_whenToLocalDateTime_thenShouldReturnLocalDateTime() {
			LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalDateTime result = converter.toLocalDateTime(date, zoneId);

			assertEquals(date, result);
		}
		@Test
		void givenValidDateAndDifferentZone_whenToLocalDateTime_thenShouldReturnLocalDateTime() {
			LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
			ZoneId zoneId = ZoneId.of("America/New_York");

			LocalDateTime result = converter.toLocalDateTime(date, zoneId);

			assertEquals(date, result);
		}
		@Test
		void givenNullDate_whenToLocalDateTime_thenShouldThrowNullPointerException() {
			LocalDateTime date = null;
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(date, zoneId));
		}
	}

	@Nested
	class fromLocalDateTime {
		@Test
		void givenValidDateAndSameZone_whenFromLocalDateTime_thenShouldReturnLocalDateTime() {
			LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalDateTime result = converter.fromLocalDateTime(date, zoneId);

			assertEquals(date, result);
		}

		@Test
		void givenValidDateAndDifferentZone_whenFromLocalDateTime_thenShouldReturnLocalDateTime() {
			LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
			ZoneId zoneId = ZoneId.of("America/New_York");

			LocalDateTime result = converter.fromLocalDateTime(date, zoneId);

			assertEquals(date, result);
		}

		@Test
		void givenNullDate_whenFromLocalDateTime_thenShouldThrowNullPointerException() {
			LocalDateTime date = null;
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			assertThrows(NullPointerException.class, () -> converter.fromLocalDateTime(date, zoneId));
		}
	}
}
