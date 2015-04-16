import java.util.*;

//Input group members and preferences and output groups
public class GMatcher {
	static int numChoices, numPeople, groupSize, numGroups;
	final static int SAMPLE_SIZE = 25;
	final static int NUM_ITERATIONS = 100;
	final static int NUM_RESET = 10;
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
		

		//2) populate groupsets (randomly)
		GroupSet[] sampleSet = populateSet(matrix);

		int m = 0;
		GroupSet res;
		do {
			//3) loop randomly through crossing over pgroups. of the three per crossover (parent, parent, child), 
			//		97/100 chance: calculate fitness and delete the least fit node
			//		2/100 chance: randomly select 2 to avoid a local max
			//		1/100 return a randomly mutated child
			// 		***currently doesn't implement random actions***
			for (int n = 0; n < NUM_ITERATIONS; n++) {
				sampleSet = crossOver(sampleSet);
				sampleSet = standardShuffle(sampleSet);
			}

			//4) find the most fit node after n iterations
			res = findBestFit(sampleSet);

			//5) repopulate based on this node by permutating psets
			res.shuffleGroups();
			matrix = res.toMatrix();
			sampleSet = populateSet(matrix);

		//6) repeat crossover/mutation and bottleneck m times (i.e. go back to step 3)
			m++;
		} while (m < NUM_RESET);

		//7) optional: calculate fitness efficiency score: (calculated fitness) / (possible matches)

		return res.toMap();
	}

	public static String[][] createMatrix(Set<String> people) {
		//step one for computeGroups
		String[][] res = new String[groupSize][numGroups];
		int i = 0;
		for (String person : people) {
			res[i/numGroups][i%numGroups] = person;
			i++;
		}
		return res;
	}

	public static GroupSet[] populateSet(String[][] matrix) {
		GroupSet[] sampleSet = new GroupSet[SAMPLE_SIZE];
		for (int i = 0; i < SAMPLE_SIZE; i++) {
			sampleSet[i] = new GroupSet(matrix);
			sampleSet[i].shuffle();
			//sampleSet[i].print();
		}
		return sampleSet;
	}

	public static GroupSet[] crossOver(GroupSet[] sampleSet) {
		//crosses over the whole sample set
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
			//System.out.println("Fitness: " + fp1);
		}
		return sampleSet;
	}

	public static GroupSet findBestFit(GroupSet[] set) {
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

	public static GroupSet[] standardShuffle(GroupSet[] ar) {
		for (GroupSet g : ar) {
			g.shuffle();
		}
		return ar;
	}


}