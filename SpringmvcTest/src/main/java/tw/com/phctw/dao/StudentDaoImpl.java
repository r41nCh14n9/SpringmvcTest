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
	private final String SQL_FIND_STUDENT_CONF = "select * from Student3 where confirm = ?";
	//	private final String SQL_DELETE_Student = "delete from Student where id = ?";
//	private final String SQL_UPDATE_Student = "update Student3 set first_name = ?, last_name = ?, age  = ? where id = ?";
	private final String SQL_UPDATE_Student_FOR = "update Student3 set spwd = ? where sno = ?";
	private final String SQL_UPDATE_Student_CONF = "update Student3 set confirm = ? where sno = ?";
	
	private final String SQL_GET_ALL = "select * from Student3";
	private final String SQL_INSERT_STUDENT = "insert into Student3(SNO, SNAME, SBDAY, SSEX, SMAIL, SACC, SPWD, CONFIRM) VALUES (?,?,?,?,?,?,?,?)";
	private final String SQL_CHECK_CONFIRM = "select confirm from student3 where sno = ?";
	
	
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
			Student student = jdbcTemplate.queryForObject(SQL_FIND_STUDENT_LOGIN, new StudentMapper(), login.getSacc(), login.getSpwd());
//			System.out.println("dao get : "+student);
			return student;
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
	
	//get confirm account
	public Student getStudentByConf(String confirm) {
		Student s = new Student();
		try {
			s = jdbcTemplate.queryForObject(SQL_FIND_STUDENT_CONF, new StudentMapper(), confirm);
			System.out.println(s);
		} catch (Exception e) {
			return null;
		}
		return s;
	}
	//update confirm code
	public boolean updateStudentConf(Student student, String conf) {
		try {
			jdbcTemplate.update(SQL_UPDATE_Student_CONF, conf, student.getSno());
		} catch (Exception e) {
			System.out.println("update fail");
			return false;
		}
		return true;
	}
	//get confirm code
	public String checkStudentConf(Student student) {
		String conf="";
		try {
			conf += jdbcTemplate.queryForObject(SQL_CHECK_CONFIRM, String.class,student.getSno());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conf;
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
					student.getSsex(), student.getSmail(), student.getSacc(), student.getSpwd(),student.getConfirm());
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
			id += jdbcTemplate.queryForObject(SQL_FIND_STUDENT_MAXSNO, String.class);
			System.out.println("sno = " + id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public static void main(String[] args) {
		StudentDaoImpl dao = new StudentDaoImpl();
		System.out.println(new StudentDaoImpl().findMaxId());
//		dao.getAllStudents().forEach(System.out::println);
		
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
		student.setConfirm(rs.getString("confirm"));

		return student;
	}

}
