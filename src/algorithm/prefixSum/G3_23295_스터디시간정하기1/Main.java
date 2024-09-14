package algorithm.prefixSum.G3_23295_스터디시간정하기1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int len = 100000;
	static int n, t;
	static int[] time = new int[len + 2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			int k = Integer.parseInt(br.readLine());
			for (int j = 0; j < k; j++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				time[s]++;
				time[e]--;
			}
		}
		for (int i = 1; i < len + 2; i++)
			time[i] += time[i - 1];
		for (int i = 1; i < len + 2; i++)
			time[i] += time[i - 1];

		int max = time[t - 1];
		int anstime = -1;
		for (int i = 0; i < len + 1 - t; i++) {
			int tmp = time[t + i] - time[i];
			if (max < tmp) {
				max = tmp;
				anstime = i;
			}
		}
		System.out.println(anstime + 1 + " " + (anstime + t + 1));
	}
}
