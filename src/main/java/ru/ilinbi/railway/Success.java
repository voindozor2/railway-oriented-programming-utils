package src.main.java.ru.ilinbi.railway;

import java.util.Optional;

public class Success<TSuccess> implements Result<TSuccess>{
    private final TSuccess value;

    Success(TSuccess value) {
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
    public TSuccess getValue() {
        return value;
    }

    @Override
    public Exception getError() {
        return null;
    }

    @Override
    public Boolean isEmpty() {
        return Optional.ofNullable(value).isEmpty();
    }
}
