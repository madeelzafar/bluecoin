package au.com.ibm.bluecoin.web.forms;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
public class LoginForm extends AbstractMaintenanceForm<String, AppUser> {

	private String userName;
	private String password;

	private String loggedUser;
	private String loggedRole;
	
	private boolean userImage;
	
	
	
	public static final String DEFAULT_USER = "admin";


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
	
	@EJB
	private IUserRewardSvc userRewardSvc;

	public IUserRewardSvc getUserRewardSvc() {
		return userRewardSvc;
	}

	public void setUserSvc(IUserRewardSvc userRewardSvc) {
		this.userRewardSvc = userRewardSvc;
	}
	
	
	@ManagedProperty(value="#{trophyBean}")
	private TrophyBean trophyBean;

	/**
	 * @return the loginForm
	 */
	public TrophyBean getTrophyBean() {
		return trophyBean;
	}

	/**
	 * @param loginForm the loginForm to set
	 */
	public void setTrophyBean(TrophyBean trophyBean) {
		this.trophyBean = trophyBean;
	}
	

	/*@PostConstruct
	public void init() {
		// TODO temporary convience for login
		setUserName("");
		setPassword("");
		Authentication authToken = SecurityContextHolder.getContext().getAuthentication();
		printUser(authToken);
		if (authToken != null) {
			setLoggedUser(authToken.getName());
			setLoggedRole(validateAdmin() ? Role.ADMIN.display() : Role.USER.display());
		}
	}*/

	private void printUser(Authentication authToken) {
		System.out.print("Login Name=" + authToken.getName()+", Login Role=" + authToken.getAuthorities()+", isAdmin=" + validateAdmin()+", isauthenticated=" + validateLoggedIn()+ "\n");
		//System.out.println((UserRepositoryUserDetails) authToken.getPrincipal());
		//System.out.println(authToken.getAuthorities());
	}

	public String getUserName() {
		System.out.println("Returning username as " + userName);
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String autoLogin() throws ServletException, IOException
	{
		Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String param = parameterMap.get("username");

		System.out.println("Auto loggin in...");
		setUserName(param);
		setPassword("password");
		login();
		return "/templates/adamantium/index.xhtml?faces-redirect=true";
	}
	
	
	
	
	public String login() throws ServletException, IOException {
		try {

			System.out.println("Logging in....");
			
			setUserName(toTitleCase(getUserName()));

			AppUser user = getUserSvc().getById(getUserName());
			if (user == null) {
				user = new AppUser();
				user.setLogin(getUserName());
				user.setPassword("password");
				user.setMobile("0430321919");
				user.addRole(Role.USER.value());
				user.addRole(Role.ADMIN.value());			
				Team team = getTeamSvc().getById("EnergyAustralia");
				user.setTeam(team);
			}
			
			user.setPassword(getPassword());
			
			getUserSvc().update(user);
			System.out.println(getUserSvc().getById(userName));

			WebApplicationContext ac = WebApplicationContextUtils
					.getWebApplicationContext((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext());
			AuthenticationManager authenticationManager = ac.getBean(AuthenticationManager.class);
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(getUserName(), getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			setLoggedUser(authentication.getName());
			setLoggedRole(validateAdmin() ? Role.ADMIN.display() : Role.USER.display());
			
			
			
			
			if (userRewardSvc.getDao().getUnReadRewardsByUser(getUserName()).size()>0)
			{
				UserReward reward = userRewardSvc.getDao().getUnReadRewardsByUser(getUserName()).get(0);
				getTrophyBean().setUserReward(reward);
				getSessionModel().setContent("/ui/viewTrophy.xhtml");
			}
			else
			{
				getSessionModel().reset();
					
			}
			
			
		} catch (Exception ex) {
			// log.equals(ex.getMessage());
			// ConfigUtil.addMessage("Login Failed: " + ex.getMessage());
			ex.printStackTrace();
			String message = ex.getLocalizedMessage();
			if (message == null) {
				message = "Incorrect user name or password";
			}
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "Login Failed: " + message));
			return null;
		}

		return ConfigUtil.getSavedUrl() + "?faces-redirect=true";
	}

	
	/*
	public String login() throws ServletException, IOException {
		try {

			System.out.println("Logging in....");
			
			AppUser user = new AppUser();
			user.setLogin("admin");
			user.setPassword("password");
			user.addRole(Role.USER.value());
			user.addRole(Role.ADMIN.value());
			getUserSvc().update(user);
			System.out.println(getUserSvc().getById(userName));

			WebApplicationContext ac = WebApplicationContextUtils
					.getWebApplicationContext((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext());
			AuthenticationManager authenticationManager = ac.getBean(AuthenticationManager.class);
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(getUserName(), getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			setLoggedUser(authentication.getName());
			setLoggedRole(validateAdmin() ? Role.ADMIN.display() : Role.USER.display());
			
			getSessionModel().reset();

		} catch (Exception ex) {
			// log.equals(ex.getMessage());
			// ConfigUtil.addMessage("Login Failed: " + ex.getMessage());
			ex.printStackTrace();
			String message = ex.getLocalizedMessage();
			if (message == null) {
				message = "Incorrect user name or password";
			}
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error", "Login Failed: " + message));
			return null;
		}

		return ConfigUtil.getSavedUrl() + "?faces-redirect=true";
	}*/



	
	
	public String logout() {
		System.out.println("Logging out...");
		getSessionModel().reset();
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		return null;

	}

	public boolean validateAdmin() {
		return ValidationUtils.isAdmin();
	}
	
	
	public boolean validateLoggedIn() {
		return ValidationUtils.isLoggedIn();
	}



	public String loginScreen() {
		return "login.xhtml?faces-redirect=true";
	}

	@Override
	public AppUser getNewOne() {
		return new AppUser();
	}

	@Override
	public List<AppUser> getDefaultSearchResults() {
		return getUserSvc().getAll();
	}

	@Override
	public String getEntityBusinessName() {
		return "Login";
	}

	@Override
	public IService getService() {
		return getUserSvc();
	}

	public String getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(String loggedUser) {
		this.loggedUser = loggedUser;
	}

	public String getLoggedRole() {
		return loggedRole;
	}

	public void setLoggedRole(String loggedRole) {
		this.loggedRole = loggedRole;
	}

	/**
	 * @return the userImage
	 */
	public boolean getUserImage() {
		
		Resource resource=null;
		try
		{
			String image = "images/" + getUserName().replace(" ", "_")+".png";
			resource=FacesContext.getCurrentInstance().getApplication().getResourceHandler().createResource(image, "custom");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		if (resource == null)
			return false;
		else
			return true;
	}
	
	
	/**
	 * @return the userImage
	 */
	public boolean imageExists(String uname) {
		
		Resource resource=null;
		try
		{
			String image = "images/" + uname.replace(" ", "_")+".png";
			resource=FacesContext.getCurrentInstance().getApplication().getResourceHandler().createResource(image, "custom");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		if (resource == null)
			return false;
		else
			return true;
	}
	

	/**
	 * @param userImage the userImage to set
	 */
	public void setUserImage(boolean userImage) {
		this.userImage = userImage;
	}
	
	
	public String toTitleCase(String input) {
	    StringBuilder titleCase = new StringBuilder();
	    boolean nextTitleCase = true;

	    for (char c : input.toCharArray()) {
	        if (Character.isSpaceChar(c)) {
	            nextTitleCase = true;
	        } else if (nextTitleCase) {
	            c = Character.toTitleCase(c);
	            nextTitleCase = false;
	        }

	        titleCase.append(c);
	    }

	    return titleCase.toString();
	}

}
