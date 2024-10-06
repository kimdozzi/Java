package 삼성문제유형.중력하강;

public class Main {
	public static void main(String[] args) {
		int[][] arr = new int[][] {
			{0, 1, 0}, {1, 0, 1}, {0, 1, 0}, {0, 0, 1}, {0, 1, 0}
		};

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

		int n = arr.length;
		int m = arr[0].length;

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < m; j++) {
				int p = i; // 맨 위의 칸부터 n-1칸까지 내려가는 위치를 잡는 변수
				while (0 <= p && arr[p][j] == 1 && arr[p + 1][j] == 0) {
					int temp = arr[p + 1][j];
					arr[p + 1][j] = arr[p][j];
					arr[p][j] = temp;
					p--;
				}
			}
		}
		System.out.println();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
