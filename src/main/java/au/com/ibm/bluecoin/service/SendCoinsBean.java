package au.com.ibm.bluecoin.service;


import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.ibm.bluecoin.utils.PageDetails;

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
	
	private String accountSID = "ACe30184e7eca62c8fa22ad111e8efa458";
	private String authToken = "4926655f54d8fd5d14d6dd6e5e967c04";
	
	
	
	
	public void sendCoins()
	{
		
		Sms message = null;
		String messageBody = "Hi " + getRecipient() + "!! You have recieved " + getAmount() + " coins..";
		LOGGER.info("Sending " + messageBody );

		TwilioRestClient client = new TwilioRestClient(accountSID, authToken);

		// Build a filter for the SmsList
		Map<String, String> params = new HashMap<String, String>();
				
				// Update with your Twilio number 
		params.put("From", "+61439767507");
		params.put("Body", messageBody);
		params.put("To", "+61430321919");
			
		SmsFactory messageFactory = client.getAccount().getSmsFactory();
		try {
				message = messageFactory.create(params);
				}
				catch (Exception e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
				}
				LOGGER.info("Sent message id: " + message.getSid());
		
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
}
