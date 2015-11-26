package me.tomassetti.javaadvent;

import javaslang.control.Either;
import me.tomassetti.javaadvent.calculators.ReallyTestableCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReallyTestableLogicCalculatorTest {

    @Test
    public void testLogic() {
        assertEquals(Either.right(10L), new ReallyTestableCalculator().calculate("add", 3, 7));
        assertEquals(Either.right(-6L), new ReallyTestableCalculator().calculate("sub", 7, 13));
        assertEquals(Either.right(3L), new ReallyTestableCalculator().calculate("mul", 3, 1));
        assertEquals(Either.right(0L), new ReallyTestableCalculator().calculate("div", 0, 7));
    }

    @Test(expected = ArithmeticException.class)
    public void testInvalidOperation() {
        Either<me.tomassetti.javaadvent.calculators.Error, Long> res = new ReallyTestableCalculator().calculate("div", 0, 0);
        assertEquals(true, res.isLeft());
        assertEquals(400, res.left().get().getHttpCode());
    }

    @Test
    public void testUnknownOperation() {
        Either<me.tomassetti.javaadvent.calculators.Error, Long> res = new ReallyTestableCalculator().calculate("foo", 0, 0);
        assertEquals(true, res.isLeft());
        assertEquals(404, res.left().get().getHttpCode());
    }

}
