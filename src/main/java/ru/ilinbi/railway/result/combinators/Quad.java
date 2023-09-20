package ru.ilinbi.railway.result.combinators;

import ru.ilinbi.railway.result.result.Error;
import ru.ilinbi.railway.result.result.Result;
import ru.ilinbi.railway.result.result.Success;

import java.util.function.Function;

@SuppressWarnings("unchecked")
public class Quad<A, B, C, D> {
    private final A first;
    private final B second;
    private final C third;
    private final D fourth;

    public Quad(A first, B second, C third, D fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    public static <A, B, C, D> Quad<A, B, C, D> of(A first, B second, C third, D fourth) {
        return new Quad<>(first, second, third, fourth);
    }

    public <T, E> Result<T, E> map(Function<Quad<A, B, C, D>, T> function) {
        try {
            return new Success<>(function.apply(this));
        } catch (Exception e) {
            return (Result<T, E>) new Error<>(e);
        }
    }

    public <T> T then(Function<Quad<A, B, C, D>, T> function) {
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

    public D getFourth() {
        return fourth;
    }
}
