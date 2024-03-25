package lab3.l3t6;

import java.util.ArrayList;
import java.util.List;

final public class SmarterCalculator {
    public static List<CalculatorResult> calculate(String[] args) {
        List<CalculatorRequest> reqs = InputConverter.mapRequests(args);
        List<CalculatorResult> res = new ArrayList<CalculatorResult>();
        for (CalculatorRequest req : reqs) {
            switch (req.getRequestType()){
                case "Boolean":{
                    res.add(new BooleanCalculatorResult(req));
                    continue;
                }
                case "Integer":{
                    res.add(new IntegerCalculatorResult(req));
                    continue;
                }
                case "Double":{
                    res.add(new DoubleCalculatorResult(req));
                    continue;
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        String[] input = {"1", "+", "2",
                "2", "*", "5",
                "1", "+", "5.0",
                "1.0", "-", "2",
                "10.0", "/", "1",
                "true", "||", "false"};
        List<CalculatorResult> calculationResults =  SmarterCalculator.calculate(input);

        for (CalculatorResult result : calculationResults) {
            CalculatorRequest request = result.getRequest();
            System.out.println("Operation " + request + " has result " + result.computeResult());
        }
    }
}
