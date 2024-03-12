package lab2.task7;
import java.util.Scanner;
abstract public class CalculatorResult {
    protected CalculatorRequest req;
    public CalculatorRequest getRequest(){
        return req;
    }
    protected CalculatorResult(CalculatorRequest calculatorRequest){
        req = calculatorRequest;
    }
    public Object computeResult(){
        return null;
    }
}
