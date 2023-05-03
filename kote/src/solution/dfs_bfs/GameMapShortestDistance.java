package solution.dfs_bfs;


// https://school.programmers.co.kr/learn/courses/30/lessons/1844

// 게임 맵의 상태 maps가 주어졌을 때,
// 캐릭터가 상대 팀 진영에 도착하기 위해서 지나가야 하는 칸의 개수의 최솟값을 return하는 문제입니다.

// 1. 왔던 길을 다시 돌아가는 경우는 절대로 존재하지 않는다.

// BFS를 사용해서 풀 수 있습니다.
// 가중치가 주어지고 음수 path가 없을 때 사용하는 다익스트라 알고리즘으로 풀 수 있습니다.

// ** DFS/BFS는 그래프 순회 알고리즘이고
// 다익스트라, 벨만-포드는 가중치 그래프 최단거리 특히,
// single destination(모든 정점에서 하나의 목적지 까지의 최단 경로를 찾는 문제)의 대표 알고리즘입니다.

// BFS는 한 칸 움직일 때마다 distance를 +1 합니다.
// neighbor를 방문하면 visited set에 add 합니다.
// 목적지에 도착하면 distance를 return 합니다.
// BFS는 시작 노드로부터 멀리 떨어진 순서대로 순회하는
// 알고리즘이기 때문에 목적지에 도착하면 그것이 최단 경로입니다.
// 만약 DFS를 사용했다면, neighbor의 값이 distance + 1 보다
// 크면 갱신하는 방식으로 진행합니다.
// 단, 그렇게 하면 보통의 경우 노드 방문이 중복되기 때문에 BFS보다
// 비효율적이며, 코드 작성도 더 어려워 집니다.
// 하지만 시간 복잡도는 BFS DFS 둘 다 O(N) 입니다.

// 다익스트라는 노드의 값이 최솟값인 노드를 뽑아 진행하는 방식입니다.
// 여기서는 가중치가 1이므로,
// 목적지에 도착했다면 그것이 최단거리 입니다.
// 단 시작 노드를 제외하고 모든 노드의 값을 무한으로 설정하는 과정이 필요하고
// 최소힙을 사용하므로 BFS보다 느립니다.
// 보통 다익스트라의 시간복잡도는 O((E+v)logV)입니다.


import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class GameMapShortestDistance {
    // BFS
    private int mySolution1(int[][] maps) {
        class Position {
            final int row;
            final int col;

            Position(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }

        int rowLimit = maps.length;
        int colLimit = maps[0].length;
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우

        Queue<Position> que = new LinkedList<>();
        boolean[][] visited = new boolean[rowLimit][colLimit];

        que.add(new Position(0, 0));
        visited[0][0] = true;

        Position p;
        while (!que.isEmpty()) {
            p = que.poll();
            int row = p.row;
            int col = p.col;

            if (row == rowLimit - 1 && col == colLimit - 1) {
                return maps[row][col];
            }

            for (int[] move : moves) {
                int newRow = row + move[0];
                int newCol = col + move[1];

                if (newRow < 0 || newRow >= rowLimit || newCol < 0 || newCol >= colLimit || visited[newRow][newCol] || maps[newRow][newCol] == 0) {
                    continue;
                }

                que.add(new Position(newRow, newCol));
                visited[newRow][newCol] = true;
                maps[newRow][newCol] = maps[row][col] + 1;
            }
        }

        return -1;
    }

    // DIJKSTRA
    private int mySolution2(int[][] maps) {
        class Position implements Comparable<Position>, Comparator<Position> {
            final int row;
            final int col;
            final int value;

            Position(int row, int col, int value) {
                this.row = row;
                this.col = col;
                this.value = value;
            }

            @Override
            public int compareTo(Position p) {
                return this.value <= p.value ? -1 : 1;
            }

            @Override
            public int compare(Position o1, Position o2) {
                return 0;
            }
        }

        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
        int intMaxValue = Integer.MAX_VALUE;
        int rowLimit = maps.length;
        int colLimit = maps[0].length;
        for (int row = 0; row < rowLimit; row++) {
            for (int col = 0; col < colLimit; col++) {
                if (maps[row][col] == 0) continue;
                maps[row][col] = intMaxValue;
            }
        }
        boolean[][] visited = new boolean[rowLimit][colLimit];
        PriorityQueue<Position> minHeap = new PriorityQueue<>();

        maps[0][0] = 1;
        minHeap.add(new Position(0, 0, 1));
        visited[0][0] = true;

        Position p;
        while (!minHeap.isEmpty()) {
            p = minHeap.poll();

            if (p.row == rowLimit - 1 && p.col == colLimit - 1) {
                return p.value;
            }

            for (int[] move : moves) {
                int newRow = p.row + move[0];
                int newCol = p.col + move[1];
                int newValue = p.value + 1;

                if (newRow < 0 || newRow >= rowLimit || newCol < 0 || newCol >= colLimit || visited[newRow][newCol] || maps[newRow][newCol] == 0) {
                    continue;
                }

                maps[newRow][newCol] = newValue;
                minHeap.add(new Position(newRow, newCol, newValue));
                visited[newRow][newCol] = true;
            }
        }

        return -1;
    }

    public int solution(int[][] maps) {
//        int a = mySolution1(maps);
//        System.out.println(a);
//        return a;
        int a = mySolution2(maps);
        System.out.println(a);
        return a;
    }
}
