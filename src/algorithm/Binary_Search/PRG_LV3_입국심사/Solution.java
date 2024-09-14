package algorithm.Binary_Search.PRG_LV3_입국심사;

class Solution {
	// https://20240228.tistory.com/96
	public long solution(int n, int[] times) {
		long lo = 0;
		long hi = (long)Math.pow(10, 15) + 1;
		while (lo + 1 < hi) {
			long mid = lo + ((hi - lo) / 2);

			long sum = 0;
			for (int i = 0; i < times.length; i++) {
				sum += mid / times[i];
			}

			if (sum < n) {
				lo = mid;
			} else {
				hi = mid;
			}
		}
		return hi;
	}
}
