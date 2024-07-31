package algorithm.arrayRotation.G5_16935_배열돌리기3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int[][] arr;
    static List<Integer> commands = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            commands.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<commands.size(); i++) {
            solve(commands.get(i));
        }
        print();

    }

    private static void solve(int command) {
        switch (command) {
            case 1:
                upDown();
                break;
            case 2:
                leftRight();
                break;
            case 3:
                right();
                break;
            case 4:
                left();
                break;
            case 5:
                divideAndRight();
                break;
            case 6:
                divideAndLeft();
                break;
            default:
                break;
        }
    }

    private static void divideAndLeft() {
        int[][] tempArr = new int[N][M];

        divideArea(tempArr, 0, 0, N / 2, 0);

        divideArea(tempArr, N / 2, 0, N / 2, M / 2);

        divideArea(tempArr, N / 2, M / 2, 0, M / 2);

        divideArea(tempArr, 0, M / 2, 0, 0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempArr[i][j] > 0) {
                    arr[i][j] = tempArr[i][j];
                }
            }
        }
    }

    private static void divideAndRight() {
        int[][] tempArr = new int[N][M];

        // 구역 1
        divideArea(tempArr, 0, 0, 0, M / 2);

        // 구역 2
        divideArea(tempArr, 0, M / 2, N / 2, M / 2);

        // 구역 3
        divideArea(tempArr, N / 2, M / 2, N / 2, 0);

        // 구역 4
        divideArea(tempArr, N / 2, 0, 0, 0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempArr[i][j] > 0) {
                    arr[i][j] = tempArr[i][j];
                }
            }
        }
    }

    private static void divideArea(int[][] tempArr, int prevX, int prevY, int nextX, int nextY) {
        for (int i = prevX; i < prevX + (N / 2); i++) {
            for (int j = prevY; j < prevY + (M / 2); j++) {
                tempArr[nextX][nextY++] = arr[i][j];
            }
            nextX++;
            nextY -= (M/2);
        }
    }

    private static void left() {
        // 회전하면서 row, col 길이가 달라짐
        int MAX = Math.max(N, M);

        int[][] tempArr = new int[M][N];

        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (i >= M || j >= N) {
                    continue;
                }
                tempArr[i][j] = arr[j][M - i - 1];
            }
        }

        arr = new int[M][N];
        for (int i = 0; i < M; i++) {
            arr[i] = tempArr[i].clone();
        }

        int temp = N;
        N = M;
        M = temp;
    }

    private static void right() {
        // 회전하면서 row, col 길이가 달라짐
        int MAX = Math.max(N, M);

        int[][] tempArr = new int[M][N];

        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if (i >= N || j >= M) {
                    continue;
                }

                tempArr[j][N - i - 1] = arr[i][j];
            }
        }

        arr = new int[M][N];
        for (int i = 0; i < M; i++) {
            arr[i] = tempArr[i].clone();
        }

        int temp = N;
        N = M;
        M = temp;
    }

    private static void leftRight() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M / 2 - 1; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[i][M - j - 1];
                arr[i][M - j - 1] = temp;
            }
        }
    }

    private static void upDown() {
        for (int i = 0; i <= N / 2 - 1; i++) {
            for (int j = 0; j < M; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[N - i - 1][j];
                arr[N - i - 1][j] = temp;
            }
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
