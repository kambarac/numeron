import java.util.Random;
import java.util.Scanner;

public class numeron {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		final int n = 3;
		int[] number = new int[n];
		int[] answer = new int[n];

		int i, j = 0;
		int eat;
		int bite = 0;
		int cnt = 0; //手数カウント用
		int range = 1;

		for (i = 0; i < n; i++) { //入力範囲の最大値
			range *= 10;
		}
		range -= 1;

		int lrange = 0;
		for(i = 1;i < n;i++) {
			lrange +=i;
			if(i != n - 1)
			lrange *=10;
		}
		Random rand = new Random();

		System.out.println("「" + n + "」" + "桁のぬめろんを開始します。\n\n～ルール説明～\n" + n
				+ "桁の数字を当ててください。\n※桁違いで同じ数字は使いません[例)×110 ]\n同じ数字だと「1BITE」\n位置と数字が同じだと「1EAT」加算されます。\nその数字を頼りに正解の数字を探してください。\n");
		System.out.println(
				"__＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿\n| 例：入力「1 2 3」、答え「3 2 1」         |\n| 2BITE(数字同じ)、1EAT(数字と位置が同じ)  |\n￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");

		for (i = 0; i < n; i++) { //答え作る
			answer[i] = rand.nextInt(10);

			if (i > 0) {
				for (int rep = 0; rep < i; rep++) { //数字同じ時用再抽選
					while (answer[i] == answer[rep]) {
						answer[i] = rand.nextInt(10);
						rep = 0;
					}
				}
			}
		}
		/*答え表示↓
				for (i = 0; i < n; i++) {
					System.out.print(answer[i]);
				}
		*/
		System.out.println("始めます\n全て違う数字で");
		int num;

		do {
			do {
				System.out.println(n + "桁の数字を入力してください☟");
				num = stdIn.nextInt(); //数字入力

				if (num < lrange|| num > range) {
					System.out.println(n + "桁の数字以外を入力しないでください。");
					continue;
				}
				for (i = n - 1; i >= 0; i--) { //配列に組み込む
					number[i] = num % 10;
					num /= 10;
				}
				Examination: for (i = 0; i < n; i++) {
					for (j = 0; j < n; j++) {
						if (number[i] == number[j]) {
							if (i == j)
								continue;
							System.out.println("全て違う数字を使ってください。");
							break Examination;
						}
					}
				}

			} while (i != n && num < lrange || num > range);

			eat = 0;
			bite = 0;

			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					if (answer[i] == number[j]) //数字の判定
						bite++;
				}
				if (answer[i] == number[i]) //数字と位置の判定
					eat++;
			}

			cnt++; //手数カウント

			if (bite < n)
				System.out.println((bite - eat) + "BITE、" + eat + "EATです。\n");
		} while (bite < n);

		System.out.println("せいかいです。\n答えは");
		for (i = 0; i < n; i++) {
			System.out.print(answer[i]);
		}
		System.out.print("、かかった手数は" + cnt + "回でした。\nお疲れ様です。");
	}

}
