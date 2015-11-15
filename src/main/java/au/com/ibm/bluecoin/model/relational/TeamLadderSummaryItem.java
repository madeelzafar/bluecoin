package au.com.ibm.bluecoin.model.relational;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeamLadderSummaryItem {

	
	private int rank;
	
	private int totalAmount;
	
	private Team team;
	

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * @return the totalAmmount
	 */
	public int getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmmount the totalAmmount to set
	 */
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
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
	
	
}
