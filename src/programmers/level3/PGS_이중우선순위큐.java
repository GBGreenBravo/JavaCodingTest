package programmers.level3;

import java.util.ArrayList;
import java.util.Comparator;

class PGS_이중우선순위큐 {

    public static void main(String[] args) {
        System.out.println(solution(new String[] {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"})[0] + " "+ solution(new String[] {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"})[1]);
//        System.out.println(solution(new String[] {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"})[0] + " "+ solution(new String[] {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"})[1]);
    }

    public static int[] solution(String[] operations) {

        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i < operations.length; i++) {
            if (operations[i].charAt(0) == 'I') {
                arr.add(Integer.parseInt(operations[i].substring(2)));
                arr.sort(Comparator.naturalOrder());
            } else {
                switch (operations[i].substring(2)) {
                    case "1" -> {
                        if (arr.isEmpty()) {
                            continue;
                        }
                        arr.remove(arr.size() - 1);
                    }
                    case "-1" -> {
                        if (arr.isEmpty()) {
                            continue;
                        }
                        arr.remove(0);
                    }
                }
            }
        }

        if (arr.isEmpty()) {
            return new int[]{0, 0};
        } else {
            return new int[]{arr.get(arr.size() - 1), arr.get(0)};
        }
    }
}
