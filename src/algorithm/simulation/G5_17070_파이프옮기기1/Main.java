package algorithm.simulation.G5_17070_파이프옮기기1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        input();

        dfs(0, 1, 0);
    }

    private static int dfs(int x, int y, int dir) {
        if (x == N - 1 && y == N - 1) {
            return 1;
        }

        int ans = 0;

        if (dir == 0) {
            if (y + 1 < N && map[x][y + 1] == 0) {
                ans += dfs(x, y + 1, 0);
            }
            if (x + 1 < N && y + 1 < N && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
                ans += dfs(x + 1, y + 1, 1);
            }
        } else if (dir == 1) {
            if (y + 1 < N && map[x][y + 1] == 0) {
                ans += dfs(x, y + 1, 0);
            }
            if (x + 1 < N && y + 1 < N && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
                ans += dfs(x + 1, y + 1, 1);
            }
            if (x + 1 < N && map[x + 1][y] == 0) {
                ans += dfs(x + 1, y, 2);
            }
        } else if (dir == 2) {
            if (x + 1 < N && y + 1 < N && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
                ans += dfs(x + 1, y + 1, 1);
            }
            if (x + 1 < N && map[x + 1][y] == 0) {
                ans += dfs(x + 1, y, 2);
            }
        }

        return ans;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0,1,0));
    }
}
