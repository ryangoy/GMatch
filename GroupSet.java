import java.util.*;

//Holds a pre-defined number of people for a group, calculates data such as fitness number
public class GroupSet {
	private static HashMap<String, HashSet<String>> preferences = null;
	private int overallFitness, peoplePerGroup, numGroups, fitness;
	private PNode[] pNodes;
	private Group[] groups;
	
	public GroupSet(String[][] data) { 
		this.peoplePerGroup = data.length;
		this.numGroups = data[0].length;
		this.groups = constructGroups(data);
		this.pNodes = populatePNodes(data);
		this.fitness = computeFitness();
	}

	public static void setPreferences(HashMap<String, HashSet<String>> prefs) {
		preferences = prefs;
	}

	private Group[] constructGroups(String[][] matrix) {

		HashMap<String, HashSet<String>> group;
		Group[] groups = new Group[numGroups];
		for (int i = 0; i < numGroups; i++) {
			group = new HashMap<String, HashSet<String>>();
			for (int j = 0; j < peoplePerGroup; j++) {
				group.put(matrix[j][i], preferences.get(matrix[j][i]));
			}
			groups[i] = new Group(group);
		}
		return groups;
	}

	private PNode[] populatePNodes(String[][] data) {
		PNode[] res = new PNode[data.length];
		int i = 0;
		for (String[] arr : data) {
			res[i] = new PNode(data[i]);
			//res[i].shuffle();
			i++;
		}
		return res;
	}

	private int computeFitness() {
		overallFitness = 0;
		for (Group g : groups) {
			overallFitness += g.getFitness();
		}
		return overallFitness;
	}

	public void shuffle() {
		for (int i = 0; i < pNodes.length; i++) {
			//groups[i].shuffle();
			pNodes[i].shuffle();

		}
		//shufflePNodes();
	}

	public Group[] getGroups() {
		return groups;
	}

	public PNode[] getPNodes() {
		return pNodes;
	}

	public int getFitness() {
		return fitness;
	}

	public static GroupSet crossover(GroupSet a, GroupSet b) {
		GroupSet child;
		PNode[] p1 = a.getPNodes();
		PNode[] p2 = b.getPNodes();
		int size = p1.length;
		String[][] c = new String[size][];
		Random rnd = new Random();
		for (int i = 0; i < p1.length; i++) {
			if (rnd.nextInt(2) > 0) {
				c[i] = p1[i].getArr();
			} else {
				c[i] = p2[i].getArr();
			}
		}
		return new GroupSet(c);

	}

	private void shufflePNodes() {
		Random rnd = new Random();
		for (int i = pNodes.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			PNode a = pNodes[index];
			pNodes[index] = pNodes[i];
			pNodes[i] = a;
		}
	}

	public HashMap<Integer, Set<String>> toMap() {
		HashMap<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();
		for (int i = 0; i < numGroups; i++) {
			map.put(i, groups[i].getGroup());
		}
		return map;
	}
}
