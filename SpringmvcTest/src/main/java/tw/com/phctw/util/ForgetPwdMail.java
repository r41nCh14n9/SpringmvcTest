package tw.com.phctw.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class ForgetPwdMail {
	public void send(String toAddr, String newPwd) {
		String subject = "Forget Password";
		String content = "Dear 顧客您好,您的新密碼是 : " + newPwd
		+ "<p /><a href='http://localhost:8080/SpringmvcTest/'>點選網址返回登入</a>";
		new SendMail().sendMail(toAddr,subject, content);
	}
	
	public String createPwd() {
		String pwd = "";
		Random r = new Random();
		while (pwd.length()<6) {
			switch (r.nextInt(3)) {
			case 0://大寫英文
				pwd += (char)(r.nextInt(26)+65);
				break;
			case 1://小寫英文
				pwd += (char)(r.nextInt(26)+97);
				break;
			case 2://數字
				pwd += r.nextInt(10)+"";
				break;
			}
		}
		return pwd;
	}
	
	public static void main(String[] args) {
		String toAddr = "ann1234555@yahoo.com.tw";
//		System.out.println(new ForgetPwdMail().createPwd());
		ForgetPwdMail fpm = new ForgetPwdMail();
		fpm.send(toAddr, fpm.createPwd());
	}
}
