package solution.dfs_bfs;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    private void generator(List<List<Integer>> result, List<Integer> temp, List<Integer> numbers, int start, int size) {
        // 어떻게 하면 조합을 만들 수 있을까?
        // 조합은 순열과 달리 순서를 고려하지 않으니까
        // 순서를 바꾸는 swap을 제외해도 된다.
        if (size == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < numbers.size() - size + 1; i++) {
            temp.add(numbers.get(i));
            generator(result, temp, numbers, i + 1, size - 1);
            temp.remove(temp.size() - 1);
        }
    }

    public void combinations(List<Integer> numbers) {
        long beforeTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < numbers.size() + 1; i++) {
            List<List<Integer>> result = new ArrayList<>();
            generator(result, new ArrayList<>(), numbers, 0, i);

            for (List<Integer> c : result) {
                System.out.println(c);
            }
            System.out.println(result.size());
            System.out.println();
            count += result.size();
        }
        long afterTime = System.currentTimeMillis();
        System.out.println(count);
        System.out.println((afterTime - beforeTime) / 100);
    }
}
