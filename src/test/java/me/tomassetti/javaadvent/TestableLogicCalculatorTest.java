package me.tomassetti.javaadvent;

import me.tomassetti.javaadvent.calculators.TestableCalculator;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestableLogicCalculatorTest {

    @Test
    public void testLogic() {
        assertEquals(10, new TestableCalculator().calculate("add", 3, 7));
        assertEquals(-6, new TestableCalculator().calculate("sub", 7, 13));
        assertEquals(3, new TestableCalculator().calculate("mul", 3, 1));
        assertEquals(0, new TestableCalculator().calculate("div", 0, 7));
    }

    @Test(expected = ArithmeticException.class)
    public void testInvalidInputs() {
        assertEquals(0, new TestableCalculator().calculate("div", 0, 0));
    }

}
