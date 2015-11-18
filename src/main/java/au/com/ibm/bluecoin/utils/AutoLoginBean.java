package au.com.ibm.bluecoin.utils;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AutoLoginBean {

	@ManagedProperty(value="#{param.uname}")

	private String uname;
	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}

	/**
	 * @param uname the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}

}
