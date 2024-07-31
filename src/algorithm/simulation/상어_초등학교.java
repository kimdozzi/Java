package algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

class Seat implements Comparable<Seat>{
    int x,y;
    int studentSum;
    int emptySum;

    public Seat(int x, int y, int studentSum, int emptySum) {
        this.x = x;
        this.y = y;
        this.studentSum = studentSum;
        this.emptySum = emptySum;
    }

    @Override
    public int compareTo(Seat otherSeat) {
        if (studentSum != otherSeat.studentSum)
            return -(studentSum- otherSeat.studentSum);

        if(emptySum != otherSeat.emptySum)
            return -(emptySum-otherSeat.emptySum);

        if (x != otherSeat.x)
            return x-otherSeat.x;

        return y-otherSeat.y;
    }

}
public class 상어_초등학교 {
    static int n, answer;
    static int[][] board;
    static int[] students;
    static Map<Integer, Set<Integer>> preferences;
    static int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};

    public static void main(String[] args) throws IOException {
        init();
        
        solve();

        print();
    }

    private static void print() {
        System.out.println(answer);
    }

    private static void solve() {
        for (int i=0; i< students.length; i++) {
            Seat seat = findSeat(students[i]);
            board[seat.x][seat.y] = students[i];
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int count = getStudentSum(i,j,board[i][j]);
                if (count > 0) answer += (int) Math.pow(10, count-1);
            }
        }
    }

    static Seat findSeat(int student) {
        Seat seat = null;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] > 0) continue;

                Seat curSeat = new Seat(i, j, getStudentSum(i, j, student), getEmptySum(i, j));

                if(seat == null) {
                    seat = curSeat;
                    continue;
                }

                if (seat.compareTo(curSeat) > 0) seat = curSeat;
            }
        }
        return seat;
    }

    private static int getEmptySum(int x, int y) {
        int count = 0;
        
        for (int i=0; i<4; i++) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];
            if (!in_range(nx,ny)) continue;
            if (board[nx][ny] == 0) count++;
        }

        return count;
    }

    private static int getStudentSum(int x, int y, int student) {
        int count = 0;

        for (int i=0; i<4; i++) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];
            if (!in_range(nx,ny)) continue;
            if (preferences.get(student).contains(board[nx][ny])) {
                count++;
            }
        }
        return count;
    }


    private static boolean in_range(int nx, int ny) {
        return (0 <= nx && nx < n && 0 <= ny && ny < n);
    }

    private static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        answer = 0;
        board = new int[n][n];
        students = new int[n*n];
        preferences = new HashMap<>();

        StringTokenizer st;

        for (int i=0; i<students.length; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            students[i] = student;
            preferences.put(student, new HashSet<>());
            for (int j=0; j<4; j++) {
                preferences.get(student).add(Integer.parseInt(st.nextToken()));
            }
        }
    }
}
