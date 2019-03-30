package game;

import java.util.ArrayList;
import java.util.Arrays;

public class Solver {
	private ArrayList<Grid> solutions;
	
	public Solver() {
		solutions = new ArrayList<Grid>();
	}
	
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
	
	public void solveRecurse(Grid g) {
		Evaluation eval = evaluate(g);
		
		if (eval == Evaluation.ACCEPT) {
			solutions.add(g);
		}
		else if (eval == Evaluation.ABANDON) {
			return;
		}
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
