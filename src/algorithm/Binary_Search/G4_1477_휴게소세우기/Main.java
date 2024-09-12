package algorithm.Binary_Search.G4_1477_휴게소세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, L;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		arr = new int[N + 2];
		arr[0] = 0;
		arr[N + 1] = L;

		if (N > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}

		Arrays.sort(arr);

		int lo = 0;
		int hi = L + 1;
		while (lo + 1 < hi) {
			int mid = lo + ((hi - lo) / 2);
			int sum = 0;
			for (int i = 1; i < arr.length; i++) {
				sum += (arr[i] - arr[i - 1] - 1) / mid;
			}

			if (sum > M) {
				lo = mid;
			} else {
				hi = mid;
			}
		}
		System.out.println(hi);
	}
}