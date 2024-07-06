package algorithm.samsung.arrayRotation.S3_1913_달팽이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int n = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];
        int start = n * n;
        int x = 0, y = 0, d = 0;
        int ansX = 1, ansY = 1;
        board[x][y] = start--;

        while (start > 0) {
            x += dirs[d][0];
            y += dirs[d][1];

            if (!(0 <= x && x < n && 0 <= y && y < n) || board[x][y] != 0) {
                x -= dirs[d][0];
                y -= dirs[d][1];
                d = (d + 1 + 4) % 4;
                continue;
            }

            if (board[x][y] == 0) {
                if (start == target) {
                    ansX = x + 1;
                    ansY = y + 1;
                }
                board[x][y] = start--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]).append(' ');
            }
            sb.append('\n');
        }
        sb.append(ansX).append(" ").append(ansY);
        System.out.print(sb);
    }
}
