package algorithm.BFS.G4_12851_숨바꼭질2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N, K;
	static int[] time = new int[100001];
	static int minTime = Integer.MAX_VALUE;
	static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 음수 처리
		if (N >= K) {
			System.out.println((N - K) + "\n1");
			return;
		}

		bfs();

		System.out.println(minTime + "\n" + count);
	}

	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		time[N] = 1;

		while (!q.isEmpty()) {
			int now = q.poll();

			// now 방문 시간이 최소 시간보다 크면 제외
			if (minTime < time[now])
				return;

			for (int i = 0; i < 3; i++) {
				int next;

				if (i == 0)
					next = now + 1;
				else if (i == 1)
					next = now - 1;
				else
					next = now * 2;

				if (next < 0 || next > 100000)
					continue;

				// 동생에게 도달한 경우
				if (next == K) {
					minTime = time[now]; // 최소 도착 시간 갱신.
					count++;
				}

				// 첫 방문이거나 (time[next] == 0)
				// 이미 방문한 곳이어도 같은 시간에 방문했다면 (time[next] == time[now] + 1)
				// 경우의 수에 추가될 수 있기 때문에 Queue 에 한번 더 넣어줌
				if (time[next] == 0 || time[next] == time[now] + 1) {
					q.add(next);
					time[next] = time[now] + 1;
				}
			}
		}
	}
}