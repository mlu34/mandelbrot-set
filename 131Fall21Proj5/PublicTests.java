import static org.junit.Assert.*;

import org.junit.Test;


public class PublicTests {

	@Test
	public void testBasicConstructorsAndGetters() {
		MyDouble a = new MyDouble(5.7), b = new MyDouble(-3.7);
		MyDouble d = new MyDouble(555.729);

		ComplexNumber x = new ComplexNumber(a, b);
		assertTrue(x.getReal().compareTo(a) == 0 && x.getImag().compareTo(b) == 0);

		ComplexNumber z = new ComplexNumber(d);
		assertTrue(z.getReal().compareTo(d) == 0 && z.getImag().compareTo(new MyDouble(0)) == 0);
	}

	@Test
	public void testCopyConstructor() {
		MyDouble a = new MyDouble(5.7), b = new MyDouble(-3.7);

		ComplexNumber x = new ComplexNumber(a, b);
		ComplexNumber y = new ComplexNumber(x);
		assertTrue(x != y);     // Check to be sure they are not aliased!
		assertTrue(y.getReal().compareTo(a) == 0 && y.getImag().compareTo(b) == 0);
	}

	@Test
	public void testAdd() {
		ComplexNumber a = new ComplexNumber(new MyDouble(1), new MyDouble(2));
		ComplexNumber b = new ComplexNumber(new MyDouble (3), new MyDouble (5));

		ComplexNumber c = a.add(b);

		assertTrue(c.equals(new ComplexNumber(new MyDouble(4), new MyDouble(7))));
	}

	@Test
	public void testSubtract() {
		ComplexNumber a = new ComplexNumber(new MyDouble(1), new MyDouble(2));
		ComplexNumber b = new ComplexNumber(new MyDouble (3), new MyDouble (5));

		ComplexNumber c = a.subtract(b);

		assertTrue(c.equals(new ComplexNumber(new MyDouble(-2), new MyDouble(-3))));
	}

	@Test
	public void testMult() {
		ComplexNumber a = new ComplexNumber(new MyDouble(1), new MyDouble(2));
		ComplexNumber b = new ComplexNumber(new MyDouble (3), new MyDouble (5));

		ComplexNumber c = a.multiply(b);

		assertTrue(c.equals(new ComplexNumber(new MyDouble(-7), new MyDouble(11))));
	}

	@Test 
	public void testDiv() {
		ComplexNumber a = new ComplexNumber(new MyDouble(1), new MyDouble(2));
		ComplexNumber b = new ComplexNumber(new MyDouble(3), new MyDouble(5));

		ComplexNumber c = a.divide(b);

		assertTrue(c.equals(new ComplexNumber(new MyDouble(13/34), new MyDouble(1/34))));
	}

	@Test 
	public void testEqComp() {
		ComplexNumber a = new ComplexNumber(new MyDouble(1), new MyDouble(2));
		ComplexNumber b = new ComplexNumber(new MyDouble(3), new MyDouble(5));
		ComplexNumber c = new ComplexNumber(new MyDouble(3), new MyDouble(5));
		
		assertFalse(a.equals(b));
		assertFalse(a.equals(c));
		assertTrue(b.equals(c));
		
		assertFalse(a.compareTo(b) == 1);
		assertFalse(b.compareTo(a) == -1);
		assertTrue(b.compareTo(c) == 0);
	}

	@Test
	public void testNorm() {
		ComplexNumber a = new ComplexNumber(new MyDouble(-3), new MyDouble(0));
		assertTrue(ComplexNumber.norm(a).equals(new MyDouble(3)));
	}

	@Test 
	public void testParse() {
		ComplexNumber a = ComplexNumber.parseComplexNumber("1.7    +   34i");
		ComplexNumber b = ComplexNumber.parseComplexNumber("1.7  -   34i");
		
		assertTrue(a.equals(new ComplexNumber(new MyDouble(1.7), new MyDouble(34))));
		assertTrue(b.equals(new ComplexNumber(new MyDouble(1.7), new MyDouble(-34))));
	}

}
