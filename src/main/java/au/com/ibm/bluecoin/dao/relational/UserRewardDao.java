package au.com.ibm.bluecoin.dao.relational;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import au.com.ibm.bluecoin.dao.Dao;
import au.com.ibm.bluecoin.dao.IUserRewardDao;
import au.com.ibm.bluecoin.dao.relational.repository.UserRewardRepository;
import au.com.ibm.bluecoin.model.relational.UserReward;


@Dao
public class UserRewardDao extends AbstractDao<UserReward, String, UserRewardRepository> implements IUserRewardDao {

	@Override
	@PostConstruct
	public void init() {
		JpaRepositoryFactory factory = new JpaRepositoryFactory(getEntityManager());
		setRepository(factory.getRepository(UserRewardRepository.class));
	}



}
