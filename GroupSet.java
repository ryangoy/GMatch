import java.util.*;

//Holds a pre-defined number of people for a group, calculates data such as fitness number
public class GroupSet {
	private HashMap<String, HashSet<String>> preferences;
	private int overallFitness, peoplePerGroup, numGroups, fitness;
	private PNode[] pNodes;
	private Group[] groups;
	
	public GroupSet(String[][] data, HashMap<String, HashSet<String>> prefs) { 
		this.preferences = prefs;
		this.peoplePerGroup = data.length;
		this.numGroups = data[0].length;
		this.groups = constructGroups(data, prefs);
		this.pNodes = populatePNodes(data);
		this.fitness = computeFitness();
	}

	private Group[] constructGroups(String[][] matrix, HashMap<String, HashSet<String>> prefs) {

		HashMap<String, HashSet<String>> group;
		Group[] groups = new Group[numGroups];
		for (int i = 0; i < numGroups; i++) {
			group = new HashMap<String, HashSet<String>>();
			for (int j = 0; j < peoplePerGroup; j++) {
				group.put(matrix[j][i], prefs.get(matrix[j][i]));
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
			pNodes[i].shuffle();
		}
	}

	public Group[] getGroups() {
		return groups;
	}

	public int getFitness() {
		return fitness;
	}

	

}