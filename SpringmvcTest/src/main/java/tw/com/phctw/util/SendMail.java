package tw.com.phctw.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	// gmail
	private final String username = "ann1234555@gmail.com";
	private final String password = "kwektvfpqoqwbdgw"; // 授權碼, 不是 email 的密碼
	//private static final String to = "ann1234555@yahoo.com.tw";

	public void sendMail(String toAddr,String subject, String content) {
		// smpt 設定資訊
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		// 會話建立與 smtp 溝通
		Session session;
		session = Session.getInstance(prop, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// 建立 Email 實體
		Message message = new MimeMessage(session);

		// 發文者 一定會是你的 username
		InternetAddress ia;
		try {
			ia = new InternetAddress("from@gmail.com");
			//發信人顯示
			ia.setPersonal("PHCTW");
			message.setFrom(ia);

			// 受文者
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddr));

			// Email 抬頭
			message.setSubject(subject);

			// Email 內容 HTML
			//...

			message.setContent(content, "text/html;charset=utf-8");

			// 發送 email
			Transport.send(message);
			System.out.println("信件發送成功 !");

		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		String toAddr = "ann1234555@yahoo.com.tw";
		String subject = "測試郵件...";
		String content = "Dear 顧客您好," + "<p /><a href='https://tw.yahoo.com/'>我的 網址</a>"
				+ "<p /> Please do not spam my email!";
		new SendMail().sendMail(toAddr,subject, content);


	}

}
