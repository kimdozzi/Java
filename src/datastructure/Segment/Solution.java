package datastructure.Segment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 수의 개수
        int m = Integer.parseInt(st.nextToken()); // 데이터 변경 개수

        // 수 저장 배열
        long[] arr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        SegmentTree stree = new SegmentTree(n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb
                    .append(stree.findMinimum(1, n, 1, a, b))
                    .append(" ")
                    .append(stree.findMaximum(1, n, 1, a, b))
                    .append("\n");
        }
        System.out.println(sb);
    }

    static class SegmentTree {
        long[] sumTree;
        long[] minTree;
        long[] maxTree;
        int treeSize;

        public SegmentTree(int arrSize) {
            treeSize = arrSize;
            sumTree = new long[arrSize * 4];
            minTree = new long[arrSize * 4];
            maxTree = new long[arrSize * 4];
        }

        // 합 트리 초기화
        public long initSum(long[] arr, int node, int start, int end) {
            if (start == end) {
                return sumTree[node] = arr[start];
            }
            return sumTree[node] =
                    initSum(arr, node * 2, start, (start + end) / 2)
                            + initSum(arr, node * 2 + 1, (start + end) / 2 + 1, end);
        }

        // 최소값, 최대값 트리 초기화
        public void initMinMax(long[] arr, int node, int start, int end) {
            if (start == end) {
                minTree[node] = arr[start];
                maxTree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                initMinMax(arr, node * 2, start, mid);
                initMinMax(arr, node * 2 + 1, mid + 1, end);
                minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
                maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
            }
        }

        // 기본값 업데이트
        public void update(int node, int start, int end, int idx, long diff) {
            if (idx < start || end < idx) {
                return;
            }

            sumTree[node] += diff;

            if (start == end) {
                minTree[node] = sumTree[node];
                maxTree[node] = sumTree[node];
            } else {
                update(node * 2, start, (start + end) / 2, idx, diff);
                update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);

                minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
                maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
            }
        }

        // 구간 합 계산
        public long sum(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }

            if (left <= start && end <= right) {
                return sumTree[node];
            }

            return sum(node * 2, start, (start + end) / 2, left, right) +
                    sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        }

        // 구간 최소값 계산
        public long findMinimum(int start, int end, int node, int left, int right) {
            if (left > end || right < start) {
                return Long.MAX_VALUE;  // 큰 값으로 초기화
            }
            if (left <= start && end <= right) {
                return minTree[node];
            }

            int mid = (start + end) / 2;
            return Math.min(
                    findMinimum(start, mid, node * 2, left, right),
                    findMinimum(mid + 1, end, node * 2 + 1, left, right)
            );
        }

        // 구간 최대값 계산
        public long findMaximum(int start, int end, int node, int left, int right) {
            if (left > end || right < start) {
                return Long.MIN_VALUE;  // 작은 값으로 초기화
            }
            if (left <= start && end <= right) {
                return maxTree[node];
            }

            int mid = (start + end) / 2;
            return Math.max(
                    findMaximum(start, mid, node * 2, left, right),
                    findMaximum(mid + 1, end, node * 2 + 1, left, right)
            );
        }
    }
}