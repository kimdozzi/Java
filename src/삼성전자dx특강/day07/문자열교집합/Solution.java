package 삼성전자dx특강.day07.문자열교집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
    static HashMap<String, Integer> a = new HashMap<>();
    static HashMap<String, Integer> b = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            a.clear();
            b.clear();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                a.put(st.nextToken(), 1);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                b.put(st.nextToken(), 1);
            }

            int cnt = 0;
            for(String str : a.keySet()) {
                if (b.containsKey(str))
                    cnt++;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ").append(cnt);
            System.out.println(sb);
        }
    }
}
