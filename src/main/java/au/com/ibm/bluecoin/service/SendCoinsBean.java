package au.com.ibm.bluecoin.service;


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

import au.com.ibm.bluecoin.dao.relational.UserDao;
import au.com.ibm.bluecoin.model.relational.AppUser;
import au.com.ibm.bluecoin.model.relational.Team;
import au.com.ibm.bluecoin.model.relational.UserReward;
import au.com.ibm.bluecoin.utils.PageDetails;
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
public class SendCoinsBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendCoinsBean.class);
	
	
	private String amount="";
	private String recipient="";
	private String message="";
	
	
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
	     
	
	
	
	public void sendCoins()
	{
		
		Sms sms = null;
		String messageBody = "Hi " + getRecipient() + "!! You have received " + getAmount() + " coins.. http://bluecoin-poc.mybluemix.net";
		LOGGER.info("Sending " + messageBody );

		TwilioRestClient client = new TwilioRestClient(accountSID, authToken);

	
	
		SmsFactory messageFactory = client.getAccount().getSmsFactory();
		try {
			
			
			AppUser user = getUserSvc().getById(getRecipient());
			AppUser sender = getUserSvc().getById(getLoginForm().getUserName()); 
					
					
			Team team = getTeamSvc().getById("EnergyAustralia");
			//user.setTeam(team);
			
			UserReward reward = new UserReward();
			reward.setRewardDate(new Date());
			reward.setRecepient(user);
			reward.setSender(sender);
			
			reward.setRewardAmount(Integer.parseInt(getAmount()));
			reward.setRewardMessage(getMessage());
			reward.setTeam(user.getTeam());
			getUserRewardSvc().create(reward);
	
			
			// Build a filter for the SmsList
			Map<String, String> params = new HashMap<String, String>();
			// Update with your Twilio number 
			//params.put("From", "+61439767507");
			//params.put("Body", messageBody);
			//params.put("To", user.getMobile());
			//sms = messageFactory.create(params);
			
			SendMail sendmail = new SendMail();
			sendmail.sendMailusingSendGrid(messageBody, user.getMobile());
			
			
		}
		catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
		}
		//LOGGER.info("Sent message id: " + sms.getSid());
				
		
		
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
}
