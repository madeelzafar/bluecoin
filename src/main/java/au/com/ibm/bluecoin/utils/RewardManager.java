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
    
    
    public RewardManager()
    {
    	rewardItems = new ArrayList<RewardItem>();
    	
    	RewardItem item = new RewardItem();
    	item.setName("One Purpose");
    	item.setValue("OnePurpose");
    	item.setPoints("90");
        rewardItems.add(item);
        
        
    	item = new RewardItem();
    	item.setName("Dedication To Client's Success");
    	item.setValue("ClientSuccess");
    	item.setPoints("30");
    	rewardItems.add(item);	
    	
    	
    	item = new RewardItem();
    	item.setName("- Put The Client First");
    	item.setValue("ClientFirst");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    	
    	item = new RewardItem();
    	item.setName("- Listen For Need, Envision The Future");
    	item.setValue("Listen");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    
    	item = new RewardItem();
    	item.setName("- Share Experties");
    	item.setValue("ShareExperties");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    	item = new RewardItem();
    	item.setName("Innovation That Matters");
    	item.setValue("Innovation");
    	item.setPoints("30");
    	rewardItems.add(item);	
    	
    	
    	item = new RewardItem();
    	item.setName("- Restlessly Reinvent");
    	item.setValue("Reinvent");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    
    	item = new RewardItem();
    	item.setName("- Dare to Create Orignal Ideas");
    	item.setValue("CreateOrignal");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    	item = new RewardItem();
    	item.setName("- Treasure Wild Ducks");
    	item.setValue("WildDucks");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    	
    	item = new RewardItem();
    	item.setName("Trust in All Relationships");
    	item.setValue("Trust");
    	item.setPoints("30");
    	rewardItems.add(item);	
    	
    	
    	item = new RewardItem();
    	item.setName("- Think Prepare Rehearse");
    	item.setValue("Think");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    	item = new RewardItem();
    	item.setName("- Unite to Get it Done Now");
    	item.setValue("Unite");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	
    	item = new RewardItem();
    	item.setName("- Show Personal Interest");
    	item.setValue("PersonalInterest");
    	item.setPoints("10");
    	rewardItems.add(item);	
    	

    	
    	
    }
    
    @PostConstruct
    public void init() {
    	
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
	
	

	
	public RewardItem getRewardByID(String rewardID)
	{
		if (rewardItems == null)
		{
			System.out.println("RewardItems is null.. but why???");
		}
		
		
		for (RewardItem rewardItem: rewardItems )
		{
			if (rewardItem.getValue().equalsIgnoreCase(rewardID))
			{
				return rewardItem;
			}
		}
		return null;
	}
 
 
}