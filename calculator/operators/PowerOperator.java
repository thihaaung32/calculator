package calculator.operators;

import calculator.evaluator.Operand;

public class PowerOperator extends Operator {
    @Override
    public int priority() {
        return 3; // The priority level for '^' operator
    }


    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int result = (int) Math.pow(operandOne.getValue(), operandTwo.getValue());
        return new Operand(result);
    }
}
