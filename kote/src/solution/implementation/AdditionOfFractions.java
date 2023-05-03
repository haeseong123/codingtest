package solution.implementation;

// https://school.programmers.co.kr/learn/courses/30/lessons/120808?language=java

// 첫 번째 분수의 분자와 분모를 뜻하는 numer1, denom1,
// 두 번째 분수의 분자와 분모를 뜻하는 numer2, denom2가 매개변수로 주어집니다.
// 두 분수를 더한 값을 기약 분수로 나타냈을 때
// 분자와 분모를 순서대로 담은 배열을 return 해주세요.

// 두 분수를 더하려면 분모를 두 분모의 최소 공배수(lcm)로 설정해야 하고
// 각 분자를 lcm이 되기위해 분모에 곱했던 수와 곱한 뒤 더해주면 됩니다.
// 그 뒤 해당 분수를 약분해야 하는데, 이는 결괏값 분수의
// 분모와 분자의 최대 공약수로 각 분모와 분자를 나누면 됩니다.

// 1. denom1과 denom2의 lcm을 구합니다. 이것이 분모입니다.
// 2. numer1 * (lcm / denom1) + numer2 * (lcm / denom2)를 구합니다. 이것이 분자입니다.
// 3. "1"과 "2"에서 구한 분모와 분자의 gcd를 구하여 각 분자 분모를 gcd로 나눕니다.
// 4. 3의 결괏값을 분자와 분모를 순서대로 담은 배열로 반환합니다.
public class AdditionOfFractions {
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        // 1
        int denom = lcm(denom1, denom2);
        // 2
        int numer = (numer1 * (denom / denom1)) + (numer2 * (denom / denom2));
        // 3, 4
        int _gcd = gcd(denom, numer);
        return new int[]{numer / _gcd, denom / _gcd};
    }
}
