package algorithm.Graph.G4_17141_연구소2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int N, M, ans = Integer.MAX_VALUE;
    static Pair[] route;
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static List<Pair> virus = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        route = new Pair[M];
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    virus.add(new Pair(i, j, 0));
                }
            }
        }

        backTracking(0, 0);

        if (ans == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(ans + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs() {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int ret = 0;

        for (int i = 0; i < M; i++) {
            Pair pair = route[i];
            q.add(new Pair(pair.x, pair.y, pair.cost));
            visited[pair.x][pair.y] = true;
        }

        while (!q.isEmpty()) {
            if (ret >= ans) {
                return;
            }
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair pair = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = pair.x + dirs[d][0];
                    int ny = pair.y + dirs[d][1];

                    if (!(0 <= nx && nx < N && 0 <= ny && ny < N)) {
                        continue;
                    }
                    if (visited[nx][ny]) {
                        continue;
                    }

                    if (board[nx][ny] != 1) {
                        visited[nx][ny] = true;
                        q.add(new Pair(nx, ny, pair.cost + 1));
                    }
                }
            }
            ret++;
        }

        if (laboratoryCheck(visited))    //모든 칸이 감염되었을 때
        {
            ans = Math.min(ans, ret - 1);
        }
    }

    static boolean laboratoryCheck(boolean[][] visited) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 1 && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    static void backTracking(int depth, int start) {
        if (depth == M) {
            bfs();
            return;
        }
        for (int i = start; i < virus.size(); i++) {
            route[depth] = virus.get(i);
            backTracking(depth + 1, i + 1);
        }
    }

    static class Pair {
        int x;
        int y;
        int cost;

        Pair(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
