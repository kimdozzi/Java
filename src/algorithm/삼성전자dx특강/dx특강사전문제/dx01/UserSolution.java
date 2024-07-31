package algorithm.samsung.dx특강사전문제.dx01;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class UserSolution {
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    private static int dy[] = {-1, 0, 1, 0};
    private static int dx[] = {0, 1, 0, -1};

    private static List<Integer> list = new ArrayList<>();

    private static int[][] orgBoard = new int[5][5];
    private static int emptyY, emptyX;
    private static Set<Pair> set = new HashSet<>();

    private static boolean inRange(int nx, int ny) {
        return 0 <= nx && nx < 5 && 0 <= ny && ny < 5;
    }

    private static void swapBlock(int fromX, int fromY, int toX, int toY) {
        if (fromX == toX && fromY > toY) {
            list.add(LEFT);
            swap(LEFT);
        } else if (fromX == toX && fromY < toY) {
            list.add(RIGHT);
            swap(RIGHT);
        } else if (fromY == toY && fromX > toX) {
            list.add(UP);
            swap(UP);
        } else if (fromY == toY && fromX < toX) {
            list.add(DOWN);
            swap(DOWN);
        }
    }

    public static boolean swap(int dir) {
        if (dir < UP || dir > LEFT) {
            return false;
        }

        int newY = emptyY + dy[dir];
        int newX = emptyX + dx[dir];

        if (newY < 0 || newY >= 5 || newX < 0 || newX >= 5) {
            return false;
        }

        orgBoard[emptyY][emptyX] = orgBoard[newY][newX];
        orgBoard[newY][newX] = 0;
        emptyY = newY;
        emptyX = newX;

        return true;
    }


    public void solve(int[][] board, int[][] pattern, int callCntLimit) {
        for (int i = 0; i < 5; i++) {
            System.arraycopy(board[i], 0, orgBoard[i], 0, 5);
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (orgBoard[i][j] == 0) {
                    emptyX = j;
                    emptyY = i;
                    break;
                }
            }
        }
        set.clear();
        list.clear();

        // 1. 좌상단부터 우하단까지 board[i][j]와 pattern[i][j]가 일치하는지 확인.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // 일치하지 않는 경우
                if (pattern[i][j] != orgBoard[i + 1][j + 1]) {
                    Block sameColorBlock = search(pattern, i + 1, j + 1, i, j);

                    int distX = 2, distY = 3, ddistX, ddistY;
                    while (sameColorBlock != null && distY < sameColorBlock.sb.length()) {
                        // 비워야할 블록의 x,y 좌표
                        int needBlankX = sameColorBlock.sb.charAt(distX) - '0';
                        int needBlankY = sameColorBlock.sb.charAt(distY) - '0';

                        Block block = blankSpace(sameColorBlock.x, sameColorBlock.y, needBlankX, needBlankY);

                        ddistX = 2;
                        ddistY = 3;
                        while (block != null && ddistY < block.sb.length()) {
                            // 빈 칸과 교환해야 할 블록의 x,y 좌표
                            int ttargetX = block.sb.charAt(ddistX) - '0';
                            int ttargetY = block.sb.charAt(ddistY) - '0';
                            swapBlock(emptyY, emptyX, ttargetX, ttargetY);

                            ddistX += 2;
                            ddistY += 2;
                        }
                        swapBlock(emptyY, emptyX, sameColorBlock.x, sameColorBlock.y);
                        distX += 2;
                        distY += 2;
                        sameColorBlock.x = needBlankX;
                        sameColorBlock.y = needBlankY;
                    }
                    // 이미 지나온 블록
                    set.add(new Pair(i + 1, j + 1));
                }
                set.add(new Pair(i + 1, j + 1));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            Solution.swap(list.get(i));
        }
    }

    private Block blankSpace(int originX, int originY, int targetX, int targetY) {
        boolean[][] visited = new boolean[5][5];
        Queue<Block> queue = new LinkedList<>();
        queue.add(new Block(targetX, targetY, new StringBuilder().append(targetX).append(targetY)));
        visited[targetX][targetY] = true;

        while (!queue.isEmpty()) {
            Block block = queue.poll();

            // 이동해야 할 블록이라면 무시.
            if (block.x == originX && block.y == originY) {
                continue;
            }

            if (block.x == emptyY && block.y == emptyX) {
                // 가장 짧은 거리를 찾은 경우
                block.emptyX = block.x;
                block.emptyY = block.y;

                return block;
            }

            for (int d = 0; d < 4; d++) {
                int nx = block.x + dx[d];
                int ny = block.y + dy[d];

                if (!inRange(nx, ny)) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }
                boolean flag = false;
                Iterator iter = set.iterator();
                while (iter.hasNext()) {
                    Pair next = (Pair) iter.next();
                    if (next.x == nx && next.y == ny) {
                        flag = true;
                    }
                }
                if (flag) {
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new Block(nx, ny, new StringBuilder().append(nx).append(ny).append(block.sb)));
            }
        }
        return null;
    }

    private Block search(int[][] pattern, int bx, int by, int px, int py) {
        boolean[][] visited = new boolean[5][5];
        Queue<Block> queue = new LinkedList<>();
        queue.add(new Block(bx, by, new StringBuilder().append(bx).append(by)));
        visited[bx][by] = true;

        while (!queue.isEmpty()) {
            Block pair = queue.poll();
            // 같은 블록을 찾은 경우.
            if (orgBoard[pair.x][pair.y] == pattern[px][py]) {
                return pair;
            }

            for (int d = 0; d < 4; d++) {
                int nx = pair.x + dx[d];
                int ny = pair.y + dy[d];

                if (!inRange(nx, ny)) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                boolean flag = false;
                Iterator iter = set.iterator();
                while (iter.hasNext()) {
                    Pair next = (Pair) iter.next();
                    if (next.x == nx && next.y == ny) {
                        flag = true;
                    }
                }
                if (flag) {
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new Block(nx, ny, new StringBuilder().append(nx).append(ny).append(pair.sb)));
            }
        }
        return null;
    }

    private static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Block {
        int x;
        int y;
        int emptyX;
        int emptyY;
        StringBuilder sb;

        public Block(int x, int y, StringBuilder sb) {
            this.x = x;
            this.y = y;
            this.sb = sb;
        }
    }
}