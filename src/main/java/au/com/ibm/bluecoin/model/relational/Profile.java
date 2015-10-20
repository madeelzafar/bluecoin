package au.com.ibm.bluecoin.model.relational;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;

import au.com.ibm.bluecoin.scaffold.AbstractMasterEntity;

@Entity
@IdClass(ProfilePk.class)
public class Profile extends AbstractMasterEntity<ProfilePk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8903572900617572889L;
	
	@Id
	@OneToOne
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String getSearchResultInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfilePk getId() {
		return new ProfilePk(getUser().getId());
	}
}
