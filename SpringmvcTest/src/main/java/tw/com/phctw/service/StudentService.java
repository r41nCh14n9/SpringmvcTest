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

@Service
public class StudentService {
	
	
	@Autowired
	private StudentDaoImpl dao;
	
	@Autowired
	private ForgetPwdMail fpm;

	public StudentDaoImpl getStudentDaoImpl() {
		return dao;
	}

	//login
	public Student validateStudent(Login login) {
		Student student = dao.getStudentForLogin(login);
		if (student!=null) {
			System.out.println("account exist.");
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
		System.out.println("student : " + student);
		
		Integer max = Integer.parseInt(dao.findMaxId().split("A")[1]);
		student.setSno("A" + (max + 1));
		student.setSpwd(getMD5Endocing(student.getSpwd()));
		System.out.println("student : " + student);
		
		return dao.insertStudent(student);
	}
	
	//forgetPwd check
	public boolean checkForgotenStd(String sacc, String smail) {
		if(dao.getStudentBySaccAndSmail(sacc, smail) != null) {
			//System.out.println("not null");
			return true;
		}else {
			//System.out.println("null");
			return false;
		}
	}
	//reset Pwd
	public boolean resetPwd(Student student) {
		String newPwd = fpm.createPwd();
		String toAddr = student.getSmail();
		
		if(dao.updateStudentPwd(student, newPwd)) {
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
		String message = "123456";
//		System.out.println(new StudentService().getMD5Endocing(message));
	}
	
	
}
