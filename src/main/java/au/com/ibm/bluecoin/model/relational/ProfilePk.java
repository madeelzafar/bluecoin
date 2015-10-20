package au.com.ibm.bluecoin.model.relational;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;

public class ProfilePk implements Serializable {

	private static final long serialVersionUID = 4133476019796534857L;

	private Long user;
	
	public ProfilePk() {
		// TODO Auto-generated constructor stub
	}
	
	public ProfilePk(Long pUser) {
		super();
		this.user = pUser;
	}

	public Long getUser() {
		return user;
	}
	
	@Override
	 public boolean equals(Object obj) {
		   if (obj == null) { return false; }
		   if (obj == this) { return true; }
		   if (obj.getClass() != getClass()) {
		     return false;
		   }
		   ProfilePk rhs = (ProfilePk) obj;
		   return new EqualsBuilder()
		                 .appendSuper(super.equals(obj))
		                 .append(user, rhs.user)
		                 .isEquals();
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
