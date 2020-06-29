package tw.com.phctw.util;

import java.util.Random;

public class VerificationMail {
	public void send(String toAddr) {
		String subject = "Account Verification";
		String content = "Dear 顧客您好,您的驗證碼是 : " + veriCode()
				+ "<p /><a href='https://tw.yahoo.com/'>點選網址返回登入</a>";
		new SendMail().sendMail(toAddr,subject, content);
	}
	
	private String veriCode() {
		Random random = new Random();
		String code = String.format("%06d", random.nextInt(1000000));
		
		return code;
	}
	
	public static void main(String[] args) {
		//new VerificationMail().send();
		
		System.out.println(new VerificationMail().veriCode());
	}
}
