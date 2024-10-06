package 삼성문제유형.배열회전;

public class Main {
	public static void main(String[] args) {
		int n = 3;
		int m = 3;
		int[][] grid = new int[][] {
			{1, 2, 3}, {4, 5, 6}, {7, 8, 9}
		};
		System.out.println("Original 정사각형");
		print(grid);
		System.out.println();

		// 1. 정사각형
		// 90도 회전
		int[][] new_90 = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				new_90[j][n - i - 1] = grid[i][j];
			}
		}

		System.out.println("정사각형 시계방향 90도 (반시계방향 270도) 회전 후 ");
		print(new_90);
		System.out.println();

		// 180도 회전
		int[][] new_180 = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				new_180[n - i - 1][m - j - 1] = grid[i][j];
			}
		}

		System.out.println("정사각형 시계방향 180도 (반시계방향 180도) 회전 후 ");
		print(new_180);
		System.out.println();

		// 270도 회전
		int[][] new_270 = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				new_90[n - j - 1][i] = grid[i][j];
			}
		}

		System.out.println("정사각형 시계방향 270도 (반시계방향 90도) 회전 후 ");
		print(new_90);
		System.out.println();

		// ============================================================

		// 2. 직사각형
		int x = 3;
		int y = 5;
		int[][] board = new int[][] {
			{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}
		};
		System.out.println("Original 직사각형");
		print(board);
		System.out.println();

		int newX = y;
		int newY = x;
		int[][] new90 = new int[newX][newY];

		for (int i = 0; i < newY; i++) {
			for (int j = 0; j < newX; j++) {
				new90[j][newY - i - 1] = board[i][j];
			}
		}
		System.out.println("직사각형 시계방향 90도 (반시계방향 270도) 회전 후 ");
		print(new90);
		System.out.println();

		int[][] new180 = new int[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				new180[x - i - 1][y - j - 1] = board[i][j];
			}
		}
		System.out.println("직사각형 시계방향 180도 (반시계방향 180도) 회전 후 ");
		print(new180);
		System.out.println();

		newX = y;
		newY = x;
		int[][] new270 = new int[newX][newY];

		for (int i = 0; i < newY; i++) {
			for (int j = 0; j < newX; j++) {
				new270[newX - j - 1][i] = board[i][j];
			}
		}
		System.out.println("직사각형 시계방향 270도 (반시계방향 90도) 회전 후 ");
		print(new270);
		System.out.println();

	}

	private static void print(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
}
