package org.example.lab12.l12t1;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class test {

    @Test
    public void testPrintCollection() {
        Collection<String> collection = Arrays.asList("Ana", "mere", "are");
        MyUtilityClass.printCollection(collection);
    }
    @Test
    public void testNull(){
        assertThrows(IllegalArgumentException.class,()->MyUtilityClass.printCollection(null));
        assertThrows(IllegalArgumentException.class,()->MyUtilityClass.aggregate(null,null,null));
        assertThrows(IllegalArgumentException.class,()->MyUtilityClass.duplicateCollection(null));
    }
    @Test
    public void testAggregate() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Integer result = MyUtilityClass.aggregate(numbers, 0, Integer::sum);
        assertEquals(15, result, "Suma ar trebui sa fie 15");

        List<String> strings = Arrays.asList("Ana", "Are", "Pere");
        String concatenated = MyUtilityClass.aggregate(strings, "", String::concat);
        assertEquals("AnaArePere", concatenated, "Concatenarea stringurilor ar trebui sa fie 'AnaArePere'");
    }

    @Test
    public void testDuplicateCollection() {
        List<String> strings = new ArrayList<>(Arrays.asList("a", "b", "c"));
        MyUtilityClass.duplicateCollection(strings);
        List<String> expected = Arrays.asList("a", "a", "b", "b", "c", "c");
        assertEquals(expected, strings, "Lista ar trebui sa aiba elementele duplicate");

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3));
        MyUtilityClass.duplicateCollection(numbers);
        List<Integer> expectedNumbers = Arrays.asList(1, 1, 2, 2, 3, 3);
        assertEquals(expectedNumbers, numbers, "Lista ar trebui sa aiba elementele duplicate");
    }
}
