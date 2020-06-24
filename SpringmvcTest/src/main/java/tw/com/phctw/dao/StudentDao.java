package tw.com.phctw.dao;

import java.util.List;

import tw.com.phctw.model.Student;

public interface StudentDao {
	Student getStudentBySno(String sno);
	
	List<Student> getAllStudents();

	boolean deleteStudent(Student student);

	boolean updateStudent(Student student);

	boolean createStudent(Student student);
	
}
