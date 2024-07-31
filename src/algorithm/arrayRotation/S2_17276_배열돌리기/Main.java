package algorithm.arrayRotation.S2_17276_배열돌리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N, D;
    static int[][] board, tempBoard;

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            if (D < 0) {
                D += 360;
            }
            D /= 45;

            board = new int[N][N];
            tempBoard = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    tempBoard[i][j] = board[i][j];
                }
            }

            // solve
            solve();

            // print
            print();
        }
    }

    private static void solve() {
        while (D-- > 0) {
            rotate();
        }
    }

    private static void rotate() {
        for (int i = 0; i < N; i++) {
            tempBoard[i][N / 2] = board[i][i];
            tempBoard[i][N - i - 1] = board[i][N / 2];
            tempBoard[N / 2][N - i - 1] = board[i][N - i - 1];
            tempBoard[N - i - 1][N - i - 1] = board[N / 2][N - i - 1];
        }
        for (int i = 0; i < N; i++) {
            board[i] = tempBoard[i].clone();
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}