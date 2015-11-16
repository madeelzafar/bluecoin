package au.com.ibm.bluecoin.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.repository.NoRepositoryBean;

import au.com.ibm.bluecoin.dao.relational.repository.UserRewardRepository;
import au.com.ibm.bluecoin.model.relational.TeamLadderSummaryItem;
import au.com.ibm.bluecoin.model.relational.UserReward;
import au.com.ibm.bluecoin.scaffold.IDao;

@NoRepositoryBean
public interface IUserRewardDao extends IDao<UserReward, String, UserRewardRepository> , UserRewardRepository {

	public List<UserReward> getRewardsByUser(String username);
	

	public List<UserReward> getUnReadRewardsByUser(String username);
	
	
	public List<TeamLadderSummaryItem> getTeamLadder();
	
}
