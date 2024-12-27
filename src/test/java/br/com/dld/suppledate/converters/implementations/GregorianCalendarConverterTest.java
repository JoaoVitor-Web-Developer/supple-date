package br.com.dld.suppledate.converters.implementations;

import lombok.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GregorianCalendarConverterTest {
	private GregorianCalendarConverter converter;
	private final ZoneId zoneId = ZoneId.systemDefault();

	@BeforeEach
	void setup() {
		converter = new GregorianCalendarConverter();
	}

	@Nested
	class localDateTimeTests {
		@Test
		void givenNullInput_whenToLocalDateTime_thenShouldThrowException() {
			@NonNull GregorianCalendar invalidDate = null;
			String pattern = "yyyyMMddHHmmss";

			assertThrows(NullPointerException.class, () -> converter.toLocalDateTime(invalidDate, pattern, zoneId));
		}

	}
}
