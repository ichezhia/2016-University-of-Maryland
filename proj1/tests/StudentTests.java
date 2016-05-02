package tests;

import org.junit.*;

import submitserver.SubmitServer;

import static org.junit.Assert.*;

public class StudentTests {

	/*
	 * Tests the addSubmission() method to make sure it returns a non-null
	 * SubmitServer.
	 */
	@Test
	public void test1() {
		SubmitServer server = new SubmitServer();

		assertNotNull(server.addSubmission("John Smith", 90));
	}

	/*
	 * Tests the addSubmission() method to make sure adding multiple students
	 * return non-null SubmitServer.
	 */
	@Test
	public void test2() {
		SubmitServer server = new SubmitServer();
		server.addSubmission("John Smith", 90);
		assertNotNull(server.addSubmission("Joe Bloggs", 80));
	}

	/*
	 * Tests the addSubmission() method to make sure it returns a non-null
	 * SubmitServer. Also tests upper bound of score (100). Also tests ability
	 * to call method on the return from same addSubmission method on server.
	 */
	@Test
	public void test3() {
		SubmitServer server = new SubmitServer();

		assertNotNull(server.addSubmission("John Smith", 90).addSubmission("Joe Bloggs", 100));
	}

	/*
	 * Tests the addSubmission() method to make sure out of range scores dont
	 * work. Also tests numSubmissions() method.
	 */
	@Test
	public void test4() {
		SubmitServer server = new SubmitServer();

		server.addSubmission("John Doe", -1);
		server.addSubmission("John Doe", 50);
		server.addSubmission("John Doe", 101);

		assertEquals(1, server.numSubmissions("John Doe"));
	}

	/*
	 * Tests the addSubmission() method to make sure invalid names don't work.
	 * Also tests numSubmissions() method.
	 */
	@Test
	public void test5() {
		SubmitServer server = new SubmitServer();

		server.addSubmission("", 25);
		server.addSubmission(null, 50);
		server.addSubmission("John Doe", 75);

		assertEquals(1, server.numSubmissions());
	}
	
	/*
	 * Tests the addSubmission() method to make sure multiple names with multiple scores work.
	 * Also tests numSubmissions() method with and without names.
	 */
	@Test
	public void test6() {
		SubmitServer server = new SubmitServer();

		server.addSubmission("John Doe", 40);
		server.addSubmission("Joe Bloggs", 50);
		server.addSubmission("Joe Bloggs", 60);
		server.addSubmission("Sara", 90);

		assertEquals(4, server.numSubmissions());
		assertEquals(2, server.numSubmissions("Joe Bloggs"));
		assertEquals(1, server.numSubmissions("Sara"));
	}
	
	
	
	/*
	 * Tests the addSubmission() method to make sure multiple scores with same name and invalid scores work.
	 * Also tests bestSubmissionNumber() method to make sure it finds the correct submission number.
	 */
	@Test
	public void test7() {
		SubmitServer server = new SubmitServer();

		server.addSubmission("Sara", 90);
		server.addSubmission("Sara", -1);
		server.addSubmission("Sara", 90);
		server.addSubmission("Sara", 110);
		server.addSubmission("Sara", 89);

	    assertEquals(2, server.bestSubmissionNumber("Sara"));
	}

	/*
	 * Tests the addSubmission() method to make sure multiple scores with same name and invalid scores work.
	 * Also tests bestSubmissionScore() method to make sure it finds the highest score.
	 */
	@Test
	public void test8() {
		SubmitServer server = new SubmitServer();

		server.addSubmission("Sara", 90);
		server.addSubmission("Sara", -1);
		server.addSubmission("Sara", 90);
		server.addSubmission("Sara", 110);
		server.addSubmission("Sara", 89);

	    assertEquals(90, server.bestSubmissionScore("Sara"));
	}
	
	/*
	 * Tests the addSubmission() method to make sure multiple scores with same name and invalid scores work.
	 * Also tests satisfactory() method.
	 */
	@Test
	public void test9() {
		SubmitServer server = new SubmitServer();

		server.addSubmission("Sara", 90);
		server.addSubmission("Sara", -1);
		server.addSubmission("Sara", 90);
		server.addSubmission("Sara", 110);
		server.addSubmission("Sara", 89);
		
		server.addSubmission("John", 0);
		server.addSubmission("John", -1);
		server.addSubmission("John", 0);
		server.addSubmission("John", 110);
		server.addSubmission("John", 0);

	    assertTrue(server.satisfactory("Sara"));
	    assertFalse(server.satisfactory("John"));
	}
	
	/*
	 * Tests the addSubmission() method to make sure multiple scores with same name and invalid scores work.
	 * Also tests got extraCredit() method. Multiple 100s should not work, only 100 works.
	 */
	@Test
	public void test10() {
		SubmitServer server = new SubmitServer();

		server.addSubmission("Sara", 100);
		server.addSubmission("Sara", 100);
		server.addSubmission("Sara", 90);
		server.addSubmission("Sara", 0);
		server.addSubmission("Sara", 89);
		
		server.addSubmission("John", 0);
		server.addSubmission("John", 0);
		server.addSubmission("John", 0);
		
		server.addSubmission("Bob", 100);

	    assertEquals(1,server.gotExtraCredit());
	}
	
	
	/*
	 * Tests the addSubmission() method to make sure multiple scores with same name and invalid scores work.
	 * Also tests changeScore() method to make sure it changes to find bestSubmissionNumber and bestSubmissionScore.
	 */
	@Test
	public void test11() {
		SubmitServer server = new SubmitServer();

		server.addSubmission("Sara", 90);
		server.addSubmission("Sara", 91);
		server.addSubmission("Sara", 89);

		assertTrue(server.changeScore("Sara", 3, 92));
	    assertEquals(3, server.bestSubmissionNumber("Sara"));
	    assertEquals(92, server.bestSubmissionScore("Sara"));
	}
	
	
	
	
	
	
}
