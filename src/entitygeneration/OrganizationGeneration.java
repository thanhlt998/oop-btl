package entitygeneration;

import java.util.List;

import entity.Organization;
import utils.Utils;

public class OrganizationGeneration extends EntityGeneration{
	private List<String> labelList;
	private List<String> descriptionList;
	private List<String> headquarterList;
	
	public void setLabelList(String fileName) {
		labelList = Utils.readStringListFromFile(fileName);
	}
	
	public void setDescriptionList(String fileName) {
		descriptionList = Utils.readStringListFromFile(fileName);
	}
	
	public void setHeadquarterList(String fileName) {
		headquarterList = Utils.readStringListFromFile(fileName);
	}
	
	public String generateRandomLabel() {
		return labelList.get(this.getRandom().nextInt(labelList.size()));
	}
	
	public String generateRandomDescription() {
		return descriptionList.get(this.getRandom().nextInt(descriptionList.size()));
	}
	
	public String generateRandomHeadquarter() {
		return headquarterList.get(this.getRandom().nextInt(headquarterList.size()));
	}
	
	public Organization generateOrganization() {
		return new Organization(generateRandomLabel(), generateRandomDescription(), generateRandomExtractedLink(), generateRandomExtractedDate(), generateRandomHeadquarter());
	}
}
