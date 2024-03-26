package lab4.l4t2;

final public class IntegerCalculatorResult extends CalculatorResult {
    public IntegerCalculatorResult(CalculatorRequest calculatorRequest) {
        super(calculatorRequest);
    }

    @Override
    public Object computeResult() throws InvalidOperationException {
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
                if(b==0)throw new InvalidOperationException();
                return a/b;
            }
            default:{
                throw new InvalidOperationException();
            }
        }
    }
}
