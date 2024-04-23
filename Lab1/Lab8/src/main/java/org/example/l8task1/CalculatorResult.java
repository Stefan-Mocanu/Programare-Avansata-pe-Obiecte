package org.example.l8task1;

abstract public class CalculatorResult {
    final protected CalculatorRequest req;
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
