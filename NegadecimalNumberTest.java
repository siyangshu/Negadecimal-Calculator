import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class NegadecimalNumberTest {
	NegadecimalNumber a;
	@Before
	public void setUp() throws Exception {
		a = new NegadecimalNumber(10);
	}

	@Test
	public void testNegadecimalNumberString() {
		NegadecimalNumber n;
		n = new NegadecimalNumber("71025");
		assertEquals(68985, n.decimalValue());
		n = new NegadecimalNumber("10");
		assertEquals(-10, n.decimalValue());
		n = new NegadecimalNumber("111");
		assertEquals(91, n.decimalValue());

	}

	@Rule
	public ExpectedException exception = ExpectedException.none();
	@Test
	public void testIllegalArgumentException(){
	    exception.expect(IllegalArgumentException.class);
	    new NegadecimalNumber("abc");
	    new NegadecimalNumber("-1");
	}

	@Test
	public void testNegadecimalNumberInt() {
		NegadecimalNumber n;
		n = new NegadecimalNumber(91);
		assertTrue(n.toString().equals("111"));
		n = new NegadecimalNumber(-10);
		assertTrue(n.toString().equals("10"));
		n = new NegadecimalNumber(68985);
		assertTrue(n.toString().equals("71025"));
		
	}

	@Test
	public void testAdd() {
		NegadecimalNumber b = new NegadecimalNumber(10);
		assertTrue(new NegadecimalNumber(20).equals(a.add(b)));
		
		NegadecimalNumber c = new NegadecimalNumber(45);
		assertTrue(new NegadecimalNumber(55).equals(a.add(c)));
		
		NegadecimalNumber d = new NegadecimalNumber(100);
		assertFalse(new NegadecimalNumber(30).equals(a.add(d)));
	}
	
	@Test
	public void testSubtract() {
		NegadecimalNumber b = new NegadecimalNumber(10);
		assertTrue(new NegadecimalNumber(0).equals(a.subtract(b)));
		
		NegadecimalNumber c = new NegadecimalNumber(200);
		assertTrue(new NegadecimalNumber(-190).equals(a.subtract(c)));
		
		NegadecimalNumber d = new NegadecimalNumber(100);
		assertFalse(new NegadecimalNumber(30).equals(a.subtract(d)));
	}

	@Test
	public void testMultiply() {
		NegadecimalNumber b = new NegadecimalNumber(10);
		assertTrue(new NegadecimalNumber(100).equals(a.multiply(b)));
		
		NegadecimalNumber c = new NegadecimalNumber(-10);
		assertTrue(new NegadecimalNumber(-100).equals(a.multiply(c)));
		
		NegadecimalNumber d = new NegadecimalNumber(5);
		assertFalse(new NegadecimalNumber(0).equals(a.multiply(d)));
	}

	@Test
	public void testDivide() {
		NegadecimalNumber b = new NegadecimalNumber(10);
		assertTrue(new NegadecimalNumber(1).equals(a.divide(b)));
		
		NegadecimalNumber c = new NegadecimalNumber(3);
		assertTrue(new NegadecimalNumber(3).equals(a.divide(c)));
		
		NegadecimalNumber d = new NegadecimalNumber(-1);
		assertFalse(new NegadecimalNumber(10).equals(a.divide(d)));
	}

	@Test
	public void testRemainder() {
		NegadecimalNumber b = new NegadecimalNumber(10);
		assertTrue(new NegadecimalNumber(0).equals(a.remainder(b)));
		
		NegadecimalNumber c = new NegadecimalNumber(3);
		assertTrue(new NegadecimalNumber(1).equals(a.remainder(c)));
		
		NegadecimalNumber d = new NegadecimalNumber(-3);
		assertFalse(new NegadecimalNumber(-1).equals(a.remainder(d)));
	}

	@Test
	public void testNegate() {
		NegadecimalNumber b = new NegadecimalNumber(0);
		assertTrue(new NegadecimalNumber(0).equals(b.negate()));
		
		NegadecimalNumber c = new NegadecimalNumber(3);
		assertTrue(new NegadecimalNumber(-3).equals(c.negate()));
		
		NegadecimalNumber d = new NegadecimalNumber(-3);
		assertFalse(new NegadecimalNumber(-3).equals(d.negate()));
	}

	@Test
	public void testDecimalValue() {
		NegadecimalNumber b = new NegadecimalNumber(50);
		assertEquals(50, b.decimalValue());
		
		NegadecimalNumber c = new NegadecimalNumber("191");
		assertEquals(11, c.decimalValue());
		
		NegadecimalNumber d = new NegadecimalNumber(192);
		assertNotEquals(12, d.decimalValue());
	}

	@Test
	public void testEquals() {
		NegadecimalNumber b = new NegadecimalNumber(10);
		assertTrue(a.equals(b));
		
		NegadecimalNumber c = new NegadecimalNumber("190");
		assertTrue(a.equals(c));
		
		NegadecimalNumber d = new NegadecimalNumber(190);
		assertFalse(a.equals(d));
	}
	
	@Test
	public void testToString() {
		NegadecimalNumber b = new NegadecimalNumber(11);
		assertEquals("191", b.toString());
		
		NegadecimalNumber c = new NegadecimalNumber("1");
		assertEquals(1, c.decimalValue());
		
		NegadecimalNumber d = new NegadecimalNumber(11);
		assertNotEquals(191, d.decimalValue());
	}

}