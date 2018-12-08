package relationship;

import java.util.List;
import java.util.Random;

import org.eclipse.rdf4j.model.IRI;

import databaseaccess.DataInsertion;
import utils.Utils;

public class RelationshipGeneration {
	private static final Random RANDOM = new Random();
	private List<String> relationshipDescriptionList;
	private List<String> per_perRelationshipDescriptionList;
	private List<String> per_eventRelationshipDescriptionList;
	private List<String> perorg_orgeventRelationshipDescriptionList;
	private List<String> event_locctytimeRelationshipDescriptionList;
	private List<String> cty_eventorgRelationshipDescriptionList;
	private List<String> ctyorg_timeRelationshipDescriptionList;
	
	public void setRelationshipDescriptionList(String fileName) {
		relationshipDescriptionList = Utils.readStringListFromFile(fileName);
	}
	
	public void setPer_perRelationshipDescriptionList(String fileName) {
		per_perRelationshipDescriptionList = Utils.readStringListFromFile(fileName);
	}
	
	public void setPer_eventRelationshipDescriptionList(String fileName) {
		per_eventRelationshipDescriptionList = Utils.readStringListFromFile(fileName);
	}
	
	public void setPerorg_orgeventRelationshipDescriptionList(String fileName) {
		perorg_orgeventRelationshipDescriptionList = Utils.readStringListFromFile(fileName);
	}
	
	public void setEvent_locctytimeRelationshipDescriptionList(String fileName) {
		event_locctytimeRelationshipDescriptionList = Utils.readStringListFromFile(fileName);
	}
	
	public void setCty_eventorgRelationshipDescriptionList(String fileName) {
		cty_eventorgRelationshipDescriptionList = Utils.readStringListFromFile(fileName);
	}
	
	public void setCtyorg_timeRelationshipDescriptionList(String fileName) {
		ctyorg_timeRelationshipDescriptionList = Utils.readStringListFromFile(fileName);
	}
	
	
	public String generateRandomRelationshipDescription(IRI entity1, IRI entity2) {
		
		if(isPersonIRI(entity1) && isPersonIRI(entity2)) {
			return per_perRelationshipDescriptionList.get(RANDOM.nextInt(per_perRelationshipDescriptionList.size()));
		}
		
		if(isPersonIRI(entity1) && isEventIRI(entity2)) {
			return per_eventRelationshipDescriptionList.get(RANDOM.nextInt(per_eventRelationshipDescriptionList.size()));
		}
		
		if((isPersonIRI(entity1) || isOrganizationIRI(entity1)) && (isOrganizationIRI(entity2) || isEventIRI(entity2))) {
			return perorg_orgeventRelationshipDescriptionList.get(RANDOM.nextInt(perorg_orgeventRelationshipDescriptionList.size()));
		}
		
		if(isEventIRI(entity1) && (isLocationIRI(entity2) || isCountryIRI(entity2) || isTimeIRI(entity2))) {
			return event_locctytimeRelationshipDescriptionList.get(RANDOM.nextInt(event_locctytimeRelationshipDescriptionList.size()));
		}
		
		if(isCountryIRI(entity1) && (isEventIRI(entity2) || isOrganizationIRI(entity2))) {
			return cty_eventorgRelationshipDescriptionList.get(RANDOM.nextInt(cty_eventorgRelationshipDescriptionList.size()));
		}
		
		if((isCountryIRI(entity1) || isOrganizationIRI(entity1)) && isTimeIRI(entity2)){
			return ctyorg_timeRelationshipDescriptionList.get(RANDOM.nextInt(ctyorg_timeRelationshipDescriptionList.size()));
		}
		
		return relationshipDescriptionList.get(RANDOM.nextInt(relationshipDescriptionList.size()));
	}
	
	public Relationship generateRelationship(IRI entity1, IRI entity2) {
		return new Relationship(generateRandomRelationshipDescription(entity1, entity2));
	}
	
	private boolean isPersonIRI(IRI iri) {
		return iri.getNamespace().equals(DataInsertion.getPersonNamespace());
	}
	
	private boolean isCountryIRI(IRI iri) {
		return iri.getNamespace().equals(DataInsertion.getCountryNamespace());
	}
	
	private boolean isEventIRI(IRI iri) {
		return iri.getNamespace().equals(DataInsertion.getEventNamespace());
	}
	
	private boolean isLocationIRI(IRI iri) {
		return iri.getNamespace().equals(DataInsertion.getLocationNamespace());
	}
	
	private boolean isOrganizationIRI(IRI iri) {
		return iri.getNamespace().equals(DataInsertion.getOrganizationNamespace());
	}
	
	private boolean isTimeIRI(IRI iri) {
		return iri.getNamespace().equals(DataInsertion.getTimeNamespace());
	}
}
