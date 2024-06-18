import java.util.Scanner;	// To get user input for minesweeper game

public class MinesweeperApp {
	
	//Private declaration of variables
    private MinesweeperGame game;
    private Scanner scanner;

	//Scanning for user input
    public MinesweeperApp() {
        scanner = new Scanner(System.in);
    }

	//Minesweeper UI welcome message
    public void startGame() {
        System.out.println("\n-----------------------");
        System.out.println("Welcome to Minesweeper!");
        System.out.println("-----------------------\n");
        setupGame();
        playGame();
        endGame();
    }
	
	// Game setup with validation checks
    private void setupGame() {
        int size = 0;
		
		// Min size 2, max size 10, must be integer
        while (size < 2 || size > 10) {
            System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Incorrect input. Please enter a valid integer!");
                scanner.next(); // consume invalid input
                continue;
            }
            size = scanner.nextInt();
            if (size < 2) {
                System.out.println("Minimum size of grid is 2. Please enter a valid size.");
            } else if (size > 10) {
                System.out.println("Maximum size of grid is 10. Please enter a valid size.");
            }
        }

		// Maximum mines allowed in grid, must be integer
        int maxMines = (int) (size * size * 0.35);
        System.out.println("Enter the number of mines to place on the grid (maximum is " + maxMines + "): ");
        int totalMines = 0;
        while (totalMines < 1 || totalMines > maxMines) {
            if (!scanner.hasNextInt()) {
                System.out.println("Incorrect input. Please enter a valid integer number of mines.");
                scanner.next(); // consume invalid input
                continue;
            }
            totalMines = scanner.nextInt();
            if (totalMines < 1) {
                System.out.println("There must be at least 1 mine. Please enter a valid number of mines.");
            } else if (totalMines > maxMines) {
                System.out.println("Maximum number of mine is " + maxMines + ". Please enter a valid number of mines.");
            }
        }

		// Initialise the game if valid inputs are given
        game = new MinesweeperGame(size, totalMines);
    }

	// Displays initial grid message
    private void playGame() {
        System.out.println("\nHere is your minefield:");
        game.printGrid();

		// When the game is not complete, program validates to make sure that user selected square matches what is given in grid.
        while (!game.hasWon()) {
            System.out.println("Select a square to reveal (e.g. A1): ");
            String input = scanner.next().toUpperCase(); 	// Forces all input to be upper case so a1 would also work as an input
            if (input.length() < 2 || input.length() > 3 || !Character.isLetter(input.charAt(0)) || !Character.isDigit(input.charAt(1))) {
                System.out.println("Incorrect input. Please enter a valid square (e.g. A1).");
                continue;
            }
            char rowChar = input.charAt(0);
            int row = rowChar - 'A';
            int col;
            if (input.length() == 3 && rowChar >= 'A' && rowChar <= 'J' && input.charAt(1) == '1' && input.charAt(2) == '0') {
                col = 9; // 'j10' corresponds to col 9
            } else {
                col = input.charAt(1) - '1';
            }
			
			// If invalid input
            if (!game.isValid(row, col)) {
                System.out.println("Invalid square. Please select a valid square.");
                continue;
            }
			
			// If square has already been uncovered, using getVisited() from MinesweeperGame
            if (game.getVisited()[row][col]) {
                System.out.println("You have already uncovered this square. Please select a different square.");
                continue;
            }
			
			// Set the selected square to be true to make sure that program knows the square that user has selected.
            boolean selectedSquare = true;
            game.uncoverSquare(row, col, selectedSquare);
			
			// Prints updated message and grid with the squares uncovered.
            System.out.println("\nHere is your updated minefield:");
            game.printGrid();
        }
    }

	// End of game 
    private void endGame() {
        System.out.println("Congratulations, you have won the game!");
        scanner.close();
    }
	
	// Start of game
    public static void main(String[] args) {
        MinesweeperApp minesweeperApp = new MinesweeperApp();
        minesweeperApp.startGame();
    }
}
