package 삼성전자dx특강.day01.메모장;
import java.util.LinkedList;

class UserSolution {
    static int MAX_H = 301;
    static int MAX_W = 26;
    static LinkedList< Character >[] ll = new LinkedList[MAX_H];
    static int[][] memoBoard = new int[MAX_H][MAX_W];

    static int nowH;
    static int nowW;
    static int curH;
    static int curW;

    void init(int H, int W, char mStr[]) {
//        System.out.println("== init == ");
        for (int i = 0; i < MAX_H; i++) {
            ll[i] = new LinkedList<>();
        }

        for (int i = 0; i < MAX_H; i++) {
            for (int j = 0; j < 26; j++) {
                memoBoard[i][j] = 0;
            }
        }

        MAX_H = H; // 5
        MAX_W = W; // 7
        curH = 0;
        curW = 0;

        int size = 0;
        for (int i = 0; i < mStr.length; i++, size++) {
            if (97 > mStr[i] || 123 < mStr[i]) {
                break;
            }
        }

        for (int i = 0; i < size; i++) {
            int h = i / MAX_W;
            ll[h].addLast(mStr[i]);
            memoBoard[h][mStr[i] - 'a']++;
        }
        nowH = (size - 1) / MAX_W;
        nowW = (size - 1) % MAX_W;

//        System.out.println("최대 높이 : " + nowH + " " + nowW);
//        for (int i = 0; i < MAX_H; i++) {
//            System.out.println(ll[i]);
//        }
    }

    void insert(char mChar) {
//        System.out.println("== insert == ");
        ll[curH].add(curW, mChar);
        memoBoard[curH][mChar - 'a']++;
        int i = curH;

        while (ll[i].size() > MAX_W) {
            Character temp = ll[i].peekLast();
            ll[i + 1].addFirst(temp);
            ll[i].pollLast();
            memoBoard[i][temp - 'a']--;
            memoBoard[i + 1][temp - 'a']++;
            i++;
        }

//        System.out.println("이동 전 커서위치: " + curH + " " + curW);
        nowW++;
        if (nowW >= MAX_W) {
            nowH++;
            nowW = 0;
        }

        curW++;
        if (curW >= MAX_W) {
            curH++;
            curW = 0;
        }
//
//        for (i = 0; i < MAX_H; i++) {
//            System.out.println(ll[i]);
//        }
//
//        System.out.println("이동 후 커서위치: " + curH + " " + curW);
    }

    char moveCursor(int mRow, int mCol) {
//        System.out.println("== moveCursor == ");
//        System.out.println("최대높이:" + nowH + " " + nowW);
//        System.out.println("이동하려는 커서위치: " + mRow+" " + mCol);
        //if (mRow > nowH || (mRow == nowH && mCol > nowW)) {
        if (MAX_W * (mRow - 1) + (mCol - 1) > MAX_W * nowH + nowW) {
            curH = nowH;
            curW = nowW + 1;
            if (curW >= MAX_W) {
                curH++;
                curW = 0;
            }
//            System.out.println("초과");
//            System.out.println("커서위치: " + curH + " "+ curW);
            return '$';
        }
        else {
//            System.out.println("안정");

            curH = mRow-1;
            curW = mCol-1;
//            System.out.println("커서위치: " + curH + " "+ curW);
//            System.out.println("cursor 앞 문자: " + ll[curH].get(curW));
            return ll[curH].get(curW);
        }
    }

    int countCharacter(char mChar) {
//        System.out.println("== countCharacter == ");
//        System.out.println("Count 문자 ");
//        System.out.println("현재 커서 위치: " + curH + " " + curW);
        int cnt = 0;

        for (int i = curW; i < ll[curH].size(); i++) {
            if (ll[curH].get(i) == mChar) {
                cnt++;
            }
        }
        for (int i = curH + 1; i <= nowH; i++) {
            cnt += memoBoard[i][mChar - 'a'];
        }
//        System.out.println(mChar+":"+cnt);
        return cnt;
    }
}