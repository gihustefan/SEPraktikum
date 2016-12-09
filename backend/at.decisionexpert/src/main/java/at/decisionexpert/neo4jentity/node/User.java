package at.decisionexpert.neo4jentity.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class User extends Node implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private String originalUsername;
	private String email;
	@JsonIgnore
	private String password;
	private String firstName;
	private String lastName;
	private String pictureUrl;
	private String about;
	private String address;
	private long dateLocked;
	private long dateActivated;
	@JsonIgnore
	private String mailActivationToken;
	
	@JsonIgnore
	@Relationship(type = "HAS_AUTHORITY", direction = Relationship.OUTGOING)
	private Set<UserAuthority> authorities = new HashSet<>(0);
	
	@Relationship(type = "WORKS_IN_COMPANY", direction = Relationship.OUTGOING)
	private Company company;
	
	@Relationship(type = "LIVES_IN_CITY", direction = Relationship.OUTGOING)
	private City city;
	
	@Relationship(type = "HAS_POSITION", direction = Relationship.OUTGOING)
	private Position position;

	public User() {
		dateActivated =new Date().getTime();
		// TODO Auto-generated constructor stub
	}

	public String getOriginalUsername() {
		return originalUsername;
	}


	public void setOriginalUsername(String originalUsername) {
		this.originalUsername = originalUsername;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getMailActivationToken() {
		return mailActivationToken;
	}

	public void setMailActivationToken(String mailActivationToken) {
		this.mailActivationToken = mailActivationToken;
	}
	
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

	public void addAuthority(UserAuthority userrole) {
		Assert.notNull(userrole);
		authorities.add(userrole);
	}
	
	public void removeAuthority(UserAuthority authority) {
		Assert.notNull(authority);
		authorities.remove(authority);
	}
	
	public void setAuthorities(Set<UserAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// Account cannot expire
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return dateLocked<dateActivated;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// Accounts credentials cannot expire
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return dateActivated>0;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public String getUsername() {
		return email;
	}
	
	public void setUsername(String username){
		this.email = username;
	}

}
