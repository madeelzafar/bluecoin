package au.com.ibm.bluecoin.web.forms.ui;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.ibm.bluecoin.config.ConfigUtil;
import au.com.ibm.bluecoin.model.relational.AppUser;
import au.com.ibm.bluecoin.model.relational.Team;
import au.com.ibm.bluecoin.model.relational.TeamLadderSummaryItem;
import au.com.ibm.bluecoin.model.relational.UserReward;
import au.com.ibm.bluecoin.scaffold.AbstractMaintenanceForm;
import au.com.ibm.bluecoin.scaffold.IService;
import au.com.ibm.bluecoin.service.ITeamSvc;
import au.com.ibm.bluecoin.service.IUserRewardSvc;
import au.com.ibm.bluecoin.service.IUserSvc;
import au.com.ibm.bluecoin.utils.Role;
import au.com.ibm.bluecoin.utils.ValidationUtils;
import au.com.ibm.bluecoin.web.forms.LoginForm;
import au.com.ibm.bluecoin.web.forms.TrophyBean;

@ManagedBean
@SessionScoped

public class UserForm extends AbstractMaintenanceForm<String, AppUser> {

	private static final long serialVersionUID = 8158434638931310723L;

	private static final Logger logger = LoggerFactory.getLogger(UserForm.class);

	@EJB
	private IUserSvc userSvc;
	
	public IUserSvc getUserSvc() {
		return userSvc;
	}

	public void setUserSvc(IUserSvc userSvc) {
		this.userSvc = userSvc;
	}
	
	@EJB
	private ITeamSvc teamSvc;
	public ITeamSvc getTeamSvc() {
		return teamSvc;
	}

	public void setUserSvc(ITeamSvc teamSvc) {
		this.teamSvc = teamSvc;
	}
	
	private List<Team> teams;
	
	
	private AppUser user;
	

	@Override
	public AppUser getNewOne() {
		return new AppUser();
	}

	@Override
	public List<AppUser> getDefaultSearchResults() {
		return userSvc.getAll();
	}

	@Override
	public String getEntityBusinessName() {
		return "User Account";
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public IService getService() {
		return userSvc;
	}

	
	@Override
	public void executeBeforeSave() {
		// Set the default password unless specified
		
	}
	

	@Override
	public void executeAfterSave() {
	

	}

	@Override
	public boolean canNewEntityEditExisting() {
		// We dont want to touch existing users
		return false;
	}

	
	/**
	 * @return the user
	 */
	public AppUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(AppUser user) {
		this.user = user;
	}
	
	
	public void update()
	{
		
		
		getUserSvc().update(getUser());
	}

	/**
	 * @return the teams
	 */
	public List<Team> getTeams() {
		
		teams = getTeamSvc().getAll();
		return teams;
	}

	/**
	 * @param teams the teams to set
	 */
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	


}
