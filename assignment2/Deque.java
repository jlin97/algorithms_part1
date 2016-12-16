import java.util.*;

public class Deque<Item> implements Iterable<Item> {
	private Node head = null, tail = null;
	private int size = 0;
	private class Node{
		Item item;
		Node next;
		Node prev;
	}
	public Deque()  {}                         // construct an empty deque
	public boolean isEmpty()                 // is the deque empty?
	{
		return size == 0;
	}
	public int size()                        // return the number of items on the deque
	{
		return size;
	}
	public void addFirst(Item item)          // add the item to the front
	{
		if(item == null)
			throw new NullPointerException("Cannot add null item");
		Node oldfirst = head;
		Node first = new Node();
		first.item = item;
		first.next = oldfirst;
		if(isEmpty())
			tail = first;
		else
			oldfirst.prev = first;
		head = first;
		size++;
	}
	public void addLast(Item item)           // add the item to the end
	{
		if(item == null)
			throw new NullPointerException("Cannot add null item");
		Node oldlast = tail;
		Node last = new Node();
		last.item = item;
		last.prev = oldlast;
		if(isEmpty())
			head = last;
		else
			oldlast.next = last;
		tail = last;
		size++;		   
	}
	public Item removeFirst()                // remove and return the item from the front
	{
		if(isEmpty())
			throw new NoSuchElementException("Cannot remove from an empty deque");
		Node oldfirst = head;
		head = head.next;
		if(head != null)
			head.prev = null;
		size--;
		if(isEmpty())
			tail = null;
		return oldfirst.item;
	}
	public Item removeLast()                 // remove and return the item from the end
	{
		if(isEmpty())
			throw new NoSuchElementException("Cannot remove from an empty deque");
		Node oldlast = tail;
		tail = tail.prev;
		if(tail != null)
			tail.next = null;
		size--;
		if(isEmpty())
			head = null;
		return oldlast.item;
	}
	public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	{
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>
	{
		private Node current = head;
		public void remove(){
			throw new UnsupportedOperationException("remove() not supported");
		}
		public boolean hasNext() { return current != null && size != 0; }
		public Item next()
		{
			if(!hasNext())
				throw new NoSuchElementException("Cannot remove from an empty deque");
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	   public static void main(String[] args)   // unit testing
	   {
		   Deque<Integer> x = new Deque<Integer>();
		   x.addFirst(4);
		   x.addLast(3);
		   x.addFirst(5);
		   x.addFirst(6);
		   for(int y : x){
			   System.out.print(y);
		   }
	   }
}