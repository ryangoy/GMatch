import java.util.Scanner;
import java.util.ArrayList;

//Runs GMatcher
public class Main {
	static int numChoices = 0, numPeople = 0, groupSize = 0;
	public static void main(String args[]) {
		
		System.out.println("Welcome to GMatch.");
		while(true) {
			System.out.println("Type 'start' for a new dataset or type 'exit' to leave the program.");
			Scanner scan = new Scanner(System.in);
			String input = scan.next();
			switch(input) {
				case("exit"):
					return;
				case("start"):
					getPreferences();
					addPeople(null, numPeople);
					break;
			} //end switch
		}
	}

	private static void addPeople(GMatcher g, int n) {
		ArrayList<String> lst = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			System.out.print("Please enter a name: ");
			Scanner scan = new Scanner(System.in);
			String input = scan.next();
			lst.add(input);
		}

		for (String s : lst) {
			String pref = "";
			for (int i = 1; i <= numChoices; i++) {
				System.out.printf("Enter preference %d for %s: ", i, s);				
				do {
					Scanner scan = new Scanner(System.in);
					pref = scan.next();
					if (!lst.contains(pref)) {
						System.out.println("That name is not in the set. Check case and spelling.");
					}
				} while (!lst.contains(pref));
			}
		}
	}

	private static void getPreferences() {
		System.out.print("Please enter the number of people: ");
		Scanner scan = new Scanner(System.in);
		numPeople = scan.nextInt();

		System.out.print("Please enter the number of people in a group: ");
		scan = new Scanner(System.in);
		groupSize = scan.nextInt();

		System.out.print("Please enter the number of choices: ");
		scan = new Scanner(System.in);
		numChoices = scan.nextInt();
	}

}
