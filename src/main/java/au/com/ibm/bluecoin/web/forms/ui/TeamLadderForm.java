package au.com.ibm.bluecoin.web.forms.ui;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

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

@ManagedBean
public class TeamLadderForm extends AbstractMaintenanceForm<String, UserReward> {

	private static final long serialVersionUID = 8158434638931310723L;

	private static final Logger logger = LoggerFactory.getLogger(TeamLadderForm.class);

	@EJB
	private IUserRewardSvc userRewardSvc;

	

	@PostConstruct
	public void init() {
	
		//userRewards=userRewardSvc.getDao().findAll();
		teamLadder = userRewardSvc.getDao().getTeamLadder();
	}
	
	private List<TeamLadderSummaryItem> teamLadder;


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
		return "Team Ladder";
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
	 * @return the teamLadder
	 */
	public List<TeamLadderSummaryItem> getTeamLadder() {
		return teamLadder;
	}

	/**
	 * @param teamLadder the teamLadder to set
	 */
	public void setTeamLadder(List<TeamLadderSummaryItem> teamLadder) {
		this.teamLadder = teamLadder;
	}


}
