package tw.com.phctw.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper<Student>{

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
		//student.setSid(rs.getString("sid"));
		
		return student;
	}

	
	
}
