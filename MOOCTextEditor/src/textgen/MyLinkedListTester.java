/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		} 
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		// TODO: Add more tests here
		try {
		list1.remove(2);
		fail("check index for removing data from list");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			list1.remove(-1);
			fail("check your indix bounadries..");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		assertEquals("check nodes ",list1.head,list1.head.next.prev);

	} 
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		emptyList.add(5);
		assertEquals("check size of emptylist ",1 , emptyList.size);
		assertEquals("element at last node is 5 ",(Integer)5,emptyList.tail.prev.data);
		
		try {
		emptyList.add(null);
		fail("cannot add null to a list");
		}
		catch (NullPointerException e) {
			
		}
		
		shortList.add("C");
		assertEquals("check for size of shortList ",3,shortList.size);	
		assertEquals("element at last node ","C",shortList.tail.prev.data);
		
		shortList.add("D");
		assertEquals("check for size of shortList ",4,shortList.size);
		assertEquals("last element is.. ","D",shortList.tail.prev.data);	
		assertEquals("checking for lasttolast ele,","C",shortList.tail.prev.prev.data);
		assertEquals("checking for lasttolast ele,","B",shortList.tail.prev.prev.prev.data);
		assertEquals("checking for lasttolast ele,","A",shortList.tail.prev.prev.prev.prev.data);
		assertEquals("checking for lasttolast ele,",shortList.head,shortList.tail.prev.prev.prev.prev.prev);

	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("emptylist size ",0,emptyList.size());
		emptyList.add(1);
		assertEquals("one element list ",1,emptyList.size());
		assertEquals("shortList size",2,shortList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		try {
			shortList.add(4,"D");
			fail("check indexing at add(x,y");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		try {
			emptyList.add(0,null);
			fail("no null vals for add(x,y)");
		}
		catch (NullPointerException e) {
			
		}
		
		try {
			list1.add(-1,999);
			fail("no neg indices..");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		test.add(1);
		test.add(3);
		test.add(1,2);
		assertEquals("value at index 1 ",(Integer)2,test.head.next.next.data);
		assertEquals("value at index 2 ",(Integer)3,test.tail.prev.data);
		assertEquals("value at index 0 ", (Integer)1,test.head.next.data);
		assertEquals("testing size ",3,test.size);
		
		shortList.add(2,"C");
		assertEquals("is value at end 'C'?","C",shortList.tail.prev.data);
		assertEquals("pos 1","B",shortList.tail.prev.prev.data);
		assertEquals("pos 0","A",shortList.head.next.data);
		assertEquals("size of shortlist A B C",3,shortList.size);
		
		shortList.add(2,"X");
		assertEquals("val at index 2 of shortList A B X C ","X",shortList.tail.prev.prev.data);
		assertEquals("val at index 3 of shortList A B X C ","C",shortList.tail.prev.data);
		assertEquals("val at index 1 of shortList A B X C ","B",shortList.head.next.next.data);
		assertEquals("size of shortList A B X C ",4,shortList.size);
		
		shortList.add(0,"Z");
		assertEquals("check index 0","Z",shortList.head.next.data);
		assertEquals("check index 1 ","A",shortList.head.next.next.data);
		assertEquals("size ",5,shortList.size);
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try {
	    list1.set(3,100);
	    fail("check index boundaries ");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			list1.set(0,null);
			fail("cannot take care of null values");
		}
		catch(NullPointerException e) {
			
		}
		
		int ans = list1.set(0,100);
		assertEquals(" check for correct value",65,ans);
		assertEquals(" check if correct value is set ", (Integer)100, list1.get(0));
		
		int ans2 = list1.set(2, 99);
		assertEquals(" check for correct value",42,ans2);
		assertEquals(" check if correct value is set ", (Integer)99, list1.get(2));
	}
	
	
	// TODO: Optionally add more test methods.
	
}
