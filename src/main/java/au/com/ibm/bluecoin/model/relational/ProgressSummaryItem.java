package au.com.ibm.bluecoin.model.relational;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import au.com.ibm.bluecoin.utils.RewardItem;

public class ProgressSummaryItem {

	
	
	private long currentPoints;
	private long totalPoints=300;
	private int progress;
	
	private RewardItem reward;

	/**
	 * @return the currentPoints
	 */
	public long getCurrentPoints() {
		return currentPoints;
	}

	/**
	 * @param currentPoints the currentPoints to set
	 */
	public void setCurrentPoints(long currentPoints) {
		this.currentPoints = currentPoints;
		setProgress((int)(currentPoints/totalPoints));
	}

	/**
	 * @return the totalPoints
	 */
	public long getTotalPoints() {
		return totalPoints;
	}

	/**
	 * @param totalPoints the totalPoints to set
	 */
	public void setTotalPoints(long totalPoints) {
		this.totalPoints = totalPoints;
	}

	/**
	 * @return the progress
	 */
	public int getProgress() {
		return progress;
	}

	/**
	 * @param progress the progress to set
	 */
	public void setProgress(int progress) {
		this.progress = progress;
	}

	/**
	 * @return the reward
	 */
	public RewardItem getReward() {
		return reward;
	}

	/**
	 * @param reward the reward to set
	 */
	public void setReward(RewardItem reward) {
		this.reward = reward;
	}
	
	

	
	
}
