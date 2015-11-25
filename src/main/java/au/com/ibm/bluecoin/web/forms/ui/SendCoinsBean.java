package au.com.ibm.bluecoin.web.forms.ui;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.ibm.bluecoin.config.ConfigUtil;
import au.com.ibm.bluecoin.dao.relational.UserDao;
import au.com.ibm.bluecoin.model.relational.AppUser;
import au.com.ibm.bluecoin.model.relational.Team;
import au.com.ibm.bluecoin.model.relational.UserReward;
import au.com.ibm.bluecoin.scaffold.AbstractMaintenanceForm;
import au.com.ibm.bluecoin.scaffold.IService;
import au.com.ibm.bluecoin.service.ITeamSvc;
import au.com.ibm.bluecoin.service.IUserRewardSvc;
import au.com.ibm.bluecoin.service.IUserSvc;
import au.com.ibm.bluecoin.utils.PageDetails;
import au.com.ibm.bluecoin.utils.RewardManager;
import au.com.ibm.bluecoin.utils.SendMail;
import au.com.ibm.bluecoin.web.forms.LoginForm;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Sms;



/**
 * This bean is used to drive the application main menu system.
 * 
 * @author devops
 *
 */
@ManagedBean
public class SendCoinsBean extends AbstractMaintenanceForm<String, UserReward> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendCoinsBean.class);
	
	
	private String amount="";
	private String recipient="";
	private String message="";
	private String amountMessage="";
	private String selectedReward="";
	
	
	
	private String accountSID = "";
	private String authToken = "";
	
	
	
	@EJB
	private IUserRewardSvc userRewardSvc;
	
	public IUserRewardSvc getUserRewardSvc() {
		return userRewardSvc;
	}

	public void setUserSvc(IUserRewardSvc userRewardSvc) {
		this.userRewardSvc = userRewardSvc;
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
	
	
	@ManagedProperty(value="#{rewardManager}")
	private RewardManager rewardManager;

	/**
	 * @return the rewardManager
	 */
	public RewardManager getRewardManager() {
		return rewardManager;
	}

	/**
	 * @param rewardManager the rewardManager to set
	 */
	public void setRewardManager(RewardManager rewardManager) {
		this.rewardManager = rewardManager;
	}
	

	
	
	@EJB(name="UserSvcLocal")
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
	
	
	
	
	public SendCoinsBean()
	{
		accountSID = "ACe30184e7eca62c8fa22ad111e8efa458";
		authToken = "4926655f54d8fd5d14d6dd6e5e967c04";
	}
	
	
	/*
	 public List<AppUser> completeText(String query) {
		 return userDAO.findByLoginLike(query);
		 
	 }*/
	 
	 public List<String> completeText(String query) {
		 
		 List<AppUser> list = getUserSvc().getDao().findByLoginLike(query);
	     List<String> results = new ArrayList<String>();
	        for(int i = 0; i < list.size(); i++) {
	            AppUser user = list.get(i);
	        	results.add(user.getLogin());
	        }
	        return results;
	    }
	     
	
	
	
	public String sendCoins()
	{
		setAmount(getRewardManager().getRewardByID(getSelectedReward()).getPoints());
		Sms sms = null;
	
		TwilioRestClient client = new TwilioRestClient(accountSID, authToken);

		
		//String broadcastGroup = "Justin Pitcher,Anthony Best,Andrew Johnstone-Burt,David Mancl,Jennifer Moxon,Joanna Batstone,Jo Dooley,Kathleen McCudden,Nicholas Flood,Omeed Kroll,Paul Wilson,Rhody Burton,Riccardo Forlenza,Robert Lee,Scott Barlow,Sonia Basser,Tammy Evans,Vijay Rao";
		String broadcastGroup = "Justin Pitcher,Adeel Zafar,Kimberly Burgos";
		
		
		SmsFactory messageFactory = client.getAccount().getSmsFactory();
		try {
			
	
			AppUser sender = getUserSvc().getById(getLoginForm().getUserName()); 
			
			List<AppUser> allUsers = getUserSvc().getAll();
			for(AppUser user: allUsers)
			{
				
				if (!getRecipient().equals("GMLT"))
				{
					user = getUserSvc().getById(getRecipient());
				}
				else
				{
					if (!broadcastGroup.contains(user.getLogin()))
					{
						continue;
					}
				}
				
				
				//AppUser user = getUserSvc().getById(getRecipient());
				String uname= user.getLogin();
				//String messageBody = "Hi " + getRecipient() + "!! You have received " + getAmount() + " coins from " + sender.getLogin();
				String messageBody = "Hi " + uname + "!! You have received " + getAmount() + " coins from " + sender.getLogin();
	
				
				System.out.println("uname before is "+ uname);
				uname=uname.replaceAll(" ", "%20");
				System.out.println("uname after is "+ uname);
	
				String messageBody2 = "Check your rewards at http://bluecoin-poc.mybluemix.net/loginas.xhtml?uname="+uname;
				LOGGER.info("Sending " + messageBody  + " " +   messageBody2);

				
						
				Team team = getTeamSvc().getById("EnergyAustralia");
				//user.setTeam(team);
				
				UserReward reward = new UserReward();
				reward.setRewardDate(new Date());
				reward.setRecepient(user);
				reward.setSender(sender);
				
				reward.setRewardAmount(Integer.parseInt(getAmount()));
				reward.setRewardMessage(getMessage());
				reward.setTeam(user.getTeam());
				reward.setRewardType(getSelectedReward());
				
				
				getUserRewardSvc().create(reward);
		
				
				// Build a filter for the SmsList
				Map<String, String> params = new HashMap<String, String>();
				// Update with your Twilio number 
				//params.put("From", "+61439767507");
				//params.put("Body", messageBody);
				//params.put("To", user.getMobile());
				//sms = messageFactory.create(params);
				
				SendMail sendmail = new SendMail();
			
				if (!getRecipient().equals("GMLT"))
				{
					sendmail.sendMailusingSendGrid(messageBody, user.getMobile());
					sendmail.sendMailusingSendGrid(messageBody2, user.getMobile());
					break;
				}
				else
				{
					sendmail.sendMailusingSendGrid(messageBody, user.getMobile());
					sendmail.sendMailusingSendGrid(messageBody2, user.getMobile());
					//sendmail.sendMailusingSendGrid(messageBody, "0430321919");
					//sendmail.sendMailusingSendGrid(messageBody2, "0430321919");
				}
			}
		}
		catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
		}
		//LOGGER.info("Sent message id: " + sms.getSid());
		
		System.out.println("Navigating back to home..");
		getSessionModel().setContent("/home.xhtml");
		return ConfigUtil.getSavedUrl() + "?faces-redirect=true";				
		
	}


	/**
	 * @return the recipient
	 */
	public String getRecipient() {
		return recipient;
	}


	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}


	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
		setAmountMessage(getAmount());
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the amountMessage
	 */
	public String getAmountMessage() {
		return amountMessage;
	}

	/**
	 * @param amountMessage the amountMessage to set
	 */
	public void setAmountMessage(String amountMessage) {
		
		if (amountMessage.equals(""))
		{
			this.amountMessage = "";
		}
		else
		{
			this.amountMessage = "Selected reward is worth " + amountMessage + " points";	
		}
		
	}

	/**
	 * @return the selectedReward
	 */
	public String getSelectedReward() {
		return selectedReward;
	}

	/**
	 * @param selectedReward the selectedReward to set
	 */
	public void setSelectedReward(String selectedReward) {
		this.selectedReward = selectedReward;
	}
	
	public void onRewardChange()
	{
		System.out.println("Changing reward to " + getSelectedReward());
		setAmount(getRewardManager().getRewardByID(getSelectedReward()).getPoints());
		System.out.println("Amount has been set to " + getAmount());
	}
	
	
	
	
	
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
		return "Send Coins";
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

	
	
	
	
	
	
	
	
	
}
