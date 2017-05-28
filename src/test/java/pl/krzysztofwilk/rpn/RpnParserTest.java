package pl.krzysztofwilk.rpn;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RpnParserTest {

    // TODO: add more test data (incl. corner cases)

    @Parameters(name = "{index}: rpn({0})={1} exception={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{"5", "1", "2", "+", "4", "*", "+", "3", "-"}, BigDecimal.valueOf(14L), false},
                {new String[]{"5", "1", "2", "+", "4", "x", "+", "3", "-"}, BigDecimal.valueOf(-1L), true}
        });
    }

    private String[] expression;
    private BigDecimal expectedResult;
    private boolean exceptionExpected;

    public RpnParserTest(String[] expression,
                         BigDecimal expectedResult,
                         boolean exceptionExpected) {
        this.expression = expression;
        this.expectedResult = expectedResult;
        this.exceptionExpected = exceptionExpected;
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testExpressions() {
        RpnParser parser = new RpnParser();

        if (exceptionExpected) {
            thrown.expect(IllegalArgumentException.class);
        }

        BigDecimal result = parser.calculate(expression);

        assertEquals(expectedResult, result);
    }
}
