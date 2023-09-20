package ru.ilinbi.railway.result.combinators;

import ru.ilinbi.railway.result.result.Error;
import ru.ilinbi.railway.result.result.Result;
import ru.ilinbi.railway.result.result.Success;

import java.util.function.Function;

@SuppressWarnings("unchecked")
public class Triple<A, B, C> {
    private final A first;
    private final B second;
    private final C third;

    public Triple(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public static <A, B, C> Triple<A, B, C> of(A first, B second, C third) {
        return new Triple<>(first, second, third);
    }

    public <T, E> Result<T, E> map(Function<Triple<A, B, C>, T> function) {
        try {
            return new Success<>(function.apply(this));
        } catch (Exception e) {
            return (Result<T, E>) new Error<>(e);
        }
    }

    public <T> T then(Function<Triple<A, B, C>, T> function) {
        return function.apply(this);
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public C getThird() {
        return third;
    }
}
