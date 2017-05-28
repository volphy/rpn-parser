package pl.krzysztofwilk.rpn.operator;

public class OperatorFactoryImpl implements OperatorFactory {

    @Override
    public Operator makeOperator(String token) {
        switch (token) {
            case "+":
                return new AdditionOperator();
            case "-":
                return new MinusOperator();
            case "*":
                return new MultiplicationOperator();
            case "/":
                return new DivisionOperator();
            default:
                throw new IllegalArgumentException("Illegal token: '" + token + "'");
        }
    }
}
