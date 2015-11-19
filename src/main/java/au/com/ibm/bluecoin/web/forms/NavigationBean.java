package au.com.ibm.bluecoin.web.forms;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.ibm.bluecoin.model.relational.AppUser;
import au.com.ibm.bluecoin.utils.PageDetails;
import au.com.ibm.bluecoin.web.forms.ui.UserForm;

/**
 * This bean is used to drive the application main menu system.
 * 
 * @author devops
 *
 */
@ManagedBean
public class NavigationBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(NavigationBean.class);
	
	// FIXME this bean maybe redundant as the actual form that we are navigating to should have the knowledge on how to
	// setup the sessionModel correctly.
	
	@SuppressWarnings("rawtypes")
	@ManagedProperty("#{sessionModel}")
	private SessionModel sessionModel;
	
	
	private boolean progressPage;
	private boolean homePage;
	
	

	@ManagedProperty(value="#{loginForm}")
	private LoginForm loginForm;

	/**
	 * @return the loginForm
	 */
	public LoginForm getLoginForm() {
		return loginForm;
	}

	/**
	 * @param loginForm the loginForm to set
	 */
	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}
	
	@ManagedProperty(value="#{userForm}")
	private UserForm userForm;

	/**
	 * @return the loginForm
	 */
	public UserForm getUserForm() {
		return userForm;
	}

	/**
	 * @param loginForm the userForm to set
	 */
	public void setUserForm(UserForm userForm) {
		this.userForm = userForm;
	}
	
	
	
	
	
		
	public void dashboard() {
		getSessionModel().addPage(new PageDetails("/dashboard.xhtml", null, null));
		getSessionModel().setContent("/dashboard.xhtml");
	}
				
	public void home() {
		getSessionModel().addPage(new PageDetails("/home.xhtml", null, null));
		getSessionModel().setContent("/home.xhtml");	
	}
		
	public void login() {
		getSessionModel().setContent("login.xhtml");
	}
	
	public void appuserlist() {
		LOGGER.info("Getting UserList");
		getSessionModel().setContent("/ui/appuser/appuser.xhtml");
	}

	public void send() {
		LOGGER.info("Send coins");
		getSessionModel().setContent("/ui/send.xhtml");
	}

	public void createTeam() {
		LOGGER.info("Send coins");
		getSessionModel().setContent("/ui/createTeam.xhtml");
	}
	
	public void viewRewards() {
		LOGGER.info("View Rewards");
		getSessionModel().setContent("/ui/rewardList.xhtml");
	}
	
	
	public void viewTrophy() {
		LOGGER.info("View trophy");
		getSessionModel().setContent("/ui/viewTrophy.xhtml");
	}
	
	public void viewTrophyCase() {
		LOGGER.info("View trophy Case");
		getSessionModel().setContent("/ui/trophyCase.xhtml");
	}
	
	
	public void viewTeamLadder() {
		LOGGER.info("View team ladder");
		getSessionModel().setContent("/ui/teamLadder.xhtml");
	}
	
	public void viewProgress() {
		LOGGER.info("View progress");
		getSessionModel().setContent("/ui/myProgress.xhtml");
	}
	
	
	public void viewUser() {
		LOGGER.info("View User");
		
		AppUser user = getLoginForm().getUserSvc().getById(getLoginForm().getUserName());
		getUserForm().setUser(user);
		getSessionModel().setContent("/ui/userAccount.xhtml");
	}
	
	
	public SessionModel getSessionModel() {
		return sessionModel;
	}
	
	public void setSessionModel(SessionModel sessionModel) {
		this.sessionModel = sessionModel;
	}

	/**
	 * @return the progressPage
	 */
	public boolean getProgressPage() {
		System.out.println("Current page is " + getSessionModel().getContent());
		String currentPage = getSessionModel().getContent();
		
		if (currentPage.contains("myProgress.xhtml")  || currentPage.contains("send.xhtml")|| currentPage.contains("rewardList.xhtml")|| currentPage.contains("trophyCase.xhtml") || currentPage.contains("viewTrophy.xhtml") || currentPage.contains("teamLadder.xhtml"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * @param progressPage the progressPage to set
	 */
	public void setProgressPage(boolean progressPage) {
		this.progressPage = progressPage;
	}

	/**
	 * @return the homePage
	 */
	public boolean getHomePage() {
		if (getSessionModel().getContent().contains("home.xhtml"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * @param homePage the homePage to set
	 */
	public void setHomePage(boolean homePage) {
		this.homePage = homePage;
	}

	
	
	
}
