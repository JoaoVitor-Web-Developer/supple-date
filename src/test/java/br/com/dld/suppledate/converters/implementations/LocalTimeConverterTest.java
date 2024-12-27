package br.com.dld.suppledate.converters.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class LocalTimeConverterTest {
	private LocalTimeConverter converter;

	@BeforeEach
	void setup() {
		converter = new LocalTimeConverter();
	}

	@Nested
	class toLocalTime {
		@Test
		void givenNullDate_whenToLocalTime_thenShouldThrowNullPointerException() {
			LocalTime time = null;
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(time, zoneId));
		}

		@Test
		void givenNullZoneId_whenToLocalTime_thenShouldThrowNullPointerException() {
			LocalTime time = LocalTime.of(0, 0, 0);
			ZoneId zoneId = null;

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(time, zoneId));
		}

		@Test
		void givenValidDateAndSameZone_whenToLocalTime_thenShouldReturnLocalTime() {
			LocalTime time = LocalTime.of(0, 0, 0);
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalTime result = LocalTime.from(converter.toLocalDateTime(time, zoneId));

			assertEquals(time, result);
		}

		@Test
		void givenValidDateAndDifferentZone_whenToLocalTime_thenShouldReturnLocalTime() {
			LocalTime time = LocalTime.of(0, 0, 0);
			ZoneId zoneId = ZoneId.of("America/New_York");

			LocalTime result = LocalTime.from(converter.toLocalDateTime(time, zoneId));

			assertEquals(time, result);
		}

		@Test
		void givenValidDate_whenToLocalTime_thenShouldReturnLocalTime() {
			LocalTime time = LocalTime.of(0, 0, 0);
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalTime result = LocalTime.from(converter.toLocalDateTime(time, zoneId));

			assertEquals(time, result);
		}

		@Test
		void givenLocalDateTime_whenToLocalTime_thenShouldReturnLocalTime() {
			LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalTime result = LocalTime.from(converter.toLocalDateTime(LocalTime.from(date), zoneId));

			assertEquals(date.toLocalTime(), result);
		}

		@Test
		void givenLocalDateTimeWithDifferentZone_whenToLocalTime_thenShouldReturnLocalTime() {
			LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
			ZoneId zoneId = ZoneId.of("America/New_York");

			LocalTime result = LocalTime.from(converter.toLocalDateTime(LocalTime.from(date), zoneId));

			assertEquals(date.toLocalTime(), result);
		}

		@Test
		void givenLocalTime_whenToLocalTime_thenShouldReturnLocalTime() {
			LocalTime time = LocalTime.of(4, 40, 0);
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalTime result = LocalTime.from(converter.toLocalDateTime(LocalTime.from(time), zoneId));

			assertEquals(time, result);
		}

		@Test
		void givenLocalTimeWithDifferentZone_whenToLocalTime_thenShouldReturnLocalTime() {
			LocalTime time = LocalTime.of(22, 20, 50);
			ZoneId zoneId = ZoneId.of("America/New_York");

			LocalTime result = LocalTime.from(converter.toLocalDateTime(LocalTime.from(time), zoneId));

			assertEquals(time, result);
		}
	}

	@Nested
	class fromLocalDateTime {
		@Test
		void givenNullDate_whenFromLocalTime_thenShouldThrowNullPointerException() {
			LocalTime time = null;
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			assertThrows(NullPointerException.class, () -> converter.fromLocalDateTime(LocalDateTime.from(time), zoneId));
		}

		@Test
		void givenNullZoneId_whenFromLocalDateTime_thenShouldThrowNullPointerException() {
			LocalDateTime dateTime = LocalDateTime.of(2024, 11, 29, 0, 0);
			ZoneId zoneId = null;

			assertThrows(NullPointerException.class, () -> converter.fromLocalDateTime(dateTime, zoneId));
		}

		@Test
		void givenValidDateAndSameZone_whenFromLocalDateTime_thenShouldReturnLocalTime() {
			LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalTime result = converter.fromLocalDateTime(date, zoneId);

			assertEquals(date.toLocalTime(), result);
		}

		@Test
		void givenActualDateAndSameZone_whenFromLocalDateTime_thenShouldReturnLocalTime() {
			LocalDateTime date = LocalDateTime.now();
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalTime result = converter.fromLocalDateTime(date, zoneId);

			assertEquals(date.toLocalTime(), result);
		}

		@Test
		void givenDifferentDateAndSameZone_whenFromLocalDateTime_thenShouldReturnLocalTime() {
			LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
			ZoneId zoneId = ZoneId.of("Australia/Darwin");

			LocalTime result = converter.fromLocalDateTime(date, zoneId);

			assertEquals(date.toLocalTime(), result);
		}

		@Test
		void givenValidDateAndDifferentZone_whenFromLocalDateTime_thenShouldReturnLocalTime() {
			LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
			ZoneId zoneId = ZoneId.of("America/New_York");

			LocalTime result = converter.fromLocalDateTime(date, zoneId);

			assertEquals(date.toLocalTime(), result);
		}

		@Test
		void givenActualDateAndDifferentZone_whenFromLocalDateTime_thenShouldReturnLocalTime() {
			LocalDateTime date = LocalDateTime.now();
			ZoneId zoneId = ZoneId.of("America/New_York");

			LocalTime result = converter.fromLocalDateTime(date, zoneId);

			assertEquals(date.toLocalTime(), result);
		}

		@Test
		void givenDifferentDateAndDifferentZone_whenFromLocalDateTime_thenShouldReturnLocalTime() {
			LocalDateTime date = LocalDateTime.of(2024, 1, 1, 0, 0, 0);
			ZoneId zoneId = ZoneId.of("Australia/Darwin");

			LocalTime result = converter.fromLocalDateTime(date, zoneId);

			assertEquals(date.toLocalTime(), result);
		}

		@Test
		void givenValidDateAndZone_whenToLocalDateTime_thenShouldReturnExpectedTime() {
			LocalTime time = LocalTime.of(10, 30);
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalDateTime result = converter.toLocalDateTime(time, zoneId);

			assertAll(
					() -> assertNotNull(result),
					() -> assertEquals(time, result.toLocalTime()),
					() -> assertEquals(zoneId, result.atZone(zoneId).getZone())
			         );
		}

		@Test
		void givenTimeNearMidnight_whenToLocalDateTime_thenShouldHandleCorrectly() {
			LocalTime time = LocalTime.of(23, 59, 59);
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalDateTime result = converter.toLocalDateTime(time, zoneId);

			assertEquals(time, result.toLocalTime());
		}

		@Test
		void givenMidnightTime_whenToLocalDateTime_thenShouldReturnSameTime() {
			LocalTime time = LocalTime.of(0, 0, 0);
			ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

			LocalDateTime result = converter.toLocalDateTime(time, zoneId);

			assertEquals(time, result.toLocalTime());
		}

		@Test
		void givenInvalidHour_whenToLocalDateTime_thenShouldThrowDateTimeException() {
			assertThrows(DateTimeException.class, () -> LocalTime.of(25, 0));
		}

		@Test
		void givenNegativeHour_whenToLocalDateTime_thenShouldThrowDateTimeException() {
			assertThrows(DateTimeException.class, () -> LocalTime.of(-1, 0));
		}


	}
}
