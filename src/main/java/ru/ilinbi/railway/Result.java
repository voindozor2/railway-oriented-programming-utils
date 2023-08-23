package src.main.java.ru.ilinbi.railway;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public interface Result<TSuccess> {
    Boolean isSuccess();

    Boolean isError();

    TSuccess getValue();

    Exception getError();

    Boolean isEmpty();

    /**
     * @param function Принимается двухдорожечная функция без исключений
     * @return Выдается двухдорожечная функция без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default Result<TSuccess> flatMap(Function<TSuccess, Result<TSuccess>> function) {
        try {
            return function.apply(getValue());
        } catch (Exception e) {
            return onError(e);
        }
    }


    /**
     * @param function Принимается однодорожечная функция без исключений
     * @return Выдается двухдорожечная функция без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default Result<TSuccess> map(UnaryOperator<TSuccess> function) {
        try {
            return onSuccess(function.apply(getValue()));
        } catch (Exception e) {
            return onError(e);
        }
    }

    /**
     * @param deadEndFunction Принимается тупиковая функция с исключениями
     * @return Выдается двухдорожечная функция без исключений , выдаются входные параметры
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default Result<TSuccess> task(Consumer<TSuccess> deadEndFunction) {
        try {
            deadEndFunction.accept(getValue());
            return onSuccess(getValue());
        } catch (Exception e) {
            return onError(e);
        }
    }

    /**
     * @param function Принимается однодорожечная функция с исключениями
     * @return Выдается двухдорожечная функция без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default Result<TSuccess> with(UnaryOperator<TSuccess> function) {
        try {
            return onSuccess(function.apply(getValue()));
        } catch (Exception e) {
            return onError(e);
        }
    }

    /**
     * @param argument Принимается любое значение но не null
     * @return Выдается двухдорожечный результат без исключений
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    static <TSuccess> Result<TSuccess> of(final TSuccess argument) {
        if(Objects.isNull(argument))
            throw new IllegalArgumentException("Value must be not null!");
        return onSuccess(argument);
    }

    static <TSuccess> Result<TSuccess> ofNullable(final TSuccess argument) {
        return onSuccess(argument);
    }

    /**
     * @param mainFunction Принимается любые 2 двухдорожечные функции
     * @return Если основная функция успешно выполняется - то выдается её результат. Если нет - то fallBackFunction
     * Author : Ilin Boris
     * Date : 23.08.2023
     */
    default Result<TSuccess> or(UnaryOperator<TSuccess> mainFunction, UnaryOperator<TSuccess> fallBackFunction) {
        try {
            return onSuccess(mainFunction.apply(getValue()));
        } catch (Exception e) {
            return onSuccess(fallBackFunction.apply(getValue()));
        }
    }


    static <TSuccess> Result<TSuccess> onSuccess(final TSuccess value) {
        return new Success(value);
    }

    static <TSuccess> Result<TSuccess> onError(final Exception exception) {
        return new Error(exception);
    }
}
