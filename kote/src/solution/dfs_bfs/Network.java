package solution.dfs_bfs;

// https://school.programmers.co.kr/learn/courses/30/lessons/43162

// 주어진 그래프에서 총 몇개의 서로소 집합이 있는지 확인하는 문제

// 1. 모든 노드에서 DFS 혹은 BFS를 사용하여
// 해당 노드가 아직 visited하지 않은 경우 count를 +1 한 뒤,
// count를 return한다.
// 그래프를 그리기위한 배열을 생성해야 하므로 O(N)
// computers를 순회해야 하므로 O(N)
// 모든 노드에 대해 순회해야 하므로 O(N)
// 따라서 O(N) + O(N) + O(N)

// 2. 모든 노드를 하나의 노드로 만들고
// disjoint-set을 사용하여
// 모든 노드를 연결시킨다.
// 마지막으로 disjoint-set의 length를 return하면
// 서로소 집합의 개수를 알 수 있다.
// 각 노드를 하나의 집합으로 만들기 위한 O(N)
// 모든 노드를 순회하며 O(N)
//      추가해야 하는지 여부 확인하는데 O(a(N))
//      추가하는데 O(1)
// 따라서 O(N) + O(N * a(N))

// 종합하면 1번은 O(N) 2번은 O(N * a(N))이므로,
// 1번 방식으로 푸는 것이 더 좋다.
// 게다가 disjoint-set은 직접 구현해야 하므로
// 2번 방식이 메모리도 더 많이 사용할 것이다.

// 그치만, 공부 목적이므로 1번과 2번 방법 두 방법을 모두 사용하여
// 문제를 해결하자.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Network {
    private void dfs(Set<Integer> visited, int start, Map<Integer, List<Integer>> graph) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited.add(start);

        while (stack.size() != 0) {
            int curr = stack.pop();

            for (int neighbor : graph.get(curr)) {
                if (visited.contains(neighbor)) {
                    continue;
                }

                stack.push(neighbor);
                visited.add(neighbor);
            }
        }
    }

    private int mySolution1(int n, int[][] computers) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());

            for (int j = 0; j < n; j++) {
                if (i == j || computers[i][j] == 0) {
                    continue;
                }
                graph.get(i).add(j);
            }
        }

        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited.contains(i)) {
                continue;
            }
            dfs(visited, i, graph);
            count += 1;
        }

        System.out.println(count);

        return count;
    }

    private int mySolution2(int n, int[][] computers) {
        class DisjointSet {
            private final int[] tree;
            private final int[] rank;

            public DisjointSet(int size) {
                this.tree = new int[size];
                this.rank = new int[size];
                for (int i = 0; i < size; i++) {
                    tree[i] = i;
                    rank[i] = 0;
                }
            }

            int find(int x) {
                if (x == tree[x]) {
                    return x;
                } else {
                    tree[x] = find(tree[x]);
                    return tree[x];
                }
            }

            void union(int x, int y) {
                x = find(x);
                y = find(y);

                if (x == y) {
                    return;
                }

                // 길이가 긴 트리에 짧은 트리를 합칩니다.
                if (rank[x] < rank[y]) {
                    tree[x] = y;
                } else if (rank[x] > rank[y]) {
                    tree[y] = x;
                } else {
                    tree[y] = x;
                    rank[x]++;
                }
            }

            int size() {
                Set<Integer> set = new HashSet<>();
                for (int i = 0; i < n; i++) {
                    int x = find(i);

                    if (set.contains(x)) {
                        continue;
                    }

                    set.add(x);
                }
                return set.size();
            }
        }

        DisjointSet ds = new DisjointSet(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || computers[i][j] == 0) continue;
                ds.union(i, j);
            }
        }

        System.out.println(ds.size());
        return ds.size();
    }

    public int solution(int n, int[][] computers) {
//        return mySolution1(n, computers);
        return mySolution2(n, computers);
    }
}
