import java.util.Arrays;
/**
 * Rule class for Project 1
 * @author Mac Syrett
 *
 */

public class Rule {
	private int ruleNum;
	private String binaryString; 
	
	
	/**
	 * Constructor to make a Rule object using a corresponding rule number.
	 * @param ruleNumInput The number corresponding to the binary representation of the rule.
	 */
	public Rule(int ruleNumInput) {
		// 255 is the number corresponding to the last binary representation
		if (ruleNumInput > 255) {
			ruleNum = 255;
		}
		else if (ruleNumInput < 0) {
			ruleNum = 0;
		}
		else {
			ruleNum = ruleNumInput;
		}
		
		// String formatting code was obtained from StackOverflow: https://stackoverflow.com/questions/4421400/how-to-get-0-padded-binary-representation-of-an-integer-in-java
		binaryString = String.format("%8s", Integer.toBinaryString(ruleNum)).replace(" ", "0");
	}
	
	
	/**
	 * Getter to return the number associated with the rule.
	 * @return The number associated with the rule.
	 */
	public int getRuleNum() {
		return this.ruleNum;
	}
	
	
	/**
	 * Obtains the neighborhood of a given cell in a Generation (the cell and the cells directly to the left and right of it).
	 * Uses circular boundary conditions when the given cell is located at the beginning or the end of the Generation.
	 * @param index The index of the cell in the Generation who's neighborhood is to be obtained.
	 * @param gen The generation containing the cell.
	 * @return Returns an array containing the cell to the left of the given cell, the given cell, and the cell to the right of the given cell, respectively.
	 */
	public static boolean[] getNeighborhood(int index, Generation gen) {
		boolean[] neighborhoodArray = new boolean[3];
		
		// If there is only one cell in the generation, circular boundary conditions dictate that the neighborhood contain the same cell three times.
		if (gen.size() == 1) {
			neighborhoodArray[0] = gen.getState(0);
			neighborhoodArray[1] = gen.getState(0);
			neighborhoodArray[2] = gen.getState(0);
		}
		// Similar logic is applied to the 2 cell case.
		else if (gen.size() == 2) {
			if (index == 0) {
				neighborhoodArray[0] = gen.getState(index + 1);
				neighborhoodArray[1] = gen.getState(index);
				neighborhoodArray[2] = gen.getState(index + 1);
			}
			else {
				neighborhoodArray[0] = gen.getState(index - 1);
				neighborhoodArray[1] = gen.getState(index);
				neighborhoodArray[2] = gen.getState(index - 1);
			}
		}
		// Logic for three or more cells.
		else { 
			// Logic for the cell being the first in the generation.
			if (index == 0) {
				neighborhoodArray[0] = gen.getState(gen.size() - 1);
				neighborhoodArray[1] = gen.getState(index);
				neighborhoodArray[2] = gen.getState(index + 1);
			}
			// Logic for the cell being the last in the generation.
			else if (index == gen.size() - 1) {
				neighborhoodArray[0] = gen.getState(index - 1);
				neighborhoodArray[1] = gen.getState(index);
				neighborhoodArray[2] = gen.getState(0);
			}
			// Logic for all other cases.
			else {
				neighborhoodArray[0] = gen.getState(index - 1);
				neighborhoodArray[1] = gen.getState(index);
				neighborhoodArray[2] = gen.getState(index + 1);
			}
		}
		return Arrays.copyOf(neighborhoodArray, 3);
	}
	
	/**
	 * Evolves a given neighborhood using the Wolfram code associated with the Rule.
	 * @param neighborhood The neighborhood to be evolved.
	 * @return The cell at the center of the evolved neighborhood.
	 */
	public boolean evolve(boolean[] neighborhood) {
		// The only way I could think of doing this was by checking each possible neighborhood individually.
		if (neighborhood[0] == true && neighborhood[1] == true && neighborhood[2] == true) {	
			if (binaryString.charAt(0) == '1') {
				neighborhood[1] = true;
			}
			else {
				neighborhood[1] = false;
			}
		}
		else if (neighborhood[0] == true && neighborhood[1] == true && neighborhood[2] == false) {	
			if (binaryString.charAt(1) == '1') {
				neighborhood[1] = true;
			}
			else {
				neighborhood[1] = false;
			}
		}
		else if (neighborhood[0] == true && neighborhood[1] == false && neighborhood[2] == true) {	
			if (binaryString.charAt(2) == '1') {
				neighborhood[1] = true;
			}
			else {
				neighborhood[1] = false;
			}
		}
		else if (neighborhood[0] == true && neighborhood[1] == false && neighborhood[2] == false) {	
			if (binaryString.charAt(3) == '1') {
				neighborhood[1] = true;
			}
			else {
				neighborhood[1] = false;
			}
		}
		else if (neighborhood[0] == false && neighborhood[1] == true && neighborhood[2] == true) {	
			if (binaryString.charAt(4) == '1') {
				neighborhood[1] = true;
			}
			else {
				neighborhood[1] = false;
			}
		}
		else if (neighborhood[0] == false && neighborhood[1] == true && neighborhood[2] == false) {	
			if (binaryString.charAt(5) == '1') {
				neighborhood[1] = true;
			}
			else {
				neighborhood[1] = false;
			}
		}
		else if (neighborhood[0] == false && neighborhood[1] == false && neighborhood[2] == true) {	
			if (binaryString.charAt(6) == '1') {
				neighborhood[1] = true;
			}
			else {
				neighborhood[1] = false;
			}
		}
		else if (neighborhood[0] == false && neighborhood[1] == false && neighborhood[2] == false) {	
			if (binaryString.charAt(7) == '1') {
				neighborhood[1] = true;
			}
			else {
				neighborhood[1] = false;
			}
		}
		return neighborhood[1];
	}
	
	/**
	 * A version of evolve, but for an entire Generation.
	 * @param gen The generation to be evolved.
	 * @return An evolved version of the given generation. 
	 */
	public Generation evolve(Generation gen) {
		boolean[] statesArray = gen.getStates();
		boolean[] neighborhoodArray = new boolean[3];
		for (int i = 0; i < gen.size(); ++i) {
			neighborhoodArray = getNeighborhood(i, gen);
			statesArray[i] = evolve(neighborhoodArray);
		}
		Generation newGen = new Generation(statesArray);
		return newGen;
	}
}
