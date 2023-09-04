package ru.ilinbi.railway;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Result<S, E> {
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
    @SuppressWarnings("unchecked")
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

    /**
     * @param argument Принимается любое значение но не null
     * @return Выдается двухдорожечный результат без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    static <T, E> Result<T, E> of(final T argument) {
        if (Objects.isNull(argument))
            throw new IllegalArgumentException("Value must be not null!");
        return new Success<>(argument);
    }

    static <T, E> Result<T, E> ofNullable(final T argument) {
        return new Success<>(argument);
    }
}
