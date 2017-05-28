package pl.krzysztofwilk.rpn.operator;


public interface OperatorFactory {
    public Operator makeOperator(String token);
}
