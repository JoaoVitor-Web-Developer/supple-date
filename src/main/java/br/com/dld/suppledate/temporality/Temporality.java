package br.com.dld.suppledate.temporality;

import br.com.dld.suppledate.Chronos;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
public class Temporality {

    private final String pattern;
    private final ZoneId zoneId;
    private LocalDateTime dateTime;

    //region Static Initializr
    public static Temporality now() {
        return of(Chronos.BAR_D4HMS.getPattern(), LocalDateTime.now(), ZoneId.systemDefault());
    }

    public static Temporality now(ZoneId zoneId) {
        return of(Chronos.BAR_D4HMS.getPattern(), LocalDateTime.now(), zoneId);
    }

    public static Temporality now(String pattern) {
        return of(pattern, LocalDateTime.now(), ZoneId.systemDefault());
    }

    public static Temporality now(String pattern, ZoneId zoneId) {
        return of(pattern, LocalDateTime.now(), zoneId);
    }

    public static Temporality of(Calendar date) {
        return of(Chronos.BAR_D4HMS.getPattern(), date, ZoneId.systemDefault());
    }

    public static Temporality of(Date date) {
        return of(Chronos.BAR_D4HMS.getPattern(), date, ZoneId.systemDefault());
    }

    public static Temporality of(LocalDate date) {
        return of(Chronos.BAR_D4HMS.getPattern(), date, ZoneId.systemDefault());
    }

    public static Temporality of(LocalDateTime date) {
        return of(Chronos.BAR_D4HMS.getPattern(), date, ZoneId.systemDefault());
    }

    public static Temporality of(Calendar date, ZoneId zoneId) {
        return of(Chronos.BAR_D4HMS.getPattern(), date, zoneId);
    }

    public static Temporality of(Date date, ZoneId zoneId) {
        return of(Chronos.BAR_D4HMS.getPattern(), date, zoneId);
    }

    public static Temporality of(LocalDate date, ZoneId zoneId) {
        return of(Chronos.BAR_D4HMS.getPattern(), date, zoneId);
    }

    public static Temporality of(LocalDateTime date, ZoneId zoneId) {
        return of(Chronos.BAR_D4HMS.getPattern(), date, zoneId);
    }

    public static Temporality of(String pattern, String date) {
        return of(pattern, date, ZoneId.systemDefault());
    }

    public static Temporality of(String pattern, long date) {
        return of(pattern, date, ZoneId.systemDefault());
    }

    public static Temporality of(String pattern, Calendar date) {
        return of(pattern, date, ZoneId.systemDefault());
    }

    public static Temporality of(String pattern, Date date) {
        return of(pattern, date, ZoneId.systemDefault());
    }

    public static Temporality of(String pattern, LocalDate date) {
        return of(pattern, date, ZoneId.systemDefault());
    }

    public static Temporality of(String pattern, LocalDateTime date) {
        return of(pattern, date, ZoneId.systemDefault());
    }

    public static Temporality of(String pattern, String date, ZoneId zoneId) {
        return new Temporality(pattern, zoneId, TemporalityParser.toLocalDateTime(date, pattern));
    }

    public static Temporality of(String pattern, long date, ZoneId zoneId) {
        return new Temporality(pattern, zoneId, TemporalityParser.toLocalDateTime(date, pattern));
    }

    public static Temporality of(String pattern, Calendar date, ZoneId zoneId) {
        return new Temporality(pattern, zoneId, TemporalityParser.toLocalDateTime(date, zoneId));
    }

    public static Temporality of(String pattern, Date date, ZoneId zoneId) {
        return new Temporality(pattern, zoneId, TemporalityParser.toLocalDateTime(date, zoneId));
    }

    public static Temporality of(String pattern, LocalDate date, ZoneId zoneId) {
        return new Temporality(pattern, zoneId, TemporalityParser.toLocalDateTime(date));
    }

    public static Temporality of(String pattern, LocalDateTime date, ZoneId zoneId) {
        return new Temporality(pattern, zoneId, date);
    }
    //endregion

    //region Handler
    public Temporality change(ChronoField field, long newValue) {
        dateTime = TemporalityHandler.change(dateTime, field, newValue);

        return this;
    }

    public Temporality sum(ChronoUnit unit, Long value) {
        dateTime = TemporalityHandler.sum(dateTime, unit, value);

        return this;
    }

    public Temporality startOfMonth() {
        dateTime = TemporalityHandler.startOfMonth(dateTime);

        return this;
    }

    public Temporality endOfMonth() {
        dateTime = TemporalityHandler.endOfMonth(dateTime);

        return this;
    }

    public Temporality startOfDay() {
        dateTime = TemporalityHandler.startOfDay(dateTime);

        return this;
    }

    public Temporality endOfDay() {
        dateTime = TemporalityHandler.endOfDay(dateTime);

        return this;
    }

    public Temporality preventWeekend() {
        dateTime = TemporalityHandler.preventWeekend(dateTime);

        return this;
    }

    public boolean isWithinRange(LocalDateTime initialDate, LocalDateTime finalDate) {
        return TemporalityHandler.isWithinRange(dateTime, initialDate, finalDate);
    }
    //endregion

    //region Formatter
    @Override
    public String toString() {
        return TemporalityFormatter.toString(dateTime, zoneId, pattern);
    }

    public Long toLong() {
        return TemporalityFormatter.toLong(dateTime, zoneId, pattern);
    }

    public Date toDate() {
        return TemporalityFormatter.toDate(dateTime, zoneId);
    }

    public Calendar toCalendar() {
        return TemporalityFormatter.toCalendar(dateTime, zoneId);
    }

    public LocalDate toLocalDate() {
        return TemporalityFormatter.toLocalDate(dateTime);
    }

    public LocalDateTime toLocalDateTime() {
        return dateTime;
    }
    //endregion
}
