package algorithm.삼성전자dx특강.day05.촛불이벤트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
         /*
         * target = 12 = k * k+1
            1 2 3 4 5 6 7 8 9 10 11 12
            0 1 2 3 4 5 6 7 8  9  10 11
                           hi
            lo = -1
            hi = 12
            11/2 = 5
            mid[5] = 6
            if (3*3+1 == target) return mid[2]
            if (6*6+1 != target)
                hi = mid
            else lo = mid

            mid[2] = 3
         * */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(br.readLine());

            long answer = -1;
            long lo = 0;
            long hi = (long) Math.sqrt(N * 2)+1;

            while (lo + 1 < hi) {
                long mid = lo + ((hi - lo) / 2);
                long target = ((mid + 1) * mid) / 2;

                if (target == N) {
                    answer = mid;
                    break;
                } else if (target > N) {
                    hi = mid;
                } else {
                    lo = mid;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ").append(answer);
            System.out.println(sb);
        }
    }
}
