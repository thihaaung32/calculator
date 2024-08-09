package calculator.evaluator;

import calculator.operators.*;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {

  private final Stack<Operand> operandStack;
  private final Stack<Operator> operatorStack;

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int evaluateExpression(String expression) throws InvalidTokenException {
    String expressionToken;
    StringTokenizer expressionTokenizer;
    String delimiters = " +/*-^()";  // Include parentheses as delimiters

    // 3 arguments tells the tokenizer to return delimiters as tokens
    expressionTokenizer = new StringTokenizer(expression, delimiters, true);

    while (expressionTokenizer.hasMoreTokens()) {
      // filter out spaces
      if (!(expressionToken = expressionTokenizer.nextToken()).equals(" ")) {
        // check if token is an operand
        if (Operand.check(expressionToken)) {
          operandStack.push(new Operand(expressionToken));
        } else if (!Operator.check(expressionToken)) {
          // Handle parentheses separately from other operators
          if (expressionToken.equals("(")) {
            operatorStack.push(null); // Push null for '('
          } else if (expressionToken.equals(")")) {
            while (operatorStack.peek() != null) {
              processOperator();
            }
            operatorStack.pop();
          } else {
            throw new InvalidTokenException(expressionToken);
          }
        } else {


          // TODO fix this line of code.
          Operator newOperator = Operator.getOperator(expressionToken);



          // Ensure there is an operator to compare precedence with
          while (!operatorStack.isEmpty() && operatorStack.peek() != null &&
                  operatorStack.peek().priority() >= newOperator.priority()) {
            processOperator();
          }

          operatorStack.push(newOperator);
        }
      }
    }

    // Process all remaining in the stack
    while (!operatorStack.isEmpty()) {
      if (operatorStack.peek() != null) {
        processOperator();
      } else {
        operatorStack.pop();
      }
    }

    return operandStack.pop().getValue();
  }

  private void processOperator() {
    Operator operatorFromStack = operatorStack.pop();
    Operand operandTwo = operandStack.pop();
    Operand operandOne = operandStack.pop();
    Operand result = operatorFromStack.execute(operandOne, operandTwo);
    operandStack.push(result);
  }

  public static void main(String[] args) throws InvalidTokenException {
    if (args.length == 1) {
      Evaluator e = new Evaluator();
      System.out.println(e.evaluateExpression(args[0]));
    } else {
      System.err.println("Invalid arguments or No expression given");
    }
  }
}
