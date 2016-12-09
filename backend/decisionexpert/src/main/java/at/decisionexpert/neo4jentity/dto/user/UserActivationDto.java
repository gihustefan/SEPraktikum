package at.decisionexpert.neo4jentity.dto.user;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class UserActivationDto {
	//new dateLocked
	long dateLocked;
	//new dateActivated
	long dateActivated;
	//defines if changes are made
	boolean effectivChange;
	public long getDateLocked() {
		return dateLocked;
	}
	public void setDateLocked(long dateLocked) {
		this.dateLocked = dateLocked;
	}
	public UserActivationDto() {
		super();
	}
	public boolean isEffectivChange() {
		return effectivChange;
	}
	public void setEffectivChange(boolean effectivChange) {
		this.effectivChange = effectivChange;
	}
	public long getDateActivated() {
		return dateActivated;
	}
	public void setDateActivated(long dateActivated) {
		this.dateActivated = dateActivated;
	}
	
	
	
	
}
