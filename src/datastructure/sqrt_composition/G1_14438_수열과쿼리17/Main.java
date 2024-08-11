package datastructure.sqrt_composition.G1_14438_수열과쿼리17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

/*
 * 제곱근 분할법 (square root decomposition)
 *
 * 크기가 N인 정수 배열 A가 있고, 여기서 다음과 같은 연산을 최대 M번 수행해야 하는 문제.
 *
 * 1. 구간 l,r (l<=r)이 주어졌을 때, min(A[l], A[l+1], .... , A[r])을 구해서 출력.
 * 2. i번째 수를 v로 바꾸기 (A[i] = v)
 *
 * */


// 제곱근 분할법
/*
 * 크기가 N인 배열을 크기가 square N인 그룹으로 나누고, 각 그룹의 최솟값을 별도로 저장.
 * 그룹의 크기가 square N이니, 그룹의 개수도 square N개. N이 제곱수가 아닌 경우에는 그룹의 크기로
 * floor(square N)을 사용한다.
 *
 * 그룹의 크기는 r, 그룹의 개수는 g, i번째 그룹에 들어있는 수의 최솟값은 D[i]로 표현.
 * */

// ex) N = 11 (1 <= N <= 20)
// 그룹의 크기 r = 3
// 그룹의 개수 g = 4

// 1. 그룹의 크기 r과 그룹의 개수 g를 구하고, 그룹에 들어있는 최솟값 D[i] 초기화.

// 2. i번째 수를 v로 바꾸는 연산.
// 이 경우, i번째 수가 포함된 그룹의 D만 변경하면 된다.
// 그룹에 포함된 수의 최솟값을 구하려면, 그 그룹에 포함된 모든 수를 조사하면 된다.
// 그룹에 포함된 수의 개수는 r = Math.floor(Math.sqrt(n))이니 시간 복잡도는 O(square N)

// 3. 최솟값 구하기
// 두 가지의 경우로 나눌 수 있다.
// 3-1. l과 r이 같은 그룹에 속한 경우
// 같은 그룹에 속한 경우 그룹의 최솟값을 저장한 배열 D를 이용할 수 없다.
// 그 이유는, 최솟값이 구간 [l,r]에 포함되지 않을 수도 있다.
// 따라서, l부터 r까지 순회해서 최솟값을 구해야 한다.
// O(square N)입니다.

// 3-2. l과 r이 같은 그룹에 속하지 않은 경우
// 1. l이 속한 그룹
// 2. r이 속한 그룹
// 3. 위의 두 그룹 사이에 있는 그룹
// 모두 O(square N)

public class Main {
    static int N, M;
    static int r, g; // r은 그룹의 크기, g는 그룹의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringJoiner sj = new StringJoiner("\n");

        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        r = (int) Math.floor(Math.sqrt(N));
        g = N / r;
        if (N % r != 0) {
            g++;
        }
        int[] d = new int[g];

        for (int i = 0; i < N; i++) {
            if (i % r == 0) {
                d[i/r] = arr[i];
            } else {
                if (d[i/r] > arr[i]) {
                    d[i/r] = arr[i];
                }
            }
        }

        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (command == 1) // 구간 최솟값 구하기
            {
                update(arr, d, r, x-1, y);
            }
            else {
                int res = query(arr, d, r, x - 1, y - 1);
                sj.add(String.valueOf(res));
            }
        }
        System.out.println(sj);
    }

    private static void update(int[] a, int[] d, int r, int index, int v) {
        a[index] = v;
        int group = index/r;
        int start = group * r;
        int end = start + r;
        if (end > a.length) end = a.length;
        d[group] = a[start];
        for (int i=start; i<end; i++) {
            if (d[group] > a[i])
                d[group] = a[i];
        }
    }

    private static int query(int[] a, int[] d, int r, int start, int end) {
        int ans = a[start]; // 최솟값을 구하기 위해 탐색을 시작할 처음 값

        if (start/r == end/r) { // 만약 같은 그룹에 속한다면
            for (int i=start; i<=end; i++) {
                if (ans > a[i])
                    ans = a[i];
            }
            return ans;
        }

        // 시작 l부터 l이 속한 그룹 끝까지 탐색.
        while (true) {
            if (ans > a[start]) {
                ans = a[start];
            }
            start += 1;

            if(start % r == 0) break;
        }

        // 끝 r부터 r이 속한 그룹의 시작까지 탐색.
        while (true) {
            if (ans > a[end]) {
                ans = a[end];
            }
            end -= 1;

            if (end % r == r-1) break;
        }

        int startGroup = start/r;
        int endGroup = end/r;
        for(int i=startGroup; i<=endGroup; i++) {
            if (ans > d[i])
                ans = d[i];
        }
        return ans;
    }
}
