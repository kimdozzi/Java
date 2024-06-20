package algorithm.baekjoon.S1_16198_에너지모으기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N; // 구슬의 개수
    static int answer; // 에너지 양의 최대값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // input
        N = Integer.parseInt(st.nextToken());
        List<Integer> energyList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            energyList.add(Integer.parseInt(st.nextToken()));
        }

        // Solve
        findMaxEnergy(energyList,0);

        // output
        System.out.println(answer);
    }

    private static void findMaxEnergy(List<Integer> energyList, int sum) {
        if (energyList.size() == 2) {
            answer = Math.max(answer, sum);
        }
        for(int i=1; i<energyList.size()-1; i++) {
            int temp = energyList.get(i);
            int value = energyList.get(i - 1) * energyList.get(i + 1);
            energyList.remove(i);
            findMaxEnergy(energyList, sum + value);
            energyList.add(i, temp);
        }
    }
}
