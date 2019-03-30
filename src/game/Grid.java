package game;

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
	private int[][] values;
	
	public Grid() {
		values = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				values[i][j] = 0;
			}
		}
	}
	
	public Grid(int[][] values) {
		this.values = values;
	}
	
	public Grid copyGrid() {
		Grid g = new Grid(new int[9][9]);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				g.values[i][j] = values[i][j];
			}
		}
		
		return g;
	}
	
	public void makeMove(String input) throws InputFormatException {
		try {
			int row = input.charAt(1) - 'A';
			int column = input.charAt(0) - 'A';

			if (inputIllegal(row, column, input.length())) {
				throw new InputFormatException("Invalid format");
			}
			
			int digit = Integer.parseInt(input.substring(2, 3));
			Grid copy = copyGrid();
			copy.values[row][column] = digit;
			if (copy.isLegal()) {
				values[row][column] = digit;
			}
		} catch (NumberFormatException x) {
			throw new InputFormatException("Enter a valid digit");
		}
	}
	
	public boolean inputIllegal(int row, int column, int length) {
		return row < 0 || row > 9 || column < 0 || column > 9 || length != 3;
	}
	
	public ArrayList<Grid> next9Grids() {
		ArrayList<Grid> list = new ArrayList<Grid>();
		
		if (isFull()) {
			return null;
		}
		
		int xOfEmpty = 0;
		int yOfEmpty = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (values[i][j] == 0) {
					yOfEmpty = i;
					xOfEmpty = j;
				}
			}
		}
		
		for (int k = 1; k <= 9; k++) {
			Grid g = copyGrid();
			g.values[yOfEmpty][xOfEmpty] = k;
			list.add(g);
		}
		
		return list;
	}
	
	public boolean isFull() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (values[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isLegal() {	
		return rowsLegal() && columnsLegal() && blocksLegal();
	}
	
	public boolean rowsLegal() {
		for (int[] row : values) {
			if (containsNonZeroRepeat(row)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean columnsLegal() {
		for (int j = 0; j < 9; j++) {
			int[] column = new int[9];
			for (int i = 0; i < 9; i++) { 
				column[i] = values[i][j];
			}
			
			if (containsNonZeroRepeat(column)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean blocksLegal() {
		for (int block = 0; block < 9; block++) {
			int startRow = block - block % 3;
			int startColumn = block * 3 % 9;
			
			int[] arr = new int[9];
			int index = 0;
			
			for (int i = startRow; i < startRow + 3; i++) {
				for (int j = startColumn; j < startColumn + 3; j++) {
					arr[index] = values[i][j];
					index++;
				}
			}
			
			if (containsNonZeroRepeat(arr)) {
				return false;
			}
		}
		
		return true;
	}
			
	public boolean containsNonZeroRepeat(int[] arr) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (arr[i] == arr[j] && arr[i] != 0 && i != j) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public String toString() {
		String s = "   A  B  C  D  E  F  G  H  I\n";
		
		for (int i = 0; i < 9; i++) {
			s += (char) ('A' + i) + " " + Arrays.toString(values[i]) + "\n";
		}
		
		return s;
	}
}
