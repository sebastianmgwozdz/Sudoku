package game;

import java.util.ArrayList;
import java.util.Arrays;

// Generates all possible solutions for the given grid using backtracking
public class Solver {
	private ArrayList<Grid> solutions;
	
	public Solver() {
		solutions = new ArrayList<Grid>();
	}
	
	// Evaluates the current board state, determining if it's 
	// complete, valid, or invalid
	public Evaluation evaluate(Grid g) {
		if (g.isLegal() && g.isFull()) {
			return Evaluation.ACCEPT;
		}
		else if (g.isLegal()) {
			return Evaluation.CONTINUE;
		}
		else {
			return Evaluation.ABANDON;
		}
	}
	
	// Solves the board with backtracking
	public void solveRecurse(Grid g) {
		Evaluation eval = evaluate(g);
		
		// Board is complete and valid, therefore its a solution
		if (eval == Evaluation.ACCEPT) {
			solutions.add(g);
		}
		
		// Board is invalid, do nothing with it
		else if (eval == Evaluation.ABANDON) {
			return;
		}
		
		// Generates all possible combinations of boards, solves those, 
		// moves backwards if one doesn't work
		else {
			ArrayList<Grid> list = g.next9Grids();
			for (Grid grid : list) {
				solveRecurse(grid);
			}
		}
	}
	
	public ArrayList<Grid> getSolutions() {
		return solutions;
	}

}
