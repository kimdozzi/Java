package algorithm.baekjoon.S2_27525_1차원2048과쿼리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//        int Q = Integer.valueOf(br.readLine());
//
//        long sum = 0;
//
//        while (Q-- > 0) {
//            sum += Long.valueOf(br.readLine());
//
//            bw.write(Long.toString(Long.highestOneBit(sum)));
//            bw.newLine();
//        }

        int Q = Integer.valueOf(br.readLine());

        long sum = 0;

        while (Q-- > 0) {
            sum += Long.valueOf(br.readLine());
            bw.write(Long.toString(highestOneBit(sum)));
            bw.newLine();
        }

        bw.close();
    }

    private static long highestOneBit(long i) {
        return i & (0x8000000000000000L >>> numberOfLeadingZeros(i));
    }

    private static int numberOfLeadingZeros(long i) {
        int x = (int) (i >>> 32);
        return x == 0 ? 32 + numberOfLeadingZero((int) i) : numberOfLeadingZero(x);
    }

    private static int numberOfLeadingZero(int i) {
            if (i <= 0)
                return i == 0 ? 32 : 0;
            int n = 31;
            if (i >= 1 << 16) { n -= 16; i >>>= 16; }
            if (i >= 1 <<  8) { n -=  8; i >>>=  8; }
            if (i >= 1 <<  4) { n -=  4; i >>>=  4; }
            if (i >= 1 <<  2) { n -=  2; i >>>=  2; }
            return n - (i >>> 1);
    }
}