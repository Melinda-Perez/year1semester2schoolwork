import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

class ArrayMinTests {
	@Test
	void testMinimumOfNullArray() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Minimum<Integer>().minimum((Integer[])null);
		  });
	}

	@Test
	void testMinimumOfEmptyArray() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Minimum<Integer>().minimum(new Integer[] {});
		  });
	}

	@Test
	void testMinimumOfSingletonIntegerArray() {
		assertEquals(1, new Minimum<Integer>().minimum(new Integer[] {1}));
		assertEquals(4, new Minimum<Integer>().minimum(new Integer[] {4}));
	}

	@Test
	void testMinimumOfSingletonDoubleArray() {
		assertEquals(-5.5, new Minimum<Double>().minimum(new Double[] {-5.5}));
		assertEquals(22.0, new Minimum<Double>().minimum(new Double[] {22.0}));
	}

	@Test
	void testMinimumOfIntegerArray() {
		assertEquals(-10, new Minimum<Integer>().minimum(new Integer[] {-1, -4, -8, -2, -10}));
	}

	@Test
	void testMinimumOfDoubleArray() {
		assertEquals(0.9, new Minimum<Double>().minimum(new Double[] {5.5, 2.3, 11.0, 0.9, Math.PI}));
	}

	@Test
	void testMinimumOfStringArray() {
		assertEquals("Ducks", new Minimum<String>().minimum(new String[] {"Swans", "Ducks", "Geese"}));
	}
}
