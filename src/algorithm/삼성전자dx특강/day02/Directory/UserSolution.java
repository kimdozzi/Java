package algorithm.삼성전자dx특강.day02.Directory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class UserSolution {
    private final static int NAME_MAXLEN = 6;
    private final static int PATH_MAXLEN = 1999;
    private final static int NODE_MAXLEN = 50001;

    int[] parent = new int[NODE_MAXLEN];
    int[] cnt = new int[NODE_MAXLEN];

    ArrayList<Set<Integer>> child = new ArrayList<>();
    ArrayList<String> dirName = new ArrayList<>();
    Queue<Integer> queue = new PriorityQueue<>(); // 사용할 수 있는 인덱스 저장

    private static String getString(char[] path) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (true) {
            if (path[idx] == '\0') {
                break;
            }
            sb.append(path[idx++]);
        }
        return sb.toString();
    }

    private void nodeInsert(int parentNode, int childNode) {
        child.get(parentNode).add(childNode);
        parent[childNode] = parentNode;
        updcnt(parentNode, cnt[childNode]);
    }

    private void updcnt(int parentNodeIdx, int childNodeDirectoryCount) {
        // 상위 디렉토리에 하위 디렉토리의 총 개수를 저장.
        cnt[parentNodeIdx] += childNodeDirectoryCount;
        // 상위 디렉토리의 부모 디렉토리가 존재하면, 부모 디렉토리에게 개수 정보 전파
        if (parent[parentNodeIdx] >= 0) {
            updcnt(parent[parentNodeIdx], childNodeDirectoryCount);
        }
    }

    private int getNewIdx() {
        // 사용할 수 있는 인덱스 하나씩 반환.
        return queue.poll();
    }

    // 파일의 경로가 주어졌을 때, 해당 노드를 찾아가는 함수
    private int getPathIdx(String path) {
        int ret = 0;
        String[] parts = path.split("/");

        for (String part : parts) {
            if (part.isEmpty()) {
                continue;
            }

            for (int i : child.get(ret)) {
                if (dirName.get(i).equals(part)) {
                    ret = i;
                    break;
                }
            }
        }
        return ret;
    }

    private int copyNode(int srcIdx, int dstIdx) {
        int newIdx = getNewIdx(); // 새로운 디렉토리 인덱스 번호를 가져옴.
        cnt[newIdx] = cnt[srcIdx]; // 옮길 디렉토리의 총 개수를 새로운 디렉토리의 cnt에 추가.
        dirName.set(newIdx, dirName.get(srcIdx)); // 디렉토리 이름 수정.

        // 옮길 디렉토리의 자식 디렉토리 탐색.
        for (int i : child.get(srcIdx)) {
            // 자식 디렉토리들을 하나씩 복사.
            int tmp = copyNode(i, newIdx);
            child.get(newIdx).add(tmp);
        }
        parent[newIdx] = dstIdx;
        return newIdx;
    }

    private void unlink(int idx) {
        if (parent[idx] >= 0) {
            updcnt(parent[idx], -cnt[idx]);
            child.get(parent[idx]).remove(idx);
        }
        parent[idx] = -1;
    }

    void init(int n) {
        queue.clear();
        Arrays.fill(cnt, 0);
        Arrays.fill(parent, -1);
        child.clear();
        dirName.clear();

        for (int i = 0; i <= n; i++) {
            child.add(new HashSet<>());
            dirName.add("");
        }
        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }
        cnt[0] = 1;
    }

    void cmd_mkdir(char[] path, char[] name) {
        String strPath = getString(path);
        int dstNodeIdx = getPathIdx(strPath); // path의 인덱스 번호
        int newNodeIdx = getNewIdx(); // 사용할 새로운 노드의 인덱스 번호
        cnt[newNodeIdx] = 1; // 해당 디렉토리는 1개의 디렉토리를 가지고 있다. (=자기 자신)

        String strName = getString(name);
        dirName.set(newNodeIdx, strName); // 디렉토리 삽입 (새로운 인덱스(newNodeIdx) 위치에 name 이름을 가진 새로운 디렉토리를 추가한다.)
        nodeInsert(dstNodeIdx, newNodeIdx);
    }

    void cmd_rm(char[] path) {
        String strPath = getString(path);
        int rmIdx = getPathIdx(strPath);
        unlink(rmIdx);
        delNode(rmIdx);
    }

    private void delNode(int rmIdx) {
        for (int i : child.get(rmIdx)) {
            delNode(i);
        }
        child.get(rmIdx).clear();
        queue.add(rmIdx);
        parent[rmIdx] = -1;
        cnt[rmIdx] = 0;
    }

    void cmd_cp(char[] srcPath, char[] dstPath) {
        String strSrcPath = getString(srcPath);
        String strDstPath = getString(dstPath);
        int srcIdx = getPathIdx(strSrcPath);
        int dstIdx = getPathIdx(strDstPath);
        int getIdx = copyNode(srcIdx, dstIdx);
        updcnt(dstIdx, cnt[getIdx]);
        child.get(dstIdx).add(getIdx);
    }


    void cmd_mv(char[] srcPath, char[] dstPath) {
        String strSrcPath = getString(srcPath);
        int srcIdx = getPathIdx(strSrcPath);
        unlink(srcIdx);
        String strDstPath = getString(dstPath);
        nodeInsert(getPathIdx(strDstPath), srcIdx);
    }


    int cmd_find(char[] path) {
        return cnt[getPathIdx(getString(path))] - 1;
    }
}

//	 The below commented methods are for your reference. If you want
//	 to use it, uncomment these methods.
//
//	int mstrcmp(char[] a, char[] b) {
//		int i;
//		for (i = 0; a[i] != '\0'; i++) {
//			if (a[i] != b[i])
//				return a[i] - b[i];
//		}
//		return a[i] - b[i];
//	}
//
//	int mstrncmp(char[] a, char[] b, int len) {
//		for (int i = 0; i < len; i++) {
//			if (a[i] != b[i])
//				return a[i] - b[i];
//		}
//		return 0;
//	}
//
//	int mstrlen(char[] a) {
//		int len = 0;
//
//		while (a[len] != '\0')
//			len++;
//
//		return len;
//	}
//
//	void mstrcpy(char[] dest, char[] src) {
//		int i = 0;
//		while (src[i] != '\0') {
//			dest[i] = src[i];
//			i++;
//		}
//		dest[i] = src[i];
//	}
//
//	void mstrncpy(char[] dest, char[] src, int len) {
//		for (int i = 0; i < len; i++) {
//			dest[i] = src[i];
//		}
//		dest[len] = '\0';
//	}