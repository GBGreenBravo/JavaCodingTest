package programmers.level2;

import java.util.ArrayList;

public class PGS_1차캐시 {
    public int solution(int cacheSize, String[] cities) {

        if (cacheSize == 0) return cities.length * 5;

        int answer = 0;

        ArrayList<String> arr = new ArrayList<>();

        for (String city : cities) {
            city = city.toUpperCase();

            if (arr.contains(city)) {
                arr.remove(arr.indexOf(city));
                arr.add(city);
                answer += 1;
            } else {
                if (arr.size() == cacheSize) arr.remove(0);
                arr.add(city);
                answer += 5;
            }
        }

        return answer;
    }
}
