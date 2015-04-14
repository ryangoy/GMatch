import java.util.*;

//Holds a pre-defined number of people for a group, calculates data such as fitness number
public class Group {
	private HashMap<String, HashSet<String>> gData;
	private int fitness;
	
	public Group(HashMap<String, HashSet<String>> gData) {
		this.gData = gData;
		fitness = computeFitness();
	}

	public int getFitness() {
		return fitness;
	}

	private int computeFitness() {
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
		int fitness = 0;
		for (String person : gData.keySet()) {
			for (String preference : gData.get(person)) {
				if (gData.keySet().contains(preference)) {
					fitness += 1;
				}
			}
		}
		return fitness;
	}
	
	public Set getGroup() {
		return gData.keySet();
	}

}