package algorithm.programmers.Lv02_도넛과막대그래프;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = {};
        Map<Integer, int[]> nodeCnt = new HashMap<>();
        int[] ans = {0,0,0,0};

        Arrays.stream(edges).forEach(edge -> {
            int a = edge[0]; // 나가는 간선
            int b = edge[1]; // 들어오는 간선
            if (!nodeCnt.containsKey(a)) {
                nodeCnt.put(a, new int[]{0,0});
            }
            if (!nodeCnt.containsKey(b)) {
                nodeCnt.put(b, new int[]{0,0});
            }
            nodeCnt.get(a)[0]++;
            nodeCnt.get(b)[1]++;
        });

        int[] counts;
        for (int key : nodeCnt.keySet()) {
            counts = nodeCnt.get(key);

            // 나가는 노드 2개, 들어오는 노드 0개 (생성된 정점)
            if (counts[0] >= 2 && counts[1] == 0) {
                ans[0] = key;
                // 나가는 노드 0개, 들어오는 정점 1개 이상 (막대 그래프)
                // 보통 1개가 들어오지만 생성된 정점으로부터 들어올 수도 있으므로 1이상으로 설정
            } else if (counts[0] == 0 && counts[1] >= 1) {
                ans[2]++;
                // 나가는 노드 2개, 들어오는 정점 2개 이상 (8자 그래프)
                // 보통 2개가 들어오지만 생성된 정점으로부터 들어올 수도 있으므로 2이상으로 설정
            } else if (counts[0] == 2 && counts[1] >= 2) {
                ans[3]++;
            }
        }
        // 도넛 그래프 = 총 그래프(생성된 정점에서 나가는 간선의 총 개수) - 막대 그래프 - 8자 그래프
        ans[1] = nodeCnt.get(ans[0])[0] - ans[2] - ans[3];

        return ans;

    }
}