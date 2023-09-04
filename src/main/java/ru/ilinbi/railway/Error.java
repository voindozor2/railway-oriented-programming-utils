package ru.ilinbi.railway;

public class Error<TSuccess,TError> implements ru.ilinbi.railway.Result<TSuccess,TError> {
    private final TError errorValue;

    Error(TError error) {
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
    public TSuccess getValue() {
        return null;
    }

    @Override
    public TError getErrorValue() {
        return errorValue;
    }

    @Override
    public Boolean isEmpty() {
        return true;
    }
}
