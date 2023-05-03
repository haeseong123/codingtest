package solution.dfs_bfs;

// https://school.programmers.co.kr/learn/courses/30/lessons/43163

// 한번 앞으로 간 뒤에 다시 이전으로 돌아갈 이유는 없다.
// BFS로 탐색한다면, begin과 가까운 순서대로 방문하므로 나중에
// 방문한 것이 가중치가 더 작을 수는 없다. 따라서 visited를 사용하여
// 어느 노드 방문 시 체크를 해주고, 이후 해당 노드로의 중복 방문을 막는 것이 필요하다.
// 같은 이유로 target 단어에 가장 먼저 도착했다면 그것이 최단 변환이므로 바로 반환하면 된다.

// 문제 풀이
// begin에서 방문할 수 있는 노드를 전부 queue에 넣는다. ->
// while(!queue.isEmpty()) queue에서 원소를 poll하고 해당 원소가 target인지 확인하다.
// 만약 target이라면 distance를 반환한다. ->
// 타겟이 아니라면 해당 단어에서 접근할 수 있고, visited 하지 않은 노드를 queue에 추가한다.
// 이때 distance도 함께 추가한다. ->
// 만약, while을 벗어났다면 타켓은 접근이 불가능한 노드이므로 0을 반환한다.

// 구현 문제 사항
// 단어의 관계를 어떻게 노드로 표현할까?

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordConversion {
    private int mySolution(String begin, String target, String[] words) {
        class Point {
            final int index;
            final int count;

            Point(int index, int count) {
                this.index = index;
                this.count = count;
            }
        }

        int wordLen = begin.length();
        List<List<Integer>> graph = new ArrayList<>();
        String[] newWords = new String[words.length + 1];
        newWords[0] = begin;
        System.arraycopy(words, 0, newWords, 1, newWords.length - 1);

        for (int i = 0; i < newWords.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < newWords.length - 1; i++) {
            String curr = newWords[i];
            for (int j = i + 1; j < newWords.length; j++) {
                String next = newWords[j];
                int diff = 0;

                for (int k = 0; k < wordLen; k++) {
                    if (curr.charAt(k) != next.charAt(k)) diff += 1;
                }

                if (diff == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        Queue<Point> que = new LinkedList<>();
        boolean[] visited = new boolean[newWords.length];

        for (int neighbor : graph.get(0)) {
            que.add(new Point(neighbor, 1));
            visited[neighbor] = true;
        }

        Point p;
        while (!que.isEmpty()) {
            p = que.poll();

            if (newWords[p.index].equals(target)) return p.count;

            for (int neighbor : graph.get(p.index)) {
                if (visited[neighbor]) continue;

                que.add(new Point(neighbor, p.count + 1));
                visited[neighbor] = true;
            }
        }

        return 0;
    }

    public int solution(String begin, String target, String[] words) {
        int i = mySolution(begin, target, words);
        System.out.println(i);
        return i;
    }
}
