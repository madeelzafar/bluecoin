package au.com.ibm.bluecoin.dao.relational.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import au.com.ibm.bluecoin.model.relational.Team;

@NoRepositoryBean
@Repo
public interface TeamRepository extends JpaRepository<Team, String>{

}
