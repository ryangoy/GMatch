import java.util.*;

//Input group members and preferences and output groups
public class GMatcher {
	int numChoices, numPeople, groupSize, numGroups;
	final int SAMPLE_SIZE = 25;
	final int NUM_ITERATIONS = 100;
	final int NUM_RESET = 10;
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

	public HashMap<Integer, Set<String>> computeGroups() {
		//1) create data structure
		GroupSet.setPreferences(data);
		String[][] matrix = createMatrix(data.keySet());
		GroupSet[] sampleSet = new GroupSet[SAMPLE_SIZE];

		//2) populate groupsets (randomly)
		for (int i = 0; i < SAMPLE_SIZE; i++) {
			sampleSet[i] = new GroupSet(matrix);
			sampleSet[i].shuffle();
		}

		//3) loop randomly through crossing over pgroups. of the three per crossover (parent, parent, child), 
		//		97/100 chance: calculate fitness and delete the least fit node
		//		2/100 chance: randomly select 2 to avoid a local max
		//		1/100 return a randomly mutated child
		// *currently doesn't implement random actions

		for (int n = 0; n < NUM_ITERATIONS; n++) {
			//this for loop crosses over all of the GroupSets
			//its a long loop so I'll modularize it later
			for (int i = 0; i < SAMPLE_SIZE - 1; i += 2) {
				GroupSet parent1 = sampleSet[i];
				GroupSet parent2 = sampleSet[i+1];
				GroupSet child = GroupSet.crossover(parent1, parent2);
				int fp1 = parent1.getFitness();
				int fp2 = parent2.getFitness();
				int fc = child.getFitness();
				if (fc > fp1) {
					sampleSet[i] = child;
					if (fp1 > fp2) {
						sampleSet[i + 1] = parent1;
					} else {
						sampleSet[i + 1] = parent2;
					}
				} else {
					sampleSet[i] = parent1;
					if (fc > fp2) {
						sampleSet[i + 1] = child;
					} else {
						sampleSet[i + 1] = parent2;
					}
				}
			}//end of crossover loop
			sampleSet = shuffleGroupSets(sampleSet);
		}
		GroupSet res = findBestFit(sampleSet);

		//4) find the most fit node after n iterations, and repopulate based on this node by permutating psets

		//5) repeat crossover/mutation and bottleneck m times (i.e. go back to step 3)

		//6) optional: calculate fitness efficiency score: (calculated fitness) / (possible matches)

		//change the orientation of pgroups

		//create groups from the pgroups

		return res.toMap();
	}

	public String[][] createMatrix(Set<String> people) {
		//step one for computeGroups
		String[][] res = new String[groupSize][numGroups];
		int i = 0;
		for (String person : people) {
			res[i/numGroups][i%numGroups] = person;
			i++;
		}
		return res;
	}

	public GroupSet findBestFit(GroupSet[] set) {
		int best = 0;
		GroupSet curr = set[0];
		for (GroupSet g : set) {
			if (g.getFitness() > curr.getFitness()) {
				best = g.getFitness();
				curr = g;
			}
		}
		return curr;
	}

	public GroupSet[] shuffleGroupSets(GroupSet[] ar) {
		for (GroupSet g : ar) {
			g.shuffle();
		}
		return ar;
			// Random rnd = new Random();
			// for (int i = ar.length - 1; i > 0; i--) {
			// 	int index = rnd.nextInt(i + 1);
			// 	// Simple swap
			// 	GroupSet a = ar[index];
			// 	ar[index] = ar[i];
			// 	ar[i] = a;
			// }
			// return ar;
	}


}