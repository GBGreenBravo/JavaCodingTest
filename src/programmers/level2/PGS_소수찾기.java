package programmers.level2;

// 소수 구하는 함수는 쉬움.
// 백트래킹으로 순열 구하는 방식 기억해놓기!!

import java.util.ArrayList;

public class PGS_소수찾기 {
    static ArrayList<Integer> arr = new ArrayList<>();
    static boolean[] check;

    public int solution(String numbers) {

        check = new boolean[numbers.length()];

        for (int i = 0; i < numbers.length(); i++) {
            dfs(numbers, "", i + 1);
        }

        int  answer = 0;

        for (int i : arr) {
            if (isPrime(i)) answer++;
        }

        return answer;
    }

    private void dfs(String str, String now, int count) {
        if (count == now.length()) {
            int temp = Integer.parseInt(now);
            if (!arr.contains(temp)) arr.add(temp);
        }
        else {
            for (int i = 0; i < str.length(); i++) {
                if(!check[i]) {
                    check[i] = true;
                    dfs(str, now + str.charAt(i), count);
                    check[i] = false;
                }
            }
        }
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}

// 초기 정답
/*public int solution(String numbers) {
    int max = 10000000;
    int[] all = new int[max];

    for (int i = 2; i < max/2; i++) {
        if (all[i] != 0) continue;
        for (int j = 2; i * j < max; j++) {
            all[i * j] = 1;
        }
    }

    Set<Integer> ss = new HashSet<>();
    for (int i = 2; i < max; i++) {
        if (all[i] == 0) ss.add(i);
    }

    ArrayList<ArrayList<Integer>> possible = getPossible(numbers);

    Set<Integer> answer = new HashSet<>();

    for (ArrayList<Integer> arr : possible) {
        String temp = "";
        for (Integer i : arr) {
            temp += String.valueOf(numbers.charAt(i));
        }
        if (ss.contains(Integer.parseInt(temp))) answer.add(Integer.parseInt(temp));
    }

    return answer.size();
}

    private ArrayList<ArrayList<Integer>> getPossible(String numbers) {
        ArrayList<ArrayList<Integer>> possible = new ArrayList<>();
        for (int i = 0; i < numbers.length(); i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(i);
            possible.add(temp);
        }
        int count = 0;
        while (count < numbers.length()) {
            count++;
            ArrayList<ArrayList<Integer>> temp = new ArrayList<>();

            for (ArrayList<Integer> arr : possible) {
                for (int i = 0; i < numbers.length(); i++) {
                    if (!arr.contains(i)) {
                        ArrayList<Integer> tempp = new ArrayList<>(arr);
                        tempp.add(i);
                        temp.add(tempp);
                    }
                }
            }
            possible.addAll(temp);
        }
        return possible;
    }*/

// 메서드 분리 & 소수 검증 메서드 수정
/*
public class PGS_소수찾기 {
    public int solution(String numbers) {

        ArrayList<ArrayList<Integer>> possible = getPossible(numbers);

        Set<Integer> answer = new HashSet<>();

        for (ArrayList<Integer> arr : possible) {
            String temp = "";
            for (Integer i : arr) {
                temp += String.valueOf(numbers.charAt(i));
            }
            if (isPrime(Integer.parseInt(temp))) answer.add(Integer.parseInt(temp));
        }

        return answer.size();
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private ArrayList<ArrayList<Integer>> getPossible(String numbers) {
        ArrayList<ArrayList<Integer>> possible = new ArrayList<>();
        for (int i = 0; i < numbers.length(); i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(i);
            possible.add(temp);
        }
        int count = 0;
        while (count < numbers.length()) {
            count++;
            ArrayList<ArrayList<Integer>> temp = new ArrayList<>();

            for (ArrayList<Integer> arr : possible) {
                for (int i = 0; i < numbers.length(); i++) {
                    if (!arr.contains(i)) {
                        ArrayList<Integer> tempp = new ArrayList<>(arr);
                        tempp.add(i);
                        temp.add(tempp);
                    }
                }
            }
            possible.addAll(temp);
        }
        return possible;
    }
}*/
