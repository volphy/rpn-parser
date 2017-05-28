package pl.krzysztofwilk.rpn.operator;

import java.math.BigDecimal;
import java.util.Deque;

public interface Operator {

    void apply(BigDecimal first, BigDecimal second, Deque<BigDecimal> stack);
}
