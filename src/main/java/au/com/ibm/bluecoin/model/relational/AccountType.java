package au.com.ibm.bluecoin.model.relational;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import au.com.ibm.bluecoin.scaffold.AbstractMasterEntity;

public class AccountType extends AbstractMasterEntity<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3869828664594910445L;

	@Id
	private String name;
	
	@ManyToOne
	private TokenType tokenType;
	
	private Direction direction;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public String getSearchResultInfo() {
		return null;
	}

	@Override
	public String getId() {
		return getName();
	}
}
