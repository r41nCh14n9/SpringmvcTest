package tw.com.phctw.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.phctw.dao.StudentDaoImpl;
import tw.com.phctw.model.Login;
import tw.com.phctw.model.Student;
import tw.com.phctw.util.ForgetPwdMail;
import tw.com.phctw.util.VerificationMail;

@Service
public class StudentService {
	@Autowired
	private StudentDaoImpl dao;
	
	@Autowired
	private ForgetPwdMail fpm;
	
	@Autowired
	private VerificationMail vm;

	public StudentDaoImpl getStudentDaoImpl() {
		return dao;
	}

	//login
	public Student validateStudent(Login login) {
		login.setSpwd(getMD5Endocing(login.getSpwd()));
		Student student = dao.getStudentForLogin(login);
		if (student!=null) {
			System.out.println("account exist." );
		}else {
			System.out.println("account is invalid.");
		}
		return student;
	}
	
	//register check
	public boolean checkSaccExist(String sacc) {
		if(dao.getStudentBySacc(sacc)!=null) {
			//System.out.println("not null");
			return true;
		}else {
			//System.out.println("null");
			return false;
		}
	}
	//register
	public boolean register(Student student) {
		System.out.println(student);
		
		Integer max = Integer.parseInt(dao.findMaxId().split("A")[1]);
		System.out.println(max);
		
		String newSno = "A" + String.format("%06d", (max + 1));
		String encPwd = getMD5Endocing(student.getSpwd());
		student.setSno(newSno);
		student.setSpwd(encPwd);
		System.out.println(student);
		
		//send confirm mail
		String conf = vm.veriCode();
		student.setConfirm(conf);
		if(dao.insertStudent(student)) {
			System.out.println("學生資料建立成功，等待驗證...");
			vm.send(student.getSmail(), conf);
			return true;
		}else {
			System.out.println("學生資料建立失敗");
			return false;
		}
	}
	
	public boolean confirmAcc(Student student) {
		try {
			dao.updateStudentConf(student, "1");
			System.out.println("Confirm Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//forgetPwd check
	public Student checkForgotenStd(String sacc, String smail) {
		Student student = dao.getStudentBySaccAndSmail(sacc, smail);
		if (student!=null) {
			System.out.println("account exist.");
		}else {
			System.out.println("account is invalid.");
		}
		return student;
	}
	//reset Pwd
	public boolean resetPwd(Student student) {
		String newPwd = fpm.createPwd();
		String encPwd = getMD5Endocing(newPwd);
		String toAddr = student.getSmail();
		
		if(dao.updateStudentPwd(student, encPwd)) {
			fpm.send(toAddr, newPwd);
			return true;
		}else {
			return false;
		}
	}
	
 	public String getMD5Endocing(String message) {
		final StringBuffer buffer = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(message.getBytes());
			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; ++i) {
				final byte b = digest[i];
				final int value = Byte.toUnsignedInt(b);
				buffer.append(value < 16 ? "0" : "");
				buffer.append(Integer.toHexString(value));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return buffer.toString();
	}

	
	public static void main(String[] args) {
		String message = "12345";
		StudentService service = new StudentService();
		System.out.println(service.getMD5Endocing(message));
		
//		service.getStudentDaoImpl().getAllStudents().forEach(System.out::println);
		
	
	}
	
	
}
