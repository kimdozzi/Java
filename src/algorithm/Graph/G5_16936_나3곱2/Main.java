package algorithm.Graph.G5_16936_나3곱2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
	static List<Long> arr = new ArrayList<>();  // 수열을 담는 리스트
	static int N; // 수열의 크기
	static boolean complete = false; // 수열 완성 여부
	static boolean[] visited; // 방문 체크 배열
	static long[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		answer = new long[N];
		visited = new boolean[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr.add(Long.parseLong(st.nextToken()));
		}

		// 순서대로 정렬
		Collections.sort(arr);

		for (int i = 0; i < N; i++) {
			if (complete) {
				break;
			}
			solve(0, i);
		}

		StringJoiner sj = new StringJoiner(" ");

		for (int i = 0; i < N; i++) {
			sj.add("" + answer[i]);
		}
		System.out.println(sj);
	}

	static void solve(int count, int index) {
		if (answer[N - 1] > 0) {
			complete = true;
			return;
		}

		if (!visited[index]) {
			visited[index] = true;
			answer[count] = arr.get(index);
			if (arr.get(index) % 3 == 0) {
				// 나3 수행
				for (int i = index - 1; i >= 0; i--) {
					if (arr.get(index) / 3 == arr.get(i)) {
						solve(count + 1, i);
						break;
					}
				}
			}

			// 곱2 수행
			long temp = arr.get(index) * 2;
			for (int i = index + 1; i < N; i++) {
				if (arr.get(i) == temp) {
					solve(count + 1, i);
					break;
				}
			}
		}

		visited[index] = false;
	}
}