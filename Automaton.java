import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Automaton class for Project 1
 * @author Mac Syrett
 *
 */
public class Automaton {
	private Rule rule;
	private ArrayList<Generation> generations= new ArrayList<Generation>();
	public char falseSymbol;
	public char trueSymbol;
	
	/**
	 * Constructor to create an Automaton using a provided rule number and an initial Generation.
	 * @param ruleNum The number corresponding to the Wolfram code of the rule to be used for the Automaton.
	 * @param initial The initial Generation of the Automaton.
	 */
	public Automaton(int ruleNum, Generation initial) {
		rule = new Rule(ruleNum);
		generations.add(initial);
		falseSymbol = '0';
		trueSymbol = '1';
	}
	
	/**
	 * Constructor to create an Automaton from a given file that follows the following format: 
	 * rule-number
	 * false-symbol true-symbol
	 * initial-cell-states
	 * 
	 * @param fileName The name of the file used to create the Automaton.
	 * @throws IOException
	 */
	public Automaton(String fileName) throws IOException {
		File myFile = new File(fileName);
		Scanner fileReader = new Scanner(myFile);
		rule = new Rule(Integer.parseInt(fileReader.nextLine()));
		falseSymbol = fileReader.next().charAt(0);
		trueSymbol = fileReader.next().charAt(0);
		fileReader.nextLine();
		generations.add(new Generation(fileReader.nextLine(), trueSymbol));
		fileReader.close();
	}
	
	/** 
	 * Evolves the latest Generation a given number of times, using a similar method from the Rule class.
	 * @param numSteps The number of times the Generation is to be evolved.
	 */
	public void evolve(int numSteps) {
		if (numSteps <= 0) {
			return;
		}
		for (int i = 0; i < numSteps; ++i) {
			generations.add(rule.evolve(generations.get(generations.size() - 1)));
		}		
	}
	
	/**
	 * Getter to return the rule number associated with the Automaton.
	 * @return The rule number associated with the Automaton.
	 */
	public int getRuleNum() {
		return rule.getRuleNum();
	}
	
	/**
	 * Getter to return the Generation at a given step number.
	 * @param stepNum The step number of the Generation to be returned.
	 * @return The Generation at the given step number.
	 */
	public Generation getGeneration(int stepNum) {
		if (stepNum >= generations.size()) {
			evolve(stepNum - generations.size() + 1);
		}
		return generations.get(stepNum);
	}
	
	/**
	 * Getter to return the total number of evolutions the Automaton has undergone.
	 * @return The total number of steps applied to the Automaton.
	 */
	public int getTotalSteps() {
		return generations.size() - 1;
	}
	
	/**
	 * Saves a String representation of the Automaton to a file with the given file name.
	 * @param filename The name of the file the Automaton is to be saved to.
	 * @throws IOException
	 */
	public void saveEvolution(String filename) throws IOException {
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filename));
		fileWriter.write(this.toString());
		fileWriter.close();
	}
	
	/**
	 * Returns a String representation of the Automaton, using methods from the Generation class.
	 * @return A String representation of the Automaton.
	 */
	public String toString() {
		String stringRep = "";
		for (int i = 0; i < generations.size(); ++i) {
			
			stringRep += generations.get(i).getStates(falseSymbol, trueSymbol);
			if (i < generations.size() - 1) {
				stringRep += System.lineSeparator();
			}
		}
		return stringRep;
	}
}
