package ru.ilinbi.railway.result.combinators;

import ru.ilinbi.railway.result.result.Error;
import ru.ilinbi.railway.result.result.Result;
import ru.ilinbi.railway.result.result.Success;

import java.util.function.Function;

@SuppressWarnings("unchecked")
public class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public static <A, B> Pair<A, B> of(A first, B second) {
        return new Pair<>(first, second);
    }

    public <T, E> Result<T, E> map(Function<Pair<A, B>, T> function) {
        try {
            return new Success<>(function.apply(this));
        } catch (Exception e) {
            return (Result<T, E>) new Error<>(e);
        }
    }

    public <T> T then(Function<Pair<A, B>, T> function) {
        return function.apply(this);
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}
