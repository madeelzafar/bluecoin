package au.com.ibm.bluecoin.service;

import javax.ejb.Local;

import au.com.ibm.bluecoin.dao.IUserRewardDao;
import au.com.ibm.bluecoin.dao.relational.repository.UserRewardRepository;
import au.com.ibm.bluecoin.model.relational.UserReward;
import au.com.ibm.bluecoin.scaffold.IService;

@Local
public interface IUserRewardSvc extends IService<UserReward, String, UserRewardRepository, IUserRewardDao> {

}
