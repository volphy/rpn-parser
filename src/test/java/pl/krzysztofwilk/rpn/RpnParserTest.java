package pl.krzysztofwilk.rpn;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RpnParserTest {

    // TODO: add more test data (incl. corner cases)

    @Parameters(name = "{index}: rpn({0})={1} exception={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"5 1 2 + 4 * + 3 -", 14, false},
                {"5 1 2 + 4 x + 3 -", -1, true}
        });
    }

    private String expression;
    private int expectedResult;
    private boolean exceptionExpected;

    public RpnParserTest(String expression,
                         int expectedResult,
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
            thrown.expect(IllegalStateException.class);
        }

        int result = parser.calculate(expression);

        assertEquals(expectedResult, result);
    }
}
