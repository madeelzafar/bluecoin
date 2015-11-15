package au.com.ibm.bluecoin.dao;


import org.springframework.data.repository.NoRepositoryBean;

import au.com.ibm.bluecoin.dao.relational.repository.UserRewardRepository;
import au.com.ibm.bluecoin.model.relational.UserReward;
import au.com.ibm.bluecoin.scaffold.IDao;

@NoRepositoryBean
public interface IUserRewardDao extends IDao<UserReward, String, UserRewardRepository> , UserRewardRepository {

}
