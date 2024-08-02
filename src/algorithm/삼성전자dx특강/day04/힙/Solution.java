package algorithm.삼성전자dx특강.day04.힙;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringJoiner;

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            sc.nextLine();
            PriorityQueue<Node> pq = new PriorityQueue<>();

            StringJoiner sj = new StringJoiner(" ");

            for (int i = 0; i < N; i++) {
                String[] input = sc.nextLine().split(" ");
                int command = Integer.parseInt(input[0]);
                switch (command) {
                    case 1:
                        // 최대 힙 데이터 추가
                        pq.add(new Node(Integer.parseInt(input[1])));
                        break;
                    case 2:
                        // 최대값 출력
                        if (!pq.isEmpty()) {
                            sj.add(String.valueOf(pq.poll().val));
                        } else {
                            sj.add(String.valueOf(-1));
                        }
                        break;
                }
            }
            System.out.printf("#%d ", test_case);
            System.out.println(sj);
        }
    }

    static class Node implements Comparable<Node> {
        int val;

        Node(int val) {
            this.val = val;
        }

        @Override
        public int compareTo(Node o1) {
            return o1.val - this.val;
        }
    }
}