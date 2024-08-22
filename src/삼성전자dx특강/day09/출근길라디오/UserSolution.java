package 삼성전자dx특강.day09.출근길라디오;
import java.util.*;

public class UserSolution {
    // 세그먼트 트리 배열
    // 1-based 인덱싱
    private int[] seg = new int[401000];

    private int n;
    private int[] a = new int[100010];

    // 각 타입별로 인덱스 위치 저장
    private List<List<Integer>> idx = new ArrayList<>();

    // 세그먼트 트리를 구축하는 함수
    // O(N)
    private void build(int now, int s, int e) {
        if (s == e) {
            // 리프 노드일 경우 a[s]로 초기화
            seg[now] = a[s];
            return;
        }
        int nl = 2 * now, nr = 2 * now + 1, m = (s + e) / 2;
        // 왼쪽 서브트리 구축
        build(nl, s, m);
        // 오른쪽 서브트리 구축
        build(nr, m + 1, e);
        // 현재 노드를 자식 노드들의 합으로 업데이트
        seg[now] = seg[nl] + seg[nr];
    }

    // [qs, qe] 범위의 합을 구하는 함수
    // O(logN)
    private int query(int now, int s, int e, int qs, int qe) {
        if (qs <= s && e <= qe) {
            // 현재 노드가 범위 내에 완전히 포함될 경우
            return seg[now];
        }
        if (e < qs || qe < s) {
            // 현재 노드가 범위 내에 포함되지 않는 경우
            return 0;
        }
        int nl = 2 * now, nr = 2 * now + 1, m = (s + e) / 2;
        // 왼쪽 및 오른쪽 서브트리에서 결과를 합산
        return query(nl, s, m, qs, qe) + query(nr, m + 1, e, qs, qe);
    }

    // 세그먼트 트리에서 idx 위치를 val로 업데이트
    // O(logN)
    private void updateSegment(int now, int s, int e, int idx, int val) {
        if (s == e) {
            // 리프 노드일 경우 val로 초기화
            seg[now] = val;
            return;
        }

        int nl = 2 * now, nr = 2 * now + 1, m = (s + e) / 2;

        // idx의 위치에 따라 왼쪽 또는 오른쪽 서브트리 업데이트
        if (idx <= m) {
            updateSegment(nl, s, m, idx, val);
        } else {
            updateSegment(nr, m + 1, e, idx, val);
        }

        // 현재 노드를 자식 노드들의 합으로 업데이트
        seg[now] = seg[nl] + seg[nr];
    }

    // 초기화 함수
    public void init(int N, int M, int[] mType, int[] mTime) {
        n = N - 1;
        Arrays.fill(seg, 0);
        Arrays.fill(a, 0);
        idx.clear();
        for (int i = 0; i < 1010; i++) {
            idx.add(new ArrayList<>());
        }
        // 타입별로 인덱스 저장
        for (int i = 0; i < N - 1; i++) {
            idx.get(mType[i]).add(i + 1);
        }
        // 인덱스는 1-based
        for (int i = 0; i < N - 1; i++) {
            a[i + 1] = mTime[i];
        }
        // 트리 구축
        build(1, 1, n);
    }

    public void destroy() {
        // Java에서는 명시적인 메모리 해제가 필요없음
    }

    // 업데이트 함수
    public void update(int mID, int mNewTime) {
        a[mID + 1] = mNewTime;
        updateSegment(1, 1, n, mID + 1, mNewTime);
    }

    // 특정 타입에 대한 모든 인덱스를 업데이트
    public int updateByType(int mTypeID, int mRatio256) {
        int ret = 0;
        for (int i : idx.get(mTypeID)) {
            a[i] = a[i] * mRatio256 / 256;
            ret += a[i];
            updateSegment(1, 1, n, i, a[i]);
        }
        return ret;
    }

    // 범위 내 합을 계산하는 함수
    public int calculate(int mA, int mB) {
        return query(1, 1, n, Math.min(mA + 1, mB + 1), Math.max(mA + 1, mB + 1) - 1);
    }
}
