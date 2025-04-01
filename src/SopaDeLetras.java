public class SopaDeLetras {
    private static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
    };

    public static boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public static boolean search(char[][] board, String word, int x, int y, int dx, int dy, int index) {
        if (index == word.length()) return true;
        int rows = board.length, cols = board[0].length;
        if (!isValid(x, y, rows, cols) || board[x][y] != word.charAt(index)) return false;
        return search(board, word, x + dx, y + dy, dx, dy, index + 1);
    }

    public static int[][] findWord(char[][] board, String word) {
        int rows = board.length, cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    for (int[] dir : DIRECTIONS) {
                        if (search(board, word, i, j, dir[0], dir[1], 0)) {
                            int endX = i + (word.length() - 1) * dir[0];
                            int endY = j + (word.length() - 1) * dir[1];
                            return new int[][]{{i + 1, j + 1}, {endX + 1, endY + 1}};
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'E', 'S', 'T', 'O'},
                {'S', 'T', 'T', 'M'},
                {'E', 'A', 'S', 'A'},
                {'P', 'R', 'N', 'E'}
        };

        String[] words = {"ESTO", "ESE", "PATO", "ESTE"};
        for (String word : words) {
            int[][] positions = findWord(board, word);
            if (positions != null) {
                System.out.println("La palabra '" + word + "' comienza en " +
                        java.util.Arrays.toString(positions[0]) + " y termina en " +
                        java.util.Arrays.toString(positions[1]));
            } else {
                System.out.println("La palabra '" + word + "' no se encontró en la sopa de letras.");
            }
        }
    }
}
