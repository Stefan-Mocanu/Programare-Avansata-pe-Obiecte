package lab2.task7;

public class DoubleCalculatorResult extends CalculatorResult{
    public DoubleCalculatorResult(CalculatorRequest calculatorRequest) {
        super(calculatorRequest);
    }

    @Override
    public Object computeResult() {
        CalculatorRequest req = getRequest();
        double a = (double)req.leftOperand;
        double b = (double)req.rightOpernad;
        switch (req.operation){
            case "+": {
                return a+b;
            }
            case "*":{
                return a*b;
            }
            case "-":{
                return a-b;
            }
            case "/":{
                return a/b;
            }
        }
        return null;
    }
}
