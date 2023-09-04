package ru.ilinbi.railway;

import java.util.Optional;

public class Success<S,E> implements Result<S,E>{
    private final S value;

    Success(S value) {
        this.value = value;
    }

    @Override
    public Boolean isSuccess() {
        return true;
    }

    @Override
    public Boolean isError() {
        return false;
    }

    @Override
    public S getValue() {
        return value;
    }

    @Override
    public E getErrorValue() {
        return null;
    }

    @Override
    public Boolean isEmpty() {
        return Optional.ofNullable(value).isEmpty();
    }
}
