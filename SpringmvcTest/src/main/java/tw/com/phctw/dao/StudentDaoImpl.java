package tw.com.phctw.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tw.com.phctw.model.Student;
import tw.com.phctw.model.StudentMapper;

@Repository
public class StudentDaoImpl implements StudentDao{
	private final String SQL_FIND_Student = "select * from Student where sno = ?";
//	private final String SQL_DELETE_Student = "delete from Student where id = ?";
//	private final String SQL_UPDATE_Student = "update Student set first_name = ?, last_name = ?, age  = ? where id = ?";
	private final String SQL_GET_ALL = "select * from Student";
//	private final String SQL_INSERT_Student = "insert into Student(id, first_name, last_name, age) values(?,?,?,?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public Student getStudentBySno(String sno) {
		return jdbcTemplate.queryForObject(SQL_FIND_Student, new StudentMapper(),sno);
	}

	@Override
	public List<Student> getAllStudents() {
		return jdbcTemplate.query(SQL_GET_ALL, new StudentMapper());
	}

	@Override
	public boolean deleteStudent(Student student) {
		
		return false;
	}

	@Override
	public boolean updateStudent(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createStudent(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

}
