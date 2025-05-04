package baekjoon.platinum;

// 20250504
// 25:08
// 1 / 2

// KMP 학습용으로, 이번만 템플릿 보고 이해하면서 코드 작성함.
// 입력받을 때 trim()써서, 한번 틀림.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1786_찾기 {
	
	private static String T, P;
	private static int[] table;
	private static List<Integer> answers = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		T = br.readLine();
		P = br.readLine();
		
		makeTable();
		KMP();
		
		if (answers.size() == 0) {
			System.out.println(0);
		} else {
			System.out.println(answers.size());
			for (int i: answers) {
				System.out.print(i + " ");
			}
		}
	}
	
	private static void makeTable() {
		table = new int[P.length()];
		
		int idx = 0;
		for (int i = 1; i < P.length(); i++) {
			while (idx > 0 && P.charAt(i) != P.charAt(idx)) {
				idx = table[idx - 1];
			}
			if (P.charAt(i) == P.charAt(idx)) {
				idx++;
				table[i] = idx;
			}
		}
	}
	
	private static void KMP() {
		int idx = 0;
		for (int i = 0; i < T.length(); i++) {
			while (idx > 0 && T.charAt(i) != P.charAt(idx)) {
				idx = table[idx - 1];
			}
			if (T.charAt(i) == P.charAt(idx)) {
				if (idx == P.length() - 1) {
					answers.add(i - P.length() + 2);
					idx = table[idx];
				} else {
					idx++;
				}
			}
		}
	}
	
}
