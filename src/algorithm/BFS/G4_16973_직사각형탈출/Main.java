package algorithm.BFS.G4_16973_직사각형탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int[][] map;
	private static boolean[][] check;
	private static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	private static int n, m, h, w, sx, sy, ex, ey, result = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		check = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken()) - 1;
		sy = Integer.parseInt(st.nextToken()) - 1;
		ex = Integer.parseInt(st.nextToken()) - 1;
		ey = Integer.parseInt(st.nextToken()) - 1;
		bfs();
		System.out.println(result);
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque();
		check[sx][sy] = true;
		q.offer(new int[] {sx, sy, 0});

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int cnt = cur[2];
			if (x == ex && y == ey) {
				result = cnt;
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				if (!inRange(nx, ny) || check[nx][ny] || map[nx][ny] == 1) {
					continue;
				}
				if (checkRange(nx, ny)) {
					check[nx][ny] = true;
					q.offer(new int[] {nx, ny, cnt + 1});
				}
			}
		}
	}

	private static boolean inRange(int nx, int ny) {
		return 0 <= nx && nx < n && 0 <= ny && ny < m;
	}

	private static boolean checkRange(int nx, int ny) {
		if (nx + h - 1 >= n || ny + w - 1 >= m) {
			return false;
		}
		for (int x = 0; x < h; x++) {
			for (int y = 0; y < w; y++) {
				if (map[nx + x][ny + y] == 1) {
					return false;
				}
			}
		}
		return true;
	}
}