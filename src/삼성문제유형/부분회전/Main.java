package 삼성문제유형.부분회전;

public class Main {
	static int[][] arr = new int[7][9];
	static int[][] temp_arr = new int[7][9];

	public static void main(String[] args) {

		for (int i = 0; i < 7; i++) {
			for (int j = 1; j <= 9; j++) {
				arr[i][j - 1] = 7 * i + j;
			}
		}
		print(arr);
		System.out.println();

		rotate_90(3, 1, 3);

	}

	private static void rotate_90(int sx, int sy, int length) {

		for (int i = 0; i < 7; i++) {
			temp_arr[i] = arr[i].clone();
		}

		System.out.println("(sx, sy) : " + sx + ", " + sy);
		System.out.println();
		for (int x = sx; x < sx + length; x++) {
			for (int y = sy; y < sy + length; y++) {
				// step 1. (0,0)좌표로 이동
				System.out.println("(x, y) : " + x + ", " + y);

				int ox = x - sx, oy = y - sy;
				System.out.println("(ox, oy) : " + ox + ", " + oy);

				// step 2. 90도 회전한 좌표 구하기
				int rx = oy, ry = length - ox - 1;
				System.out.println("(rx, ry) : " + rx + ", " + ry);

				// step 3. 다시 sx, sy 더한 좌표로 돌아가기
				temp_arr[rx + sx][ry + sy] = arr[x][y];
				System.out.println("(rx+sx, ry+sy) : " + (rx + sx) + ", " + (ry + sy));
				System.out.println();
			}
			System.out.println();
		}

		print(temp_arr);

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
