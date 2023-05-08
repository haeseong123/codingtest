package solution.graph;

// https://school.programmers.co.kr/learn/courses/30/lessons/49189?language=java

// n개의 노드가 있는 그래프가 있습니다.
// 각 노드는 1부터 n까지 번호가 적혀있습니다.
// 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다.
// 가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.

// 노드의 개수 n,
// 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때,
// 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.

// 한 점에서 다른 모든 점까지의 최단거리를 구하는
// single source 알고리즘인
// 다익스트라 혹은 벨만-포드
// 간선의 가중치는 모두 1이므로 즉, 음수가 주어지지 않으므로, 다익스트라를 사용하자.

// 풀이 순서
// 0. 주어진 매개변수를 통해 graph 생성
// 1. 모든 노드의 값을 무한으로
// 2. 시작 노드는 0으로
// 3. 시작 노드를 priorityqueue에 넣음
// 4. 아래를 반복
//      priorityqueue에서 최솟값을 뽑음
//      최솟값 노드의 neighbors를 업데이트
// 5. 모든 노드를 순회하며 Map<떨어진 거리, 해당되는 노드 수>
//      를 작성함
// 6. map을 순회하며 가장 큰 key를 찾고 해당되는 value를 return

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FurthestNode {
    public int solution(int n, int[][] edge) {
        // 0, 1
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] distances = new int[n + 1];
        int INF = Integer.MAX_VALUE;
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
            distances[i] = INF;
        }
        for (int[] e : edge) {
            int a = e[0];
            int b = e[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        // 2, 3
        distances[1] = 0;
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        heap.offer(new int[]{0, 1});
        // 4
        while (!heap.isEmpty()) {
            // 거리, 노드 인덱스
            int[] data = heap.poll();
            int distance = data[0];
            int node = data[1];

            if (distance > distances[node]) {
                continue;
            }

            for (int neighbor : graph.get(node)) {
                if (distances[neighbor] > distance + 1) {
                    distances[neighbor] = distance + 1;
                    heap.offer(new int[]{distances[neighbor], neighbor});
                }
            }
        }
        // 5
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int node = 2; node < n + 1; node++) {
            int distance = distances[node];
            int count = map.getOrDefault(distance, 0) + 1;
            map.put(distance, count);
        }
        // 6
        int maxDistance = 0;
        int answer = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int distance = entry.getKey();
            int count = entry.getValue();
            if (distance > maxDistance) {
                answer = count;
            }
        }

        return answer;
    }
}
