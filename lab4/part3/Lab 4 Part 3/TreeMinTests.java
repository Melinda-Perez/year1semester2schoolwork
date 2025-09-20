import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

class TreeMinTests {
	@Test
	void testMinimumOfNullTree() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Minimum<Integer>().minimum((Node<Integer>)null);
		});
	}

	@Test
	void testMinimumOfSmallTreeWithNullNode() {
		var tree =
			new Branch<Integer>(
				new Branch<Integer>(
					null,
					new Leaf<Integer>(3)),
				new Leaf<Integer>(6));
		assertThrows(IllegalArgumentException.class, () -> {
			new Minimum<Integer>().minimum(tree);
		});
	}

	@Test
	void testMinimumOfIntegerLeaf() {
		assertEquals(1, new Minimum<Integer>().minimum(new Leaf<Integer>(1)));
		assertEquals(7, new Minimum<Integer>().minimum(new Leaf<Integer>(7)));
	}

	@Test
	void testMinimumOfDoubleLeft() {
		assertEquals(-5.5, new Minimum<Double>().minimum(new Leaf<Double>(-5.5)));
		assertEquals(22.0, new Minimum<Double>().minimum(new Leaf<Double>(22.0)));
	}

	@Test
	void testMinimumOfSingleBranch() {
		var tree =
			new Branch<String>(
				new Leaf<String>("A"),
				new Leaf<String>("B"));
		assertEquals("A", new Minimum<String>().minimum(tree));
	}

	@Test
	void testMinimumOfSmallTree() {
		var tree =
			new Branch<Integer>(
				new Branch<Integer>(
					new Leaf<Integer>(7),
					new Leaf<Integer>(3)),
				new Leaf<Integer>(6));
		assertEquals(3, new Minimum<Integer>().minimum(tree));
	}

	@Test
	void testMinimumOfLargerTree() {
		var tree =
			new Branch<Integer>(
				new Branch<Integer>(
					new Branch<Integer>(
						new Branch<Integer>(
							new Leaf<Integer>(7),
							new Leaf<Integer>(3)),
						new Branch<Integer>(
							new Leaf<Integer>(12),
							new Leaf<Integer>(9))),
					new Leaf<Integer>(3)),
				new Branch<Integer>(
					new Leaf<Integer>(7),
					new Branch<Integer>(
						new Branch<Integer>(
							new Leaf<Integer>(7),
							new Leaf<Integer>(11)),
						new Branch<Integer>(
							new Leaf<Integer>(2),
							new Leaf<Integer>(9)))));
		assertEquals(2, new Minimum<Integer>().minimum(tree));
	}

	@Test
	void testMinimumOfAnotherLargerTree() {
		var tree =
			new Branch<Integer>(
				new Branch<Integer>(
					new Branch<Integer>(
						new Branch<Integer>(
							new Branch<Integer>(
								new Leaf<Integer>(17),
								new Leaf<Integer>(18)),
							new Leaf<Integer>(8)),
						new Branch<Integer>(
							new Leaf<Integer>(12),
							new Leaf<Integer>(9))),
					new Leaf<Integer>(5)),
				new Branch<Integer>(
					new Leaf<Integer>(7),
					new Branch<Integer>(
						new Branch<Integer>(
							new Leaf<Integer>(7),
							new Leaf<Integer>(11)),
						new Branch<Integer>(
							new Branch<Integer>(
								new Leaf<Integer>(9),
								new Leaf<Integer>(13)),
							new Leaf<Integer>(9)))));
		assertEquals(5, new Minimum<Integer>().minimum(tree));
	}

	private class FakeNode<T extends Comparable<T>> implements Node<T> {
		// I'm a FakeNode
	}

	@Test
	void testMinimumOfFakeNode() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Minimum<Integer>().minimum(new FakeNode<Integer>());
		});
	}

	@Test
	void testMinimumOfTreeWithFakeNode() {
		var tree =
			new Branch<Integer>(
				new Branch<Integer>(
					new FakeNode<Integer>(),
					new Leaf<Integer>(3)),
				new Leaf<Integer>(6));
		assertThrows(IllegalArgumentException.class, () -> {
			new Minimum<Integer>().minimum(tree);
		});
	}
}
