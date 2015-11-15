package au.com.ibm.bluecoin.dao;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import au.com.ibm.bluecoin.dao.relational.repository.TeamRepository;
import au.com.ibm.bluecoin.model.relational.Team;
import au.com.ibm.bluecoin.scaffold.IDao;

@NoRepositoryBean
public interface ITeamDao extends IDao<Team, String, TeamRepository> , TeamRepository {

}
