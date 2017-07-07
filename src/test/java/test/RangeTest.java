package test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RangeTest {

    final Range range = new Range(-3, 10);

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    void constructorThrowsException(){
        assertThrows(IllegalArgumentException.class, (Executable) new Range(-1,-2));
    }

    @ParameterizedTest
    @CsvSource({"-2,8,false", "-8,-4, true", "10,14, false"})
    void isBeforeTest(long left, long right, boolean expected) {
        Range otherRange = new Range(left,right);
        assertThat(otherRange.isBefore(range), is(expected));
    }

    @ParameterizedTest
    @CsvSource({"-2,7,false", "-10,-3, false", "100,120, true"})
    void isAfterTest(long left, long right, boolean expected) {
        Range otherRange = new Range(left,right);
        assertThat(otherRange.isAfter(range), is(expected));
    }

    @ParameterizedTest
    @CsvSource({"-2,7, true", "4,5,true", "13,16,false"})
    void isConcurrentTest(long left, long right, boolean expected){
        Range otherRange = new Range(left, right);
        assertThat(otherRange.isConcurrent(range), is(expected));
    }

    @ParameterizedTest
    @CsvSource({"-2,7, -2", "4,5,4", "13,16,13"})
    void getLowerBoundTest(long left, long right, long expected){
        Range otherRange = new Range(left, right);
        assertThat(otherRange.getLowerBound(), is(expected));
    }

    @ParameterizedTest
    @CsvSource({"3,7, 7", "4,8,8", "-5,16,16"})
    void getUpperBoundTest(long left, long right, long expected){
        Range otherRange = new Range(left, right);
        assertThat(otherRange.getUpperBound(), is(expected));
    }
    @ParameterizedTest
    @CsvSource({"-1,10,7, true", "-2,4,8, false", "-5,16,-5, true"})
    void containsTest(long left,
                      long right,
                      long value,
    boolean expected){
        Range otherRange = new Range(left, right);
        assertThat(otherRange.contains(value), is(expected));
    }

    @Test
    void asListTest(){
        Range otherRange = new Range(1, 5);
        List<Long> expected = Arrays.asList(1L,2L,3L,4L,5L);
        assertThat(otherRange.asList(), is(expected));
    }

    @Test
    void asIteratorTest(){
        Range otherRange = new Range(1, 5);
        Iterator<Long> iter = otherRange.asIterator();
        assertThat(iter.next(), is(otherRange.getLeft()));
    }

    @Test
    void asIteratorWorksAtAllElementsTest(){
        Range otherRange = new Range(1, 5);
        Iterator<Long> iter = otherRange.asIterator();
        List<Long> list = otherRange.asList();
        for (Long elem: list) {
            assertThat(iter.next(), is(elem));
        }
    }
}