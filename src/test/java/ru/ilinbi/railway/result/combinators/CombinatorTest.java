package ru.ilinbi.railway.result.combinators;

import org.junit.jupiter.api.Test;
import ru.ilinbi.railway.result.result.Result;

import static org.junit.jupiter.api.Assertions.*;

class CombinatorTest {

    @Test
    void combinePairTest() {
        var first = Result.of(1);
        var second = Result.of(2);
        var combined = Combinator.combinePair(first, second);
        assertEquals(1, combined.getValue().getFirst());
        assertEquals(2, combined.getValue().getSecond());
    }

    @Test
    void combineTripleTest() {
        var first = Result.of(1);
        var second = Result.of(2);
        var third = Result.of(3);
        var combined = Combinator.combineTriple(first, second, third);
        assertEquals(1, combined.getValue().getFirst());
        assertEquals(2, combined.getValue().getSecond());
        assertEquals(3, combined.getValue().getThird());
    }

    @Test
    void combineQuadTest() {
        var first = Result.of(1);
        var second = Result.of(2);
        var third = Result.of(3);
        var fourth = Result.of(4);
        var combined = Combinator.combineQuad(first, second, third,fourth);
        assertEquals(1, combined.getValue().getFirst());
        assertEquals(2, combined.getValue().getSecond());
        assertEquals(3, combined.getValue().getThird());
        assertEquals(4, combined.getValue().getFourth());
    }
}