package solution.brute_force;

// https://www.acmicpc.net/problem/1018

// 지민이는 자신의 저택에서 MN개의 단위 정사각형으로 나누어져 있는 M×N 크기의 보드를 찾았다.
// 어떤 정사각형은 검은색으로 칠해져 있고,
// 나머지는 흰색으로 칠해져 있다.
// 지민이는 이 보드를 잘라서 8×8 크기의
// 체스판으로 만들려고 한다.

// 체스판은 검은색과 흰색이 번갈아서 칠해져 있어야 한다.
// 구체적으로, 각 칸이 검은색과 흰색 중 하나로 색칠되어 있고,
// 변을 공유하는 두 개의 사각형은 다른 색으로 칠해져 있어야 한다.
// 따라서 이 정의를 따르면 체스판을 색칠하는 경우는 두 가지뿐이다.
// 하나는 맨 왼쪽 위 칸이 흰색인 경우, 하나는 검은색인 경우이다.

// 보드가 체스판처럼 칠해져 있다는 보장이 없어서,
// 지민이는 8×8 크기의 체스판으로 잘라낸 후에
// 몇 개의 정사각형을 다시 칠해야겠다고 생각했다.
// 당연히 8*8 크기는 아무데서나 골라도 된다.
// 지민이가 다시 칠해야 하는 정사각형의 최소 개수를 구하는 프로그램을 작성하시오.

// [입력]
// 첫째 줄에 N과 M이 주어진다.
// N, M은 8 <= N, M <= 50 을 만족하는 자연수이다.
// 둘째 줄부터 N개의 줄에는 보드의 각 행의 상태가 주어진다. B는 검은색이며, W는 흰색이다.

// [출력]
// 첫째 줄에 지민이가 다시 칠해야 하는 정사각형 개수의 최솟값을 출력한다.

// [예제 입력 1]
// 8 8
// WBWBWBWB
// BWBWBWBW
// WBWBWBWB
// BWBBBWBW
// WBWBWBWB
// BWBWBWBW
// WBWBWBWB
// BWBWBWBW
// [예제 출력 1]
// 1


// [예제 입력 2]
// 10 13
// BBBBBBBBWBWBW
// BBBBBBBBBWBWB
// BBBBBBBBWBWBW
// BBBBBBBBBWBWB
// BBBBBBBBWBWBW
// BBBBBBBBBWBWB
// BBBBBBBBWBWBW
// BBBBBBBBBWBWB
// WWWWWWWWWWBWB
// WWWWWWWWWWBWB
// [예제 출력 2]
// 12

// [예제 입력 3]
// 8 8
// BWBWBWBW
// WBWBWBWB
// BWBWBWBW
// WBWBWBWB
// BWBWBWBW
// WBWBWBWB
// BWBWBWBW
// WBWBWBWB
// [예제 출력 3]
// 0

// 0. 입력 받기
// 1. 체스판 자르기
// 2. 현 체스판의 최소 비용 구하기
//      촤측 상단이 black 일때 + white 일때
//      바꿔야 하는 총 비용은 8*8=64 입니다.
//      따라서 black일때 혹은 white일때만 계산하면
//      그 반대 경우는 64 - x로 구할 수 있습니다.
//      또, row와 col의 합이 짝수라면 시작 문자와 같아야하고
//      row와 col의 합이 홀수라면 시작 문자와 달라야합니다.
// 3. 전체 최솟값과 "2"의 결괏값 비교하기

import java.util.Scanner;

public class RepaintTheChessboard {
    public void solution() {
        // 0
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        String[][] matrix = new String[row][col];
        for (int i = 0; i < row; i++) {
            String[] line = sc.next().split("");
            System.arraycopy(line, 0, matrix[i], 0, col);
        }
        // 1
        int min = 64;
        for (int i = 0; i < row - 7; i++) {
            for (int j = 0; j < col - 7; j++) {
                int count = 0;

                for (int r = i; r < i + 8; r++) {
                    for (int c = j; c < j + 8; c++) {
                        // 2
                        int rc = r + c;
                        if (rc % 2 == 0) {
                            if (!matrix[r][c].equals("W")) count += 1;
                        } else {
                            if (matrix[r][c].equals("W")) count += 1;
                        }
                    }
                }
                count = count > 32 ? 64 - count : count;
                // 3
                min = Math.min(min, count);
            }
        }

        System.out.println(min);
    }
}
