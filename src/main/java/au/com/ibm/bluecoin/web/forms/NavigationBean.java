package au.com.ibm.bluecoin.web.forms;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import au.com.ibm.bluecoin.utils.PageDetails;

/**
 * This bean is used to drive the application main menu system.
 * 
 * @author devops
 *
 */
@ManagedBean
public class NavigationBean {

	// FIXME this bean maybe redundant as the actual form that we are navigating to should have the knowledge on how to
	// setup the sessionModel correctly.
	
	@SuppressWarnings("rawtypes")
	@ManagedProperty("#{sessionModel}")
	private SessionModel sessionModel;
		
	public void dashboard() {
		getSessionModel().addPage(new PageDetails("/dashboard.xhtml", null, null));
		getSessionModel().setContent("/dashboard.xhtml");
	}
				
	public void home() {
		getSessionModel().addPage(new PageDetails("home.xhtml", null, null));
		getSessionModel().setContent("home.xhtml");	
	}
		
	public void login() {
		getSessionModel().setContent("login.xhtml");
	}

	public SessionModel getSessionModel() {
		return sessionModel;
	}
	
	public void setSessionModel(SessionModel sessionModel) {
		this.sessionModel = sessionModel;
	}
}
