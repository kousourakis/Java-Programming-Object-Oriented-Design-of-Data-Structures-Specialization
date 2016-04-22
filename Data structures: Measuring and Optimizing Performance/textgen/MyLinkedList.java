package textgen;

import java.security.GeneralSecurityException;
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
		super();
		head = new LLNode(null);
		tail = new LLNode(null);
		head.next=tail;
		tail.prev=head;
		size=0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		LLNode<E> node_ins=new LLNode(element);
		tail.prev.next=node_ins;
		node_ins.prev=tail.prev;
		node_ins.next=tail;
		tail.prev=node_ins;
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index>size-1 || index<0)throw new IndexOutOfBoundsException();
		LLNode<E> temp=head.next;
		for(int i=0;i<index;i++){
			temp=temp.next;
		}
		return temp.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (index<0||index>size)throw new IndexOutOfBoundsException();
		LLNode<E> temp=new LLNode(element);
		LLNode<E> temp2=head.next;
		for(int i=0;i<index;i++){
			temp2=temp2.next;
		}
		temp.prev=temp2.prev;
		temp.prev.next=temp;
		temp2.prev=temp;
		temp.next=temp2;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
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
		if (index<0||index>size)throw new IndexOutOfBoundsException();
		LLNode<E> temp=head.next;
		for(int i=0;i<index;i++){
			temp=temp.next;
		}
		temp.next.prev=temp.prev;
		temp.prev.next=temp.next;
		size--;
		return temp.data;
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
		if (index<0||index>size)throw new IndexOutOfBoundsException();
		if (element==null)throw new NullPointerException();
		E oldvalue;
		LLNode<E> temp=head.next;
		for(int i=0;i<index;i++){
			temp=temp.next;
		}
		oldvalue=temp.data;
		temp.data=element;
		return oldvalue;
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
