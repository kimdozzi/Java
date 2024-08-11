package 삼성전자dx특강.day02.Directory;

import java.util.*;

public class UserSolution {
    private static final int NAME_MAXLEN = 6;
    private static final int PATH_MAXLEN = 1999;

    // 각 노드의 부모 노드, 각 노드의 자식 개수 + 1
    private int[] parent = new int[50010];
    private int[] cnt = new int[50010];

    // 각 노드의 자식 노드 번호
    private List<Map<String, Integer>> child = new ArrayList<>();

    // 각 노드의 이름
    private List<String> dirName = new ArrayList<>();

    // 사용할 수 있는 인덱스 저장
    private Queue<Integer> que = new LinkedList<>();

    // 경로 캐시
    private Map<String, Integer> pathCache = new HashMap<>();

    public static String convertCharArrayToString(char[] charArray) {
        if (charArray == null) {
            return null; // 입력이 null인 경우 null 반환
        }

        // '\0' 문자가 있는지 확인하고 이를 제외한 길이 계산
        int length = 0;
        while (length < charArray.length && charArray[length] != '\0') {
            length++;
        }

        // '\0' 문자가 없다면 전체 배열을 사용하여 문자열 생성
        if (length == charArray.length) {
            return new String(charArray);
        }

        // '\0' 문자가 있다면 해당 길이까지만 사용하여 문자열 생성
        return new String(charArray, 0, length);
    }

    // 사용할 수 있는 인덱스 하나씩 반환
    private int getIdx() {
        int t = que.poll();
        return t;
    }

    // idx 노드와 그 부모 노드들의 cnt를 +C만큼 업데이트
    private void updcnt(int idx, int C) {
        cnt[idx] += C;
        if (parent[idx] >= 0) {
            updcnt(parent[idx], C);
        }
    }

    // parent_node 아래에 child_node 삽입
    private void nodeInsert(int parentNode, int childNode) {
        child.get(parentNode).put(dirName.get(childNode), childNode);
        parent[childNode] = parentNode;
        updcnt(parentNode, cnt[childNode]);
    }

    // idx 노드와 그 부모 노드 사이에 연결을 끊음
    private void unlink(int idx) {
        if (parent[idx] >= 0) {
            updcnt(parent[idx], -cnt[idx]);
            child.get(parent[idx]).remove(dirName.get(idx));
        }
        parent[idx] = -1;
    }

    // dst 노드 아래에 src 노드와 같은 노드 복사
    private int copyNode(int src, int dst) {
        int newIdx = getIdx();
        cnt[newIdx] = cnt[src];
        dirName.set(newIdx, dirName.get(src));
        child.set(newIdx, new HashMap<>());
        for (int i : child.get(src).values()) {
            int tmp = copyNode(i, newIdx);
            child.get(newIdx).put(dirName.get(tmp), tmp);
        }
        parent[newIdx] = dst;
        return newIdx;
    }

    // idx 노드와 그 자식 노드들 모두 삭제
    private void delNode(int idx) {
        for (int i : child.get(idx).values()) {
            delNode(i);
        }
        child.get(idx).clear();
        que.add(idx);
        parent[idx] = -1;
        cnt[idx] = 0;
    }

    // path가 가리키는 노드 번호 반환
    private int getPathIdx(String path) {
        if (pathCache.containsKey(path)) {
            return pathCache.get(path);
        }

        int ret = 0;
        String[] parts = path.split("/");
        for (String part : parts) {
            if (part.isEmpty()) {
                continue;
            }
            if (!child.get(ret).containsKey(part)) {
                return -1; // 경로가 존재하지 않는 경우
            }
            ret = child.get(ret).get(part);
        }
        pathCache.put(path, ret);
        return ret;
    }

    // 초기화 메서드
    public void init(int n) {
        que.clear();
        pathCache.clear();
        Arrays.fill(cnt, 0);
        Arrays.fill(parent, -1);
        child.clear();
        dirName.clear();
        for (int i = 0; i <= n; i++) {
            child.add(new HashMap<>());
            dirName.add("");
        }
        for (int i = 1; i <= n; i++) {
            que.add(i);
        }
        cnt[0] = 1;
    }

    // 디렉토리 생성 명령
    public void cmd_mkdir(char[] path, char[] name) {
        int t = getPathIdx(convertCharArrayToString(path));
        if (t == -1) return; // 경로가 존재하지 않는 경우 무시
        int newIdx = getIdx();
        cnt[newIdx] = 1;
        dirName.set(newIdx, convertCharArrayToString(name));
        nodeInsert(t, newIdx);
        pathCache.clear(); // 캐시를 지워서 일관성 유지
    }

    // 디렉토리 삭제 명령
    public void cmd_rm(char[] path) {
        int tidx = getPathIdx(convertCharArrayToString(path));
        if (tidx == -1) return; // 경로가 존재하지 않는 경우 무시
        unlink(tidx);
        delNode(tidx);
        pathCache.clear(); // 캐시를 지워서 일관성 유지
    }

    // 디렉토리 복사 명령
    public void cmd_cp(char[] srcPath, char[] dstPath) {
        int srcIdx = getPathIdx(convertCharArrayToString(srcPath));
        int dstIdx = getPathIdx(convertCharArrayToString(dstPath));
        if (srcIdx == -1 || dstIdx == -1) return; // 경로가 존재하지 않는 경우 무시
        int getI = copyNode(srcIdx, dstIdx);
        updcnt(dstIdx, cnt[getI]);
        child.get(dstIdx).put(dirName.get(getI), getI);
        pathCache.clear(); // 캐시를 지워서 일관성 유지
    }

    // 디렉토리 이동 명령
    public void cmd_mv(char[] srcPath, char[] dstPath) {
        int srcIdx = getPathIdx(convertCharArrayToString(srcPath));
        int dstIdx = getPathIdx(convertCharArrayToString(dstPath));
        if (srcIdx == -1 || dstIdx == -1) return; // 경로가 존재하지 않는 경우 무시
        unlink(srcIdx);
        nodeInsert(dstIdx, srcIdx);
        pathCache.clear(); // 캐시를 지워서 일관성 유지
    }

    // 디렉토리 내 파일 개수 찾기 명령
    public int cmd_find(char[] path) {
        int idx = getPathIdx(convertCharArrayToString(path));
        if (idx == -1) return -1; // 경로가 존재하지 않는 경우
        return cnt[idx] - 1;
    }
}