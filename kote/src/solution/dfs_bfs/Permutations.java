package solution.dfs_bfs;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    private void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    private void generator(List<List<Integer>> result, List<Integer> numbers, int start, int size) {
        if (start == size) {
            result.add(new ArrayList<>(numbers.subList(0, size)));
        }

        for (int i = start; i < numbers.size(); i++) {
            swap(numbers, start, i);
            generator(result, numbers, start + 1, size);
            swap(numbers, start, i);
        }
    }

    public void permutations(List<Integer> numbers) {
        int size = 0;
        for (int i = 0; i < numbers.size() + 1; i++) {
            List<List<Integer>> result = new ArrayList<>();
            generator(result, numbers, 0, i);
            for (List<Integer> p : result) {
                System.out.println(p);
            }
            System.out.println(result.size());
            System.out.println();
            size += result.size();
        }
        System.out.println(size);
    }
}
