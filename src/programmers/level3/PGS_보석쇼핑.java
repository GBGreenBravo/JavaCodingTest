package programmers.level3;

// 15:38

// 우선 int target에 모든 보석의 종류 개수를 저장한다.
// 그러고 나서는 for문을 통해, gems의 index를 처음부터 하나씩 탐색하며,
    // HashSet temp에 현재 index에서 조회한 보석을 저장한다.
    // 만약 temp.size()가 target과 동일하다면,
        // temp를 리셋하고,
        // tempStart(temp가 저장하기 시작한 index)에서 현재까지 인덱스에서 앞 부분에 무의미한 index를 지운, 다이어트된 start index를 조회한다.
        // 위 결과의 i - tempStart 가 end - start보다 작으면, start와 end 갱신해준다.
    // 반복문 조회는 tempStart + 1 부터 다시 시작할 수 있도록, i = tempStart로 갱신.

// target 구할 때, new HashSet<>(Arrays.asList(gems)).size() 로 한 줄로 구할 수 있음.
// 그리고, Map<String, Integer>로 중복 점검하는 풀이가 조금 더 효율적으로 보임.

import java.util.HashSet;

class PGS_보석쇼핑 {
    public int[] solution(String[] gems) {

        HashSet<String> targetGems = new HashSet<>();
        for (String gem : gems) {
            targetGems.add(gem);
        }
        int target = targetGems.size();

        int start = 0;
        int end = gems.length - 1;
        HashSet<String> temp = new HashSet<>();
        int tempStart = 0;

        for (int i = 0; i < gems.length; i++) {
            temp.add(gems[i]);
            if (temp.size() == target) {
                temp.clear();
                tempStart = diet(gems, i, tempStart, target);
                if (i - tempStart < end - start) {
                    start = tempStart;
                    end = i;
                }
                i = tempStart;
            }
        }

        int[] answer = new int[]{start + 1, end + 1};
        return answer;
    }

    private int diet(String[] gems, int nowIndex, int tempStart, int target) {
        HashSet<String> dietSet = new HashSet<>();

        for (int i = nowIndex; i >= tempStart; i--) {
            dietSet.add(gems[i]);
            if (dietSet.size() == target) {
                return i;
            }
        }

        return tempStart;
    }
}