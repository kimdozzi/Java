package algorithm.samsung.arrayRotation.G5_16927_배열돌리기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r;
    static int min;
    static int[][] board;
    static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Math.min(n, m);

        for (int i = 0; i < min / 2; i++) {
            int cnt = r % (((n - 2 * i) + (m - 2 * i)) * 2 - 4);
            rotate(i, cnt);
        }

        print();
    }

    private static void rotate(int idx, int cnt) {
        for (int t = 0; t < cnt; t++) {
            int x = idx; // 시작 좌표 x
            int y = idx; // 시작 좌표 y
            int temp = board[x][y], d = 0;

            while (d < 4) {
                int nx = x + dirs[d][0];
                int ny = y + dirs[d][1];

                if (!(idx <= nx && nx < n - idx && idx <= ny && ny < m - idx)) {
                    d++;
                    continue;
                }

                board[x][y] = board[nx][ny];
                x = nx;
                y = ny;
            }
            board[idx + 1][idx] = temp;
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}

