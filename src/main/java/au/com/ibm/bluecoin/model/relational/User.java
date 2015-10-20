package au.com.ibm.bluecoin.model.relational;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import au.com.ibm.bluecoin.scaffold.IMasterPersistentEntity;

@Entity
public class User implements IMasterPersistentEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5524111697909781319L;

	@Id
	private Long serialNumber;
	
	private String firstName;
	
	private String surname;
	
	private String email;	
	
	@OneToOne
	private Profile profile;
	
	public Long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Profile getProfile() {
		return profile;
	}
		
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public String getSearchResultInfo() {
		return null;
	}

	@Override
	public Long getId() {
		return getSerialNumber();
	}

}
