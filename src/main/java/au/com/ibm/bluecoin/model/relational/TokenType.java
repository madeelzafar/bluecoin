package au.com.ibm.bluecoin.model.relational;

import javax.persistence.Id;

import au.com.ibm.bluecoin.scaffold.AbstractMasterEntity;

public class TokenType extends AbstractMasterEntity<String> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1867520368352108701L;

	@Id
	private String code;

	private String name;	

	private Double factor;
	
	private String icon;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String getSearchResultInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		return getCode();
	}

}
