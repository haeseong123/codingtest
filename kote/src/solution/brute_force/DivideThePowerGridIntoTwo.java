package solution.brute_force;

// https://school.programmers.co.kr/learn/courses/30/lessons/87946?language=java

// n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다.
// 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다.
// 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.

// 송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다.
// 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때,
// 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.

// 제한사항
// n은 2 이상 100 이하인 자연수입니다.
// wires는 길이가 n-1인 정수형 2차원 배열입니다.
//      wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며,
//      이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
//      1 ≤ v1 < v2 ≤ n 입니다.
//      전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.

// 1. wires를 순회하며 간선 정보를 완성합니다.
//      이를 edges라 합니다.
// 2. wires를 순회하며 해당 간선을 끊습니다.
//      이때 edges에 담긴 정보를 false로 바꿉니다.
// 3. 끊긴 각각의 노드의 총 개수를 센 뒤 둘을 빼고
//      절댓값을 구한뒤 min(answer, abs)를 수행합니다.
// 4. edges를 다시 true로 이어놓습니다.
// 5. res를 반환합니다.

import java.util.Stack;

public class DivideThePowerGridIntoTwo {

    boolean[][] edges;
    int res = Integer.MAX_VALUE;
    int N;

    public int solution(int n, int[][] wires) {
        N = n + 1;
        edges = new boolean[N][N];
        for (int[] wire : wires) {
            edges[wire[0]][wire[1]] = true;
            edges[wire[1]][wire[0]] = true;
        }

        for (int[] wire : wires) {
            boolean[] visited = new boolean[N];
            edges[wire[0]][wire[1]] = false;
            edges[wire[1]][wire[0]] = false;

            int cnt1 = dfs(wire[0], visited);
            int cnt2 = dfs(wire[1], visited);
            res = Math.min(res, Math.abs(cnt1 - cnt2));

            edges[wire[0]][wire[1]] = true;
            edges[wire[1]][wire[0]] = true;
        }

        return res;
    }

    private int dfs(int start, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;
        int count = 1;

        while (!stack.isEmpty()) {
            int curr = stack.pop();

            for (int next = 1; next < N; next++) {
                if (!edges[curr][next] || visited[next]) continue;

                stack.push(next);
                visited[next] = true;
                count += 1;
            }
        }

        return count;
    }
}
