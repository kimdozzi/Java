package algorithm.Binary_Search.G4_1253_좋다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		for (int i = 0; i < arr.length; i++) {
			int target = arr[i]; // 두 수의 합으로 만들 수 (기준)

			boolean isExist = false; // 기준이 되는 수를 만들 수 있는지 여부
			for (int j = 0; j < arr.length; j++) {
				if (i == j) // 기준이 되는 수의 인덱스의 경우 넘어감.
					continue;

				if (isExist) // 이미 기준이 되는 수를 만들었다면 for문 종료.
					break;

				int lo = -1, hi = arr.length;
				while (lo + 1 < hi) {
					int mid = lo + (hi - lo) / 2;

					if (mid == i) {
						hi = mid;
						continue;
					}
					if (mid == j) {
						lo = mid;
						continue;
					}

					if (arr[mid] + arr[j] == target) {
						cnt++;
						isExist = true;
						break;
					}

					if (arr[mid] + arr[j] > target) {
						hi = mid;
					} else {
						lo = mid;
					}
				}
			}
		}
		System.out.println(cnt);
	}
}
