package au.com.ibm.bluecoin.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean
@SessionScoped
public class RewardManager {
         
    private List<RewardItem> rewardItems;
     
    @PostConstruct
    public void init() {
    	
    	rewardItems = new ArrayList<RewardItem>();
    	
    	RewardItem item = new RewardItem();
    	item.setName("One Purpose");
    	item.setValue("OnePurpose");
    	item.setPoints("90");
        rewardItems.add(item);
        
        
    	item = new RewardItem();
    	item.setName("Dedication to client's success");
    	item.setValue("ClientSuccess");
    	item.setPoints("30");
    	rewardItems.add(item);	
    	
    	
    	item = new RewardItem();
    	item.setName("- Put the client first");
    	item.setValue("ClientFirst");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    	
    	item = new RewardItem();
    	item.setName("- Listen for need, envision the future");
    	item.setValue("Listen");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    
    	item = new RewardItem();
    	item.setName("- Share experties");
    	item.setValue("ShareExperties");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    	item = new RewardItem();
    	item.setName("Innovation that matters");
    	item.setValue("Innovation");
    	item.setPoints("30");
    	rewardItems.add(item);	
    	
    	
    	item = new RewardItem();
    	item.setName("- Restlessly reinvent");
    	item.setValue("Reinvent");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    
    	item = new RewardItem();
    	item.setName("- Dare to create orignal ideas");
    	item.setValue("CreateOrignal");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    	item = new RewardItem();
    	item.setName("- Wild ducks");
    	item.setValue("WildDucks");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    	
    	item = new RewardItem();
    	item.setName("Trust in all relationships");
    	item.setValue("Trust");
    	item.setPoints("30");
    	rewardItems.add(item);	
    	
    	
    	item = new RewardItem();
    	item.setName("- Think Prepare Rehearse");
    	item.setValue("Think");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    	item = new RewardItem();
    	item.setName("- Unite to get it done now");
    	item.setValue("Unite");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    	item = new RewardItem();
    	item.setName("- Show personal interest");
    	item.setValue("PersonalInterest");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
  }
 
    

	/**
	 * @return the rewardItems
	 */
	public List<RewardItem> getRewardItems() {
		return rewardItems;
	}

	/**
	 * @param rewardItems the rewardItems to set
	 */
	public void setRewardItems(List<RewardItem> rewardItems) {
		this.rewardItems = rewardItems;
	}
	
	
	public String getRewardPoints(String rewardID)
	{
		for (RewardItem rewardItem: rewardItems )
		{
			if (rewardItem.getValue().toUpperCase().equals(rewardID.toUpperCase()))
			{
				return rewardItem.getPoints();
			}
		}
		return "";
	}
	
	public String getRewardName(String rewardID)
	{
		for (RewardItem rewardItem: rewardItems )
		{
			if (rewardItem.getName().equalsIgnoreCase(rewardID))
			{
				return rewardItem.getPoints();
			}
		}
		return "";
	}
 
 
}