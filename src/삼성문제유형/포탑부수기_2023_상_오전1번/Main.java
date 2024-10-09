package 삼성문제유형.포탑부수기_2023_상_오전1번;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K; // 행, 열, 총 수행 횟수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

	}

	private static void chooseMethod() {
		int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}}; // 우 하 좌 상

	}

	private static void chooseTarget() {

	}

	private static void chooseAttacker() {

	}

	static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Turret implements Comparable<Turret> {
		int x;
		int y;
		int p;
		int r;

		public Turret(int x, int y, int r, int p) {
			this.x = x;
			this.y = y;
			this.r = r;
			this.p = p;
		}

		@Override
		public int compareTo(Turret t) {
			if (this.p != t.p)
				return this.p - t.p;
			if (this.r != t.r)
				return t.r - this.r;
			if ((this.x + this.y) != (t.x + t.y))
				return (t.x + t.y) - (this.x + this.y);
			return t.y - this.y;
		}
	}
}














