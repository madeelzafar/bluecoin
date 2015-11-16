package au.com.ibm.bluecoin.model.relational;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import au.com.ibm.bluecoin.scaffold.AbstractMasterEntity;

@Entity
public class UserReward extends AbstractMasterEntity<String> {

	private AppUser recepient = new AppUser();
	//Team team = null;
	private int rewardAmount;
	private String rewardMessage;
	boolean read=false;
	private Team team = null;
	private AppUser sender = new AppUser();
	
	
	@Id
	private
	Date rewardDate;
	
	
	@Override
	public String getSearchResultInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		return null;
	}

	
	public void markAsRead()
	{
		read = true;
	}
	
	public void markAsUnRead()
	{
		read = false;
	}

	/**
	 * @return the rewardMessage
	 */
	public String getRewardMessage() {
		return rewardMessage;
	}

	/**
	 * @param rewardMessage the rewardMessage to set
	 */
	public void setRewardMessage(String rewardMessage) {
		this.rewardMessage = rewardMessage;
	}

	/**
	 * @return the rewardDate
	 */
	public Date getRewardDate() {
		return rewardDate;
	}

	/**
	 * @param rewardDate the rewardDate to set
	 */
	public void setRewardDate(Date rewardDate) {
		this.rewardDate = rewardDate;
	}

	/**
	 * @return the rewardAmount
	 */
	public int getRewardAmount() {
		return rewardAmount;
	}

	/**
	 * @param rewardAmount the rewardAmount to set
	 */
	public void setRewardAmount(int rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * @return the recepient
	 */
	public AppUser getRecepient() {
		return recepient;
	}

	/**
	 * @param recepient the recepient to set
	 */
	public void setRecepient(AppUser recepient) {
		this.recepient = recepient;
	}

	/**
	 * @return the sender
	 */
	public AppUser getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(AppUser sender) {
		this.sender = sender;
	}
}
