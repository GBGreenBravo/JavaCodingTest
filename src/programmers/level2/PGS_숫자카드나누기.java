package programmers.level2;

// 1:10:47

// 유클리드 호제법으로 최대공약수 구하는 공식만 제대로 숙지했어도 빨리 풀었던 문제.
// 주석처리된 부분 때문에, 격차 많이 나는 곳에서 의미 없이 연산 많이 했음.

public class PGS_숫자카드나누기 {
    public int solution(int[] arrayA, int[] arrayB) {

        int maxA = maxNumOfArr(arrayA);
        int maxB = maxNumOfArr(arrayB);

        boolean successA = true;
        boolean successB = true;

        for (int i : arrayB) {
            if (i % maxA == 0) {
                successA = false;
                break;
            }
        }
        for (int i : arrayA) {
            if (i % maxB == 0) {
                successB = false;
                break;
            }
        }

        if (successA == true && successB == false) return maxA;
        if (successA == false && successB == true) return maxB;
        if (successA == true && successB == true) return Math.max(maxA, maxB);

        return 0;
    }

    private int maxNumOfArr(int[] arr) {
        int result = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] >= result) result = maxNum(result, arr[i + 1]);
            else result = maxNum(arr[i + 1], result);
        }
        return result;
    }

    private int maxNum(int a, int b) {
        if (b % a == 0) return a;
        else {
            return maxNum(b % a, a);
            // b -= a;
        }
        // return maxNum(Math.min(a, b), Math.max(a, b));
    }
}
