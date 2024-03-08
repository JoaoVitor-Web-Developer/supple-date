package br.com.dld.suppledate.converters.implementations;

import br.com.dld.suppledate.converters.NoPatternDateConverter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class GregorianCalendarConverter implements NoPatternDateConverter<GregorianCalendar> {
    @Override
    public LocalDateTime toLocalDateTime(@NonNull GregorianCalendar date, ZoneId zoneId) {
        return LocalDateTime.ofInstant(date.toInstant(), zoneId);
    }

    @Override
    public GregorianCalendar fromLocalDateTime(@NonNull LocalDateTime date, ZoneId zoneId) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new DateConverter().fromLocalDateTime(date, zoneId));
        return calendar;
    }
}