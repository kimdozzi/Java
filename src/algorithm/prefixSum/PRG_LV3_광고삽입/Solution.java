package algorithm.prefixSum.PRG_LV3_광고삽입;

class Solution {
	public String solution(String play_time, String adv_time, String[] logs) {
		long[] times = new long[360001];

		for (String log : logs) {
			String[] splitLog = log.split("-");
			long startTime = ToInt(splitLog[0]);
			long endTime = ToInt(splitLog[1]);

			times[(int)startTime]++;
			times[(int)endTime]--;
		}
		long playTime = ToInt(play_time);
		long advTime = ToInt(adv_time);

		for (long i = 1; i < times.length; i++) {
			times[(int)i] += times[(int)i - 1];
		}
		for (long i = 1; i < times.length; i++) {
			times[(int)i] += times[(int)i - 1];
		}

		long maxCnt = times[(int)advTime - 1];
		long anstime = 0;

		for (long i = 0; i < playTime - advTime + 1; i++) {
			long tmp = times[(int)advTime + (int)i] - times[(int)i];
			if (tmp > maxCnt) {
				maxCnt = tmp;
				anstime = i + 1;
			}
		}

		return String.format("%02d:%02d:%02d",
			anstime / 3600, (anstime / 60) % 60, anstime % 60);
	}

	public int ToInt(String time) {
		String[] t = time.split(":");
		return Integer.parseInt(t[2]) + Integer.parseInt(t[1]) * 60 + Integer.parseInt(t[0]) * 60 * 60;
	}
}
