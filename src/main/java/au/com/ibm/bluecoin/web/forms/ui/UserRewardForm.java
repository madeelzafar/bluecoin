package au.com.ibm.bluecoin.web.forms.ui;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

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

@ManagedBean
public class UserRewardForm extends AbstractMaintenanceForm<String, UserReward> {

	private static final long serialVersionUID = 8158434638931310723L;

	private static final Logger logger = LoggerFactory.getLogger(UserRewardForm.class);

	@EJB
	private IUserRewardSvc userRewardSvc;

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

	@PostConstruct
	public void init() {
	
		try
		{
		
			userRewards=userRewardSvc.getDao().getAllRewards();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
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
		return "User Rewards";
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
		
		userRewards=userRewardSvc.getDao().getRewardsByUser(getLoginForm().getUserName());
		return userRewards;
	}

	/**
	 * @param userRewards the userRewards to set
	 */
	public void setUserRewards(List<UserReward> userRewards) {
		this.userRewards = userRewards;
	}



}
