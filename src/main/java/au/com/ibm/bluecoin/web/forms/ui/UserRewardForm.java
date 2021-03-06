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
import au.com.ibm.bluecoin.model.relational.TeamLadderSummaryItem;
import au.com.ibm.bluecoin.model.relational.UserReward;
import au.com.ibm.bluecoin.scaffold.AbstractMaintenanceForm;
import au.com.ibm.bluecoin.scaffold.IService;
import au.com.ibm.bluecoin.service.IUserRewardSvc;
import au.com.ibm.bluecoin.service.IUserSvc;
import au.com.ibm.bluecoin.utils.Role;
import au.com.ibm.bluecoin.utils.ValidationUtils;
import au.com.ibm.bluecoin.web.forms.LoginForm;
import au.com.ibm.bluecoin.web.forms.TrophyBean;

@ManagedBean
@SessionScoped
public class UserRewardForm extends AbstractMaintenanceForm<String, UserReward> {

	private static final long serialVersionUID = 8158434638931310723L;

	private static final Logger logger = LoggerFactory.getLogger(UserRewardForm.class);

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

	
	private List<UserReward> userRewards;
	

	@Override
	public UserReward getNewOne() {
		return new UserReward();
	}

	@Override
	public List<UserReward> getDefaultSearchResults() {
		return userRewardSvc.getAll();
	}

	@Override
	public String getEntityBusinessName() {
		return "My Rewards";
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public IService getService() {
		return userRewardSvc;
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
	 * @return the userRewards
	 */
	public List<UserReward> getUserRewards() {
		
		//System.out.println("Getting user rewards from the database....");
		//userRewards=userRewardSvc.getDao().getRewardsByUser(getLoginForm().getUserName());
		return userRewards;
	}

	/**
	 * @param userRewards the userRewards to set
	 */
	public void setUserRewards(List<UserReward> userRewards) {
		this.userRewards = userRewards;
	}
	
	
	 public void  onRowSelect(SelectEvent event) {
		 
		 	UserReward reward =  (UserReward) event.getObject();
	        System.out.println("Selecting following reward.. " + reward.getRewardType());
	    	getTrophyBean().setUserReward(reward);
			getSessionModel().setContent("/ui/viewTrophy.xhtml");
			try
			{
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
	  }



}
