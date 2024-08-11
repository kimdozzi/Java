package algorithm.Exhaustive_Search.bruteforce.S2_16924_십자가찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int N, M;
    static char[][] board;
    static char[][] newBoard;
    static int ans = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        newBoard = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
                newBoard[i][j] = '.';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == '*') {
                    isAvailable(i, j);
                }
            }
        }

        //print(board);

        //print(newBoard);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != newBoard[i][j]) {
                    System.out.println(-1);
                    System.exit(0);
                }
            }
        }
        System.out.println(ans);
        System.out.println(sb);
    }

    private static void print(char[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void draw(int x, int y, int val) {
        int k = 0;
        newBoard[x][y] = '*';

        while (k <= val) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j <= k; j++) {
                    int nx = x + (dirs[i][0] * (k + 1));
                    int ny = y + (dirs[i][1] * (k + 1));

                    if (!inRange(nx, ny)) {
                        continue;
                    }
                    newBoard[nx][ny] = '*';

                }
            }
            k++;
        }
    }

    private static void isAvailable(int x, int y) {
        int k = 0;
        while (true) {
            boolean flag = false;

            for (int i = 0; i < 4; i++) {
                int nx = x + (dirs[i][0] * (k + 1));
                int ny = y + (dirs[i][1] * (k + 1));
                if (!inRange(nx, ny)) {
                    flag = true;
                    break;
                }
                if (board[nx][ny] != '*') {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                draw(x, y, k);
                k++;
                ans++;
                sb.append(x + 1).append(" ").append(y + 1).append(" ").append(k).append("\n");

            } else {
                break;
            }
        }
    }

    private static boolean inRange(int nx, int ny) {
        return 0 <= nx && nx < N && 0 <= ny && ny < M;
    }
}
