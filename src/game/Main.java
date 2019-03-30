package game;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		Grid board = new Grid();
		Random random = new Random();
		
		while (true) {
			System.out.println(board.toString());
			System.out.print("Enter column/row/digit in format 'DE4', 'quit' to exit: ");
			
			String input = reader.nextLine();
			
			if (input.equals("quit")) {
				Solver s = new Solver();
				s.solveRecurse(board);
				System.out.println("Possible solution: ");
				try {
					System.out.println(s.getSolutions().get(random.nextInt(s.getSolutions().size())).toString());
				}
				catch (IllegalArgumentException x) {
					System.out.println("No possible solutions");
				}
				break;
			}
			
			try {
				board.makeMove(input);
			} catch (InputFormatException x) {
				System.out.println(x.getMessage());
			}
			
			if (board.isFull() && board.isLegal()) {
				System.out.println("Congrats!");
				break;
			}
		}
	}
}
