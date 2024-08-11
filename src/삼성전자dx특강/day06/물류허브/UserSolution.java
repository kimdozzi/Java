package algorithm.삼성전자dx특강.day06.물류허브;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class UserSolution {
    static final int MAX_CITY = 610;
    static final int MAX_N = 1410;
    static HashMap<Integer, Integer> d = new HashMap<>(); // key : 정점, value : 최소 비용
    static HashMap<Integer, Integer> newd = new HashMap<>();
    static HashMap<Integer, List<Edge>> graph = new HashMap<>(); // key : 출발 정점, Edge (to, weight) : (도착 정점, 운송 비용)
    static HashMap<Integer, List<Edge>> reverseGraph = new HashMap<>();

    public int init(int N, int sCity[], int eCity[], int mCost[]) {
        d.clear();
        newd.clear();
        graph.clear();
        reverseGraph.clear();

        for (int i = 0; i < N; i++) {
            int start = sCity[i];
            int end = eCity[i];
            int weight = mCost[i];

            // 최소 비용 초기화
            d.putIfAbsent(start, Integer.MAX_VALUE);
            d.putIfAbsent(end, Integer.MAX_VALUE);

            graph.putIfAbsent(start, new ArrayList<>());
            graph.get(start).add(new Edge(end, weight));

            reverseGraph.putIfAbsent(end, new ArrayList<>());
            reverseGraph.get(end).add(new Edge(start, weight));
        }

//        for (int key : d.keySet()) {
//            System.out.println("정점 " + key + "의 비용 : " + d.get(key));
//        }

        // 데이터 출력 (디버깅용)
//        for (int key : graph.keySet()) {
//            System.out.print("From " + key + ": ");
//            for (Edge edge : graph.get(key)) {
//                System.out.print("(To: " + edge.to + ", Weight: " + edge.weight + ") ");
//            }
//            System.out.println();
//        }
        return graph.keySet().size();
    }

    public void add(int sCity, int eCity, int mCost) {
        graph.get(sCity).add(new Edge(eCity, mCost));
        reverseGraph.get(eCity).add(new Edge(sCity, mCost));
    }

    static int dijkstra(HashMap<Integer,List<Edge>> graphs, int start) {
        Queue<Edge> pq = new PriorityQueue<>();
        HashSet<Integer> set = new HashSet<>();
        pq.add(new Edge(start, 0));
        // 원본 d는 놔두고, newd로 매 번 다익스트라 수행
        for(int key : d.keySet()) {
            newd.put(key, d.get(key));
        }
        // 시작 정점 0으로 초기화.
        newd.replace(start, 0);

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (set.contains(edge.to) || newd.get(edge.to) < edge.weight) continue;
            set.add(edge.to);

            for(Edge next : graphs.get(edge.to)) {
                if (newd.get(next.to) > newd.get(edge.to) + next.weight) {
                    newd.put(next.to, newd.get(edge.to) + next.weight);
                    pq.add(new Edge(next.to, newd.get(next.to)));
                }
            }
        }

        int ret = 0;
        for(int i : newd.keySet()) {
            ret += newd.get(i);
        }
        return ret;
    }

    public int cost(int mHub) {
        int x = dijkstra(graph, mHub);
        int y = dijkstra(reverseGraph, mHub);

//        System.out.println(x+y);
        return x+y;
    }

    static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o1) {
            return this.weight - o1.weight;
        }
    }
}