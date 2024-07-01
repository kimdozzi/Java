package algorithm.baekjoon.S1_16948_데스나이트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int x;
    int y;
    int dist;

    public Pair(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class Main {
    static int[][] dir = {{-2, -1}, {-2, 1}, {0, -2}, {0, 2}, {2, -1}, {2, 1}};
    static int[][] board;
    static boolean[][] visited;
    static int N, cnt = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        visited = new boolean[N][N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        solve(x1, y1, x2, y2);
        System.out.print(cnt != Integer.MAX_VALUE ? cnt : -1);
    }

    private static void solve(int x1, int y1, int x2, int y2) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x1,y1,0));
        visited[x1][y1] = true;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            if (pair.x == x2 && pair.y == y2) {
                cnt = Math.min(cnt, pair.dist);
                return;
            }

            for(int d=0; d<dir.length; d++) {
                int nx = pair.x + dir[d][0];
                int ny = pair.y + dir[d][1];

                if(!(0 <= nx && nx < N && 0 <= ny && ny < N)) continue;
                if(!visited[nx][ny]) {
                    q.add(new Pair(nx,ny, pair.dist+1));
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
