package au.com.ibm.bluecoin.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.ibm.bluecoin.dao.relational.UserDao;
import au.com.ibm.bluecoin.model.relational.AppUser;
import au.com.ibm.bluecoin.model.relational.TeamLadderSummaryItem;
import au.com.ibm.bluecoin.model.relational.UserReward;
import au.com.ibm.bluecoin.model.relational.UserRewardSummaryItem;
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
public class RewardBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(RewardBean.class);
	
	List<UserReward> rewardList = null;
	
	
	public RewardBean()
	{
		rewardList = new ArrayList<UserReward>();
	}
	
	
	public List<TeamLadderSummaryItem> getTeamRanking() {
	        return new ArrayList<TeamLadderSummaryItem>();
	}
	
	/*
	 * 
	 * 		This method will return summary of rewards achieved by all user in the team
	 * 
	 */
	public List<UserRewardSummaryItem> getRewardsByTeam(String teamName) {
        return new ArrayList<UserRewardSummaryItem>();
	}

	
	public List<UserReward> getUserRewardsByTeam() {
        return rewardList;
	}

	

}
