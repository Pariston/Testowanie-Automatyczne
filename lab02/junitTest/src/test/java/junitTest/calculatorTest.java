package junitTest;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class calculatorTest {
	// System Under Test test test
	calculator calc = new calculator();
	
	// 1b Dzielenie przez zero
	@Test(expected = ArithmeticException.class)
	public void zero() {
		calc.div(5, 0);
	}
	
	@BeforeClass
	public static void globalSetup() {
		System.out.println("Before all tests");
	}
	
	@AfterClass
	public static void globalTearDown() {
		System.out.println("After all tests");
	}
	
	@Test
	public void checkAdding() {
		// Sprawdzam, że jakieś 2 wartości są równe
		assertEquals(5, calc.add(2,  3));
		
	}
	
	@Test
	public void checkSub() {
		assertEquals(3, calc.sub(10, 7));
	}
	
	@Test
	public void checkDividing() {
		assertEquals(5, calc.div(10, 2));
		assertEquals(5, calc.div(10, 2));
		System.out.println("Dividing " + calc);
		assertNotEquals(5, calc.div(12, 2));
		//assertThat([1, 2, 3], hasItem(calc.div(6, 2)));
	}
	
	@Test
	public void checkMultiplying() {
		assertEquals(6, calc.multi(2, 3));
		assertNotEquals("OK", 6, calc.multi(3, 3));
	}
	
	@Test
	public void checkGreater() {
		assertTrue(calc.greater(5, 4));
		assertFalse(calc.greater(4, 5));
	}
}
