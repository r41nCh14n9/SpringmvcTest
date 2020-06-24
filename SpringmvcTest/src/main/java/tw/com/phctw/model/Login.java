package tw.com.phctw.model;

public class Login {
	private String sacc;
	private String spwd;

	public Login() {
		super();
	}

	public Login(String sacc, String spwd) {
		super();
		this.sacc = sacc;
		this.spwd = spwd;
	}

	public String getSacc() {
		return sacc;
	}

	public void setSacc(String sacc) {
		this.sacc = sacc;
	}

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	@Override
	public String toString() {
		return "Login [sacc=" + sacc + ", spwd=" + spwd + "]";
	}

}
