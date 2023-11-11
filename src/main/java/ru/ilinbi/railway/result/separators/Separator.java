package ru.ilinbi.railway.result.separators;

import ru.ilinbi.railway.result.result.Result;

public class Separator {
    private Separator() {
    }

    /**
     * @param result Принимается любое значение Result
     * @return Выдается результат разъединения , или выводится исключение
     * Author : Ilin Boris
     * Date : 23.09.2023
     */
    public static  <A, E> A separate(Result<A, E> result) throws Throwable {
        if(Boolean.TRUE.equals(result.isSuccess())){
            return result.getValue();
        }
        else {
            throw (Throwable) result.getErrorValue();
        }
    }
}
