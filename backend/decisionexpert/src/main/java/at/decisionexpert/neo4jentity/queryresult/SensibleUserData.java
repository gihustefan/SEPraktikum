package at.decisionexpert.neo4jentity.queryresult;

import at.decisionexpert.neo4jentity.node.UserAuthority;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.Set;

@QueryResult
public class SensibleUserData {
	private long dateLocked;
	private long dateActivated;
	private String mailActivationToken;
	private String password;
	private String email;
	private Set<UserAuthority> authorities;
	
	public long getDateLocked() {
		return dateLocked;
	}
	public void setDateLocked(long dateLocked) {
		this.dateLocked = dateLocked;
	}
	public long getDateActivated() {
		return dateActivated;
	}
	public void setDateActivated(long dateActivated) {
		this.dateActivated = dateActivated;
	}
	public String getMailActivationToken() {
		return mailActivationToken;
	}
	public void setMailActivationToken(String mailActivationToken) {
		this.mailActivationToken = mailActivationToken;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<UserAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<UserAuthority> authorities) {
		this.authorities = authorities;
	}
	public SensibleUserData() {
		super();
		// TODO Auto-generated constructor stub
	}
}
