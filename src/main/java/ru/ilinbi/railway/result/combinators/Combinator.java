package ru.ilinbi.railway.result.combinators;

import ru.ilinbi.railway.result.result.Error;
import ru.ilinbi.railway.result.result.Result;
import ru.ilinbi.railway.result.sets.Pair;
import ru.ilinbi.railway.result.sets.Quad;
import ru.ilinbi.railway.result.sets.Triple;

public class Combinator {
    private Combinator() {
    }

    /**
     * @param first,second Принимается любое значение Result
     * @return Выдается скомбинированный результат 2 агрументов
     * Author : Ilin Boris
     * Date : 23.09.2023
     */
    public static  <A, B, E> Result<Pair<A, B>, E> combinePair(Result<A, E> first, Result<B, E> second) {
        if (first.isSuccess() && second.isSuccess()) {
            return Result.of(Pair.of(first.getValue(), second.getValue()));
        } else {
            return new Error<>(Boolean.TRUE.equals(first.isError()) ? first.getErrorValue() : second.getErrorValue());
        }
    }

    /**
     * @param first,second Принимается любое значение Result
     * @return Выдается скомбинированный результат 3 агрументов
     * Author : Ilin Boris
     * Date : 23.09.2023
     */
    public static <A, B, C, E> Result<Triple<A, B, C>, E> combineTriple(Result<A, E> first, Result<B, E> second, Result<C, E> third) {
        if (first.isSuccess() && second.isSuccess()) {
            return Result.of(Triple.of(first.getValue(), second.getValue(), third.getValue()));
        } else {
            if (Boolean.TRUE.equals(first.isError())) {
                return new Error<>(first.getErrorValue());
            } else if (Boolean.TRUE.equals(second.isError())) {
                return new Error<>(second.getErrorValue());
            } else {
                return new Error<>(third.getErrorValue());
            }
        }
    }

    /**
     * @param first,second Принимается любое значение Result
     * @return Выдается скомбинированный результат 4 агрументов
     * Author : Ilin Boris
     * Date : 23.09.2023
     */
    public static <A, B, C, D, E> Result<Quad<A, B, C, D>, E> combineQuad(Result<A, E> first, Result<B, E> second,
                                                                   Result<C, E> third, Result<D, E> fourth
    ) {
        if (first.isSuccess() && second.isSuccess()) {
            return Result.of(Quad.of(first.getValue(), second.getValue(), third.getValue(), fourth.getValue()));
        } else {
            if (Boolean.TRUE.equals(first.isError())) {
                return new Error<>(first.getErrorValue());
            } else if (Boolean.TRUE.equals(second.isError())) {
                return new Error<>(second.getErrorValue());
            } else if (Boolean.TRUE.equals(third.isError())) {
                return new Error<>(third.getErrorValue());
            } else {
                return new Error<>(fourth.getErrorValue());
            }
        }
    }
}
