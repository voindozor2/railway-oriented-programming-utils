package ru.ilinbi.railway;

public class Error<TSuccess> implements ru.ilinbi.railway.Result<TSuccess> {
    private final Exception exception;

    Error(Exception exception) {
        this.exception = exception;
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
    public Exception getError() {
        return exception;
    }

    @Override
    public Boolean isEmpty() {
        return true;
    }
}
