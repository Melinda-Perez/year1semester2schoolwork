import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnimalTest {


	class OutputCapturer {
		private PrintStream origOut;

		private ByteArrayOutputStream outputStream;

		public void start()
		{
			this.origOut = System.out;
			this.outputStream = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(this.outputStream);
			System.setOut(ps);
		}

		public String getOutput() {
			System.out.flush();
			return this.outputStream.toString().replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
		}
		public void stop() {
			System.setOut(this.origOut);
		}
	}

	OutputCapturer outputHarness;

	@BeforeEach
	public void setup() {
		this.outputHarness = new OutputCapturer();
		this.outputHarness.start();
	}

	@AfterEach
	public void tearDown() {
		this.outputHarness.stop();
	}
	

	private String wolfName = "Fuzzy";
	private int wolfAge = 8;	
	
	private String parrotName = "Squarker";
	private int parrotAge = 5;	
	
	@Test
	@DisplayName("Test wolf constructor and getters")
	void wolfTest() {
		
		Wolf wolf = new Wolf(wolfName, wolfAge);
		assertEquals(wolfName, wolf.getName(), "Testing that the wolf gets his assigned name");
		assertEquals(wolfAge, wolf.getAge(), "Testing that the wolf gets his assigned age");
	}
	
	
	@Test
	@DisplayName("Test Wolf's methods")
	void wolfMethodsTest() {
		
		Wolf wolf = new Wolf(wolfName, wolfAge);
		
		List<String> cm = new ArrayList<String> ();
		Method[] classmethods = wolf.getClass().getDeclaredMethods();
		for(int i = 0; i<classmethods.length; i++) {
			cm.add(classmethods[i].getName());
		}
		
		assertFalse(cm.contains("getName"), "Testing that the getName method is not in the Wolf class");
		assertFalse(cm.contains("getAge"), "Testing that the getAge method is not in the Wolf class");
		assertTrue(cm.contains("makeNoise"), "Testing that the makeNoise method is in the Wolf class");
	}

	@Test
	@DisplayName("Test Animal's methods")
	void wolfAnimalMethodsTest() {
		
		Wolf wolf = new Wolf(wolfName, wolfAge);
			
		List<String> scm = new ArrayList<String> ();
		Method[] superclassmethods = wolf.getClass().getSuperclass().getDeclaredMethods();
		for(int i = 0; i<superclassmethods.length; i++) {
			scm.add(superclassmethods[i].getName());
		}
		
		assertTrue(scm.contains("getName"), "Testing that the getName method is in the Animal class");
		assertTrue(scm.contains("getAge"), "Testing that the getAge method is in the Animal class");
		assertFalse(scm.contains("makeNoise"), "Testing that the makeNoise method is not in the Animal class");
	}
		
	@Test
	@DisplayName("Test wolf class fields")
	void wolfFieldsTest() {
		
		Wolf wolf = new Wolf(wolfName, wolfAge);
			
		List<String> cf = new ArrayList<String> ();
		Field[] classfields = wolf.getClass().getDeclaredFields();
		for(int i = 0; i<classfields.length; i++) {
			cf.add(classfields[i].getName());
		}

		List<String> f = new ArrayList<String> ();
		Field[] fields = wolf.getClass().getSuperclass().getDeclaredFields();
		for(int i = 0; i<fields.length; i++) {
			f.add(fields[i].getName());
		}
		
		assertEquals(0, classfields.length, "Testing that there are no class fields in the Wolf class");
		assertTrue(fields.length>0, "Testing that there are class fields in the Animal class");
	
	}	
	
	@Test
	@DisplayName("Test parrot constructor and getters")
	void parrotTest() {
		
		Parrot parrot = new Parrot(parrotName, parrotAge);
		
		assertEquals(parrotName, parrot.getName(), "Testing that the parrot gets his assigned name");
		assertEquals(parrotAge, parrot.getAge(), "Testing that the parrot gets his assigned age");
	}
	
	@Test
	@DisplayName("Test parrot makeNoise method")
	void parrotMakeNoiseTest() {
		
		Parrot parrot = new Parrot(parrotName, parrotAge);
		parrot.makeNoise();

		String output = outputHarness.getOutput();

		assertTrue(output.length()>0, "Testing that noise is printed");
	}
	
	@Test
	@DisplayName("Test Parrot's methods")
	void parrotMethodsTest() {
		
		Parrot parrot = new Parrot(parrotName, parrotAge);
		
		List<String> cm = new ArrayList<String> ();
		Method[] classmethods = parrot.getClass().getDeclaredMethods();
		for(int i = 0; i<classmethods.length; i++) {
			cm.add(classmethods[i].getName());
		}
		
		assertFalse(cm.contains("getName"), "Testing that the getName method is not in the Wolf class");
		assertFalse(cm.contains("getAge"), "Testing that the getAge method is not in the Wolf class");
		assertTrue(cm.contains("makeNoise"), "Testing that the makeNoise method is in the Wolf class");
	}

	@Test
	@DisplayName("Test Animal's methods")
	void parrotAnimalMethodsTest() {
		
		Parrot parrot = new Parrot(parrotName, parrotAge);
			
		List<String> scm = new ArrayList<String> ();
		Method[] superclassmethods = parrot.getClass().getSuperclass().getDeclaredMethods();
		for(int i = 0; i<superclassmethods.length; i++) {
			scm.add(superclassmethods[i].getName());
		}
		
		assertTrue(scm.contains("getName"), "Testing that the getName method is in the Animal class");
		assertTrue(scm.contains("getAge"), "Testing that the getAge method is in the Animal class");
		assertFalse(scm.contains("makeNoise"), "Testing that the makeNoise method is not in the Animal class");
	}
		
	@Test
	@DisplayName("Test parrot class fields")
	void parrotFieldsTest() {
		
		Parrot parrot = new Parrot(parrotName, parrotAge);
			
		List<String> cf = new ArrayList<String> ();
		Field[] classfields = parrot.getClass().getDeclaredFields();
		for(int i = 0; i<classfields.length; i++) {
			cf.add(classfields[i].getName());
		}

		List<String> f = new ArrayList<String> ();
		Field[] fields = parrot.getClass().getSuperclass().getDeclaredFields();
		for(int i = 0; i<fields.length; i++) {
			f.add(fields[i].getName());
		}
		
		assertEquals(0, classfields.length, "Testing that there are no class fields in the Wolf class");
		assertTrue(fields.length>0, "Testing that there are class fields in the Animal class");
	
	}
	@Test
	@DisplayName("Test wolf makeNoise method")
	void wolflMakeNoiseTest() {
		
		OutputCapturer outputHarness;
		outputHarness = new OutputCapturer();
		outputHarness.start();

		Wolf wolf = new Wolf("Fuzzy", 8);
		wolf.makeNoise();

		outputHarness.stop();
		String w_noise = outputHarness.getOutput();

		assertTrue(w_noise.length()>0, "Testing that noise is printed");
	}
	
	@Test
	@DisplayName("Test make noise inhertience")
	void differentNoiseTest() {
		
		OutputCapturer outputHarness;
		outputHarness = new OutputCapturer();
		outputHarness.start();

		Wolf wolf = new Wolf("Fuzzy", 8);
		wolf.makeNoise();

		outputHarness.stop();
		String w_noise = outputHarness.getOutput();
		
		outputHarness = new OutputCapturer();
		outputHarness.start();

		Parrot parrot = new Parrot("Squawker", 5);
		parrot.makeNoise();

		outputHarness.stop();
		String p_noise = outputHarness.getOutput();
		
		assertFalse(p_noise.equals(w_noise), "Testing that a parraot and wolf make different noises");
	}
}
