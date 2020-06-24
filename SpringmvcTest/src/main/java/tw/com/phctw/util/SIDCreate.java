package tw.com.phctw.util;

import java.util.Random;


public class SIDCreate {
	static String checkHead = "ABCDEFGHJKLMNPQRSTUVWXYZIO";

	public static void main(String[] args) {
		// 身分證產生
		System.out.println(new SIDCreate().idCardProduce());
		
	}

	public String idCardProduce() {
		Random r = new Random();
		String s = "";
		int checknum = 0;

		// 產生第一個英文字母
		int t = (r.nextInt(26) + 65);
		s += (char) t;
		t = checkHead.indexOf((char) t) + 10;
		checknum += t / 10;
		checknum += (t % 10) * 9;

		// 第2個數字 (1~2)
		t = r.nextInt(2) + 1;
		s += Integer.toString(t);
		checknum += t * 8;

		// 後8碼 [2...8]
		for (int i = 2; i < 9; i++) {
			t = r.nextInt(10);
			s += Integer.toString(t);
			checknum += t * (9 - i);
		}

		// 驗證
		checknum = (10 - ((checknum) % 10)) % 10;
		
		s += Integer.toString(checknum);
		//System.out.println(s);
		
		return s;
	}
}
