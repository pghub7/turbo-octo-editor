package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
			size = 0;
			head = new LLNode<E>(null);
			tail = new LLNode<E>(null);
			head.next = tail;
			tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(size == 0 && element != null) {
			size+=1;
			LLNode<E> newNode = new LLNode<E>(element);
			head.next = newNode;
			newNode.prev = head;
			newNode.next = tail;
			tail.prev = newNode;
			//System.out.println("p1:" + newNode.data + size);
			return true;
		}
		
		else if(element == null) {
			throw new NullPointerException("element to be added cannot be null.");
		}
		
		
		else {	
			size+=1;
			LLNode<E> newNode = new LLNode<E>(element);
			newNode.prev = tail.prev;
			tail.prev.next = newNode;
			newNode.next = tail;
			tail.prev = newNode;
			//System.out.println("p2:" + newNode.data + size);
			//System.out.println("previous node data: " + newNode.prev.data);
			//System.out.println("currNode data: " + newNode.data);
		
		
		return true;
		}

	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method.
		
		if(index >= size || index < 0 || size == 0)
			throw new IndexOutOfBoundsException("index for getting element from list is OTOFBD.");
		
		else {
		LLNode<E> currNode = new LLNode<E>(null);
		currNode = this.head;
		E data;
		
		for(int i = 0;i<=index;i++) {
			currNode = currNode.next;
			//System.out.println("currNode: "+currNode.data);
		}
		
		data = currNode.data;
		return data;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		
		if(index>size|| index < 0)
			throw new IndexOutOfBoundsException();
		
		else if(element == null)
			throw new NullPointerException("No null values to be added to LL bruh..");
		
		else if(index == size) {
			add(element);
		}
		
		else {
			//System.out.println(this.size);
			size+=1;
			LLNode<E> newNode = new LLNode<E>(element);
			LLNode<E> currNode = new LLNode<E>(null);
			currNode = this.head;
			for(int i = 0; i<=index;i++) {
				//System.out.println(i);
				currNode = currNode.next;
				//System.out.println("currNode: " + currNode.data);

			}
			newNode.next = currNode;
			newNode.prev = currNode.prev;
			currNode.prev.next = newNode;
			currNode.prev = newNode;
			//System.out.println("currNode: " + currNode.data);
			//System.out.println("newNode: " + newNode.data);
		
		}
		

	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException("cannot remove element since index is OOB"); 
		}
		
		else {
			size-=1;
			LLNode<E> currNode = new LLNode<E>(null);
			currNode = head;
			for(int i = 0; i<=index; i++) {
				currNode = currNode.next;
			}
			currNode.next.prev = currNode.prev;
			currNode.prev.next = currNode.next;
			
			return currNode.data;
		}
		
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(index >= size || index < 0 ) {
			throw new IndexOutOfBoundsException("index boundaries not properly set");
		}
		else if(element == null) {
			throw new NullPointerException("no setting null elements in list");
		}
		else {
			LLNode<E> currNode = new LLNode<E>(null);
			currNode = this.head;
			for(int i = 0; i<=index; i++) {
				currNode = currNode.next;
			}
			E dataToSend = currNode.data;
			currNode.data = element;
			return dataToSend;
		}
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
