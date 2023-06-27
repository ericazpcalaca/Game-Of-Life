import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*
 * Student: Erica Zasimowicz Pinto Calaca
 * Student ID: 300361965
 */
import java.time.LocalTime;

public class Game {
	static final String IN_FILE = "input.txt";

	public static char[][] readInitialPattern(char[][] cells) {
		/*
		 * It will read through the file input 
		 * to mark the cells that will initialize 
		 * the game 
		 */
		
		BufferedReader br = null;
		String line = "";

		try {
			// Open file
			br = new BufferedReader(new FileReader(IN_FILE));

			// Process file
			int count = 0;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(" ");
				if (count > 1) {
					int row = Integer.parseInt(tokens[0]);
					int marks = Integer.parseInt(tokens[1]);
					cells[row][marks] = '#';
				}
				count++;
			}
			// Close the file
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cells;
	}

	public static int[][] calculateNeighbors(char[][] cells, int heightBoard, int widthBoard) {
		/*
		 * It is going to create a 2-D array 
		 * to store the number of neighbors
		 */
		
		int[][] neighbor = new int[heightBoard][widthBoard];

		// Loop through every cell
		for (int i = 0; i < heightBoard; i++) {
			for (int j = 0; j < widthBoard; j++) {

				// If cells alive, count the neighbor
				if (cells[i][j] == '#') {
					
						//Check if cells are on the border 
						int above, bellow;
						above = i > 0 ? i-1 : heightBoard -1;
						bellow = i < heightBoard-1 ? i+1 : 0;
						
						int left, right;
						left = j > 0? j - 1: widthBoard -1;
						right = j < widthBoard - 1? j+1: 0;
						
						//Mark the neighbors
						neighbor[above][left]++;
						neighbor[above][j]++;
						neighbor[above][right]++;
						neighbor[i][left]++;
						neighbor[i][right]++;
						neighbor[bellow][left]++;
						neighbor[bellow][j]++;
						neighbor[bellow][right]++;
				}
			}
		}
		
		return neighbor;
	}
	
	public static char[][] nextGeneration(char[][] cells, int[][] cellsNeighbors, int heightBoard, int widthBoard) {
		/*
		 * It is going to update the live/dead cells 
		 * for each cell in the next generation
		 * following the rules
		 */
		
		char[][] nextGeneration = new char[heightBoard][widthBoard];

		// Loop through every cell
		for (int i = 0; i < heightBoard; i++) {
			for (int j = 0; j < widthBoard; j++) {
			
				//Implementing the rules
				
				if (cells[i][j] == '#' && (cellsNeighbors[i][j] == 2 || cellsNeighbors[i][j] == 3)) {
					//1. Any live cell with two or three live neighbors 
					// survives in the next generation	
					nextGeneration[i][j] = '#';					
				} else if (cells[i][j] == ' ' && cellsNeighbors[i][j] == 3) {
					//2. Any dead cell with three live neighbors  
					// becomes a live cell in the next generation;
					nextGeneration[i][j] = '#';
				} else {
					//3. All other live cells die in the next generation. 
					// All other dead cells stay dead in the next generation;
					nextGeneration[i][j] = ' ';
				} 	
			}
		}
		
		return nextGeneration;
	}

	public static void main(String[] args) {

		int rowBoard = 0;
		int columnBoard = 0;

		// Get the number of rows and columns from the file
		BufferedReader br = null;
		String line = "";

		try {
			// Open file
			br = new BufferedReader(new FileReader(IN_FILE));

			// Process file
			int count = 0;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(" ");
				if (count == 0) {
					rowBoard = Integer.parseInt(tokens[0]);
				} else if (count == 1) {
					columnBoard = Integer.parseInt(tokens[0]);
				} else {
					break;
				}
				count++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create the new board with the size on the input file
		Board board = new Board(rowBoard, columnBoard);
		
		// Read the initial pattern on the file
		readInitialPattern(board.cells);
		board.clearConsole();
		board.print();
		
		int lastSecond = -1;
		
		while(true) {
			LocalTime now = LocalTime.now();
			int currentSecond = now.getSecond();
			
			if (currentSecond != lastSecond) {
				//Calculate the number of neighbors
				int[][] cellsNeighbors = calculateNeighbors(board.cells, board.getHeight(), board.getWidth());
				
				//Update the next generation of cells
				board.cells = nextGeneration(board.cells, cellsNeighbors, board.getHeight(), board.getWidth());
				board.clearConsole();
				board.setCells(board.cells);
				board.print();
				lastSecond = currentSecond;
			}
		}
	}

}
