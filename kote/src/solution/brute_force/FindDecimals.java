package solution.brute_force;

// https://school.programmers.co.kr/learn/courses/30/lessons/42839?language=java

// 한자리 숫자가 적힌 종이 조각이 흩어져있습니다.
// 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
// 각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때,
// 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 해주세요.

// [제한사항]
// numbers는 길이 1 이상 7 이하인 문자열입니다.
// numbers는 0~9까지 숫자만으로 이루어져 있습니다.
// "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.

// [입출력 예]
// numbers = "17" return 3
// [1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.

// numbers = "011" return 2
// [0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.
// 11과 011은 같은 숫자로 취급합니다.

// 0. answer, set을 선언합니다.
// 1. 주어진 numbers로 만들 수 있는 모든 순열을 계산합니다.
//      순열을 숫자로 변환한 뒤 set에 넣습니다.
// 2. 순열을 순회하며 prime()을 확인하고, prime()이라면 answer에 +1을 합니다.
// 3. answer를 반환합니다.

import java.util.ArrayList;
import java.util.HashSet;

public class FindDecimals {
    private void swap(ArrayList<String> arr, int a, int b) {
        String temp = arr.get(a);
        arr.set(a, arr.get(b));
        arr.set(b, temp);
    }

    private void permutations(HashSet<Integer> set, ArrayList<String> numbers, int start, int size) {
        if (start == size) {
            set.add(Integer.parseInt(String.join("", numbers.subList(0, start))));
            return;
        }

        for (int i = start; i < numbers.size(); i++) {
            swap(numbers, start, i);
            permutations(set, numbers, start + 1, size);
            swap(numbers, start, i);
        }
    }

    private boolean isPrime(int a) {
        if (a < 2) return false;

        for (int i = 2; i < (int) (Math.sqrt(a) + 1); i++) {
            if (a % i == 0) return false;
        }
        return true;
    }

    public int solution(String numbers) {
        // 0
        int answer = 0;
        HashSet<Integer> set = new HashSet<>();
        ArrayList<String> numbersArr = new ArrayList<>();
        for (int i = 0; i < numbers.length(); i++) {
            numbersArr.add(Character.toString(numbers.charAt(i)));
        }
        // 1
        for (int i = 1; i < numbers.length() + 1; i++) {
            permutations(set, numbersArr, 0, i);
        }
        // 2
        for (int p : set) {
            if (isPrime(p)) answer += 1;
        }
        // 3
        return answer;
    }
}
