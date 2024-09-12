package algorithm.MST.G4_1922_네트워크연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parent;
	static int[][] trees;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parent = new int[N + 1];
		trees = new int[M][3];

		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			trees[i][0] = a;
			trees[i][1] = b;
			trees[i][2] = c;
		}

		// cost 오름차순 정렬
		Arrays.sort(trees, (o1, o2) -> Integer.compare(o1[2], o2[2]));

		// makeSet
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			if (find(trees[i][0]) != find(trees[i][1])) {
				union(trees[i][0], trees[i][1]);
				ans += trees[i][2];
			}
		}

		System.out.println(ans);

	}

	private static int find(int x) {
		if (parent[x] == x)
			return x;
		else
			return parent[x] = find(parent[x]);
	}

	private static boolean union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return false;

		if (x > y) {
			parent[x] = y;
		} else {
			parent[y] = x;
		}
		return true;
	}

}
