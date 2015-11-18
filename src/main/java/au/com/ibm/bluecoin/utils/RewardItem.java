package au.com.ibm.bluecoin.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class RewardItem {
         
    private String name;
    private String value;
    private String points;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the points
	 */
	public String getPoints() {
		return points;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(String points) {
		this.points = points;
	}
    
  
}