package ru.ilinbi.railway;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Result<TSuccess, TError> {
    Boolean isSuccess();

    Boolean isError();

    TSuccess getValue();

    TError getErrorValue();

    Boolean isEmpty();

    /**
     * @param function Принимается двухдорожечная функция без исключений
     * @return Выдается двухдорожечная функция без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    @SuppressWarnings("unchecked")
    default <T, E> Result<T, E> flatMap(Function<TSuccess, Result<T, E>> function) {
        try {
            if (Boolean.TRUE.equals(isSuccess())) {
                return function.apply(getValue());
            } else {
                return (Result<T, E>) new Error<>(getErrorValue());
            }
        } catch (Exception e) {
            return (Result<T, E>) new Error<>(e);
        }
    }


    /**
     * @param function Принимается однодорожечная функция без исключений
     * @return Выдается двухдорожечная функция без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default <T,E> Result<T, E> map(Function<TSuccess, T> function) {
        try {
            if (Boolean.TRUE.equals(isSuccess())) {
                return new Success<>(function.apply(getValue()));
            } else {
                return (Result<T, E>) new Error<>(getErrorValue());
            }
        } catch (Exception e) {
            return (Result<T, E>) new Error(e);
        }
    }

    /**
     * @param deadEndFunction Принимается тупиковая функция с исключениями
     * @return Выдается двухдорожечная функция без исключений , выдаются входные параметры
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default Result<TSuccess, TError> task(Consumer<TSuccess> deadEndFunction) {
        try {
            if (Boolean.TRUE.equals(isSuccess())) {
                deadEndFunction.accept(getValue());
                return new Success<>(getValue());
            } else {
                return new Error<>(getErrorValue());
            }
        } catch (Exception e) {
            return (Result<TSuccess, TError>) new Error(e);
        }
    }

    /**
     * @param function Принимается однодорожечная функция с исключениями
     * @return Выдается двухдорожечная функция без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default <T,E> Result<T, E> with(Function<TSuccess, T> function) {
        try {
            if (Boolean.TRUE.equals(isSuccess())) {
                return new Success<>(function.apply(getValue()));
            } else {
                return (Result<T, E>) new Error<>(getErrorValue());
            }
        } catch (Exception e) {
            return (Result<T, E>) new Error(e);
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
