package br.com.dld.suppledate.converters.implementations;

import br.com.dld.suppledate.converters.NoPatternDateConverter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeConverter implements NoPatternDateConverter<LocalDateTime> {
	@Override
	public LocalDateTime toLocalDateTime(@NonNull LocalDateTime date, ZoneId zoneId) {
		return date.atZone(zoneId).toLocalDateTime();
	}

	@Override
	public LocalDateTime fromLocalDateTime(@NonNull LocalDateTime date, ZoneId zoneId) {
		return date.atZone(zoneId).toLocalDateTime();
	}
}
