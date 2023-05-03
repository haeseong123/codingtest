package solution.implementation;

// https://school.programmers.co.kr/learn/courses/30/lessons/135807

// 철수와 영희는 선생님으로부터 숫자가 하나씩 적힌 카드들을
// 절반씩 나눠서 가진 후, 다음 두 조건 중 하나를 만족하는
// 가장 큰 양의 정수 a의 값을 구하려고 합니다.

// 철수가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고
// 영희가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수 a
// 영희가 가진 카드들에 적힌 모든 숫자를 나눌 수 있고,
// 철수가 가진 카드들에 적힌 모든 숫자들 중 하나도 나눌 수 없는 양의 정수 a

// 예를 들어, 카드들에 10, 5, 20, 17이 적혀 있는 경우에 대해 생각해 봅시다.
// 만약, 철수가 [10, 17]이 적힌 카드를 갖고,
// 영희가 [5, 20]이 적힌 카드를 갖는다면
// 두 조건 중 하나를 만족하는 양의 정수 a는 존재하지 않습니다.
// 하지만, 철수가 [10, 20]이 적힌 카드를 갖고,
// 영희가 [5, 17]이 적힌 카드를 갖는다면,
// 철수가 가진 카드들의 숫자는 모두 10으로 나눌 수 있고,
// 영희가 가진 카드들의 숫자는 모두 10으로 나눌 수 없습니다.
// 따라서 철수와 영희는 각각 [10, 20]이 적힌 카드,
// [5, 17]이 적힌 카드로 나눠 가졌다면
// 조건에 해당하는 양의 정수 a는 10이 됩니다.
//
// 철수가 가진 카드에 적힌 숫자들을 나타내는 정수 배열 arrayA와
// 영희가 가진 카드에 적힌 숫자들을 나타내는 정수 배열 arrayB가 주어졌을 때,
// 주어진 조건을 만족하는 가장 큰 양의 정수 a를 return 해주세요.
// 만약, 조건을 만족하는 a가 없다면, 0을 return 해 주세요.

// 0. answer, gcdA, gcdB 를 선언합니다.
// 1. arrayA와 arrayB 각각의 최대 공약수를 구합니다.
// 2. 최대 공약수로 상대방의 배열의 원소를 나눌 수 없는지 확인합니다.
//      나눌 수 없는 것이 확인된다면 현재 answer의 값보다 해당 최대 공약수가 더 큰지 확인합니다.
//          더 크다면 answer에 현재 최대 공약수를 넣습니다.
// 3. answer를 반환합니다.
public class DivideNumberCards {
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }

    private boolean isDivisible(int[] array, int g) {
        for (int n : array) {
            if (n % g == 0) return true;
        }
        return false;
    }

    public int solution(int[] arrayA, int[] arrayB) {
        // 0
        int answer = 0;
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        // 1
        for (int i = 1; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
            gcdB = gcd(gcdB, arrayB[i]);
        }
        // 2
        if (!isDivisible(arrayB, gcdA)) {
            if (answer < gcdA) answer = gcdA;
        }
        if (!isDivisible(arrayA, gcdB)) {
            if (answer < gcdB) answer = gcdB;
        }
        return answer;
    }
}
