package algorithm.graph.G4_14226_이모티콘;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int screen;
    int store;
    int time;

    public Pair(int screen, int store, int time) {
        this.screen = screen;
        this.store = store;
        this.time = time;
    }
}

public class Main {
    static int n;
    static boolean[][] visited = new boolean[1001][1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        bfs(1, 0);
    }

    private static void bfs(int screen, int store) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(screen, store, 0));
        visited[screen][store] = true;

        while (!q.isEmpty()) {
            Pair pair = q.poll();

            if (pair.screen == n) {
                System.out.print(pair.time);
                break;
            }
            // 화면에 있는 이모티콘 -> 클립보드에 저장
            q.add(new Pair(pair.screen, pair.screen, pair.time + 1));

            // 클립보드 -> 화면으로 이모티콘 추가
            // 클립보드가 비어있지 않고, 붙여넣은 후 이모티콘의 총 개수가 N(구하려는 값)보다 작아야 하고, 이전에 방문한 적이 없어야한다.
            if (pair.store != 0 && pair.screen + pair.store <= n && !visited[pair.store + pair.screen][pair.store]) {
                q.add(new Pair(pair.screen + pair.store, pair.store, pair.time + 1));
                visited[pair.store + pair.screen][pair.store] = true;
            }

            // 화면에 있는 이모티콘 1개 삭제
            // 화면에 있는 이모티콘이 1개 이상이어야 하고, 방문한 적이 없어야 한다.
            if (pair.screen - 1 >= 0 && !visited[pair.screen - 1][pair.store]) {
                q.add(new Pair(pair.screen - 1, pair.store, pair.time + 1));
                visited[pair.screen - 1][pair.store] = true;
            }
        }
    }
}
