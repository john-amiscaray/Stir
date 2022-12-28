package io.john.amiscaray.stir.setup;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StirAssertions {

    public static <T> void assertListEquality(List<T> expected, List<T> actual){

        assertTrue(expected.containsAll(actual) && expected.size() == actual.size());

    }

}
