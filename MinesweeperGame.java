import java.util.Random; // To get pseudo-random numbers for the position of mines

public class MinesweeperGame {
	
	// Variables declared for the minesweeper game
    private char[][] grid;
    private boolean[][] visited;
    private boolean[][] mines;
    private int size;
    private int totalMines;
    private int remainingSquares;

	// Preparing the minesweeper board
    public MinesweeperGame(int size, int totalMines) {
        this.size = size;
        this.totalMines = totalMines;
        this.remainingSquares = size * size;
        this.grid = new char[size][size];
        this.visited = new boolean[size][size];
        this.mines = new boolean[size][size];

		// Calling upon methods to prepare the minesweeper board
        initializeGrid();
        placeMines();
    }

	// Method to initialize grid used as '-'
    private void initializeGrid() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = '-';
            }
        }
    }

	// Method to place mines randomly in the grid
    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < totalMines) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (!mines[row][col]) {
                mines[row][col] = true;
                minesPlaced++;
            }
        }
    }
	
	// Method to print grid visually according to the user input
    public void printGrid() {
        System.out.print("  ");
        for (int col = 1; col <= size; col++) {
            System.out.print(col + " ");
        }
        System.out.println();
        for (int row = 0; row < size; row++) {
            System.out.print((char) ('A' + row) + " ");
            for (int col = 0; col < size; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }

	 // To prevent index out-of-bounds errors when accessing grid cells; Checking if values are within the bounds of game grid
    public boolean isValid(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }
	
	
	// Method to uncover squares according to user input; Checks if selected square has adjacent mines.
    public void uncoverSquare(int row, int col, boolean selectedSquare) {
        if (!isValid(row, col) || visited[row][col]) {
            return;
        }

        visited[row][col] = true;
        remainingSquares--;

        if (mines[row][col]) {
            gameOver();
            return;
        }

        int adjacentMines = countAdjacentMines(row, col);
        if (selectedSquare) {
            if (adjacentMines == 0) {
                System.out.println("\nThis square contains 0 adjacent mines.");
            } else if (adjacentMines > 0) {
                System.out.println("\nThis square contains " + adjacentMines + " adjacent mines.");
            }
        }

        grid[row][col] = (char) (adjacentMines + '0');

        if (adjacentMines == 0) {
            uncoverAdjacentSquares(row, col);
        }
    }

	// Method to automatically uncover adjacent squares
    private void uncoverAdjacentSquares(int row, int col) {
        for (int x = row - 1; x <= row + 1; x++) {
            for (int y = col - 1; y <= col + 1; y++) {
                if (isValid(x, y) && !visited[x][y]) {
                    uncoverSquare(x, y, false); 	// Squares uncovered are not selected by player.
                }
            }
        }
    }

	// Method to count amount of mines on selected square
    public int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int x = row - 1; x <= row + 1; x++) {
            for (int y = col - 1; y <= col + 1; y++) {
                if (isValid(x, y) && mines[x][y]) {
                    count++;
                }
            }
        }
        return count;
    }

	// Winning condition
    public boolean hasWon() {
        return remainingSquares == totalMines;
    }

	// Game over message
    public void gameOver() {
        System.out.println("Oh no, you detonated a mine! Game over.");
        System.exit(0);
    }

	// Return values
    public char[][] getGrid() {
        return grid;
    }
	
    public boolean[][] getMines() {
        return mines;
    }

    public boolean[][] getVisited() {
        return visited;
    }

    public int getSize() {
        return size;
    }

    public int getTotalMines() {
        return totalMines;
    }
	
	// Getter setter for remaining squares
    public int getRemainingSquares() {
        return remainingSquares;
    }

    public void setRemainingSquares(int remainingSquares) {
        this.remainingSquares = remainingSquares;
    }
}
