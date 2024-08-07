package algorithm.graph.G5_16936_나3곱2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[] visited;    //방문 확인 배열
    static boolean complete = false;    //완성 확인 변수
    static ArrayList<Long> num = new ArrayList<>();    //수열 정보 저장 리스트
    static long[] answer;    //결과가 되는 순서 저장 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        answer = new long[N];
        visited = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        //입력받은 수열값 저장
        for (int i = 0; i < N; i++) {
            num.add(Long.parseLong(st.nextToken()));
        }
        Collections.sort(num);    //오름차순 정렬

        //입력 받은 수열로 모든 경우의 나3곱2 게임 진행
        for (int i = 0; i < N; i++) {
            if (complete)    //나3곱2로 순서 완성시
            {
                break;
            }
            cal(0, i);
        }

        //결과로 얻은 순서 BufferedWriter 저장
        for (int i = 0; i < N; i++) {
            bw.write(answer[i] + " ");
        }
        bw.flush();        //결과 출력
        bw.close();
        br.close();
    }

    //모든 경우 나3곱2 진행하여 올바른 순서 찾는 함수
    static void cal(int count, int index) {
        if (answer[N - 1] != 0) {        //올바른 순서 찾았을 때
            complete = true;        //완성!
            return;
        }
        //탐색 진행
        if (!visited[index]) {
            visited[index] = true;
            answer[count] = num.get(index);
            if (num.get(index) % 3 == 0) {    //나3 진행
                //오름차순 정렬로 나누기를 진행해서 현재 값보다 작은 수만 탐색
                for (int i = index - 1; i >= 0; i--) {
                    if (num.get(index) / 3 == num.get(i)) {
                        cal(count + 1, i);
                        break;
                    }
                }
            }
            long temp = num.get(index) * 2;    //곱2 진행
            //오름차순 정렬로 곱하기를 진행해서 현재 값보다 큰 수만 탐색
            for (int i = index + 1; i < N; i++) {
                if (temp == num.get(i)) {
                    cal(count + 1, i);
                    break;
                }
            }
        }
        visited[index] = false;
    }
}