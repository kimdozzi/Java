package algorithm.baekjoon;

import java.util.Scanner;

public class BOJ_2011_암호코드 {

    static private long MOD = 1000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next().strip();

        if (s.charAt(0) == '0') {
            System.out.println(0);
            System.exit(0);
        }

        long[] dp = new long[s.length()+1];
        dp[0] = dp[1] = 1;

        for (int i=2; i <= s.length(); i++) {
            char ch = s.charAt(i-1); // 현재 체크하는 문자
            char prev = s.charAt(i-2); // prev
            if (ch == '0') {
                if (prev=='1' || prev=='2') dp[i] = dp[i-2] % MOD;
                else break;
            }
            else {
                if (prev=='0') dp[i] = dp[i-1] % MOD;
                else {
                    // 앞 문자와 연결할 수 있는지 체크
                    int temp = (prev-'0') * 10 + (ch-'0');
                    if (1 <= temp && temp <= 26) dp[i] = (dp[i-2] + dp[i-1]) % MOD;
                    else dp[i] = dp[i-1] % MOD;
                }
            }
        }
        System.out.println(dp[s.length()] % MOD);
    }
}
