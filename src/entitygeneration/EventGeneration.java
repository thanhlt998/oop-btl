package entitygeneration;

import java.util.List;

import entity.Event;
import utils.Utils;

public class EventGeneration extends EntityGeneration {
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
	
	public Event generateEvent() {
		return new Event(this.generateRandomLabel(), this.generateRandomDescription(), this.generateRandomExtractedLink(), this.generateRandomExtractedDate());
	}
}
