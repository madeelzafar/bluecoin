package au.com.ibm.bluecoin.web.forms;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import au.com.ibm.bluecoin.config.ConfigUtil;
import au.com.ibm.bluecoin.model.relational.AppUser;
import au.com.ibm.bluecoin.model.relational.Team;
import au.com.ibm.bluecoin.scaffold.AbstractMaintenanceForm;
import au.com.ibm.bluecoin.scaffold.IService;
import au.com.ibm.bluecoin.service.ITeamSvc;
import au.com.ibm.bluecoin.service.IUserSvc;
import au.com.ibm.bluecoin.utils.Role;
import au.com.ibm.bluecoin.utils.ValidationUtils;

@ManagedBean
public class TeamForm extends AbstractMaintenanceForm<String, Team> {

	private String name;
	

	
	@EJB
	private ITeamSvc teamSvc;

	public ITeamSvc getTeamSvc() {
		return teamSvc;
	}

	public void setUserSvc(ITeamSvc teamSvc) {
		this.teamSvc = teamSvc;
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	public String createTeam() throws ServletException, IOException {
		try {

			System.out.println("Creating team....");
			
			Team team = new Team();
			team.setName(getName());
			getTeamSvc().create(team);
			System.out.println(getTeamSvc().getById(name));

			getSessionModel().reset();

		} catch (Exception ex) {
			// log.equals(ex.getMessage());
			// ConfigUtil.addMessage("Login Failed: " + ex.getMessage());
			ex.printStackTrace();
			String message = ex.getLocalizedMessage();
			if (message == null) {
				message = "Unable to create Team";
			}
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "Unable to create team: " + message));
			return null;
		}

		return ConfigUtil.getSavedUrl() + "?faces-redirect=true";
	}

	
	

	@Override
	public Team getNewOne() {
		return new Team();
	}

	@Override
	public List<Team> getDefaultSearchResults() {
		return getTeamSvc().getAll();
	}

	@Override
	public String getEntityBusinessName() {
		return "Team";
	}

	@Override
	public IService getService() {
		return getTeamSvc();
	}



}
