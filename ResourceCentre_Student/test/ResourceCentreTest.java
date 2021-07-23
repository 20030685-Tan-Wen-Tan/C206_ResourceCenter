import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResourceCentreTest {
	private Camcorder cc1;
	private Camcorder cc2;
	private Chromebook cb1;
	private Chromebook cb2;

	private ArrayList<Camcorder> camcorderList;
	private ArrayList<Chromebook> chromebookList;

	public ResourceCentreTest() {
		super();
	}

	@Before
	// methods that will be executed before any of the @test methods
	// create test data, object
	public void setUp() throws Exception {
		// prepare test data
		cc1 = new Camcorder("CC0011", "Nikon HDSLR", 40);
		cc2 = new Camcorder("CC0012", "Sony DSC-RX100M7", 20);
		cb1 = new Chromebook("CB0011", "My Google Chromebook 1st", "Mac OS");
		cb2 = new Chromebook("CB0012", "SAMSUNG Chromebook 4+", "Win 10");

		camcorderList = new ArrayList<Camcorder>(); // empty list
		chromebookList = new ArrayList<Chromebook>(); // empty list
	}

	@Test
	public void testAddCamcorder() {
		// Item list is not null, so that can add a new item
		// Checking camcorderList is not null, boundary
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);

		// Given an empty list, after adding 1 item, the size of the list is 1
		// Call the add camcorder, add cc1 into the camcorder list.
		// And check the expected result, that the size should be 1.
		// Will pass if true, if the size is 1.
		ResourceCentre.addCamcorder(camcorderList, cc1);
		assertEquals("Test if that Camcorder arraylist size is 1?", 1, camcorderList.size());

		// The item just added is as same as the first item of the list
		// Get the first item out and compare with the one added.
		assertSame("Test that Camcorder is added same as 1st item of the list?", cc1, camcorderList.get(0));

		// Add another item. test The size of the list is 2?
		// Check size of 2.
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test that Camcorder arraylist size is 2?", 2, camcorderList.size());
	}

	@Test
	public void testAddChromebook() {
		// fail("Not yet implemented");
		// write your code here

		// No.1
		assertNotNull("Test if there is valid Chromebook arraylist to add to", chromebookList);

		// No.2 - size 1
		ResourceCentre.addChromebook(chromebookList, cb1);
		assertEquals("Test if that Chromebook arraylist size is 1?", 1, chromebookList.size());

		// No.2 - same item added, size 1
		assertSame("Test that Chromebook is added same as 1st item of the list?", cb1, chromebookList.get(0));

		// No.3 - size 2
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test that Chromebook arraylist size is 2?", 2, chromebookList.size());

		// No.3 - same item added, size 2
		assertSame("Test that Chromebook is added same as 2nd item of the list?", cb2, chromebookList.get(1));

	}

	@Test
	public void testRetrieveAllCamcorder() {
		// Test if Item list is not null but empty, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);

		// test if the list of camcorders retrieved from the SourceCentre is empty
		String allCamcorder = ResourceCentre.retrieveAllCamcorder(camcorderList);
		String testOutput = "";
		assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);

		// Given an empty list, after adding 2 items, test if the size of the list is 2
		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test if that Camcorder arraylist size is 2?", 2, camcorderList.size());

		// test if the expected output string same as the list of camcorders retrieved
		// from the SourceCentre
		allCamcorder = ResourceCentre.retrieveAllCamcorder(camcorderList);

		testOutput = String.format("%-10s %-30s %-10s %-10s %-20d\n", "CC0011", "Nikon HDSLR", "Yes", "", 40);
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20d\n", "CC0012", "Sony DSC-RX100M7", "Yes", "", 20);

		assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);

	}

	@Test
	public void testRetrieveAllChromebook() {
		// fail("Not yet implemented");
		// write your code here

		// No.1
		assertNotNull("Test if there is valid Chromebook arraylist to add to", chromebookList);

		String allChromebook = ResourceCentre.retrieveAllChromebook(chromebookList);
		String testOutput = "";
		assertEquals("Check that ViewAllChromebooklist", testOutput, allChromebook);

		// No.2
		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test if that Chromebook arraylist size is 2?", 2, chromebookList.size());

		// No.3
		allChromebook = ResourceCentre.retrieveAllChromebook(chromebookList);

		testOutput = String.format("%-10s %-30s %-10s %-10s %-20s\n", "CB0011", "My Google Chromebook 1st", "Yes", "",
				"Mac OS");
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20s\n", "CB0012", "SAMSUNG Chromebook 4+", "Yes", "",
				"Win 10");

		assertEquals("Check that ViewAllChromebooklist", testOutput, allChromebook);

	}

	@Test
	public void testDoLoanCamcorder() {
		// fail("Not yet implemented");
		// write your code here

		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);

		// Item list is not null, newly added item can be loaned out successfully.
		assertNotNull("Test that list is not null", camcorderList);
		assertTrue("Test if item can be loaned successfully",
				ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "01/01/2010"));

		// Item availability is false, when item is loaned out.
		assertFalse("Test that itemAvailability is false when loaned out", cc1.getIsAvailable());

		// After item is loaned out, it cannot be loaned again.
		assertFalse("Test that item cannot be loaned once loaned out",
				ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "01/01/2010"));

		// Another item in the list can be loaned out successfully.
		assertTrue("Test that item can be loaned out successfully",
				ResourceCentre.doLoanCamcorder(camcorderList, "CC0012", "01/01/2010"));
		assertFalse("Test that itemAvailability is false when loaned out", cc2.getIsAvailable());

		// Item can be loaned out again when returned.
		assertTrue("Return item.", ResourceCentre.doReturnCamcorder(camcorderList, "CC0011"));
		assertTrue("Test that item can be loaned again when returned.",
				ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "01/01/2010"));

	}

	@Test
	public void testDoLoanChromebook() {
		// fail("Not yet implemented");
		// write your code here

		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);

		// Item list is not null, newly added item can be loaned out successfully.
		assertNotNull("Test that list is not null", chromebookList);
		assertTrue("Test if item can be loaned successfully",
				ResourceCentre.doLoanChromebook(chromebookList, "CB0011", "01/01/2010"));

		// Item availability is false, when item is loaned out.
		assertFalse("Test that itemAvailability is false when loaned out", cb1.getIsAvailable());

		// After item is loaned out, it cannot be loaned again.
		assertFalse("Test that item cannot be loaned once loaned out",
				ResourceCentre.doLoanChromebook(chromebookList, "CB0011", "01/01/2010"));

		// Another item in the list can be loaned out successfully.
		assertTrue("Test that item can be loaned out successfully",
				ResourceCentre.doLoanChromebook(chromebookList, "CB0012", "01/01/2010"));
		assertFalse("Test that itemAvailability is false when loaned out", cb2.getIsAvailable());

		// Item can be loaned out again when returned.
		assertTrue("Return item.", ResourceCentre.doReturnChromebook(chromebookList, "CB0011"));
		assertTrue("Test that item can be loaned again when returned.",
				ResourceCentre.doLoanChromebook(chromebookList, "CB0011", "01/01/2010"));

	}

	@Test
	public void testDoReturnCamcorder() {
		// fail("Not yet implemented");
		// write your code here

		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);

		// Item list is not null, newly added item cannot be returned successfully.
		assertNotNull("Test that list is not null", camcorderList);
		assertFalse("Test that item cannot be returned if not loaned",
				ResourceCentre.doReturnCamcorder(camcorderList, "CC0011"));

		// Item can be returned successfully, when loaned out.
		ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "01/01/2010");
		assertTrue("Test that item can be returned when loaned out.",
				ResourceCentre.doReturnCamcorder(camcorderList, "CC0011"));

		// Item can be loaned again and returned successfully
		assertTrue("Test that item can be loaned again when returned.",
				ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "01/01/2010"));
		assertTrue("Test that item can be returned when reloaned.",
				ResourceCentre.doReturnCamcorder(camcorderList, "CC0011"));

		// Another item loaned out, can be returned successfully.
		ResourceCentre.doLoanCamcorder(camcorderList, "CC0012", "01/01/2010");
		assertTrue("Test that other loaned items can be returned.",
				ResourceCentre.doReturnCamcorder(camcorderList, "CC0012"));

	}

	@Test
	public void testDoReturnChromebook() {
		// fail("Not yet implemented");
		// write your code here

		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);

		// Item list is not null, newly added item cannot be returned successfully.
		assertNotNull("Test that list is not null", chromebookList);
		assertFalse("Test that item cannot be returned if not loaned",
				ResourceCentre.doReturnChromebook(chromebookList, "CB0011"));

		// Item can be returned successfully, when loaned out.
		ResourceCentre.doLoanChromebook(chromebookList, "CB0011", "01/01/2010");
		assertTrue("Test that item can be returned when loaned out.",
				ResourceCentre.doReturnChromebook(chromebookList, "CB0011"));

		// Item can be loaned again and returned successfully
		assertTrue("Test that item can be loaned again when returned.",
				ResourceCentre.doLoanChromebook(chromebookList, "CB0011", "01/01/2010"));
		assertTrue("Test that item can be returned when reloaned.",
				ResourceCentre.doReturnChromebook(chromebookList, "CB0011"));

		// Another item loaned out, can be returned successfully.
		ResourceCentre.doLoanChromebook(chromebookList, "CB0012", "01/01/2010");
		assertTrue("Test that other loaned items can be returned.",
				ResourceCentre.doReturnChromebook(chromebookList, "CB0012"));

	}

	@After
	public void tearDown() throws Exception {
		cc1 = null;
		cc2 = null;
		cb1 = null;
		cb2 = null;
		camcorderList = null;
		chromebookList = null;

	}

}
