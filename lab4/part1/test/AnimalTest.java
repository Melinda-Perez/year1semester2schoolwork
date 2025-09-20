

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnimalTest {
	@Test
	@DisplayName("Tests that Wolf implements Comparable<Animal>")
	void wolfImplementsComparable() {
		Wolf wolf = new Wolf("Denali", 4);
		assertTrue(wolf instanceof Comparable<Animal>);
	}

	@Test
	@DisplayName("Tests that Parrot implements Comparable<Animal>")
	void parrotImplementsComparable() {
		Parrot parrot = new Parrot("Archie", 6);
		assertTrue(parrot instanceof Comparable<Animal>);
	}

	@Test
	@DisplayName("Tests that Wolves compare correctly")
	void wolfComparesByAge() {
		Wolf aidan = new Wolf("Aidan", 4);
		Wolf malik = new Wolf("Malik", 8);
		assertTrue(aidan.compareTo(malik) < 0);
		assertTrue(malik.compareTo(aidan) > 0);
		assertTrue(malik.compareTo(malik) == 0);
	}

	@Test
	@DisplayName("Tests that Wolves compare correctly")
	void compareAnimalsByAge() {
		Parrot patrick = new Parrot("Patrick", 11);
		Wolf shadow = new Wolf("Shadow", 8);
		assertTrue(patrick.compareTo(shadow) > 0);
		assertTrue(shadow.compareTo(patrick) < 0);
	}
}
