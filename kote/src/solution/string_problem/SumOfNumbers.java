package solution.string_problem;

// https://www.acmicpc.net/problem/11720

// N개의 숫자가 공백없이 쓰여있습니다. 이 숫자를 모두 합해서 출력하는 프로그램을 작성하면 됩니다.

// 예제 입력
// 1
// 1
// 예제 출력
// 1

// 예제 입력
// 5
// 54321
// 예제 출력
// 15

// 예제 입력
// 11
// 10987654321
// 예제 출력
// 46

// 1. scanner로 두 값을 입력 받습니다.
// 2. 입력받은 값을 토대로 두 번째 입력값을 순회하며 acc에 저장합니다.
//      이때 chatAt() 사용시 아스키 코드 값이 들어가므로 '0'을 빼줘야 합니다.
// 3. acc를 출력합니다.

import java.util.Scanner;

public class SumOfNumbers {
    public void solution() {
        // 1
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        String N = sc.next();
        sc.close();
        // 2
        int acc = 0;
        for (int i = 0; i < n; i++) {
            acc += N.charAt(i) - '0';
        }
        // 3
        System.out.println(acc);
    }
}
