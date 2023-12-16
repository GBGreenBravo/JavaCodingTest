package programmers.level2;

// ArrayList로 대기중인트럭들(trucks)과 다리지나는트럭들(now)의 정보를 저장.
// 다리 지나는 트럭들의 무게 합을 구하면서, 트럭 하나씩 이동시키고, 다음에 통과되는 트럭 제거.
// (다리 감당 가능 무게 - 다리 지나는 트럭 무게 합)과 다음 대기 트럭 무게 비교해서 추가.
// 종료 조건: 다리 지나는 트럭도 없어야 하고, 대기 중인 트럭도 없어야 함.
// 다음 턴에 지날 트럭들을 미리 now에서 제거했기 때문에 return은 ++answer로 합니다.

import java.util.ArrayList;

public class PGS_다리를지나는트럭 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        ArrayList<int[]> trucks = new ArrayList<>();
        for (int truckWeight : truck_weights) {
            trucks.add(new int[]{truckWeight, bridge_length - 1});
        }
        ArrayList<int[]> now = new ArrayList<>();

        int answer = 0;

        while (!trucks.isEmpty() || !now.isEmpty()) {
            int sum = 0;
            for (int i = 0; i < now.size(); i++) {
                sum += now.get(i)[0];
                now.get(i)[1]--;
                if (now.get(i)[1] == 0) {
                    now.remove(i--);
                }
            }

            if (!trucks.isEmpty()) {
                if (weight - sum >= trucks.get(0)[0]) {
                    now.add(trucks.get(0));
                    trucks.remove(0);
                }
            }
            answer++;
        }


        return ++answer;
    }
}
