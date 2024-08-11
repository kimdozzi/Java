package 삼성전자dx특강.dx특강사전문제.dx02;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class UserSolution {
    static final int MAXL = 5;
    static final int MAXF = 10;
    static Map<Integer, User> userList = new HashMap<>();

    public void init(int N) {
        userList.clear();
        for (int i = 1; i <= N; i++) {
            userList.put(i, new User(i));
        }
    }

    public void add(int id, int F, int ids[]) {
        User findUser = userList.get(id);
        for (int i = 0; i < F; i++) {
            User addUser = userList.get(ids[i]);
            findUser.friends.put(addUser.id, 1);
            addUser.friends.put(findUser.id, 1);
        }
    }

    public void del(int id1, int id2) {
        User userA = userList.get(id1);
        User userB = userList.get(id2);
        userA.friends.remove(userB.id);
        userB.friends.remove(userA.id);
    }

    public int recommend(int id, int list[]) {
        Map<Integer, Integer> recommendList = new HashMap<>();

        User user = userList.get(id);

        // user의 친구들 id를 먼저 담기
        Set<Integer> myFriends = new HashSet<>(user.friends.keySet());

        for (Integer friendId : user.friends.keySet()) {
            User friend = userList.get(friendId);
            for (Integer nextId : friend.friends.keySet()) {
                if (nextId != user.id && !myFriends.contains(nextId)) {
                    recommendList.put(nextId, recommendList.getOrDefault(nextId, 0) + 1);
                }
            }
        }

        // HashMap의 entrySet()을 List로 변환
        List<Entry<Integer, Integer>> sortedList = new ArrayList<>(recommendList.entrySet());
        sortedList.sort((o1, o2) -> {
            int valueComparison = o2.getValue().compareTo(o1.getValue());
            return (valueComparison != 0) ? valueComparison : o1.getKey().compareTo(o2.getKey());
        });

        // 정렬된 결과를 list 배열에 삽입
        int index = 0;
        for (Entry<Integer, Integer> entry : sortedList) {
            if (index >= MAXL) {
                break;
            }
            list[index++] = entry.getKey();
        }

        return index;
    }

    private static class User {
        int id; // 사용자 id
        Map<Integer, Integer> friends; // 친구 리스트

        public User(int id) {
            this.id = id;
            this.friends = new HashMap<>();
        }
    }
}