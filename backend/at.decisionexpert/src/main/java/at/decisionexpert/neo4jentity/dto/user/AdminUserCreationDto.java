package at.decisionexpert.neo4jentity.dto.user;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class AdminUserCreationDto {
	
	private long id;
	private String username;
	private String email;
	private String password;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AdminUserCreationDto() {
		super();
	}
}
