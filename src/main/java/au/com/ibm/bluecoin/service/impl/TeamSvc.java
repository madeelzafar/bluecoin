package au.com.ibm.bluecoin.service.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import au.com.ibm.bluecoin.dao.Dao;
import au.com.ibm.bluecoin.dao.ITeamDao;
import au.com.ibm.bluecoin.model.relational.Team;
import au.com.ibm.bluecoin.service.ITeamSvc;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TeamSvc implements ITeamSvc {

	@Inject
	@Dao
	private ITeamDao dao;

	public ITeamDao getDao() {
		return dao;
	}

	public void setDao(ITeamDao dao) {
		this.dao = dao;
	}

	@Override
	public Team getById(String pId) {
		return getDao().findOne(pId);
	}
	
	@Override
	public Team create(Team pEntity) {
		return getDao().save(pEntity);
	}
	
	@Override
	public Team update(Team pEntity) {
		return getDao().save(pEntity);
	}

	@Override
	public void delete(Team pEntity) {
		getDao().delete(pEntity);
	}
	
	@Override
	public List<Team> getAll() {
		return getDao().findAll();
	}

}
