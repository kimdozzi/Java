package 삼성전자dx특강.day02.중위순회;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
    private static List<Node> tree;
    private static StringBuilder sb;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            sc.nextLine();

            // init
            tree = new ArrayList<>();
            sb = new StringBuilder();
            tree.add(new Node("", null, null));

            // 정점의 수 N개 주어짐.
            for (int i = 0; i < N; i++) {
                String[] input = sc.nextLine().split(" ");

                // 1번(루트)인 경우
                if (i == 0) {
                    Node node = new Node(input[1], null, null);
                    tree.add(node);

                // 루트를 제외한 노드인 경우
                } else {
                    Node node = new Node(input[1], null, null);
                    Node parentNode = tree.get(Integer.parseInt(input[0]) / 2);
                    if (Integer.parseInt(input[0]) % 2 == 0) {
                        parentNode.left = node;
                    } else {
                        parentNode.right = node;
                    }
                    tree.add(node);
                }
            }
            inOrder(tree.get(1));
            sb.append("\n");
            System.out.printf("#%d %s", test_case, sb);
        }
    }

    static void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        sb.append(node.value);
        inOrder(node.right);
    }

    static class Node {
        String value;
        Node left;
        Node right;

        public Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}