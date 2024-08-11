package algorithm.삼성전자dx특강.day04.수만들기;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Solution {
    static int N, K;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            // init
            N = sc.nextInt();
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }
            K = sc.nextInt();

            // solve
            int res = solve(arr);
            System.out.println(res);
        }
    }

    static int solve(int[] a) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(K, 0));

        int cnt = K;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.leftNumber == 0) {
                cnt = node.cnt;
                break;
            }

            // x와 k의 차이를 3의 배수로 만든 후 d에 3을 곱한다.
            // x에 1을 두번 더하고, d에 3을 곱한다.

            // ex)
            // k = 11
            // x = 0, d = 1
            // arr = [2,3]
            // d(=1)에 2,3을 곱하던
            // d(=2)에 2,3을 곱하던
            // d(1*2) 에 2,3을 곱하던
            // d(1*3) 에 2,3을 곱하던 모두 arr 배열의 원소 arr[i] 값들의 배수 -> d는 필요없음

            pq.add(new Node(0, node.cnt + node.leftNumber));
            for (int i = 0; i < N; i++) {
                if (node.leftNumber >= a[i]) {
                    pq.add(new Node(node.leftNumber / a[i], node.cnt + node.leftNumber % a[i]));
                }
            }
        }
        return cnt;
    }

    static class Node implements Comparable<Node> {
        int leftNumber = 0;
        int cnt = 0;

        public Node(int leftNumber, int cnt) {
            this.leftNumber = leftNumber;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o1) {
            if (this.cnt > o1.cnt) {
                return 1;
            } else if (this.cnt == o1.cnt) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
