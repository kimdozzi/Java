package algorithm.Simulation.G3_15683_감시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M; // 행, 열
	static int[][] map; // 원본 격자
	static int[][] copyMap; // 사각 지대 계산을 위한 복사본 격자
	static int[] permutation;
	static List<CCTV> cctvList;
	static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌 시계방향
	static int[] dy = {0, 1, 0, -1};
	static int answer = Integer.MAX_VALUE; // 최소 사각 지대의 수를 구하기 위한 변수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cctvList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] != 0 && map[i][j] != 6)
					cctvList.add(new CCTV(map[i][j], i, j));
			}
		}
		permutation = new int[cctvList.size()];
		rec_func(0, cctvList.size());

		System.out.println(answer);
	}

	static void rec_func(int depth, int target) {
		if (depth == target) {
			copyMap = new int[N][M];
			for (int i = 0; i < map.length; i++) {
				copyMap[i] = map[i].clone();
			}

			for (int i = 0; i < cctvList.size(); i++) {
				direction(cctvList.get(i), permutation[i]);
			}

			getBlindSpot();

			return;
		}

		for (int i = 0; i < 4; i++) {
			permutation[depth] = i;
			rec_func(depth + 1, target);
		}
	}

	static void getBlindSpot() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 0)
					cnt++;
			}
		}
		answer = Math.min(answer, cnt);
	}

	static void direction(CCTV cctv, int d) {
		int cctvNumber = cctv.num;

		switch (cctvNumber) {
			case 1:
				watch(cctv, d);
				break;
			case 2:
				if (d == 0 || d == 2) {
					watch(cctv, 0);
					watch(cctv, 2);
				} else {
					watch(cctv, 1);
					watch(cctv, 3);
				}
				break;
			case 3:
				if (d == 0) {
					watch(cctv, 0); // 상우
					watch(cctv, 1);
				} else if (d == 1) {
					watch(cctv, 1); // 우하
					watch(cctv, 2);
				} else if (d == 2) {
					watch(cctv, 2); // 하좌
					watch(cctv, 3);
				} else if (d == 3) {
					watch(cctv, 0); // 좌상
					watch(cctv, 3);
				}
				break;
			case 4:
				if (d == 0) {
					watch(cctv, 0); // 좌상우
					watch(cctv, 1);
					watch(cctv, 3);
				} else if (d == 1) {
					watch(cctv, 0); // 상우하
					watch(cctv, 1);
					watch(cctv, 2);
				} else if (d == 2) {
					watch(cctv, 1); // 좌하우
					watch(cctv, 2);
					watch(cctv, 3);
				} else if (d == 3) {
					watch(cctv, 0); // 상좌하
					watch(cctv, 2);
					watch(cctv, 3);
				}
				break;
			case 5:
				watch(cctv, 0);
				watch(cctv, 1);
				watch(cctv, 2);
				watch(cctv, 3);
				break;
		}
	}

	static void watch(CCTV cctv, int d) {
		Queue<CCTV> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.add(cctv);
		visited[cctv.x][cctv.y] = true;

		while (!q.isEmpty()) {
			CCTV cctvObj = q.poll();
			int nx = cctvObj.x + dx[d];
			int ny = cctvObj.y + dy[d];

			if (!(0 <= nx && nx < N && 0 <= ny && ny < M))
				break;
			if (copyMap[nx][ny] == 6)
				break;

			if (copyMap[nx][ny] == 0) {
				copyMap[nx][ny] = -1;
			}
			q.add(new CCTV(cctv.num, nx, ny));
		}
	}

	static class CCTV {
		int num;
		int x;
		int y;

		CCTV(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}
}
