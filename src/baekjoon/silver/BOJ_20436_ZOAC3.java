package baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_20436_ZOAC3 {
	
	static HashMap<Character, int[]> dict;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		dict = new HashMap<>();
		dict.put('q', new int[]{0, 0});
		dict.put('w', new int[]{0, 1});
		dict.put('e', new int[]{0, 2});
		dict.put('r', new int[]{0, 3});
		dict.put('t', new int[]{0, 4});
		dict.put('y', new int[]{0, 5});
		dict.put('u', new int[]{0, 6});
		dict.put('i', new int[]{0, 7});
		dict.put('o', new int[]{0, 8});
		dict.put('p', new int[]{0, 9});
		
		dict.put('a', new int[]{1, 0});
		dict.put('s', new int[]{1, 1});
		dict.put('d', new int[]{1, 2});
		dict.put('f', new int[]{1, 3});
		dict.put('g', new int[]{1, 4});
		dict.put('h', new int[]{1, 5});
		dict.put('j', new int[]{1, 6});
		dict.put('k', new int[]{1, 7});
		dict.put('l', new int[]{1, 8});
		
		dict.put('z', new int[]{2, 0});
		dict.put('x', new int[]{2, 1});
		dict.put('c', new int[]{2, 2});
		dict.put('v', new int[]{2, 3});
		dict.put('b', new int[]{2, 4});
		dict.put('n', new int[]{2, 5});
		dict.put('m', new int[]{2, 6});
		
		char left = st.nextToken().charAt(0);
		char right = st.nextToken().charAt(0);
		HashSet<Character> rights = new HashSet<>();
		char[] right_alphabets = {'y', 'u', 'i', 'o', 'p', 'h', 'j', 'k', 'l', 'b', 'n', 'm'}; 
		for (char r: right_alphabets) {
			rights.add(r);
		}
		
		int answer = 0;
		st = new StringTokenizer(br.readLine());
		
		for (char word: st.nextToken().toCharArray()) {
			if (rights.contains(word)) {
				answer += calculateDistance(right, word);
				right = word;
			} else {
				answer += calculateDistance(left, word);
				left = word;
			}
			answer++;
		}
		System.out.println(answer);
	}
	
	private static int calculateDistance(char a, char b) {
		return Math.abs(dict.get(a)[0] - dict.get(b)[0]) + Math.abs(dict.get(a)[1] - dict.get(b)[1]);
	}
	
}
