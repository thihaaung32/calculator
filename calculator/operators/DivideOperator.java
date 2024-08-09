package calculator.operators;

import calculator.evaluator.Operand;

public class DivideOperator extends Operator {
    @Override
    public int priority() {
        return 2; // The priority level for '/' operator
    }


    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        if (operandTwo.getValue() == 0) {
            throw new ArithmeticException("Can't divide by zero.");
        }
        int result = operandOne.getValue() / operandTwo.getValue();
        return new Operand(result);
    }
}
