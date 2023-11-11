package ru.ilinbi.railway.result.separators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ilinbi.railway.result.combinators.Combinator;
import ru.ilinbi.railway.result.result.Result;

import static org.junit.jupiter.api.Assertions.*;

class SeparatorTest {
    @Test
    void separatorTestSuccess() throws Throwable {
        var result = Result.of(1);
        Integer separate = Separator.separate(result);
        Assertions.assertEquals(1,separate);
    }

    @Test
    void separatorTestError() {
        var result = Result.of(1)
                .map(e-> e/0);
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            Separator.separate(result);
        });
        Assertions.assertNotNull(exception);
        Assertions.assertEquals("/ by zero",exception.getMessage());
    }
}