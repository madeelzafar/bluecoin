package au.com.ibm.bluecoin.model.relational;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import au.com.ibm.bluecoin.scaffold.AbstractMasterEntity;

public class Team extends AbstractMasterEntity<String> {

	@Id
	private String name;
	
	@OneToMany
	private List<User> members;
	
	@Override
	public String getSearchResultInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
