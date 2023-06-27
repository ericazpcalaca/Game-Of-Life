public class Board {

	// Default height and width of the board
	private static final int LENGTH = 20;

	private int width;
	private int height;
	public char[][] cells;

	public final static void clearConsole() {
		/*
		 * clearConsole method to clear the console if you are using Windows' command
		 * prompt or Linux-based system This method does not work in Eclipse IDE.
		 */
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			// Handle any exceptions.
		}
	}

	public void init() {
		/*
		 * Init method to create a 2-D array of characters, set all cells to space
		 * characters
		 */
		Board.clearConsole();
		cells = new char[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = ' ';
			}
		}
	}

	public Board() {
		/*
		 * Constructor to create a board with default height(20) and width(20)
		 */
		width = LENGTH;
		height = LENGTH;
		init();
	}

	public Board(int height, int width) {
		/*
		 * Constructor to create a board with height and width
		 */
		this.width = width;
		this.height = height;
		init();
	}

	public void clear() {
		/*
		 * Set all cells to space characters
		 */
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = ' ';
			}
		}
	}

	public void print() {
		/*
		 * Print all cells of the board
		 */
		Board.clearConsole();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(cells[i][j]);
			}
			System.out.println();
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public char[][] getCells() {
		return cells;
	}

	public void setCells(char[][] cells) {
		this.cells = cells;
	}
}