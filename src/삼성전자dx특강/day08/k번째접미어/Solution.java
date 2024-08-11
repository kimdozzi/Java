package 삼성전자dx특강.day08.k번째접미어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            int k = Integer.parseInt(br.readLine());
            String text = br.readLine();
            Integer[] suffixArr = createSuffixArray(text);
            Arrays.sort(suffixArr, (i,j) -> text.substring(i).compareTo(text.substring(j)));

            String ans = text.substring(suffixArr[k-1]);
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ").append(ans);
            System.out.println(sb);
        }
    }
    static Integer[] createSuffixArray(String text) {
        Integer[] sfx = new Integer[text.length()];

        for(int i=0; i<text.length(); i++) {
            sfx[i] = i;
        }
        return sfx;
    }
}
