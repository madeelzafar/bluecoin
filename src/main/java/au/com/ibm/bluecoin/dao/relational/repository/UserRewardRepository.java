package au.com.ibm.bluecoin.dao.relational.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import au.com.ibm.bluecoin.model.relational.AppUser;
import au.com.ibm.bluecoin.model.relational.UserReward;

@NoRepositoryBean
@Repo
public interface UserRewardRepository extends JpaRepository<UserReward, String>{

}
