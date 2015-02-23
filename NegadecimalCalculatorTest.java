import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NegadecimalCalculatorTest {
	NegadecimalCalculator calc;
	@Before
	public void setUp() throws Exception {
		 calc = new NegadecimalCalculator();
	}

	@Test
	public void testEvaluateClear() {
		assertTrue(calc.evaluate("clear").equals("0"));
	}
	
	@Test
	public void testEvaluateNdn() {
		assertTrue(calc.evaluate("3").equals("3"));
	}
	
	@Test
	public void testEvaluateDecimalInt() {
		assertTrue(calc.evaluate("decimal 10").equals("190"));
		assertTrue(calc.evaluate("decimal -10").equals("10"));
	}
	
	@Test
	public void testEvaluateArithmetic() {
		calc.evaluate("10");
		assertTrue(calc.evaluate("+10").equals("20"));
		assertTrue(calc.evaluate("+     10").equals("30"));
		calc.evaluate("clear");
		calc.evaluate("10");
		assertTrue(calc.evaluate("*10").equals("100"));	
	}

}
