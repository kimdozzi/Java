package algorithm.삼성전자dx특강.day06.보급로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    static final int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static int N;
    static int[][] board;
    static int[][] newBoard;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            newBoard = new int[N][N];

            for (int i = 0; i < N; i++) {
                Arrays.fill(newBoard[i], Integer.MAX_VALUE);
            }

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                char[] chars = input.toCharArray();
                for (int j = 0; j < N; j++) {
                    board[i][j] = chars[j] - '0';
                }
            }
            bfs();

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ").append(newBoard[N - 1][N - 1]);
            System.out.println(sb);
        }

        System.out.println(
                (System.currentTimeMillis() - startTime) / 1000 + "." + (System.currentTimeMillis() - startTime) % 1000
                        + "초");
    }

    private static void bfs() {
        Queue<Pair> q = new PriorityQueue<>();
        q.add(new Pair(0, 0, 0));
        newBoard[0][0] = 0;

        while (!q.isEmpty()) {
            Pair pair = q.poll();
            // 추가
            if (pair.w > newBoard[pair.x][pair.y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = pair.x + dirs[i][0];
                int ny = pair.y + dirs[i][1];

                if (!inRange(nx, ny)) {
                    continue;
                }
                int nw = pair.w + board[nx][ny];
                if (newBoard[nx][ny] > nw) {
                    newBoard[nx][ny] = nw;
                    q.add(new Pair(nx, ny, newBoard[nx][ny]));
                }

//                if (newBoard[nx][ny] > newBoard[pair.x][pair.y] + board[nx][ny]) {
//                    newBoard[nx][ny] = newBoard[pair.x][pair.y] + board[nx][ny];
//                    q.add(new Pair(nx, ny,newBoard[nx][ny]));
//                }
            }
        }
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static void print(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int w;

        Pair(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Pair o1) {
            return this.w - o1.w;

        }
    }
}
