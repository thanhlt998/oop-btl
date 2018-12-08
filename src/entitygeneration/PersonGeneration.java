package entitygeneration;

import java.util.List;

import entity.Person;
import utils.Utils;

public class PersonGeneration extends EntityGeneration{
	private List<String> labelList;
	private List<String> descriptionList;
	
	public void setLabelList(String fileName) {
		labelList = Utils.readStringListFromFile(fileName);
	}
	
	public void setDescriptionList(String fileName) {
		descriptionList = Utils.readStringListFromFile(fileName);
	}
	
	public String generateRandomLabel() {
		return labelList.get(this.getRandom().nextInt(labelList.size()));
	}
	
	public String generateRandomDescription() {
		return descriptionList.get(this.getRandom().nextInt(descriptionList.size()));
	}
	
	public int generateRandomAge() {
		return this.getRandom().nextInt(100);
	}
	
	public Person generatePerson() {
		return new Person(generateRandomLabel(), generateRandomDescription(), this.generateRandomExtractedLink(), this.generateRandomExtractedDate(), generateRandomAge());
	}
}
