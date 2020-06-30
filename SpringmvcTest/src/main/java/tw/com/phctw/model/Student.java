package tw.com.phctw.model;

import java.sql.Date;

//table:STUDENT3
public class Student {
	private String sno;
	private String sname;
	private Date sbday;
	private Integer ssex;
	private String smail;
	private String sacc;
	private String spwd;
	private String confirm;

	public Student() {
		super();
	}

	public Student(String sno, String sname, Date sbday, Integer ssex, String smail, String sacc, String spwd,
			String confirm) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.sbday = sbday;
		this.ssex = ssex;
		this.smail = smail;
		this.sacc = sacc;
		this.spwd = spwd;
		this.confirm = confirm;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Date getSbday() {
		return sbday;
	}

	public void setSbday(Date sbday) {
		this.sbday = sbday;
	}

	public Integer getSsex() {
		return ssex;
	}

	public void setSsex(Integer ssex) {
		this.ssex = ssex;
	}

	public String getSacc() {
		return sacc;
	}

	public void setSacc(String sacc) {
		this.sacc = sacc;
	}

	public String getSmail() {
		return smail;
	}

	public void setSmail(String smail) {
		this.smail = smail;
	}

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	@Override
	public String toString() {
		return "Student [sno=" + sno + ", sname=" + sname + ", sbday=" + sbday + ", ssex=" + ssex + ", smail=" + smail
				+ ", sacc=" + sacc + ", spwd=" + spwd + ", confirm=" + confirm + "]";
	}

}
