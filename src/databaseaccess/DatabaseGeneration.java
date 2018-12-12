package databaseaccess;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rdf4j.model.IRI;

import entitygeneration.RandomEntityGeneration;
import multithread.EntityGenerationThread;
import multithread.RelationshipGenerationThread;
import relationship.RelationshipGeneration;

public class DatabaseGeneration {
	private static final int NO_ENTITY_GENERATION_THREAD = 10;
	private static final int NO_RELATIONSHIP_GENERATION_THREAD = 10;

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

	private static String relationshipDescriptionFileName = "relationshipDescription.txt";
	private static String per_perRelationshipDescriptionFileName = "per_perRelationshipDescription.txt";
	private static String per_eventRelationshipDescriptionFileName = "per_eventRelationshipDescription.txt";
	private static String perorg_orgeventRelationshipDescriptionFileName = "perorg_orgeventRelationshipDescription.txt";
	private static String event_locctytimeRelationshipDescriptionFileName = "event_locctytimeRelationshipDescription.txt";
	private static String cty_eventorgRelationshipDescriptionFileName = "cty_eventorgRelationshipDescription.txt";
	private static String ctyorg_timeRelationshipDescriptionFileName = "ctyorg_timeRelationshipDescription.txt";

	private DataAccessObject dataAcessObject;
	private DataInsertion dataInsertion;
	private RandomEntityGeneration randomEntityGeneration;
	private RelationshipGeneration relationshipGeneration;

	private List<IRI> entityIRIList;

	public DatabaseGeneration(DataAccessObject dataAccessObject) {
		this.dataAcessObject = dataAccessObject;
		dataInsertion = new DataInsertion(this.dataAcessObject.getConnection());
		randomEntityGeneration = new RandomEntityGeneration();
		relationshipGeneration = new RelationshipGeneration();

		randomEntityGeneration.setEntityGenerationData(noLink, noDate, startDate);
		randomEntityGeneration.setPersonGenerationData(personLabelFileName, personDescriptionFileName);
		randomEntityGeneration.setOrganizationGenerationData(organizationLabelFileName, organizationDescriptionFileName,
				headquarterFileName);
		randomEntityGeneration.setCountryGenerationData(countryLabelFileName, countryDescriptionFileName);
		randomEntityGeneration.setLocationGenerationData(locationLabelFileName, locationDescriptionFileName);
		randomEntityGeneration.setTimeGenerationData(timeLabelFileName, timeDescriptionFileName);
		randomEntityGeneration.setEventGenerationData(eventLabelFileName, eventDescriptionFileName);

		relationshipGeneration.setRelationshipDescriptionList(relationshipDescriptionFileName);
		relationshipGeneration.setPer_perRelationshipDescriptionList(per_perRelationshipDescriptionFileName);
		relationshipGeneration.setPer_eventRelationshipDescriptionList(per_eventRelationshipDescriptionFileName);
		relationshipGeneration
				.setPerorg_orgeventRelationshipDescriptionList(perorg_orgeventRelationshipDescriptionFileName);
		relationshipGeneration
				.setEvent_locctytimeRelationshipDescriptionList(event_locctytimeRelationshipDescriptionFileName);
		relationshipGeneration.setCty_eventorgRelationshipDescriptionList(cty_eventorgRelationshipDescriptionFileName);
		relationshipGeneration.setCtyorg_timeRelationshipDescriptionList(ctyorg_timeRelationshipDescriptionFileName);

		entityIRIList = new ArrayList<>();
	}

	private void generateEntity(int numberOfEntity) throws InterruptedException {

		/*
		 * if (numberOfEntity > noEntity) { Entity entity = null; for (int i = 0; i <
		 * numberOfEntity - noEntity; i++) { entity =
		 * randomEntityGeneration.generateRandomEntity();
		 * entityIRIList.add(dataInsertion.insertEntity(entity)); }
		 * 
		 * noEntity = numberOfEntity; }
		 */

		if (numberOfEntity > noEntity) {
			List<EntityGenerationThread> threadList = new ArrayList<>();
			for (int i = 0; i < NO_ENTITY_GENERATION_THREAD; i++) {
				threadList.add(new EntityGenerationThread(randomEntityGeneration, dataInsertion, entityIRIList,
						(numberOfEntity - noEntity) / NO_ENTITY_GENERATION_THREAD));
			}

			for (EntityGenerationThread entityGenerationThread : threadList) {
				entityGenerationThread.start();
				entityGenerationThread.join();
			}

			noEntity = numberOfEntity;
		}

	}

	private void generateRelationship(int numberOfRelationship) throws InterruptedException {

		/*
		 * if (numberOfRelationship > noRelationship) { IRI entity1 = null; IRI entity2
		 * = null; IRI relationship = null;
		 * 
		 * for (int i = 0; i < numberOfRelationship - noRelationship; i++) { entity1 =
		 * entityIRIList.get(RANDOM.nextInt(noEntity)); entity2 =
		 * entityIRIList.get(RANDOM.nextInt(noEntity)); relationship =
		 * dataInsertion.createRelationship(
		 * relationshipGeneration.generateRandomRelationshipDescription(entity1,
		 * entity2)); dataInsertion.insertStatement(entity1, relationship, entity2); }
		 * noRelationship = numberOfRelationship; }
		 */

		if (numberOfRelationship > noRelationship) {
			List<RelationshipGenerationThread> threadList = new ArrayList<>();

			for (int i = 0; i < NO_RELATIONSHIP_GENERATION_THREAD; i++) {
				threadList.add(new RelationshipGenerationThread(dataInsertion, relationshipGeneration, entityIRIList,
						noEntity, (numberOfRelationship - noRelationship) / NO_RELATIONSHIP_GENERATION_THREAD));
			}

			for (RelationshipGenerationThread relationshipGenerationThread : threadList) {
				relationshipGenerationThread.start();
				relationshipGenerationThread.join();
			}
			noRelationship = numberOfRelationship;
		}

	}

	public void generateDatabase(int numberOfEntity, int numberOfRelationship) throws InterruptedException {
		generateEntity(numberOfEntity);
		generateRelationship(numberOfRelationship);
	}

	public DataAccessObject getDataAcessObject() {
		return dataAcessObject;
	}

	public void setDataAcessObject(DataAccessObject dataAcessObject) {
		this.dataAcessObject = dataAcessObject;
	}

	public DataInsertion getDataInsertion() {
		return dataInsertion;
	}

	public void setDataInsertion(DataInsertion dataInsertion) {
		this.dataInsertion = dataInsertion;
	}

}
