package lab4.l4t2;

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
                "true", "||", "false",
                "55", "^", "12",
                "4", "/", "0",
                "ana ", "++", "mere"
        };
        if(input.length < 3 || input.length%3!=0)throw new InvalidArgumentsLengthException();

        List<CalculatorResult> calculationResults =  SmarterCalculator.calculate(input);

        for (CalculatorResult result : calculationResults) {
            CalculatorRequest request = result.getRequest();
            try{
                System.out.println("Operation " + request + " has result " + result.computeResult());
            }catch(InvalidOperationException e){
                System.err.println(request);
            }
        }
    }
}
