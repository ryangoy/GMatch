import java.util.*;
//Input group members and preferences and output groups
public class GMatcher {
	int numChoices, numPeople, groupSize, numGroups;
	HashMap<String, HashSet<String>> data;

	public GMatcher(int numChoices, int numPeople, int groupSize) {
		this.numChoices = numChoices;
		this.numPeople = numPeople;
		this.groupSize = groupSize;
		if (numPeople % groupSize == 0) {
			this.numGroups = numPeople / groupSize;
		} else {
			this.numGroups = numPeople / groupSize + 1;
		}
		HashMap<String, HashSet<String>> data = new HashMap<String, HashSet<String>>();
	}

	public void putData(HashMap<String, HashSet<String>> data) {
		this.data = data;
	}

	public HashMap<Integer, HashSet<String>> computeGroups() {
		//1) create data structure

		//2) populate pgroups (randomly)

		//3) loop through crossing over pgroups. of the three per crossover (parent, parent, child), 
		//		97/100 chance: calculate fitness and delete the least fit node
		//		2/100 chance: randomly select 2 to avoid a local max
		//		1/100 return a randomly mutated child

		//4) find the most fit node after n iterations, and repopulate based on this node by permutating psets

		//5) repeat crossover/mutation and bottleneck m times (i.e. go back to step 3)

		//6) optional: calculate fitness efficiency score: (calculated fitness) / (possible matches)

		//change the orientation of pgroups

		//create groups from the pgroups

		return null;
	}
}