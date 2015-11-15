package au.com.ibm.bluecoin.dao.relational;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import au.com.ibm.bluecoin.dao.Dao;
import au.com.ibm.bluecoin.dao.ITeamDao;
import au.com.ibm.bluecoin.dao.relational.repository.TeamRepository;
import au.com.ibm.bluecoin.model.relational.Team;


@Dao
public class TeamDao extends AbstractDao<Team, String, TeamRepository> implements ITeamDao {

	@Override
	@PostConstruct
	public void init() {
		JpaRepositoryFactory factory = new JpaRepositoryFactory(getEntityManager());
		setRepository(factory.getRepository(TeamRepository.class));
	}



}
