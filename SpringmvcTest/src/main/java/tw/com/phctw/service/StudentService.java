package tw.com.phctw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.phctw.dao.StudentDaoImpl;

@Service
public class StudentService {
	
	@Autowired
	private StudentDaoImpl studentDaoImpl;
	
	public StudentDaoImpl getStudentDaoImpl() {
		return studentDaoImpl;
	}
	
	
	
}
