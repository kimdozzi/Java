package algorithm.baekjoon.G5_14222_배열과연산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
        }
        for (int i = 1; i <= N; i++) {
            map.put(i, 1);
        }

        while (!queue.isEmpty()) {
            int val = queue.poll();
            if (val > N) {
                break;
            }
            if (map.containsKey(val)) {
                if (map.get(val) > 0) {
                    map.replace(val, map.get(val) - 1);
                } else {
                    queue.add(val + K);
                }
            }
        }

        boolean flag = false;
        for (int i : map.values()) {
            if (i > 0) {
                flag = true;
            }
        }
        System.out.print(flag ? 0 : 1);
    }
}
