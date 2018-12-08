package entitygeneration;

import java.util.Random;

import entity.Entity;

public class RandomEntityGeneration {
	private static final Random RANDOM = new Random();
	private EntityGeneration entityGeneration;
	private PersonGeneration personGeneration;
	private OrganizationGeneration organizationGeneration;
	private LocationGeneration locationGeneration;
	private TimeGeneration timeGeneration;
	private EventGeneration eventGeneration;
	private CountryGeneration countryGeneration;

	public RandomEntityGeneration() {
		entityGeneration = new EntityGeneration();
		personGeneration = new PersonGeneration();
		organizationGeneration = new OrganizationGeneration();
		locationGeneration = new LocationGeneration();
		timeGeneration = new TimeGeneration();
		eventGeneration = new EventGeneration();
		countryGeneration = new CountryGeneration();
	}

	public void setEntityGenerationData(int noLink, int noDate, String startDate) {
		entityGeneration.setExtractedDateList(noDate, startDate);
		entityGeneration.setExtractedLinkList(noLink);
	}

	public void setPersonGenerationData(String personLabelFileName, String personDescriptionFileName) {
		personGeneration.setDescriptionList(personDescriptionFileName);
		personGeneration.setLabelList(personLabelFileName);
	}

	public void setOrganizationGenerationData(String organizationLabelFileName, String organizationDescriptionFileName,
			String headquarterFileName) {
		organizationGeneration.setLabelList(organizationLabelFileName);
		organizationGeneration.setDescriptionList(organizationDescriptionFileName);
		organizationGeneration.setHeadquarterList(headquarterFileName);
	}

	public void setLocationGenerationData(String locationLabelFileName, String locationDescriptionFileName) {
		locationGeneration.setDescriptionList(locationDescriptionFileName);
		locationGeneration.setLabelList(locationLabelFileName);
	}

	public void setTimeGenerationData(String timeLabelFileName, String timeDescriptionFileName) {
		timeGeneration.setDescriptionList(timeDescriptionFileName);
		timeGeneration.setLabelList(timeLabelFileName);
	}

	public void setEventGenerationData(String eventLabelFileName, String eventDescriptionFileName) {
		eventGeneration.setDescriptionList(eventDescriptionFileName);
		eventGeneration.setLabelList(eventLabelFileName);
	}

	public void setCountryGenerationData(String countryLabelFileName, String countryDescriptionFileName) {
		countryGeneration.setLabelList(countryLabelFileName);
		countryGeneration.setDescriptionList(countryDescriptionFileName);
	}
	public Entity generateRandomEntity() {
		int random = RANDOM.nextInt(6);

		switch (random) {
		case 0:
			return personGeneration.generatePerson();
		case 1:
			return organizationGeneration.generateOrganization();
		case 2:
			return locationGeneration.generateLocation();
		case 3:
			return timeGeneration.generateTime();
		case 4:
			return eventGeneration.generateEvent();
		case 5:
			return countryGeneration.generateCountry();
		default:
			break;
		}
		return null;
	}
}
