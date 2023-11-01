package br.com.dld.suppledate;

import br.com.dld.suppledate.converters.implementations.StringConverter;
import br.com.dld.suppledate.exceptions.PatternRequiredException;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

import static br.com.dld.suppledate.converters.DateConverterFactory.fromLocalDateTime;
import static br.com.dld.suppledate.converters.DateConverterFactory.toLocalDateTime;

public class Temporality {
	private LocalDateTime date;
	private final String pattern;

	private <T> Temporality(@NonNull final T date, final String pattern, @NonNull final ZoneId zoneId) {
		this.date = toLocalDateTime(date, pattern, zoneId);
		this.pattern = pattern;
	}

	public static Temporality of(@NonNull final Object date, final String pattern, @NonNull final ZoneId zoneId) {
		return new Temporality(date, pattern, zoneId);
	}

	public static Temporality of(@NonNull final Object date, final Chronos pattern, @NonNull final ZoneId zoneId) {
		return of(date, pattern.getPattern(), zoneId);
	}

	public static Temporality of(@NonNull final Object date, final String pattern) {
		return new Temporality(date, pattern, ZoneId.systemDefault());
	}

	public static Temporality of(@NonNull final Object date, final Chronos pattern) {
		return of(date, pattern.getPattern());
	}

	public static Temporality of(@NonNull final Object date, @NonNull final ZoneId zoneId) {
		return new Temporality(date, null, zoneId);
	}

	public static Temporality of(@NonNull final Object date) {
		return new Temporality(date, null, ZoneId.systemDefault());
	}

	public static Temporality now(@NonNull final String pattern, @NonNull final ZoneId zoneId) {
		return new Temporality(LocalDateTime.now(), pattern, zoneId);
	}

	public static Temporality now(@NonNull final Chronos pattern, @NonNull final ZoneId zoneId) {
		return now(pattern.getPattern(), zoneId);
	}

	public static Temporality now(@NonNull final String pattern) {
		return new Temporality(LocalDateTime.now(), pattern, ZoneId.systemDefault());
	}

	public static Temporality now(@NonNull final Chronos pattern) {
		return now(pattern.getPattern());
	}

	public static Temporality now(@NonNull final ZoneId zoneId) {
		return new Temporality(LocalDateTime.now(), null, zoneId);
	}

	public static Temporality now() {
		return new Temporality(LocalDateTime.now(), null, ZoneId.systemDefault());
	}

	public Temporality plus(final long value, @NonNull final ChronoUnit unit) {
		this.date = date.plus(value, unit);
		return this;
	}

	public Temporality minus(final long value, @NonNull final ChronoUnit unit) {
		this.date = date.minus(value, unit);
		return this;
	}

	public Temporality change(final long value, @NonNull final ChronoField field) {
		this.date = date.with(field, value);
		return this;
	}

	public Temporality startOfDay() {
		this.date = LocalDateTime.of(date.toLocalDate(), LocalTime.MIN);
		return this;
	}

	public Temporality endOfDay() {
		this.date = LocalDateTime.of(date.toLocalDate(), LocalTime.MAX);
		return this;
	}

	public Temporality startOfMonth() {
		return change(1, ChronoField.DAY_OF_MONTH).startOfDay();
	}

	public Temporality endOfMonth() {
		this.date = date.with(TemporalAdjusters.lastDayOfMonth());
		return endOfDay();
	}

	public Temporality preventWeekend() {
		switch(date.getDayOfWeek()) {
			case SATURDAY:
				return plus(2, ChronoUnit.DAYS);
			case SUNDAY:
				return plus(1, ChronoUnit.DAYS);
			default:
				return this;
		}
	}

	public long until(@NonNull final Object otherDate, final String pattern, @NonNull final ChronoUnit unit) {
		return date.until(toLocalDateTime(otherDate, pattern), unit);
	}

	public long until(@NonNull final Object otherDate, @NonNull final ChronoUnit unit) {
		return until(otherDate, null, unit);
	}

	public boolean isBefore(@NonNull final Object otherDate, final String pattern) {
		return date.isBefore(toLocalDateTime(otherDate, pattern));
	}

	public boolean isBefore(@NonNull final Object otherDate) {
		return isBefore(otherDate, null);
	}

	public boolean isAfter(@NonNull final Object otherDate, final String pattern) {
		return date.isAfter(toLocalDateTime(otherDate, pattern));
	}

	public boolean isAfter(@NonNull final Object otherDate) {
		return isAfter(otherDate, null);
	}

	public boolean isEqual(@NonNull final Object otherDate, final String pattern) {
		return date.isEqual(toLocalDateTime(otherDate, pattern));
	}

	public boolean isEqual(@NonNull final Object otherDate) {
		return isEqual(otherDate, null);
	}

	public boolean isBetween(@NonNull final Object initialDate, @NonNull final Object finalDate, final String initialDatePattern,
	                         final String finalDatePattern) {
		return !date.isBefore(toLocalDateTime(initialDate, initialDatePattern)) && !date.isAfter(toLocalDateTime(finalDate, finalDatePattern));
	}

	public boolean isBetween(@NonNull final Object initialDate, @NonNull final Object finalDate, final String pattern) {
		return isBetween(initialDate, finalDate, pattern, pattern);
	}

	public boolean isBetween(@NonNull final Object initialDate, @NonNull final Object finalDate) {
		return isBetween(initialDate, finalDate, null);
	}

	public <T> T parse(Class<T> type, String pattern, ZoneId zoneId) {
		try {
			return fromLocalDateTime(date, type, pattern, zoneId);
		} catch(PatternRequiredException e) {
			if(this.pattern != null) {
				return fromLocalDateTime(date, type, this.pattern, zoneId);
			}
			throw e;
		}
	}

	public <T> T parse(Class<T> type, Chronos pattern, ZoneId zoneId) {
		return parse(type, pattern.getPattern(), zoneId);
	}

	public <T> T parse(Class<T> type, String pattern) {
		return parse(type, pattern, ZoneId.systemDefault());
	}

	public <T> T parse(Class<T> type, Chronos pattern) {
		return parse(type, pattern.getPattern());
	}

	public <T> T parse(Class<T> type, ZoneId zoneId) {
		return parse(type, (String) null, zoneId);
	}

	public <T> T parse(Class<T> type) {
		return parse(type, (String) null, ZoneId.systemDefault());
	}

	@Override
	public String toString() {
		if(pattern == null) {
			return new StringConverter().fromLocalDateTime(date, Chronos.TRACE_D4HMS_ISO.getPattern(), ZoneId.systemDefault());
		}
		return new StringConverter().fromLocalDateTime(date, pattern, ZoneId.systemDefault());
	}
}
