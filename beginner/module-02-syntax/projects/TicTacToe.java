
import java.util.Scanner;

/**
 * МИНИ-ПРОЕКТ: Крестики-нолики (Tic-Tac-Toe)
 *
 * Полная реализация игры с: - Игровым полем 3x3 - Двумя игроками - Проверкой
 * победы - Проверкой ничьей - Валидацией ходов
 */
public class TicTacToe {

    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static Scanner scanner = new Scanner(System.in);
    private static int movesCount = 0;

    public static void main(String[] args) {
        printWelcome();
        initializeBoard();

        boolean gameRunning = true;

        while (gameRunning) {
            printBoard();
            makeMove();

            if (checkWin()) {
                printBoard();
                System.out.println("\n🎉 Игрок " + currentPlayer + " победил!");
                gameRunning = false;
            } else if (movesCount == 9) {
                printBoard();
                System.out.println("\n🤝 Ничья!");
                gameRunning = false;
            } else {
                switchPlayer();
            }
        }

        scanner.close();
    }

    private static void printWelcome() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║                                        ║");
        System.out.println("║         КРЕСТИКИ-НОЛИКИ                ║");
        System.out.println("║                                        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Правила:");
        System.out.println("- Игроки ходят по очереди");
        System.out.println("- Игрок X ходит первым");
        System.out.println("- Введите номер строки (1-3) и столбца (1-3)");
        System.out.println("- Побеждает тот, кто первым выстроит 3 в ряд");
        System.out.println();
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        movesCount = 0;
    }

    private static void printBoard() {
        System.out.println("\n    1   2   3");
        System.out.println("  ┌───┬───┬───┐");

        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " │");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " │");
            }
            System.out.println();

            if (i < 2) {
                System.out.println("  ├───┼───┼───┤");
            }
        }

        System.out.println("  └───┴───┴───┘");
    }

    private static void makeMove() {
        boolean validMove = false;

        while (!validMove) {
            System.out.println("\nХод игрока " + currentPlayer);
            System.out.print("Введите строку (1-3): ");
            int row = scanner.nextInt() - 1;

            System.out.print("Введите столбец (1-3): ");
            int col = scanner.nextInt() - 1;

            if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                if (board[row][col] == ' ') {
                    board[row][col] = currentPlayer;
                    movesCount++;
                    validMove = true;
                } else {
                    System.out.println("❌ Эта клетка уже занята!");
                }
            } else {
                System.out.println("❌ Неверные координаты! Введите числа от 1 до 3.");
            }
        }
    }

    private static boolean checkWin() {
        // Проверка строк
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer
                    && board[i][1] == currentPlayer
                    && board[i][2] == currentPlayer) {
                return true;
            }
        }

        // Проверка столбцов
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer
                    && board[1][j] == currentPlayer
                    && board[2][j] == currentPlayer) {
                return true;
            }
        }

        // Проверка диагоналей
        if (board[0][0] == currentPlayer
                && board[1][1] == currentPlayer
                && board[2][2] == currentPlayer) {
            return true;
        }

        if (board[0][2] == currentPlayer
                && board[1][1] == currentPlayer
                && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}
