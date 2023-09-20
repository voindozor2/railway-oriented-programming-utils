package ru.ilinbi.railway.result.result;

import ru.ilinbi.railway.result.combinators.Pair;
import ru.ilinbi.railway.result.combinators.Quad;
import ru.ilinbi.railway.result.combinators.Triple;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public interface Result<S, E> {

    static final String CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL = "Value must be not null!";

    /**
     * @param argument Принимается любое значение но не null
     * @return Выдается двухдорожечный результат без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    static <T, E> Result<T, E> of(final T argument) {
        if (Objects.isNull(argument))
            throw new IllegalArgumentException(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL);
        return new Success<>(argument);
    }

    /**
     * @param firstArgument,secondArgument,thirdArgument,fourthArgument Принимается любое значение но не null
     * @return Выдается скомбинированный двухдорожечный результат без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    static <A, B, C, D, E> Result<Quad<A, B, C, D>, E> of(final A firstArgument, final B secondArgument,
                                                          final C thirdArgument, final D fourthArgument) {
        if (Objects.isNull(firstArgument) || Objects.isNull(secondArgument)
                || Objects.isNull(thirdArgument) || Objects.isNull(fourthArgument))
            throw new IllegalArgumentException(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL);
        return new Success<>(Quad.of(firstArgument, secondArgument, thirdArgument, fourthArgument));
    }

    /**
     * @param firstArgument,secondArgument,thirdArgument Принимается любое значение но не null
     * @return Выдается скомбинированный двухдорожечный результат без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    static <A, B, C, E> Result<Triple<A, B, C>, E> of(final A firstArgument, final B secondArgument,
                                                      final C thirdArgument) {
        if (Objects.isNull(firstArgument) || Objects.isNull(secondArgument)
                || Objects.isNull(thirdArgument))
            throw new IllegalArgumentException(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL);
        return new Success<>(Triple.of(firstArgument, secondArgument, thirdArgument));
    }

    /**
     * @param firstArgument,secondArgument Принимается любое значение но не null
     * @return Выдается скомбинированный двухдорожечный результат без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    static <A, B, E> Result<Pair<A, B>, E> of(final A firstArgument, final B secondArgument) {
        if (Objects.isNull(firstArgument) || Objects.isNull(secondArgument))
            throw new IllegalArgumentException(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL);
        return new Success<>(Pair.of(firstArgument, secondArgument));
    }

    static <T, E> Result<T, E> ofNullable(final T argument) {
        return new Success<>(argument);
    }

    static <A, B, E> Result<Pair<A, B>, E> ofNullable(final A firstArgument, final B secondArgument) {
        return new Success<>(new Pair<>(firstArgument, secondArgument));
    }

    static <A, B, C, E> Result<Triple<A, B, C>, E> ofNullable(final A firstArgument, final B secondArgument,
                                                              final C thirdArgument) {
        return new Success<>(new Triple<>(firstArgument, secondArgument, thirdArgument));
    }

    static <A, B, C, D, E> Result<Quad<A, B, C, D>, E> ofNullable(final A firstArgument, final B secondArgument,
                                                                  final C thirdArgument, final D fourthArgument) {
        return new Success<>(new Quad<>(firstArgument, secondArgument, thirdArgument, fourthArgument));
    }

    Boolean isSuccess();

    Boolean isError();

    S getValue();

    E getErrorValue();

    Boolean isEmpty();

    /**
     * @param function Принимается двухдорожечная функция без исключений
     * @return Выдается двухдорожечная функция без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default <T, G> Result<T, G> flatMap(Function<S, Result<T, G>> function) {
        try {
            if (Boolean.TRUE.equals(isSuccess())) {
                return function.apply(getValue());
            } else {
                return (Result<T, G>) new Error<>(getErrorValue());
            }
        } catch (Exception e) {
            return (Result<T, G>) new Error<>(e);
        }
    }

    /**
     * @param function Принимается однодорожечная функция без исключений
     * @return Выдается двухдорожечная функция без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default <T, G> Result<T, G> map(Function<S, T> function) {
        try {
            if (Boolean.TRUE.equals(isSuccess())) {
                return new Success<>(function.apply(getValue()));
            } else {
                return (Result<T, G>) new Error<>(getErrorValue());
            }
        } catch (Exception e) {
            return (Result<T, G>) new Error<>(e);
        }
    }

    /**
     * @param function Принимается однодорожечная функция без исключений
     * @return Выдается двухдорожечная функция без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default <T, G, A, B, C, D> Result<T, G> mapQuad(Function<Quad<A, B, C, D>, T> function) {
        try {
            if (Boolean.TRUE.equals(isSuccess())) {
                return new Success<>(function.apply((Quad<A, B, C, D>) getValue()));
            } else {
                return (Result<T, G>) new Error<>(getErrorValue());
            }
        } catch (Exception e) {
            return (Result<T, G>) new Error<>(e);
        }
    }

    /**
     * @param function Принимается однодорожечная функция без исключений
     * @return Выдается двухдорожечная функция без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default <T, G, A, B, C> Result<T, G> mapTriple(Function<Triple<A, B, C>, T> function) {
        try {
            if (Boolean.TRUE.equals(isSuccess())) {
                return new Success<>(function.apply((Triple<A, B, C>) getValue()));
            } else {
                return (Result<T, G>) new Error<>(getErrorValue());
            }
        } catch (Exception e) {
            return (Result<T, G>) new Error<>(e);
        }
    }

    /**
     * @param function Принимается однодорожечная функция без исключений
     * @return Выдается двухдорожечная функция без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default <T, G, A, B> Result<T, G> mapPair(Function<Pair<A, B>, T> function) {
        try {
            if (Boolean.TRUE.equals(isSuccess())) {
                return new Success<>(function.apply((Pair<A, B>) getValue()));
            } else {
                return (Result<T, G>) new Error<>(getErrorValue());
            }
        } catch (Exception e) {
            return (Result<T, G>) new Error<>(e);
        }
    }

    /**
     * @param deadEndFunction Принимается тупиковая функция с исключениями
     * @return Выдается двухдорожечная функция без исключений , выдаются входные параметры
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default Result<S, E> task(Consumer<S> deadEndFunction) {
        try {
            if (Boolean.TRUE.equals(isSuccess())) {
                deadEndFunction.accept(getValue());
                return new Success<>(getValue());
            } else {
                return new Error<>(getErrorValue());
            }
        } catch (Exception e) {
            return (Result<S, E>) new Error<>(e);
        }
    }

    /**
     * @param function Принимается однодорожечная функция с исключениями
     * @return Выдается двухдорожечная функция без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default <T, G> Result<T, G> with(Function<S, T> function) {
        try {
            if (Boolean.TRUE.equals(isSuccess())) {
                return new Success<>(function.apply(getValue()));
            } else {
                return (Result<T, G>) new Error<>(getErrorValue());
            }
        } catch (Exception e) {
            return (Result<T, G>) new Error<>(e);
        }
    }
}
