import java.util.Arrays;
/**
 * Generation class for Project 1
 * @author Mac Syrett
 */

public class Generation {
	

	private boolean[] cellStates;
	
	
	// Constructor to create a Generation with one cell for each element in the given array
	/**
	 * Constructor to create a Generation of cells.
	 * @param states A list of states from which the Generation is constructed.
	 */
	public Generation(boolean... states) {
		if ((states == null) || (states.length == 0)) {
			cellStates = new boolean[] {false};
		}
		else {
			cellStates = Arrays.copyOf(states, states.length);
		}
	}
	
	
	/**
	 * Constructor to create a Generation with one cell for each element in the given string, 
	 * and set each cell with a corresponding trueSymbol in the string to true.
	 * @param states A string representing the Generation to be constructed.
	 * @param trueSymbol The character which, when found in the states string, will be converted to a value of true in the Generation.
	 */
	public Generation(String states, char trueSymbol) {
		if (states == null) {
			cellStates = new boolean[] {false};
		}
		else if (states.isEmpty()) {
			cellStates = new boolean[] {false};
		}
		else {
			cellStates = new boolean[states.length()];
			for (int i = 0; i < states.length(); ++i) {
				if (states.charAt(i) == trueSymbol) {
					cellStates[i] = true;
				}
				else {
					cellStates[i] = false;
				}
			}
		}
	}
	
	/**
	 * Getter to return the state of a given cell.
	 * @param index The index of the cell.
	 * @return The state of the given cell.
	 */
	public boolean getState(int index) {
		return cellStates[index];
	}
	
	/**
	 * A getter to return all of the states in the Generation.
	 * @return An array of all the states in the Generation.
	 */
	public boolean[] getStates() {
		boolean[] cellStatesCopy = Arrays.copyOf(cellStates, cellStates.length);
		return cellStatesCopy;
	}
	
	/**
	 * A getter to obtain a string representation of the states of the Generation.
	 * @param falseSymbol The character in the returned string that will represent the value of false.
	 * @param trueSymbol The character in the returned string that will represent the value of false.
	 * @return Returns a string representation of all the states in the Generation, using falseSymbol and trueSymbol.
	 */
	public String getStates(char falseSymbol, char trueSymbol) {
		String statesString = "";
		for (int i = 0; i < cellStates.length; ++i) {
			if (cellStates[i] == true) {
				statesString += trueSymbol;
			}
			else {
				statesString += falseSymbol;
			}
		}
		return statesString;
	}
	
	/**
	 * Getter to return the number of cells in the generation.
	 * @return Returns the number of cells in the generation.
	 */
	public int size() {
		return cellStates.length;
	}
}
