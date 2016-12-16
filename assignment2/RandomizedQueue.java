import java.util.*;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>  { 

	private Item[] rQueue;
	private int size = 0;
	private int capacity = 2;

	public RandomizedQueue()                 // construct an empty randomized queue
	{
		rQueue = (Item[]) new Object[capacity];
	}
	public boolean isEmpty()                 // is the queue empty?
	{
		return size == 0;
	}
	public int size()                        // return the number of items on the queue
	{
		return size;
	}
	private void resize(int mod_capacity) 
	{
		Item[] temp = (Item[]) new Object[mod_capacity];
		for(int i = 0; i < size; i++){
			temp[i] = rQueue[i];
		}
		rQueue = temp;
	}
	public void enqueue(Item item)           // add the item
	{
		if(item == null)
			throw new NullPointerException("Cannot add null item");
		if(size == capacity){
			resize(capacity*2);
			capacity*=2;
		}
		rQueue[size++] = item;     
	}
	public Item dequeue()                    // remove and return a random item
	{
		if(isEmpty())
			throw new NoSuchElementException("Cannot remove from an empty queue");
		if(size == (capacity/4)){
			resize(capacity/2);
			capacity/=2;
		}
		int rand = StdRandom.uniform(size);
		Item saved = rQueue[rand];
		if(rand != size-1){						// instead of shifting all elements
			rQueue[rand] = rQueue[size-1];		// move last element to the randomly selected index
		}
		rQueue[size-1] = null;
		size--;
		return saved;
	}
	public Item sample()                     // return (but do not remove) a random item
	{
		if(isEmpty())
			throw new NoSuchElementException("Cannot remove from an empty queue");
		int choice = StdRandom.uniform(size);
		return rQueue[choice];
	}
	public Iterator<Item> iterator()         // return an independent iterator over items in random order
	{
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>
	{
		private int counter = 0;
		private int[] scrambled;
		public ListIterator() {
			scrambled = new int[size];
			for (int i = 0; i < size; i++) {
				scrambled[i] = i;
			}
			StdRandom.shuffle(scrambled);
		}
		public void remove()
		{
			throw new UnsupportedOperationException("remove() not supported");
		}
		public boolean hasNext() { return counter != size; }
		public Item next()
		{
			if(!hasNext())
				throw new NoSuchElementException("Cannot remove from an empty queue");
			int index = scrambled[counter++];
			return rQueue[index];
		}
	}
	/*
	public static void main(String[] args)   // unit testing
	{
		RandomizedQueue<Integer> x = new RandomizedQueue<Integer>();
		x.enqueue(3);
		x.enqueue(5);
		x.enqueue(4);
		x.enqueue(10);
		int limit = x.size();
		for(int y : x){
			StdOut.print(y);
			System.out.println("");
		}
		for(int i = 0; i < limit; i++){
			int j = x.dequeue();
			System.out.println(j);
		}
	}
	 */
}