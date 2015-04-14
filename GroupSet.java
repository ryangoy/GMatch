import java.util.*;

//Holds a pre-defined number of people for a group, calculates data such as fitness number
public class GroupSet {
	private HashMap<String, HashSet<String>> data;
	private int overallFitness, peoplePerGroup, numGroups, fitness;
	private PNode[] pNodes;
	private Group[] groups;
	
	public GroupSet(PNode[] nodes, HashMap<String, HashSet<String>> data) {
		this.pNodes = nodes;
		this.data = data;
		this.peoplePerGroup = pNodes.length;
		this.numGroups = pNodes[0].size();
		this.groups = constructGroups();
		fitness = computeFitness();
	}

	private int computeFitness() {
		overallFitness = 0;
		for (Group g : groups) {
			overallFitness += g.getFitness();
		}
		return overallFitness;
	}

	private Group[] constructGroups() {
		HashMap<String, HashSet<String>> group;
		Group[] groups = new Group[numGroups];
		for (int i = 0; i < numGroups; i++) {
			group = new HashMap<String, HashSet<String>>();
			for (int j = 0; j < peoplePerGroup; j++) {
				group.put(pNodes[j].get(i), null);
			}
			for (String s : group.keySet()) {
				group.put(s, data.get(s));
			}
			groups[i] = new Group(group);
		}
		return groups;
	}
	
	public Group[] getGroups() {
		return groups;
	}

	public int getFitness() {
		return fitness;
	}

}