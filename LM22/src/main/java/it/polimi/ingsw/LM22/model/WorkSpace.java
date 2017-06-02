package it.polimi.ingsw.LM22.model;

import java.io.Serializable;
import java.util.List;

public class WorkSpace extends AbstractSpace implements Serializable {
	
	private final String workType; 
	private final Integer MALUS = 3;
	private List<FamilyMember> members;
	private List<String> coloredMemberOnIt;

	public WorkSpace(Integer requirement, String workType){
		super(requirement);
		this.workType = workType;
	}		
		
	public List<FamilyMember> getMembers() {
		return members;
	}
	public void setMembers(List<FamilyMember> members) {
		this.members = members;
	}
	public String getWorkType() {
		return workType;
	}

	public List<String> getColoredMemberOnIt() {
		return coloredMemberOnIt;
	}
	
	
		
}
