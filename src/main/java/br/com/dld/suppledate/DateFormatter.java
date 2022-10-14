package br.com.dld.suppledate;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author David Duarte
 * @created 16/08/2022
 * @project supple-date
 */
public class DateFormatter {

    private final LocalDateTime date;
    private final ZoneId zoneId;
    private final String pattern;

    public DateFormatter() {
        this.date = LocalDateTime.now();
        this.zoneId = ZoneId.systemDefault();
        this.pattern = Shape.BAR_D4HMS.getPattern();
    }

    public DateFormatter(@NotNull SuppleDate suppleDate) {
        this.date = suppleDate.get();
        this.pattern = Shape.BAR_D4HMS.getPattern();
        this.zoneId = ZoneId.systemDefault();
    }

    public DateFormatter(@NotNull SuppleDate suppleDate, @NotNull Shape shape) {
        this.date = suppleDate.get();
        this.pattern = shape.getPattern();
        this.zoneId = ZoneId.systemDefault();
    }

    public DateFormatter(@NotNull SuppleDate suppleDate, @NotNull String pattern) {
        this.date = suppleDate.get();
        this.pattern = pattern;
        this.zoneId = ZoneId.systemDefault();
    }

    public DateFormatter(@NotNull SuppleDate suppleDate, @NotNull ZoneId zoneId) {
        this.date = suppleDate.get();
        this.pattern = Shape.BAR_D4HMS.getPattern();
        this.zoneId = zoneId;
    }

    public DateFormatter(@NotNull SuppleDate suppleDate, @NotNull Shape shape, @NotNull ZoneId zoneId) {
        this.date = suppleDate.get();
        this.pattern = shape.getPattern();
        this.zoneId = zoneId;
    }

    public DateFormatter(@NotNull SuppleDate suppleDate, @NotNull String pattern, @NotNull ZoneId zoneId) {
        this.date = suppleDate.get();
        this.pattern = pattern;
        this.zoneId = zoneId;
    }

    public static @NotNull DateFormatter now() {
        return new DateFormatter();
    }

    public static @NotNull DateFormatter of(@NotNull SuppleDate suppleDate) {
        return new DateFormatter(suppleDate);
    }

    public static @NotNull DateFormatter of(@NotNull SuppleDate suppleDate, @NotNull Shape shape) {
        return new DateFormatter(suppleDate, shape);
    }

    public static @NotNull DateFormatter of(@NotNull SuppleDate suppleDate, @NotNull String pattern) {
        return new DateFormatter(suppleDate, pattern);
    }

    public static @NotNull DateFormatter of(@NotNull SuppleDate suppleDate, @NotNull ZoneId zoneId) {
        return new DateFormatter(suppleDate, zoneId);
    }

    public static @NotNull DateFormatter of(@NotNull SuppleDate suppleDate, @NotNull Shape shape, @NotNull ZoneId zoneId) {
        return new DateFormatter(suppleDate, shape, zoneId);
    }

    public static @NotNull DateFormatter of(@NotNull SuppleDate suppleDate, @NotNull String pattern, @NotNull ZoneId zoneId) {
        return new DateFormatter(suppleDate, pattern, zoneId);
    }

    public String toString() {
        return ZonedDateTime.of(date, zoneId).format(DateTimeFormatter.ofPattern(pattern));
    }

    public Long toLong() {
        return Long.parseLong(toString().replaceAll("[^0-9]", ""));
    }

    public Date toDate() {
        return Date.from(this.date.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Calendar toCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(toDate());

        return calendar;
    }

    public LocalDate toLocalDate() {
        return this.date.toLocalDate();
    }

    public LocalDateTime toLocalDateTime() {
        return this.date;
    }
}
