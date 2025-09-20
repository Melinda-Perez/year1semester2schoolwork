import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class UserGroupTest {

	UserGroup users;
	
	@BeforeEach
	public void setup() {
		users = new UserGroup();
		
		users.getUsers().add(new User("id0", "user", "Kevin Rowe"));
		users.getUsers().add(new User("id1", "user", "Jack Daniels"));
		users.getUsers().add(new User("id2", "user", "Barry Smith"));
		users.getUsers().add(new User("id3", "user", "Hugh Davies"));
		users.getUsers().add(new User("id4", "user", "Pete Jackson"));
		users.getUsers().add(new User("id5", "user", "Jerry Simpson"));
		users.getUsers().add(new User("id6", "user", "Teresa Szelankovic"));
		users.getUsers().add(new User("id7", "user", "Brian Degrasse Tyson"));
		users.getUsers().add(new User("id8", "user", "Mike Hardcastle"));
		users.getUsers().add(new User("id9", "user", "Danny Hanson"));
	}
	
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

	@Test
	@DisplayName("Test User and getter methods")
	void test() {
		User user = new User("aj", "user", "Alice Jones");
		assertEquals("aj", user.getUsername());
		assertEquals("user", user.getUserType());
		assertEquals("Alice Jones", user.getName());
	}
	
	@Test
	@DisplayName("Test the sample data")
	void testSampleData() {
		UserGroup users = new UserGroup();
		users.addSampleData();
		ArrayList<User> allUsers = users.getUsers();
		assertEquals(10, allUsers.size(), "Testing size of sample data is correct");
	}
	
	@Test
	@DisplayName("Tests the removeFirstUser method")
	void testGetUser() {	
		User firstUser = users.getUser(0);
		users.removeFirstUser();
		assertFalse(users.getUsers().contains(firstUser), "Testing that the first user was removed");
		
	}
	
	@Test
	@DisplayName("Tests the removeLastUser method")
	void testGetUser2() {
		User lastUser = users.getUser(users.getUsers().size()-1);
		users.removeLastUser();
		assertFalse(users.getUsers().contains(lastUser), "Testing that the last user was removed");
		
	}

	@Test
	@DisplayName("Tests the removeUser method")
	void testGetUser3() {
		User newFirstUser = users.getUser(0);
		String newFirstUserName = users.getUser(0).getUsername();
		users.removeUser(newFirstUserName);
		
		assertFalse(users.getUsers().contains(newFirstUser), "Testing that the new first user was removed");
	}
}
