package au.com.ibm.bluecoin.web.forms;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.ibm.bluecoin.utils.PageDetails;

/**
 * This bean is used to drive the application main menu system.
 * 
 * @author devops
 *
 */
@ManagedBean
public class NavigationBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(NavigationBean.class);
	
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
		getSessionModel().addPage(new PageDetails("/home.xhtml", null, null));
		getSessionModel().setContent("/home.xhtml");	
	}
		
	public void login() {
		getSessionModel().setContent("login.xhtml");
	}
	
	public void appuserlist() {
		LOGGER.info("Getting UserList coins");
		getSessionModel().setContent("/ui/appuser/appuserList.xhtml");
	}

	public void send() {
		LOGGER.info("Send coins");
		getSessionModel().setContent("/ui/send.xhtml");
	}

	
	
	public SessionModel getSessionModel() {
		return sessionModel;
	}
	
	public void setSessionModel(SessionModel sessionModel) {
		this.sessionModel = sessionModel;
	}
}
