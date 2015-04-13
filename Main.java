import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
//@author Ryan Goy 

//Runs GMatcher
public class Main {
	static int numChoices = 0, numPeople = 0, groupSize = 0;
	public static void main(String args[]) {
		
		System.out.println("Welcome to GMatch.");
		while(true) {
			System.out.println("Type (start) for a new dataset or type (exit) to leave the program.");
			Scanner scan = new Scanner(System.in);
			String input = scan.next();
			switch(input) {
				case("exit"):
					return;
				case("start"):
					getPreferences();
					GMatcher gm = new GMatcher(numChoices, numPeople, groupSize);
					addPeople(gm, numPeople);
					HashMap<Integer, HashSet<String>> groups = gm.computeGroups();
					printGroups(groups);
					break;
			} //end switch
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

	private static void addPeople(GMatcher g, int n) {
		HashMap<String, HashSet<String>> lst = new HashMap<String, HashSet<String>>();
		//add Names
		for (int i = 0; i < n; i++) {
			String input = "";
			do {
				System.out.print("Please enter a name (no duplicates): ");
				Scanner scan = new Scanner(System.in);
				input = scan.next();
				if (lst.containsKey(input)) {
					System.out.println("That name already exists.");
				}
			} while(lst.containsKey(input));
			lst.put(input, new HashSet<String>());
		}

		//add their preferences
		System.out.println("Enter ther preferences for each person in order of weight.");
		for (String s : lst.keySet()) {
			HashSet<String> prefLst = lst.get(s);
			String pref = "";
			for (int i = 1; i <= numChoices; i++) {
				System.out.printf("Enter preference %d for %s: ", i, s);				
				do {
					Scanner scan = new Scanner(System.in);
					pref = scan.next();
					if (!lst.containsKey(pref)) {
						System.out.println("That name is not in the set. Check case and spelling.");
					}
				} while (!lst.containsKey(pref));
				prefLst.add(pref);
			}
		}

		//output the given dataset
		confirmCorrect(lst);

		//add this dataset to the GroupMatcher
		g.putData(lst);
	}

	private static void confirmCorrect(HashMap<String, HashSet<String>> lst) {
		System.out.println("Your dataset is as follows:");
		for (String s : lst.keySet()) {
			System.out.print(s + ": ");
			for (String g : lst.get(s)) {
				System.out.print(g + " ");
			}
			System.out.println();
		}
	}

	private static void printGroups(HashMap<Integer, HashSet<String>> groups) {
		for (int i : groups.keySet()) {
			System.out.printf("Group %d:\n", i);
			for (String person : groups.get(i)) {
				System.out.println(person);
			}
			System.out.println();
		}
		System.out.print("Thank you for using GMatcher. ");
	}
}
