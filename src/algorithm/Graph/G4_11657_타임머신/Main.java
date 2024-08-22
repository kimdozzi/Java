package algorithm.Graph.G4_11657_타임머신;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Edge>[] graph = new List[510];
	static long[] d = new long[510];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// init
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
			d[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[u].add(new Edge(v, w));
		}

		StringBuilder sb = new StringBuilder();
		if (bellmanFord()) {
			sb.append("-1\n");
		} else {
			for (int i = 2; i <= N; i++) {
				if (d[i] == Integer.MAX_VALUE) {
					sb.append("-1\n");
				} else {
					sb.append(d[i] + "\n");
				}
			}
		}
		System.out.println(sb);
	}

	public static boolean bellmanFord() {
		d[1] = 0; // 시작점은 0으로 초기화.
		boolean update = false;

		// (정점의 개수 - 1)번 동안 최단거리 초기화 작업을 반복함.
		for (int i = 1; i < N; i++) {
			update = false;

			// 최단거리 초기화.
			for (int j = 1; j <= N; j++) {
				for (Edge edge : graph[j]) {
					if (d[j] == Integer.MAX_VALUE) {
						break;
					}

					if (d[edge.to] > d[j] + edge.weight) {
						d[edge.to] = d[j] + edge.weight;
						update = true;
					}
				}
			}

			// 더 이상 최단거리 초기화가 일어나지 않았을 경우 반복문을 종료.
			if (!update) {
				break;
			}
		}

		// (정점의 개수 - 1)번까지 계속 업데이트가 발생했을 경우
		// (정점의 개수)번도 업데이트 발생하면 음수 사이클이 일어난 것을 의미함.
		if (update) {
			for (int i = 1; i <= N; i++) {
				for (Edge edge : graph[i]) {
					if (d[i] == Integer.MAX_VALUE) {
						break;
					}

					if (d[edge.to] > d[i] + edge.weight) {
						return true;
					}
				}
			}
		}
		return false;
	}

	static class Edge implements Comparable<Edge> {
		int to;
		int weight;

		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o1) {
			return this.weight - o1.weight;
		}
	}
}
