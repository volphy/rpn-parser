package pl.krzysztofwilk.rpn.operator;

import java.math.BigDecimal;
import java.util.Deque;

public class MultiplicationOperator implements Operator {

    @Override
    public void apply(BigDecimal first, BigDecimal second, Deque<BigDecimal> stack) {
        stack.push(second.multiply(first));
    }
}
