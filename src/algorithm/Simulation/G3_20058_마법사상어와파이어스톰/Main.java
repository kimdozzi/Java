package algorithm.Simulation.G3_20058_마법사상어와파이어스톰;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[][] board;
    static int[] queries;
    static int boardSize;
    static int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        boardSize = (int) Math.pow(2, N);
        board = new int[boardSize][boardSize];
        queries = new int[Q];

        for (int i = 0; i < boardSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            queries[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < Q; i++) {
            int[][] tempBoard = new int[boardSize][boardSize];
            solve(tempBoard, queries[i], 0, 0, N, boardSize);
            for (int j = 0; j < boardSize; j++) {
                board[j] = tempBoard[j].clone();
            }

            board = ice(boardSize);
        }

//        print(board);
        System.out.println(totalIce(boardSize));

        int ans = 0;
        for(int i=0; i<boardSize; i++) {
            for (int j=0; j<boardSize; j++) {
                if (board[i][j] > 0) {
                    ans = Math.max(ans, bfs(boardSize, i,j));
                }
            }
        }
        System.out.println(ans);
    }
    static int bfs(int boardSize, int x,int y) {
        Queue<Pair> q = new LinkedList<>();
        int cnt = 1;
        q.add(new Pair(x, y));
        boolean[][] visited = new boolean[boardSize][boardSize];
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Pair pair = q.poll();

            for(int d=0; d<4; d++) {
                int nx = pair.x + dirs[d][0];
                int ny = pair.y + dirs[d][1];

                if (!inRange(boardSize,nx,ny) || visited[nx][ny]) continue;
                if (board[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    cnt++;
                    q.add(new Pair(nx, ny));
                }
            }
        }
        return cnt;
    }
    static int totalIce(int len) {
        int ret = 0;
        for(int i=0; i<len; i++) {
            for (int j=0; j<len; j++) {
                ret += board[i][j];
            }
        }
        return ret;
    }

    static int[][] ice(int len) {
        int[][] tempBoard = new int[len][len];
        for(int i=0; i<len; i++) {
            tempBoard[i] = board[i].clone();
        }
        for (int x = 0; x < len; x++) {
            for (int y = 0; y < len; y++) {
                if (board[x][y] <= 0) continue;
                int iceCount = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = x + dirs[d][0];
                    int ny = y + dirs[d][1];
                    if (!inRange(len, nx, ny)) {
                        continue;
                    }
                    if (board[nx][ny] > 0) {
                        iceCount++;
                    }
                }
                if (iceCount < 3) {
                    tempBoard[x][y]--;
                }
            }
        }
        return tempBoard;
    }

    private static boolean inRange(int len, int nx, int ny) {
        return 0 <= nx && nx < len && 0 <= ny && ny < len;
    }

    static void solve(int[][] tempBoard, int query, int x, int y, int nn, int boardSize) { // query : 1
        if (query == nn) {
            rotate(tempBoard, x, y, boardSize);
        } else {
            int mid = boardSize / 2;
            // 좌상단
            solve(tempBoard, query, x, y, nn - 1, mid);
            // 우상단
            solve(tempBoard, query, x, y + mid, nn - 1, mid);
            // 좌하단
            solve(tempBoard, query, x + mid, y, nn - 1, mid);
            // 우하단
            solve(tempBoard, query, x + mid, y + mid, nn - 1, mid);
        }
    }

    static void rotate(int[][] tempBoard, int x, int y, int boardSize) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                tempBoard[x + j][y + boardSize - 1 - i] = board[x + i][y + j];
            }
        }

    }

    static void print(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
