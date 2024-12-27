package br.com.dld.suppledate.converters.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalDateConverterTest {
	private LocalDateConverter converter;

	@BeforeEach
	void setup() {
		converter = new LocalDateConverter();
	}

	@Nested
	class toLocalDateTime {
		@Test
		void givenNullDate_whenToLocalDateTime_thenShouldThrowNullPointerException() {
			LocalDate date = null;
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(date, zoneId));
		}

		@Test
		void givenNullZoneId_whenToLocalDateTime_thenShouldThrowNullPointerException() {
			LocalDate date = LocalDate.of(2022, 1, 1);
			ZoneId zoneId = null;

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(date, zoneId));
		}

		@Test
		void givenValidDate_whenToLocalDateTime_thenShouldReturnLocalDateTime() {
			LocalDate date = LocalDate.of(2024, 1, 1);
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalDateTime result = converter.toLocalDateTime(date, zoneId);

			assertEquals(LocalDate.of(2024, 1, 1), result.toLocalDate());
		}

		@Test
		void givenActualDate_whenToLocalDateTime_thenShouldReturnLocalDateTime() {
			LocalDate date = LocalDate.now();
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalDateTime result = converter.toLocalDateTime(date, zoneId);

			assertEquals(LocalDate.now(), result.toLocalDate());
		}

		@Test
		void givenTimeZoneDate_whenToLocalDateTime_thenShouldReturnLocalDateTime() {
			LocalDate date = LocalDate.of(2024, 1, 1);
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalDateTime result = converter.toLocalDateTime(date, zoneId);

			assertEquals(LocalDate.of(2024, 1, 1), result.toLocalDate());
		}

		@Test
		void givenDifferentTimeZoneDate_whenToLocalDateTime_thenShouldReturnLocalDateTime() {
			LocalDate date = LocalDate.of(2024, 1, 31);
			ZoneId zoneId = ZoneId.of("America/New_York");

			LocalDateTime result = converter.toLocalDateTime(date, zoneId);

			assertEquals(LocalDate.of(2024, 1, 31), result.toLocalDate());
		}

		@Test
		void givenInvalidTimeZone_whenToLocalDateTime_thenShouldThrowIllegalArgumentException() {
			LocalDate date = LocalDate.of(2024, 1, 1);
			ZoneId zoneId = null;

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(date, zoneId));
		}
	}
	@Nested
	class fromLocalDateTime {
		@Test
		void givenNullDate_whenFromLocalDateTime_thenShouldThrowNullPointerException() {
			LocalDateTime date = null;
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			assertThrows(NullPointerException.class, () -> converter.fromLocalDateTime(date, zoneId));
		}

		@Test
		void givenNullZoneId_whenFromLocalDateTime_thenShouldThrowNullPointerException() {
			LocalDateTime date = LocalDateTime.of(2022, 1, 1, 0, 0);
			ZoneId zoneId = null;

			assertThrows(NullPointerException.class, () -> converter.fromLocalDateTime(date, zoneId));
		}

		@Test
		void givenValidDateAndSameZone_whenFromLocalDateTime_thenShouldReturnLocalDateTime() {
			LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalDate result = converter.fromLocalDateTime(date, zoneId);

			assertEquals(LocalDate.of(2024, 1, 1), result);
		}
		
		@Test
		void givenActualDateAndSameZone_whenFromLocalDateTime_thenShouldReturnLocalDateTime() {
			LocalDateTime date = LocalDateTime.now();
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalDate result = converter.fromLocalDateTime(date, zoneId);

			assertEquals(LocalDate.now(), result);
		}
	}
}
