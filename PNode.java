import java.util.*;

public class PNode {
	
	String[] arr;

	public PNode(String[] people) {
		arr = people;
	}

	public void shuffle() {
		shuffleArray(arr);
	}

	//http://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
	private static void shuffleArray(String[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	public void set(int index, String s) {
		arr[index] = s;
	}

	public String get(int index) {
		return arr[index];
	}

	public int size() {
		return arr.length;
	}

	public String[] getArr() {
		return arr;
	}

}