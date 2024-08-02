package datastructure.unionFind;

public class Main {
    static int[] parent;
    static int[] rank;

    // union 연산
    static boolean union(int x, int y) {
        System.out.println("== union == : " + x + " " + y);
        x = find(x);
        y = find(y);

        if (x == y) {
            return false;
        }
        if (rank[x] > rank[y]) {
            parent[y] = x;
        } else if (rank[x] < rank[y]) {
            parent[x] = y;
        } else {
            parent[y] = x;
            rank[x]++;
        }
        return true;
    }

    // find 연산
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // parent 출력
    public static void parentPrint() {
        System.out.println("Parent");
        System.out.print("[ ");
        for (int i = 1; i < parent.length; i++) {
            System.out.print(parent[i] + " ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int n = 5;
        parent = new int[n + 1];
        rank = new int[n + 1];
        // 부모 노드 초기화
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        //위의 예제 실행
        union(1, 2);
        parentPrint();
        rankPrint();
        union(3, 4);
        parentPrint();
        rankPrint();
        union(3, 5);
        parentPrint();
        rankPrint();
        System.out.println("find(2): " + find(2));
        System.out.println("find(4): " + find(4));

        union(2, 4);
        parentPrint();
        rankPrint();
        System.out.println("find(4): " + find(4));
        union(1, 5);
        parentPrint();
        rankPrint();
    }

    private static void rankPrint() {
        System.out.println("RANK");
        for (int i = 1; i < rank.length; i++) {
            System.out.print(rank[i] + " ");
        }
        System.out.println();
        System.out.println();
    }
}