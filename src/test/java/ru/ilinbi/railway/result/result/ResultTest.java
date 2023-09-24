package ru.ilinbi.railway.result.result;

import org.junit.jupiter.api.Test;
import ru.ilinbi.railway.result.sets.Quad;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {
    static final String CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL = "java.lang.IllegalArgumentException: Value must be not null!";

    @Test
    void ofTest() {
        var result = Result.of(1);
        assertEquals(1, result.getValue());
    }

    @Test
    void ofExceptionTest() {
        Result<Integer, Object> result = Result.of(null);
        assertEquals(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL, result.getErrorValue().toString());
    }

    @Test
    void OfQuadTest() {
        var result = Result.of(1, 2, 3, 4);
        assertEquals(1, result.getValue().getFirst());
        assertEquals(2, result.getValue().getSecond());
        assertEquals(3, result.getValue().getThird());
        assertEquals(4, result.getValue().getFourth());
    }

    @Test
    void OfQuadExceptionFirstTest() {
        var result = Result.of(null, 2, 3, 4);
        assertEquals(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL, result.getErrorValue().toString());
    }

    @Test
    void OfQuadExceptionSecondTest() {
        var result = Result.of(1, null, 3, 4);
        assertEquals(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL, result.getErrorValue().toString());
    }

    @Test
    void OfQuadExceptionThirdTest() {
        var result = Result.of(1, 2, null, 4);
        assertEquals(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL, result.getErrorValue().toString());
    }

    @Test
    void OfQuadExceptionFourthTest() {
        var result = Result.of(1, 2, 3, null);
        assertEquals(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL, result.getErrorValue().toString());
    }

    @Test
    void OfQuadExceptionAllTest() {
        var result = Result.of(null, null, null, null);
        assertEquals(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL, result.getErrorValue().toString());
    }


    @Test
    void OfTripleTest() {
        var result = Result.of(1, 2, 3);
        assertEquals(1, result.getValue().getFirst());
        assertEquals(2, result.getValue().getSecond());
        assertEquals(3, result.getValue().getThird());
    }

    @Test
    void OfTripleFirstNullArgumentTest() {
        var result = Result.of(null, 2, 3);
        assertEquals(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL, result.getErrorValue().toString());
    }

    @Test
    void OfTripleSecondNullArgumentTest() {
        var result = Result.of(1, null, 3);
        assertEquals(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL, result.getErrorValue().toString());
    }

    @Test
    void OfTripleThirdNullArgumentTest() {
        var result = Result.of(1, 2, null);
        assertEquals(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL, result.getErrorValue().toString());
    }

    @Test
    void OfTripleAllNullArgumentTest() {
        var result = Result.of(null, null, null);
        assertEquals(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL, result.getErrorValue().toString());
    }

    @Test
    void OfPairTest() {
        var result = Result.of(1, 2);
        assertEquals(1, result.getValue().getFirst());
        assertEquals(2, result.getValue().getSecond());
    }

    @Test
    void OfPairFirstArgumentNullTest() {
        var result = Result.of(null, 2);
        assertEquals(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL, result.getErrorValue().toString());
    }

    @Test
    void OfPairSecondArgumentNullTest() {
        var result = Result.of(1, null);
        assertEquals(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL, result.getErrorValue().toString());
    }

    @Test
    void OfPairAllArgumentNullTest() {
        var result = Result.of(null, null);
        assertEquals(CONSTANT_MESSAGE_VALUE_MUST_BE_NOT_NULL, result.getErrorValue().toString());
    }

    @Test
    void ofNullableTest() {
        var result = Result.ofNullable(null);
        assertNull(result.getValue());
    }

    @Test
    void ofNullableQuadTest() {
        var result = Result.ofNullable(null, null, null, null);
        assertNotNull(result.getValue());
        assertNull(result.getValue().getFirst());
        assertNull(result.getValue().getSecond());
        assertNull(result.getValue().getThird());
        assertNull(result.getValue().getFourth());
    }

    @Test
    void ofNullableTripleTest() {
        var result = Result.ofNullable(null, null, null);
        assertNotNull(result.getValue());
        assertNull(result.getValue().getFirst());
        assertNull(result.getValue().getSecond());
        assertNull(result.getValue().getThird());
    }

    @Test
    void ofNullablePairTest() {
        var result = Result.ofNullable(null, null);
        assertNotNull(result.getValue());
        assertNull(result.getValue().getFirst());
        assertNull(result.getValue().getSecond());
    }

    @Test
    void isSuccessTest() {
        var result = Result.of(1);
        assertTrue(result.isSuccess());
    }

    @Test
    void isErrorTest() {
        var result = Result.of(1)
                .flatMap(integer -> Result.of(integer.getValue() / 0));
        assertTrue(result.isError());
    }

    @Test
    void getValueTest() {
        var result = Result.of(1);
        assertNotNull(result.getValue());
        assertEquals(1, result.getValue());
    }

    @Test
    void getErrorValueTest() {
        var result = Result.of(1)
                .flatMap(integer -> Result.of(integer.getValue() / 0));
        assertTrue(result.isError());
        assertNotNull(result.getErrorValue());
    }

    @Test
    void isEmptyTest() {
        var result = Result.ofNullable(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void flatMapTest() {
        var result = Result.of(10)
                .flatMap(integer -> Result.of(integer.getValue() / 2));
        assertEquals(5, result.getValue());
    }

    @Test
    void mapTest() {
        var result = Result.of(10)
                .map(integer -> integer / 2);
        assertEquals(5, result.getValue());
    }

    @Test
    void mapQuadTest() {
        var result = Result.of(1, 2, 3, 4)
                .mapQuad(quad -> (Integer) quad.getFirst() + (Integer) quad.getSecond()
                        + (Integer) quad.getThird() + (Integer) quad.getFourth());
        assertEquals(10, result.getValue());
    }

    @Test
    void mapTripleTest() {
        var result = Result.of(1, 2, 3)
                .mapTriple(triple -> (Integer) triple.getFirst() + (Integer) triple.getSecond()
                        + (Integer) triple.getThird());
        assertEquals(6, result.getValue());
    }

    @Test
    void mapPairTest() {
        var result = Result.of(1, 2)
                .mapPair(pair -> (Integer) pair.getFirst() + (Integer) pair.getSecond());
        assertEquals(3, result.getValue());
    }

    @Test
    void taskTest() {
        var result = Result.of(1)
                .task(argument -> {
                    var arg = argument + 1 + 2 + 3;
                });
        assertEquals(1, result.getValue());
    }

    @Test
    void withSuccessTest() {
        var result = Result.of(1)
                .with(arg -> arg + 1);
        assertEquals(2, result.getValue());
    }

    @Test
    void withErrorTest() {
        var result = Result.of(1)
                .with(arg -> arg + 1 / 0);
        assertNotNull(result.getErrorValue());
    }

    @Test
    void eitherSuccessTest() {
        var result = Result.of(1)
                .either(arg -> arg + 1, arg -> arg + 2);
        assertEquals(2, result.getValue());
    }

    @Test
    void eitherErrorTest() {
        var result = Result.of(1)
                .either(arg -> arg / 0, arg -> arg + 2);
        assertEquals(3, result.getValue());
    }
}