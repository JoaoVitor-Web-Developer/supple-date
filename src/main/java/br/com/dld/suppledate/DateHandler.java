package br.com.dld.suppledate;

import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @author David Duarte
 * @created 15/08/2022
 * @project supple-date
 */
public class DateHandler {

    private LocalDateTime date;

    public DateHandler() {
        this.date = LocalDateTime.now();
    }

    public DateHandler(@NotNull SuppleDate suppleDate) {
        this.date = suppleDate.get();
    }

    public static @NotNull DateHandler now() {
        return new DateHandler();
    }

    public static @NotNull DateHandler of(SuppleDate suppleDate) {
        return new DateHandler(suppleDate);
    }

    public @NotNull DateFormatter format() {
        return new DateFormatter(SuppleDate.of(this.date));
    }

    public @NotNull DateFormatter format(Shape shape) {
        return new DateFormatter(new SuppleDate(this.date), shape);
    }

    public @NotNull DateFormatter format(String pattern) {
        return new DateFormatter(new SuppleDate(this.date), pattern);
    }

    public DateHandler change(ChronoField field, Long valor) {

        if (this.date == null) {
            this.date = LocalDateTime.now();
        }

        this.date = this.date.with(field, valor);

        return this;
    }

    public DateHandler sum(ChronoUnit unit, Long valor) {

        if (this.date == null) {
            this.date = LocalDateTime.now();
        }

        this.date = this.date.plus(valor, unit);

        return this;
    }

    public long between(ChronoUnit uni, LocalDateTime lastDate) {
        return this.date.until(lastDate, uni);
    }

    public DateHandler firstDayOfTheMonth() {
        if (this.date == null) {
            this.date = LocalDateTime.now();
        }

        change(ChronoField.DAY_OF_MONTH, 1L);

        return this;
    }

    public DateHandler lastDayOfTheMonth() {
        if (this.date == null) {
            this.date = LocalDateTime.now();
        }

        this.date = this.date.with(TemporalAdjusters.lastDayOfMonth());

        return this;
    }

    public DateHandler startPointOfTheDay() {

        if (this.date == null) {
            this.date = LocalDateTime.now();
        }

        this.date = date.truncatedTo(ChronoUnit.DAYS);

        return this;
    }

    public DateHandler endingPointOfTheDay() {

        if (this.date == null) {
            this.date = LocalDateTime.now();
        }

        this.date = date
                .withHour(23)
                .withMinute(59)
                .withSecond(59);

        return this;
    }

    public DateHandler preventWeekend() {

        if (this.date == null) {
            this.date = LocalDateTime.now();
        }

        while (DayOfWeek.SATURDAY.equals(this.date.getDayOfWeek())
                || DayOfWeek.SUNDAY.equals(this.date.getDayOfWeek())) {
            this.date = date.plusDays(1L);
        }

        return this;
    }

    public boolean isWithinRange(LocalDateTime initialDate, LocalDateTime finalDate) {
        if (this.date == null) {
            this.date = LocalDateTime.now();
        }

        return (this.date.isAfter(initialDate) || this.date.isEqual(initialDate)) && (this.date.isBefore(finalDate) || this.date.isEqual(finalDate));
    }
}
