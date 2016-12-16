import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
	public static void main(String[] args){
		int numStrings = Integer.parseInt(args[0]);
		RandomizedQueue<String> rQueue = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			String x = StdIn.readString();
			rQueue.enqueue(x);
		}
		for(int i = 0; i < numStrings; i++){
			StdOut.println(rQueue.dequeue());
		}
	}
}
