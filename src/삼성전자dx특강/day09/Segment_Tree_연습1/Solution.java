package 삼성전자dx특강.day09.Segment_Tree_연습1;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static class SegmentTree {
        long[] minTree;
        long[] maxTree;
        int size;

        public SegmentTree(int n) {
            size = n;
            minTree = new long[n * 4];
            maxTree = new long[n * 4];
        }

        public void build(long[] arr, int node, int start, int end) {
            if (start == end) {
                minTree[node] = arr[start];
                maxTree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, node * 2, start, mid);
                build(arr, node * 2 + 1, mid + 1, end);
                minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
                maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
            }
        }

        public void update(int node, int start, int end, int idx, long value) {
            if (start == end) {
                minTree[node] = value;
                maxTree[node] = value;
            } else {
                int mid = (start + end) / 2;
                if (idx <= mid) {
                    update(node * 2, start, mid, idx, value);
                } else {
                    update(node * 2 + 1, mid + 1, end, idx, value);
                }
                minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
                maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
            }
        }

        public long queryMin(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return Long.MAX_VALUE;
            }
            if (left <= start && end <= right) {
                return minTree[node];
            }
            int mid = (start + end) / 2;
            long minLeft = queryMin(node * 2, start, mid, left, right);
            long minRight = queryMin(node * 2 + 1, mid + 1, end, left, right);
            return Math.min(minLeft, minRight);
        }

        public long queryMax(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return Long.MIN_VALUE;
            }
            if (left <= start && end <= right) {
                return maxTree[node];
            }
            int mid = (start + end) / 2;
            long maxLeft = queryMax(node * 2, start, mid, left, right);
            long maxRight = queryMax(node * 2 + 1, mid + 1, end, left, right);
            return Math.max(maxLeft, maxRight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            StringBuilder sb = new StringBuilder();
            long[] arr = new long[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }

            SegmentTree stree = new SegmentTree(n);
            stree.build(arr, 1, 0, n - 1);

            sb.append("#").append(tc);
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (cmd == 0) {
                    stree.update(1, 0, n - 1, a, b);
                } else if (cmd == 1) {
                    long maxVal = stree.queryMax(1, 0, n - 1, a, b - 1);
                    long minVal = stree.queryMin(1, 0, n - 1, a, b - 1);
                    sb.append(" ").append(maxVal - minVal);
                }
            }
            System.out.println(sb);
        }

    }
}
