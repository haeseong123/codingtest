import solution.graph.FurthestNode;

public class Main {
    public static void main(String[] args) {
        FurthestNode node = new FurthestNode();
        int[][] vertex = new int[][]{
                {3, 6}, {4, 3}, {3, 2}, {1, 3},
                {1, 2}, {2, 4}, {5, 2}
        };
        node.solution(6, vertex);
    }
}
