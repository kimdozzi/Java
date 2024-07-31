package algorithm.arrayRotation.G5_16926_배열돌리기1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m,r;
    static int min;
    static int[][] board;
    static int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Math.min(n,m);

        while (r-- > 0) {
            rotate();
        }

        print();
    }

    private static void rotate() {
        for(int t=0; t<min/2; t++) {
            int x = t; // 시작 좌표 x
            int y = t; // 시작 좌표 y
            int temp = board[x][y], d = 0;

            while (d < 4) {
                int nx = x + dirs[d][0];
                int ny = y + dirs[d][1];

                if(!(t <= nx && nx < n-t && t <= ny && ny < m-t)) {
                    d++;
                    continue;
                }

                board[x][y] = board[nx][ny];
                x = nx;
                y = ny;
            }
            board[t+1][t] = temp;
        }
    }
    static void print() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
