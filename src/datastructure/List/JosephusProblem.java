package algorithm.samsung.STL.List;

import java.util.Arrays;

public class JosephusProblem {
    public static void main(String[] args) {
        int[] player = new int[7];
        int n = 7, k = 3, moveCount, target = 0;
        int i, j;

        for (i = 0; i < n; i++) {
            player[i] = i + 1;
        }

        for(j=0; j<n-1; j++) {
            moveCount = 0;
            while (moveCount < k) {
                target = (target + 1) % n;
                if (player[target] != -1) {
                    moveCount++;
                }
            }
            player[target] = -1;

            while (player[target] == -1) {
                target = (target + 1) % n;
            }
        }

        System.out.println(Arrays.toString(player));
    }
}
