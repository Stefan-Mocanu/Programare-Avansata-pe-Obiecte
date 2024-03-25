package lab3.l3t6;

final public class IntegerCalculatorResult extends CalculatorResult {
    public IntegerCalculatorResult(CalculatorRequest calculatorRequest) {
        super(calculatorRequest);
    }

    @Override
    public Object computeResult() {
        CalculatorRequest req = getRequest();
        int a = (int)req.leftOperand();
        int b = (int)req.rightOperand();
        switch (req.operation()){
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
