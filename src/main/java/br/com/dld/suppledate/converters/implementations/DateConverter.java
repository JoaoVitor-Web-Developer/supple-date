package br.com.dld.suppledate.converters.implementations;

import br.com.dld.suppledate.converters.NoPatternDateConverter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter implements NoPatternDateConverter<Date> {
	@Override
	public LocalDateTime toLocalDateTime(@NonNull Date date, ZoneId zoneId) {
		return LocalDateTime.ofInstant(date.toInstant(), zoneId);
	}

	@Override
	public Date fromLocalDateTime(@NonNull LocalDateTime date, ZoneId zoneId) {
		return Date.from(date.atZone(zoneId).toInstant());
	}
}
