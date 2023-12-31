package ru.ilinbi.railway.result.result;

public class Error<S, E> implements Result<S, E> {
    private final E errorValue;

    public Error(E error) {
        this.errorValue = error;
    }

    @Override
    public Boolean isSuccess() {
        return false;
    }

    @Override
    public Boolean isError() {
        return true;
    }

    @Override
    public S getValue() {
        return null;
    }

    @Override
    public E getErrorValue() {
        return errorValue;
    }

    @Override
    public Boolean isEmpty() {
        return true;
    }
}
