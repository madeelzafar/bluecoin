package au.com.ibm.bluecoin.utils;


import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
 
import org.primefaces.model.chart.DonutChartModel;
 
@ManagedBean
public class CreditView implements Serializable {
 
    private List<TeamMember> developmentTeam;
 
    @PostConstruct
    public void init() {
        createTeam();
    }
 
     
    private void createTeam() {
    	
    	developmentTeam = new ArrayList<TeamMember>();
    	
    	TeamMember member = new TeamMember();
    	member.setName("Justin Pitcher");
    	member.setDesignation("The Visionary");
    	developmentTeam.add(member);
    	
    	member = new TeamMember();
    	member.setName("Adeel Zafar");
    	member.setDesignation("The Makes Things Real Developer");
    	developmentTeam.add(member);
    	
    	member = new TeamMember();
    	member.setName("Joseph Wakim");
    	member.setDesignation("The Design Guru");
    	developmentTeam.add(member);
    	
    	
    	member = new TeamMember();
    	member.setName("Edwin Hoi");
    	member.setDesignation("The Ideas Man");
    	developmentTeam.add(member);
    	
    	
    	member = new TeamMember();
    	member.setName("Michael Garry");
    	member.setDesignation("The Fun Director");
    	developmentTeam.add(member);
    	
    	
    	member = new TeamMember();
    	member.setName("James Garwoli");
    	member.setDesignation("The Fun Police");
    	developmentTeam.add(member);
    	
      	member = new TeamMember();
    	member.setName("Mitchell Brien");
    	member.setDesignation("The Makes Things Spin Guy");
    	developmentTeam.add(member);
    	
      	member = new TeamMember();
    	member.setName("Kimberly Burgos");
    	member.setDesignation("The Voice of Reason");
    	developmentTeam.add(member);
    	
    	
    }
 
  

	/**
	 * @return the developmentTeam
	 */
	public List<TeamMember> getDevelopmentTeam() {
		return developmentTeam;
	}


	/**
	 * @param developmentTeam the developmentTeam to set
	 */
	public void setDevelopmentTeam(List<TeamMember> developmentTeam) {
		this.developmentTeam = developmentTeam;
	}
}