package algorithm.삼성전자dx특강.코드배틀;

import java.util.ArrayList;
import java.util.List;

class UserSolution {
    private final static int NUM_PEOPLE = 1000000;
    private final static int MAX_ROOM = 1000000;

    void investigate(MagicRoom api) {
        // 1. 100만명의 사람들을 square N 개의 그룹으로 나눔. (sqrt composition)
        int r = (int) Math.floor(Math.sqrt(NUM_PEOPLE)); // 그룹의 크기
        int g = NUM_PEOPLE / r; // 그룹의 개수
        if (NUM_PEOPLE % r != 0) {
            g++;
        }

        List<Integer> d = new ArrayList<>();

        int idx=0;
        for(int i=0; i<g; i++) {
            int start = i*r;
            for(int j=start; j<start+r; j++) {
                api.putln(idx, j);
            }
            if (api.closeDoor(idx) != 0) {
                d.add(start);
                idx++;
            }
        }


        idx = 0;
        api.clearRoom();
        for(int i=0; i<d.size(); i++) {
            int start = d.get(i);
            for(int j=start; j<start+r; j++) {
                api.putln(idx, j);
                if (api.closeDoor(idx) != 0) {
                    api.arrest(j);
                    idx++;
                }
            }
        }
    }
}