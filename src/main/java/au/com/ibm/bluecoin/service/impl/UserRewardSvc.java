package au.com.ibm.bluecoin.service.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import au.com.ibm.bluecoin.dao.Dao;
import au.com.ibm.bluecoin.dao.IUserRewardDao;
import au.com.ibm.bluecoin.model.relational.UserReward;
import au.com.ibm.bluecoin.service.IUserRewardSvc;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserRewardSvc implements IUserRewardSvc {

	@Inject
	@Dao
	private IUserRewardDao dao;

	public IUserRewardDao getDao() {
		return dao;
	}

	public void setDao(IUserRewardDao dao) {
		this.dao = dao;
	}

	@Override
	public UserReward getById(String pId) {
		return getDao().findOne(pId);
	}
	
	@Override
	public UserReward create(UserReward pEntity) {
		return getDao().save(pEntity);
	}
	
	@Override
	public UserReward update(UserReward pEntity) {
		return getDao().save(pEntity);
	}

	@Override
	public void delete(UserReward pEntity) {
		getDao().delete(pEntity);
	}
	
	@Override
	public List<UserReward> getAll() {
		return getDao().findAll();
	}

}
