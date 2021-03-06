package au.com.ibm.bluecoin.model.relational;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import au.com.ibm.bluecoin.scaffold.AbstractMasterEntity;

@Entity
public class UserReward extends AbstractMasterEntity<String> {

	@OneToOne
	private AppUser recepient = new AppUser();
	private int rewardAmount;
	private String rewardType;
	
	private String rewardMessage;
	boolean read=false;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@OneToOne
	private Team team = null;
	
	@OneToOne
	private AppUser sender = new AppUser();
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private	Date rewardDate;
	
	
	@Override
	public String getSearchResultInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		return id;
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

	/**
	 * @return the rewardType
	 */
	public String getRewardType() {
		return rewardType;
	}

	/**
	 * @param rewardType the rewardType to set
	 */
	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}
}
