package au.com.ibm.bluecoin.dao.relational;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import au.com.ibm.bluecoin.dao.Dao;
import au.com.ibm.bluecoin.dao.IUserRewardDao;
import au.com.ibm.bluecoin.dao.relational.repository.UserRewardRepository;
import au.com.ibm.bluecoin.model.relational.AppUser;
import au.com.ibm.bluecoin.model.relational.UserReward;


@Dao
public class UserRewardDao extends AbstractDao<UserReward, String, UserRewardRepository> implements IUserRewardDao {

	@Override
	@PostConstruct
	public void init() {
		JpaRepositoryFactory factory = new JpaRepositoryFactory(getEntityManager());
		setRepository(factory.getRepository(UserRewardRepository.class));
	}
	
	
	public List<UserReward> getRewardsByUser(String username) {
		CriteriaBuilder c = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserReward> criteria = c.createQuery(UserReward.class);
		
		/*
		Root<UserReward> usr = criteria.from(UserReward.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		criteria.select(usr);
		if (username != null && !username.isEmpty()) {
			Predicate predicate = c.equal(c.upper(usr.get(AppUser_"login")), username.toUpperCase());
			predicates.add(predicate);
		}
		criteria.where(c.or(predicates.toArray(new Predicate[0])));
		*/
		List<UserReward> resultList = getEntityManager().createQuery(criteria).getResultList();
		List<UserReward> result = new ArrayList<UserReward>();
		for (int i=0; i< resultList.size(); i++){
			
			UserReward u = resultList.get(i);
			if (u.getAppUser().getLogin().toUpperCase().equals(username.toUpperCase()))
			{
				result.add(u);
			}
			
		}
		return result;
	}
	

	public List<UserReward> getUnReadRewardsByUser(String username) {
		
		System.out.println("getUnReadRewardsByUser");
		CriteriaBuilder c = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserReward> criteria = c.createQuery(UserReward.class);
		
		Root<UserReward> usr = criteria.from(UserReward.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		criteria.select(usr);
		
		Predicate predicate = c.equal(usr.get("read"), "0");
		predicates.add(predicate);
		criteria.where(c.or(predicates.toArray(new Predicate[0])));
		List<UserReward> resultList = getEntityManager().createQuery(criteria).getResultList();
		
		List<UserReward> result = new ArrayList<UserReward>();
		
		System.out.println("From the initial predicates, result is " + resultList.size());
		for (int i=0; i< resultList.size(); i++){
			
			UserReward u = resultList.get(i);
			if (u.getAppUser().getLogin().toUpperCase().equals(username.toUpperCase()))
			{
				result.add(u);
			}
		}
		return result;
	}



}
