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

	public double computeFitness(HashSet<String> group) {
		/*
		factors: matches, gender balance, score balance, matches balance
			within each group, calculate each of the factors

		matches (only one to be implemented right now):
			for person in the group,
				for preference of the person,
					if preference is in the group,
						add weight*rank_preference to fitness number

		gender balance (optional):
			calculate overall gender average, calculate group gender average
			=> gb = 100 - |gga - oga|

		score balance (optional): 
			calculate overall score range, calculate score average, group score average
			=> sb = 100 - (sr/100)|gsa - osa|

		match balance (optional):
			per generation, keep track of match calculation, average @ at the end of match calculation
			=> mb = 100 - |oma - mc|

		with each thing calculated, multiply each score by the user inputted weight, add each factor
			together to yield fitness calculation
		*/
	}

	public String[][] createMatrix(Set<String> people) {
		//step one for computeGroups
		String[][] res = new String[groupSize][numGroups];
		Arrays.fill(res, null);
		int i = 0;
		for (String person : people) {
			res[i/numGroups][i%groupSize] = person;
			i++;
		}
	}

	public 
}