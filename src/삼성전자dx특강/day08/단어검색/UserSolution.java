package 삼성전자dx특강.day08.단어검색;

class UserSolution {
    static final int MAXSIZE = 26; // a-z는 26개
    static final char WILD = '?';
    static final char EMPTY = '\0';

    TrieNode root;
    boolean isRemov; // flag
    int wordCount; // 답안제출용

    class TrieNode {
        TrieNode[] nodes = new TrieNode[MAXSIZE];
        int cnt; // 노드에 연결된 문자열 갯수
    }

    void init() {
        root = new TrieNode(); // 루트는 글자를 안 가짐.
    }

    int add(char[] str) {
        TrieNode curNode = root;  // 루트 부터 시작해서 내려감
        for (char c : str) {
            if (c == EMPTY) {
                break;
            }
            int idx = c - 'a';// 한글자씩 추출하여 숫자인덱스로 변환
            if (curNode.nodes[idx] == null) {// null이란 소리는: 연결 문자열로 처음 추가된다는 의미
                curNode.nodes[idx] = new TrieNode();
            }
            curNode = curNode.nodes[idx]; // 자식 노드로 넘어감
        }
        return ++curNode.cnt;
    }

    int remove(char[] str) {
        isRemov = true; // 초기화
        updateCount(str);
        return wordCount;
    }

    int search(char[] str) {
        isRemov = false; // 초기화
        updateCount(str);
        return wordCount;
    }

    void updateCount(char[] str) {
        wordCount = 0; // 초기화
        char firstChar = str[0]; // 이 단어로 시작할 수 있는 경우 다 검색.
        if (firstChar == WILD) {
            //와일드라서 26가지로 시작할 수 있는 경우 모두 조사
            for (int i = 0; i < MAXSIZE; i++) {
                TrieNode startNode = root.nodes[i];
                updateCount(startNode, str, 0);
            }
        } else {
            TrieNode startNode = root.nodes[firstChar - 'a'];
            updateCount(startNode, str, 0);
        }
    }

    void updateCount(TrieNode node, char[] str, int idx) {
        if (node != null) {
            char nextChar = str[idx + 1];
            switch (nextChar) { // 다음 문자
                case EMPTY: //단어의 끝. 탐색 종료.
                    wordCount += node.cnt; //현재 노드까지의 딸린 문자수.
                    if (isRemov) node.cnt = 0;
                    break;
                case WILD:
                    for (int i = 0; i < MAXSIZE; i++) { // 26개 경우의 수
                        updateCount(node.nodes[i], str, idx + 1);
                    }
                    break;
                default:
                    updateCount(node.nodes[nextChar - 'a'], str, idx + 1);
            }
        }
    }
}