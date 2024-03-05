package samsung.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Cloud {
    int x;
    int y;

    Cloud(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class 마법사_상어와_비바라기 {
    static int n,m;
    static int[][] board;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static boolean[][] visit;
    static Queue<Cloud> clouds = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][n];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        init_cloud();

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            // 1. 모든 구름 이동
            move_clouds(d, s);

            // 2. 구름에서 비가 내려 구름이 있는 칸의 물의 양 증가
            increase_water();

            // 3. 구름 소멸 and 4. 물복사버그
            delete_clouds_and_water_copy_bug();

            // 5. 구름 생성
            create_clouds();
        }

        int res = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                res += board[i][j];
            }
        }
        System.out.println(res);
    }

    private static void create_clouds() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (!visit[i][j] && board[i][j] >= 2){
                    create_cloud(i, j);
                    board[i][j] -= 2;
                }
            }
        }
    }


    private static void delete_clouds_and_water_copy_bug() {
        visit = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visit[i], false);
        }

        while (!clouds.isEmpty()) {
            Cloud cloud = clouds.poll();
            int cnt = 0;

            visit[cloud.x][cloud.y] = true;
            for (int i=1; i<8; i+=2) {
                int nx = cloud.x + dx[i];
                int ny = cloud.y + dy[i];
                if (!in_range(nx, ny)) continue;
                if (board[nx][ny] >= 1) cnt++;
            }
            board[cloud.x][cloud.y] += cnt;
        }
    }

    private static boolean in_range(int nx, int ny) {
        return 0 <= nx && nx < n && 0 <= ny && ny < n;
    }

    private static void increase_water() {
        for (Cloud cloud: clouds) {
            board[cloud.x][cloud.y]++;
        }
    }

    private static void move_clouds(int d, int s) {
        int dist_x = (dx[d] * s) + (n * 50);
        int dist_y = (dy[d] * s) + (n * 50);
        for (Cloud cloud : clouds) {
            cloud.x = (dist_x + cloud.x) % n;
            cloud.y = (dist_y + cloud.y) % n;
        }
    }

    private static void init_cloud() {
        create_cloud(n-1, 0);
        create_cloud(n-1,1);
        create_cloud(n-2,0);
        create_cloud(n-2,1);
    }

    private static void create_cloud(int x, int y) {
        clouds.add(new Cloud(x, y));
    }
}
