package rdfgeneration;

import java.util.Random;

import entitygeneration.RandomEntityGeneration;
import relationship.RelationshipGeneration;

public class RdfGeneration {
	private static final Random RANDOM = new Random();

	private static int noEntity = 0;
	private static int noRelationship = 0;

	private static int noLink = 1000;
	private static int noDate = 1000;
	private static String startDate = "2011-01-01";

	private static String personLabelFileName = "personLabel.txt";
	private static String personDescriptionFileName = "personDescription.txt";

	private static String organizationLabelFileName = "organizationLabel.txt";
	private static String organizationDescriptionFileName = "organizationDescription.txt";
	private static String headquarterFileName = "headquarter.txt";

	private static String countryLabelFileName = "countryLabel.txt";
	private static String countryDescriptionFileName = "countryDescription.txt";

	private static String locationLabelFileName = "locationLabel.txt";
	private static String locationDescriptionFileName = "locationDescription.txt";

	private static String timeLabelFileName = "timeLabel.txt";
	private static String timeDescriptionFileName = "timeDescription.txt";

	private static String eventLabelFileName = "eventLabel.txt";
	private static String eventDescriptionFileName = "eventDescription.txt";

	private static String ontologyNamespace = "http://www.randomlink.org/ontology/";

	private static String personNamespace = "http://www.randomlink.org/person/";
	private static String organizationNamespace = "http://www.randomlink.org/organization/";
	private static String locationNamespace = "http://www.randomlink.org/location/";
	private static String countryNamespace = "http://www.randomlink.org/country/";
	private static String timeNamespace = "http://www.randomlink.org/time/";
	private static String eventNamespace = "http://www.randomlink.org/event/";
	private static String relationshipNamespace = "http://randomlink.org/relationship/";

	private RandomEntityGeneration randomEntityGeneration;
	private RelationshipGeneration relationshipGeneration;

	public RdfGeneration() {
		randomEntityGeneration = new RandomEntityGeneration();
		relationshipGeneration = new RelationshipGeneration();

		randomEntityGeneration.setEntityGeneration(noLink, noDate, startDate);
		randomEntityGeneration.setPersonGeneration(personLabelFileName, personDescriptionFileName);
		randomEntityGeneration.setOrganizationGeneration(organizationLabelFileName, organizationDescriptionFileName,
				headquarterFileName);
		randomEntityGeneration.setCountryGeneration(countryLabelFileName, countryDescriptionFileName);
		randomEntityGeneration.setLocationGeneration(locationLabelFileName, locationDescriptionFileName);
		randomEntityGeneration.setTimeGeneration(timeLabelFileName, timeDescriptionFileName);
		randomEntityGeneration.setEventGeneration(eventLabelFileName, eventDescriptionFileName);

		relationshipGeneration.setRelationshipDescriptionList("relationshipDescription.txt");
	}

	public void generateRdfFile(int noEntity, int noRelationship, int outputFileName) {

	}
}
