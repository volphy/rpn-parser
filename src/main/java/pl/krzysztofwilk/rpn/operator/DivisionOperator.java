package pl.krzysztofwilk.rpn.operator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;

public class DivisionOperator implements Operator {

    @Override
    public void apply(BigDecimal first, BigDecimal second, Deque<BigDecimal> stack) {
        stack.push(second.divide(first, RoundingMode.HALF_UP));
    }
}
