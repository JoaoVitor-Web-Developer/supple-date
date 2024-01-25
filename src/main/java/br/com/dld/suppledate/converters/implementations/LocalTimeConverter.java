package br.com.dld.suppledate.converters.implementations;

import br.com.dld.suppledate.converters.NoPatternDateConverter;
import lombok.NonNull;

import java.time.*;

public class LocalTimeConverter implements NoPatternDateConverter<LocalTime> {

    @Override
    public LocalDateTime toLocalDateTime(@NonNull LocalTime time, ZoneId zoneId) {
        return ZonedDateTime.of(LocalDate.now(), time, zoneId).toLocalDateTime();
    }

    @Override
    public LocalTime fromLocalDateTime(@NonNull LocalDateTime date, ZoneId zoneId) {
        return date.atZone(zoneId).toLocalTime();
    }
}
