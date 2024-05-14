package org.example.l8task1;



import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class task5 {


    /*@ParameterizedTest
    @MethodSource("testAddData")
    void testAdd(Object e1, Object e2,String e3 , Object expectedResult) {
        CalculatorRequest aux = new CalculatorRequest(e1,e2,e3);
        Object actualResult = null;
        switch (aux.getRequestType()){
            case "Integer":
                actualResult = new IntegerCalculatorResult(aux).computeResult();
                break;
            case "Double":
                actualResult = new DoubleCalculatorResult(aux).computeResult();
                break;
            case "Boolean":
                actualResult = new BooleanCalculatorResult(aux).computeResult();
                break;
        }
        assertEquals(expectedResult, actualResult, "Results should be equal.");
    }

    private static Stream<Arguments> testAddData() {
        return Stream.of(
                Arguments.of(0, 0, "+",0),
                Arguments.of(true, false, "||",true),
                Arguments.of(2.2, 1d,"+", 3.2),
                Arguments.of(5, 8, "+",13),
                Arguments.of(-5, 8, "+",3),
                Arguments.of(5, -8, "+",-3)
        );
    }*/

}
