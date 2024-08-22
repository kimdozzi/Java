package algorithm.Graph.G3_1865_웜홀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
	static int T, N, M, W;
	static int[] d;
	static List<Edge>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringJoiner sj = new StringJoiner("\n");
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			d = new int[N + 1];
			graph = new List[N + 1];
			for (int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				graph[u].add(new Edge(v, val));
				graph[v].add(new Edge(u, val));
			}

			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				graph[u].add(new Edge(v, -val));
			}

			if (bellmanford(1))
				sj.add("YES");
			else
				sj.add("NO");
		}
		System.out.println(sj);
	}

	static boolean bellmanford(int start) {
		d[start] = 0;
		boolean update = false;

		for (int i = 1; i < N; i++) {
			update = false;
			for (int j = 1; j <= N; j++) {
				for (Edge edge : graph[j]) {
					if (d[edge.y] > d[j] + edge.val) {
						d[edge.y] = d[j] + edge.val;
						update = true;
					}
				}
			}

			if (!update) {
				break;
			}
		}

		if (update) {
			for (int i = 1; i <= N; i++) {
				for (Edge edge : graph[i]) {
					if (d[edge.y] > d[i] + edge.val) {
						return true;
					}
				}
			}
		}
		return false;
	}

	static class Edge implements Comparable<Edge> {
		int y;
		int val;

		public Edge(int y, int val) {
			this.y = y;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) {
			return this.val - o.val;
		}
	}
}