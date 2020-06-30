package tw.com.phctw.util;

import java.util.Random;

import org.springframework.stereotype.Component;

import tw.com.phctw.model.Student;

@Component
public class VerificationMail {
	public void send(String toAddr, String veriCode) {
		String subject = "Account Verification";
		String content = "Dear 顧客您好,您的驗證碼是 : " + veriCode
				+ "<p /><a href='http://localhost:8080/SpringmvcTest/student/"+veriCode+"'>點選網址驗證帳號</a>";
		new SendMail().sendMail(toAddr,subject, content);
	}
	
	public String veriCode() {
		String code = "";
		Random r = new Random();
		while (code.length()<6) {
			switch (r.nextInt(3)) {
			case 0://大寫英文
				code += (char)(r.nextInt(26)+65);
				break;
			case 1://小寫英文
				code += (char)(r.nextInt(26)+97);
				break;
			case 2://數字
				code += r.nextInt(10)+"";
				break;
			}
		}
		
		return code;
	}
	
	public static void main(String[] args) {
		//new VerificationMail().send();
		
		System.out.println(new VerificationMail().veriCode());
	}
}
