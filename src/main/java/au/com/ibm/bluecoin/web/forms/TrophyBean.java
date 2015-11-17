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
import au.com.ibm.bluecoin.model.relational.UserReward;
import au.com.ibm.bluecoin.scaffold.AbstractMaintenanceForm;
import au.com.ibm.bluecoin.scaffold.IService;
import au.com.ibm.bluecoin.service.ITeamSvc;
import au.com.ibm.bluecoin.service.IUserRewardSvc;
import au.com.ibm.bluecoin.service.IUserSvc;
import au.com.ibm.bluecoin.service.RewardBean;
import au.com.ibm.bluecoin.utils.Role;
import au.com.ibm.bluecoin.utils.ValidationUtils;

@ManagedBean
@SessionScoped
public class TrophyBean  extends AbstractMaintenanceForm<String, UserReward> {

	
	
	private UserReward userReward;
	
	
	public static final String DEFAULT_USER = "admin";

	@EJB
	private IUserSvc userSvc;

	
	@EJB
	private ITeamSvc teamSvc;

	public ITeamSvc getTeamSvc() {
		return teamSvc;
	}

	public void setUserSvc(ITeamSvc teamSvc) {
		this.teamSvc = teamSvc;
	}
	
	@EJB
	private IUserRewardSvc userRewardSvc;

	public IUserRewardSvc getUserRewardSvc() {
		return userRewardSvc;
	}

	public void setUserSvc(IUserRewardSvc userRewardSvc) {
		this.userRewardSvc = userRewardSvc;
	}
	




	@Override
	public UserReward getNewOne() {
		return new UserReward();
	}

	@Override
	public List<UserReward> getDefaultSearchResults() {
		return getUserRewardSvc().getAll();
	}

	@Override
	public String getEntityBusinessName() {
		return "Login";
	}

	@Override
	public IService getService() {
		return getUserRewardSvc();
	}

	/**
	 * @return the userReward
	 */
	public UserReward getUserReward() {
		return userReward;
	}

	/**
	 * @param userReward the userReward to set
	 */
	public void setUserReward(UserReward userReward) {
		this.userReward = userReward;
	}
	
	public void setAsRead()
	{
		UserReward u = getUserRewardSvc().getById(userReward.getId());
		u.markAsRead();
		getUserRewardSvc().update(u);
		getSessionModel().setContent("/home.xhtml");
	}


}
