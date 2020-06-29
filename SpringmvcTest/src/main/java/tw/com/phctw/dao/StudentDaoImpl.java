package tw.com.phctw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tw.com.phctw.model.Login;
import tw.com.phctw.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao{
	private final String SQL_FIND_STUDENT = "select * from Student3 where sno = ?";
	private final String SQL_FIND_STUDENT_MAXSNO = "SELECT MAX(SNO) MAX FROM STUDENT3";
	private final String SQL_FIND_STUDENT_LOGIN = "select * from Student3 where sacc = ? and spwd = ?";
	private final String SQL_FIND_STUDENT_REG = "select * from Student3 where sacc = ?";
	private final String SQL_FIND_STUDENT_FOR = "select * from Student3 where sacc = ? and smail = ?";
//	private final String SQL_DELETE_Student = "delete from Student where id = ?";
//	private final String SQL_UPDATE_Student = "update Student3 set first_name = ?, last_name = ?, age  = ? where id = ?";
	private final String SQL_UPDATE_Student_FOR = "update Student3 set spwd = ? where sno = ?";
	
	private final String SQL_GET_ALL = "select * from Student3";
	private final String SQL_INSERT_STUDENT = "insert into Student3(SNO, SNAME, SBDAY, SSEX, SMAIL, SACC, SPWD) VALUES (?,?,?,?,?,?,?)";
	
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public Student getStudentBySno(String sno) {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_STUDENT, new StudentMapper(),sno);
		} catch (Exception e) {
			return null;
		}
		
	}

	//login
	public Student getStudentForLogin(Login login) {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_STUDENT_LOGIN, new StudentMapper(), login.getSacc(), login.getSpwd());
		} catch (Exception e) {
			return null;
		}
		
	}
	
	//register
	public Student getStudentBySacc(String sacc) {
		Student student = null;
		try {
			student = jdbcTemplate.queryForObject(SQL_FIND_STUDENT_REG, new StudentMapper(),sacc);
			
		} catch (Exception e) {
			System.out.println(e);
			//System.out.println(student);
			return null;
		}
		return student;
	}
	
	@Override
	public List<Student> getAllStudents() {
		try {
			return jdbcTemplate.query(SQL_GET_ALL, new StudentMapper());
		} catch (Exception e) {
			return null;
		}
	}

	//forget password
	public Student getStudentBySaccAndSmail(String sacc, String smail) {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_STUDENT_FOR, new StudentMapper(),sacc,smail);
		} catch (Exception e) {
			return null;
		}
	}
	//reset password
	public boolean updateStudentPwd(Student student, String newPwd) {
		try {
			jdbcTemplate.update(SQL_UPDATE_Student_FOR, newPwd, student.getSno());
		} catch (Exception e) {
			System.out.println("update fail");
			return false;
		}
		System.out.println("update successfull");
		return true;
	}
	
	
	@Override
	public boolean deleteStudent(Student student) {
		
		return false;
	}
	@Override
	public boolean updateStudent(Student student) {
		return false;
	}

	@Override
	public boolean insertStudent(Student student) {
		try {
			jdbcTemplate.update(SQL_INSERT_STUDENT, student.getSno(), student.getSname(), student.getSbday(),
					student.getSsex(), student.getSmail(), student.getSacc(), student.getSpwd());
		} catch (Exception e) {
			System.out.println("insert fail");
			return false;
		}
		System.out.println("insert success");
		return true;
	}

	public String findMaxId() {
		String id = "";
		try {
			Student student = jdbcTemplate.queryForObject(SQL_FIND_STUDENT_MAXSNO, new StudentMapper());
			id += student.getSno();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	
}

class StudentMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student = new Student();
		student.setSno(rs.getString("sno"));
		student.setSname(rs.getString("sname"));
		student.setSsex(Integer.valueOf(rs.getString("ssex")));
		// student.setSbday(sdf.parse(rs.getString("sbday")));
		student.setSbday(rs.getDate("sbday"));
		student.setSmail(rs.getString("smail"));
		student.setSpwd(rs.getString("spwd"));
		student.setSacc(rs.getString("sacc"));

		return student;
	}

}
