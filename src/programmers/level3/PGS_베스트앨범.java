package programmers.level3;

// genresArr에 새로 접하는 장르 순서대로 담으면서, sumAndMax2에도 (합계, 최대값1, 최대값2, 최대값1인덱스, 최대값2인덱스)의 정보를 담음.
// 합계 내림차순으로 sumAndMax2 정렬.

import java.util.ArrayList;
import java.util.Comparator;

public class PGS_베스트앨범 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"classic", "pop", "classic", "classic", "pop", "aaa"}, new int[]{500, 600, 150, 800, 2500, 2000}));
    }
    public static int[] solution(String[] genres, int[] plays) {

        ArrayList<String> genresArr = new ArrayList<>();
        ArrayList<int[]> sumAndMax2 = new ArrayList<>();

        for (int i = 0; i < genres.length; i++) {
            if(!genresArr.contains(genres[i])) {
                genresArr.add(genres[i]);
                sumAndMax2.add(new int[]{plays[i], plays[i], -1, i, -1});
                continue;
            }

            sumAndMax2.get(genresArr.indexOf(genres[i]))[0] += plays[i];

            if (sumAndMax2.get(genresArr.indexOf(genres[i]))[2] < plays[i]) {
                sumAndMax2.get(genresArr.indexOf(genres[i]))[2] = plays[i];
                sumAndMax2.get(genresArr.indexOf(genres[i]))[4] = i;
                if (sumAndMax2.get(genresArr.indexOf(genres[i]))[1] < plays[i]) {
                    sumAndMax2.get(genresArr.indexOf(genres[i]))[2] = sumAndMax2.get(genresArr.indexOf(genres[i]))[1];
                    sumAndMax2.get(genresArr.indexOf(genres[i]))[4] = sumAndMax2.get(genresArr.indexOf(genres[i]))[3];
                    sumAndMax2.get(genresArr.indexOf(genres[i]))[1] = plays[i];
                    sumAndMax2.get(genresArr.indexOf(genres[i]))[3] = i;
                }
            }
        }

        sumAndMax2.sort(Comparator.comparing((int[] arr) -> arr[0]).reversed());

        int count = 0;
        for (int i = 0; i < sumAndMax2.size(); i++) {
            count++;
            if (sumAndMax2.get(i)[2] != -1) count++;
        }

        int[] answer = new int[count];

        int index = 0;
        for (int i = 0; i < sumAndMax2.size(); i++) {
            answer[index++] = sumAndMax2.get(i)[3];
            if (sumAndMax2.get(i)[2] != -1) answer[index++] = sumAndMax2.get(i)[4];
        }

        return answer;
    }
}