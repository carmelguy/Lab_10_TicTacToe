import java.util.Scanner;

public class TicTacToe {
    // Class constants and variables
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String player;
        int moveCnt;
        int row, col;
        boolean playAgain = true;

        // Pseudocode Outline
        // 1. Initialize game variables
        // 2. Enter the play again loop
        //    a. Clear the board
        //    b. Set player to X (starts first)
        //    c. Reset move counter
        //    d. Enter the game loop
        //       i. Display the board
        //       ii. Get the player's move (row and column 1-3)
        //       iii. Convert player input to array indices (0-2)
        //       iv. Loop until valid move
        //       v. Record move on board
        //       vi. Increment move counter
        //       vii. Check for win or tie if move count >= 5
        //       viii. If game over, break game loop
        //       ix. Toggle player (X to O, O to X)
        //    e. Display final board and result
        //    f. Prompt to play again
        // 3. Thank the players for playing

        // Main play again loop
        while (playAgain) {
            // Initialize for a new game
            clearBoard();
            player = "X";  // X always goes first
            moveCnt = 0;
            boolean gameOver = false;

            // Main game loop
            while (!gameOver) {
                // Display current board state
                System.out.println("Current board:");
                display();

                // Get player move
                System.out.println("Player " + player + "'s turn");

                boolean validInput = false;
                do {
                    // Get the row and column from the user (1-3)
                    row = SafeInput.getRangedInt(console, "Enter row", 1, 3);
                    col = SafeInput.getRangedInt(console, "Enter column", 1, 3);

                    // Convert to array indices (0-2)
                    row--;
                    col--;

                    // Check if move is valid
                    if (isValidMove(row, col)) {
                        validInput = true;
                    } else {
                        System.out.println("That space is already taken. Try again.");
                    }
                } while (!validInput);

                // Record the move
                board[row][col] = player;
                moveCnt++;

                // Check for win or tie, but only if enough moves have been made
                if (moveCnt >= 5) { // No win possible before 5 moves
                    if (isWin(player)) {
                        System.out.println("Current board:");
                        display();
                        System.out.println("Player " + player + " wins!");
                        gameOver = true;
                    } else if (moveCnt >= 9 || isTie()) {
                        System.out.println("Current board:");
                        display();
                        System.out.println("It's a tie!");
                        gameOver = true;
                    }
                }

                // Toggle player if game not over
                if (!gameOver) {
                    player = (player.equals("X")) ? "O" : "X";
                }
            }

            // Ask to play again
            playAgain = SafeInput.getYNConfirm(console, "Do you want to play again?");
        }

        System.out.println("Thanks for playing!");
    }

    /**
     * Clear the board by setting all elements to a space
     */
    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    /**
     * Display the current state of the Tic Tac Toe board
     */
    private static void display() {
        System.out.println("  1 2 3");
        for (int row = 0; row < ROWS; row++) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col]);
                if (col < COLS - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < ROWS - 1) {
                System.out.println("  -----");
            }
        }
    }

    /**
     * Check if the move is valid (space is empty)
     * @param row Row index (0-2)
     * @param col Column index (0-2)
     * @return true if the move is valid
     */
    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    /**
     * Check if the specified player has won
     * @param player The player to check (X or O)
     * @return true if the player has won
     */
    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    /**
     * Check if the player has a column win
     * @param player The player to check (X or O)
     * @return true if a column win is detected
     */
    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the player has a row win
     * @param player The player to check (X or O)
     * @return true if a row win is detected
     */
    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the player has a diagonal win
     * @param player The player to check (X or O)
     * @return true if a diagonal win is detected
     */
    private static boolean isDiagonalWin(String player) {
        // Check main diagonal (top-left to bottom-right)
        if (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player)) {
            return true;
        }

        // Check other diagonal (top-right to bottom-left)
        if (board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player)) {
            return true;
        }

        return false;
    }

    /**
     * Check if the game is a tie
     * @return true if the game is a tie
     */
    private static boolean isTie() {
        // Check if all spaces are filled
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(" ")) {
                    return false; // There's at least one empty space
                }
            }
        }
        return true; // All spaces are filled and no win
    }
}