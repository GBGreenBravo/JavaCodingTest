package programmers.level4;

// 47:23
//
// +2하거나 +3함.
// index==0을 들르는 케이스와 index==1을 들르는 케이스로 나뉨.
// index==0을 들렀다면 마지막 들르면 안되지만, index==1을 들렀다면 마지막 들러도 됨.
// 그래서 이 케이스를 따로 나눠서 gone0와 gone1 각각에 저장하고 답 구함.
//
// 헤맸던 포인트는, gone[index]을 구할 때 반복문으로 차근차근히 Math.max() 계산할 생각을 못했다는 것.
// 그래서 진작에 해법 알고 있었음에도, 틀린 줄 알고 돌아갔음.
// (index, sum) 인자로 주고, 여러 thread 돌릴 필요 없이!
// index 작은 것부터 차차 하나의 thread로 반복문 돌리면 됨!

public class PGS_도둑질 {
    public int solution(int[] money) {
        int end = money.length - 1;
        int[] gone0 = new int[money.length];
        int[] gone1 = new int[money.length];

        gone0[0] = money[0];
        gone0[1] = money[1];
        gone0[2] = money[0] + money[2];

        gone1[0] = 0;
        gone1[1] = money[1];
        gone1[2] = money[2];

        for (int i = 3; i < end; i++) {
            gone0[i] = money[i] + Math.max(gone0[i - 2], gone0[i - 3]);
            gone1[i] = money[i] + Math.max(gone1[i - 2], gone1[i - 3]);
        }
        gone1[end] = money[end] + Math.max(gone1[end - 2], gone1[end - 3]);

        int answer0 = Math.max(gone0[end - 1], gone0[end - 2]);
        int answer1 = Math.max(gone1[end], gone1[end - 1]);

        return Math.max(answer0, answer1);
    }
}
