package au.com.ibm.bluecoin.service;

import javax.ejb.Local;

import au.com.ibm.bluecoin.dao.ITeamDao;
import au.com.ibm.bluecoin.dao.relational.repository.TeamRepository;
import au.com.ibm.bluecoin.model.relational.Team;
import au.com.ibm.bluecoin.scaffold.IService;

@Local
public interface ITeamSvc extends IService<Team, String, TeamRepository, ITeamDao> {

}
