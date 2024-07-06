package algorithm.dynamicProgramming.G4_25958_예쁜수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int m, k;
    static List<Integer> coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            if (isPretty(i)) {
                coins.add(i);
            }
        }
        long[] dp = new long[m + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = 1; j <= m; j++) {
                if (coin <= j) {
                    dp[j] += dp[j - coin] % k;
                }
            }
        }
        System.out.print(dp[m] % k);
    }

    private static boolean isPretty(int n) {
        int sum = 0, num = n;

        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return n % sum == 0;
    }
}
