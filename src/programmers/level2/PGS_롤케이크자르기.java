package programmers.level2;

// 37:46
//
// 중복검사를 HashSet으로 안 하고 ArrayList로 하려해서 시간 초과 이슈가 있었던 문제.
// 좌측, 우측에서 각각 종류 수를 count하고, 이를 int[]에 저장.
// 정수와 정수 사이에서 left[왼쪽 정수] == right[오른쪽 정수]면 answer++;

import java.util.HashSet;
import java.util.Set;

public class PGS_롤케이크자르기 {
    public int solution(int[] topping) {
        int[] left = new int[topping.length];
        int[] right = new int[topping.length];

        int leftTemp = 0;
        Set<Integer> leftArr = new HashSet<>();
        for (int i = 0; i < topping.length; i++) {
            if (leftArr.contains(topping[i])) {
                left[i] = leftTemp;
                continue;
            }
            leftArr.add(topping[i]);
            left[i] = ++leftTemp;
        }

        int rightTemp = 0;
        Set<Integer> rightArr = new HashSet<>();
        for (int i = topping.length - 1; i >= 0; i--) {
            if (rightArr.contains(topping[i])) {
                right[i] = rightTemp;
                continue;
            }
            rightArr.add(topping[i]);
            right[i] = ++rightTemp;
        }


        int answer = 0;
        for (int i = 0; i < topping.length - 1; i++) {
            if (left[i] == right[i + 1]) {
                answer++;
            }
        }
        return answer;
    }
}

// 1개 시간초과.
/* public int solution(int[] topping) {
        int[] left = new int[topping.length];
        int[] right = new int[topping.length];

        int leftTemp = 0;
        ArrayList<Integer> leftArr = new ArrayList<>();
        for (int i = 0; i < topping.length; i++) {
            if (leftArr.contains(topping[i])) {
                left[i] = leftTemp;
                continue;
            }
            leftArr.add(topping[i]);
            left[i] = ++leftTemp;
        }

        int rightTemp = 0;
        ArrayList<Integer> rightArr = new ArrayList<>();
        for (int i = topping.length - 1; i >= 0; i--) {
            if (rightArr.contains(topping[i])) {
                right[i] = rightTemp;
                continue;
            }
            rightArr.add(topping[i]);
            right[i] = ++rightTemp;
        }


        int answer = 0;
        for (int i = 0; i < topping.length - 1; i++) {
            if (left[i] == right[i + 1]) answer++;
        }
        return answer;
    } */

// 시간초과 4개
/* public int solution(int[] topping) {
        int[] left = new int[topping.length];
        int[] right = new int[topping.length];

        int start = 0;
        int end = topping.length - 1;
        int leftTemp = 0;
        int rightTemp = 0;
        boolean leftStop = false;
        boolean rightStop = false;
        ArrayList<Integer> leftArr = new ArrayList<>();
        ArrayList<Integer> rightArr = new ArrayList<>();
        for (int i = 0; i < topping.length; i++) {
            if (leftStop && rightStop) break;

            if (!leftStop) {
                if (leftArr.contains(topping[i])) {
                    left[i] = leftTemp;

                } else {
                    leftArr.add(topping[i]);
                    left[i] = ++leftTemp;
                    if (right[i] != 0 && left[i] > right[i]) {
                        end = i;
                        leftStop = true;
                    }
                }
            }

            if (!rightStop) {
                int rightI = topping.length - i - 1;
                if (rightArr.contains(topping[rightI])) {
                    right[rightI] = rightTemp;
                } else {
                    rightArr.add(topping[rightI]);
                    right[rightI] = ++rightTemp;
                    if (left[rightI] != 0 && left[rightI] < right[rightI]) {
                        start = rightI;
                        rightStop = true;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = start; i < end; i++) {
            if (left[i] == right[i + 1]) answer++;
        }
        return answer;
    } */

// 이런 꼼수도 써봤음.
// 결국 ArrayList로 중복 검사를 하는 것이 비효율적이었던 것!
/* public int solution(int[] topping) {
        int[] left = new int[topping.length];
        int[] right = new int[topping.length];

        int leftTemp = 0;
        ArrayList<Integer> leftArr = new ArrayList<>();
        for (int i = 0; i < topping.length; i++) {
            if (leftArr.contains(topping[i])) {
                left[i] = leftTemp;
                continue;
            }
            leftArr.add(topping[i]);
            left[i] = ++leftTemp;
        }

        int rightTemp = 0;
        ArrayList<Integer> rightArr = new ArrayList<>();
        for (int i = topping.length - 1; i >= 0; i--) {
            if (rightArr.contains(topping[i])) {
                right[i] = rightTemp;
                continue;
            }
            rightArr.add(topping[i]);
            right[i] = ++rightTemp;
            if (left[i] != 0 && left[i] < right[i]) break;
        }


        int answer = 0;
        for (int i = 0; i < topping.length - 1; i++) {
            if (left[i] == right[i + 1]) {
                answer++;
                if (left[i + 1] != right[i + 2]) {
                    break;
                } else {
                    answer++;
                    i++;
                }
            }
        }
        return answer;
    } */
