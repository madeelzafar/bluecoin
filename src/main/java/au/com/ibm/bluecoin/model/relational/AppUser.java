package au.com.ibm.bluecoin.model.relational;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import au.com.ibm.bluecoin.scaffold.AbstractMasterEntity;
import au.com.ibm.bluecoin.utils.Role;

@Entity
public class AppUser extends AbstractMasterEntity<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5548325782346547585L;

	public static final String DEFAULT_PASSWORD = "password";

	@Id
	String login;

	String password;

	String roles;
	
	String mobile;
	
	String email;
	
	private Team team;
	

	@Version
	private int version;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String getSearchResultInfo() {
		return null;
	}

	@Override
	public String getId() {
		return getLogin();
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public void addRole(String role) {
		if (roles == null) {
			roles = "";
		}
		if (!this.roles.contains(role)) {
			roles += roles.isEmpty() ? role : "," + role;
		}
	}

	public String getRolesDisplayString() {
		String resultRoles = "";
		if (roles != null) {
			String[] results = roles.split(",");
			for (String role : results) {
				resultRoles += resultRoles.isEmpty() ? Role.getDisplayValue(role) : ", " + Role.getDisplayValue(role);
			}
		}
		return resultRoles;
	}

	public List<String> getRolesList() {
		List<String> resultRoles = new ArrayList<String>();
		if (roles != null) {
			String[] results = roles.split(",");
			for (String role : results) {
				resultRoles.add(Role.getDisplayValue(role));
			}
		}
		return resultRoles;
	}

	public String[] getSelectedRolesList() {
		if (roles != null) {
			String[] results = roles.split(",");
			String[] returnVal = new String[results.length];
			for (int i = 0; i < results.length; i++) {
				returnVal[i] = Role.getDisplayValue(results[i]);
				System.out.println(returnVal[i]);
			}
			return returnVal;
		}
		return null;
	}

	public void setSelectedRolesList(String[] results) {
		resetRole();
		if (results != null) {
			for (String role : results) {
				addRole(Role.getValue(role));
			}
		}
		System.out.println(roles);
	}

	private void resetRole() {
		setRoles("");
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

}
