package algorithm.dynamicProgramming.S1_2705_팰린드롬파티션;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] dp = new int[1001];
        for(int i=1; i<=1000; i++) dp[i] = 1;

        for(int i=1; i<=1000; i++)
            for(int j=0; j<=i-1; j++) { // 자기자신 1 추가했으므로 i-1까지
                if((i-j) % 2 != 0) continue;
                dp[i] += dp[i - j >> 1];
            }

        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            System.out.println(dp[N]);
        }

        System.out.println(0>>1);
    }
}
