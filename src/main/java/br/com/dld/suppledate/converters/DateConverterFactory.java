package br.com.dld.suppledate.converters;

import br.com.dld.suppledate.exceptions.PatternRequiredException;
import br.com.dld.suppledate.exceptions.TypeNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access=AccessLevel.NONE)
public class DateConverterFactory {
	private static final Map<Class<?>, PatternDateConverter<?>> CONVERTERS = new HashMap<>();

	public static void add(@NonNull final PatternDateConverter<?> converter) {
		CONVERTERS.put(getType(converter), converter);
	}

	@SuppressWarnings("unchecked")
	public static <T> PatternDateConverter<T> of(@NonNull final T date) {
		Class<?> type = date.getClass();
		PatternDateConverter<T> converter = (PatternDateConverter<T>) CONVERTERS.get(type);
		validateConverterFound(converter, type);
		return converter;
	}

	@SuppressWarnings("unchecked")
	public static <T> PatternDateConverter<T> of(@NonNull final Class<T> type) {
		PatternDateConverter<T> converter = (PatternDateConverter<T>) CONVERTERS.get(type);
		validateConverterFound(converter, type);
		return converter;
	}

	public static <T> LocalDateTime toLocalDateTime(@NonNull final T date, final String pattern, @NonNull final ZoneId zoneId) {
		PatternDateConverter<T> converter = of(date);
		validateConverterNeedsPattern(converter, date.getClass());
		return converter.toLocalDateTime(date, pattern, zoneId);
	}

	public static LocalDateTime toLocalDateTime(@NonNull final Object date, final String pattern) {
		return toLocalDateTime(date, pattern, ZoneId.systemDefault());
	}

	public static <T> T fromLocalDateTime(@NonNull final LocalDateTime date, @NonNull final Class<T> type, final String pattern, @NonNull final ZoneId zoneId) {
		PatternDateConverter<T> converter = of(type);
		validateConverterNeedsPattern(converter, type);
		return converter.fromLocalDateTime(date, pattern, zoneId);
	}

	public static <T> T fromLocalDateTime(@NonNull final LocalDateTime date, @NonNull final Class<T> type, final String pattern) {
		return fromLocalDateTime(date, type, pattern, ZoneId.systemDefault());
	}

	private static Class<?> getType(@NonNull final PatternDateConverter<?> converter) {
		Type genericSuperclass = converter.getClass().getGenericSuperclass();

		if(genericSuperclass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
			Type[] typeArguments = parameterizedType.getActualTypeArguments();

			if(typeArguments.length > 0) {
				return (Class<?>) typeArguments[0];
			}
		}

		throw new IllegalArgumentException("Cannot determine type of converter");
	}

	private static void validateConverterNeedsPattern(@NonNull final PatternDateConverter<?> converter, @NonNull final Class<?> type) {
		if(converter instanceof NoPatternDateConverter) {
			throw new PatternRequiredException(String.format("Converter for type '%s' need a pattern", type.getSimpleName()));
		}
	}

	private static void validateConverterFound(final PatternDateConverter<?> converter, @NonNull final Class<?> type) {
		if(converter == null) {
			throw new TypeNotFoundException(String.format("Converter for type '%s' not found", type.getSimpleName()));
		}
	}
}
