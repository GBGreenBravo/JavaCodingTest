package programmers.level2;

// 42:14
//
// 우선, people에 P의 좌표를 저장합니다.
// 그리고 people을 2개씩 비교하면서 규칙을 어기는 경우, correct = false;
// Math.abs()를 통해 distance(맨해튼 거리)를 구합니다.
// distance > 2 인 경우, continue;
// distance == 1 인 경우, correct = false;
// distance == 2 인 경우,
    // (0, +2) / (+2, 0) / (+1, +1) / (+1, -1)
    // 위의 경우에 각각 파티션 있는지 검사하고, 없으면 correct = false;
// people에 저장할 때, i 낮은 순, 그 다음으로 j 낮은 순으로 저장했기에, 맨해튼 거리가 2인 경우 8개 중, 4개만 확인하면 됩니다.

import java.util.ArrayList;

public class PGS_거리두기확인하기 {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int index = 0;

        for (String[] map : places) {
            ArrayList<int[]> people = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (map[i].charAt(j) == 'P') {
                        people.add(new int[]{i, j});
                    }
                }
            }

            boolean correct = true;
            for (int i = 0; i < people.size() - 1; i++) {
                for (int j = i + 1; j < people.size(); j ++) {
                    int distance = Math.abs(people.get(i)[0] - people.get(j)[0]) + Math.abs(people.get(i)[1] - people.get(j)[1]);
                    if (distance > 2) continue;
                    else if (distance == 1) correct = false;
                    else if (distance == 2) {
                        if (people.get(i)[0] == people.get(j)[0]) {
                            int middleJ = (people.get(i)[1] + people.get(j)[1]) / 2;
                            if (map[people.get(i)[0]].charAt(middleJ) != 'X') correct = false;
                        } else if (people.get(i)[1] == people.get(j)[1]) {
                            int middleI = (people.get(i)[0] + people.get(j)[0]) / 2;
                            if (map[middleI].charAt(people.get(j)[1]) != 'X') correct = false;
                        } else if (people.get(i)[0] + 1 == people.get(j)[0] && people.get(i)[1] + 1 == people.get(j)[1]) {
                            if (map[people.get(i)[0] + 1].charAt(people.get(i)[1]) != 'X' || map[people.get(i)[0]].charAt(people.get(i)[1] + 1) != 'X') correct = false;
                        } else if (people.get(i)[0] + 1 == people.get(j)[0] && people.get(i)[1] == people.get(j)[1] + 1) {
                            if (map[people.get(i)[0]].charAt(people.get(i)[1] - 1) != 'X' || map[people.get(i)[0] + 1].charAt(people.get(i)[1]) != 'X') correct = false;
                        }
                    }
                }
            }
            answer[index++] = (correct) ? 1 : 0;
        }

        return answer;
    }
}
