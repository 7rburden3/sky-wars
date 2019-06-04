package grid;

import static org.junit.Assert.*;

import org.junit.Test;

public class SquareTest {

	@Test
	public void testSetNumber() {
		Row r1 = new Row();
		int number = 1;

		r1.setNumber(number);

		int actual = r1.getNumber();
		int expected = number;

		assertTrue(actual==expected);

	}

}
