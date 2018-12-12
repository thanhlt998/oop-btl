package entitygeneration;

import java.util.ArrayList;
import java.util.List;

import entity.Time;
import utils.Utils;

public class TimeGeneration extends EntityGeneration {
	private static List<String> labelList = new ArrayList<>();
	private static List<String> descriptionList = new ArrayList<>();
	
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
	
	public Time generateTime() {
		return new Time(this.generateRandomLabel(), this.generateRandomDescription(), this.generateRandomExtractedLink(), this.generateRandomExtractedDate());
	}
}
