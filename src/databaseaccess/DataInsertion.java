package databaseaccess;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.XMLSchema;

import com.franz.agraph.repository.AGRepositoryConnection;

import entity.Country;
import entity.Entity;
import entity.Event;
import entity.Location;
import entity.Organization;
import entity.Person;
import entity.Time;

public class DataInsertion {
	private AGRepositoryConnection connection;
	private ValueFactory valueFactory;

	private static String ontologyNamespace = "http://www.randomlink.org/ontology/";

	private static String personNamespace = "http://www.randomlink.org/person/";
	private static String organizationNamespace = "http://www.randomlink.org/organization/";
	private static String locationNamespace = "http://www.randomlink.org/location/";
	private static String countryNamespace = "http://www.randomlink.org/country/";
	private static String timeNamespace = "http://www.randomlink.org/time/";
	private static String eventNamespace = "http://www.randomlink.org/event/";
	private static String relationshipNamespace = "http://www.randomlink.org/relationship/";

	private IRI labelOntology;
	private IRI descriptionOntology;
	private IRI extractedLinkOntology;
	private IRI extractedDateOntology;
	private IRI ageOntology;
	private IRI headquarterOntology;

	private IRI personType;
	private IRI organizationType;
	private IRI locationType;
	private IRI countryType;
	private IRI timeType;
	private IRI eventType;
	
	private Literal label;
	private Literal description;
	private Literal extractedLink;
	private Literal extractedDate;
	private Literal age;
	private Literal headquarter;
	
	private ModelBuilder modelBuilder;

	public DataInsertion(AGRepositoryConnection connection) {
		this.connection = connection;
		this.valueFactory = connection.getValueFactory();
		
		setNamespace("ns", ontologyNamespace);
		setNamespace("rela", relationshipNamespace);

		labelOntology = valueFactory.createIRI(ontologyNamespace, "label");
		descriptionOntology = valueFactory.createIRI(ontologyNamespace, "description");
		extractedLinkOntology = valueFactory.createIRI(ontologyNamespace, "extracted-link");
		extractedDateOntology = valueFactory.createIRI(ontologyNamespace, "extracted-date");
		ageOntology = valueFactory.createIRI(ontologyNamespace, "age");
		headquarterOntology = valueFactory.createIRI(ontologyNamespace, "headquarter");

		personType = valueFactory.createIRI(ontologyNamespace, "Person");
		organizationType = valueFactory.createIRI(ontologyNamespace, "Organization");
		locationType = valueFactory.createIRI(ontologyNamespace, "Location");
		countryType = valueFactory.createIRI(ontologyNamespace, "Country");
		timeType = valueFactory.createIRI(ontologyNamespace, "Time");
		eventType = valueFactory.createIRI(ontologyNamespace, "Event");
		
		modelBuilder = new ModelBuilder();
	}

	private IRI insertPerson(Person person) {
		IRI iri = valueFactory.createIRI(personNamespace, person.getId());
		label = valueFactory.createLiteral(person.getLabel());
		description = valueFactory.createLiteral(person.getDescription());
		extractedLink = valueFactory.createLiteral(person.getExtractedLink());
		extractedDate = valueFactory.createLiteral(person.getExtractedDate(), XMLSchema.DATE);
		age = valueFactory.createLiteral(person.getAge());

		modelBuilder.add(iri, RDF.TYPE, personType);
		modelBuilder.add(iri, labelOntology, label);
		modelBuilder.add(iri, descriptionOntology, description);
		modelBuilder.add(iri, extractedLinkOntology, extractedLink);
		modelBuilder.add(iri, extractedDateOntology, extractedDate);
		modelBuilder.add(iri, ageOntology, age);

		return iri;
	}

	private IRI insertOrganization(Organization organization) {
		IRI iri = valueFactory.createIRI(organizationNamespace, organization.getId());
		label = valueFactory.createLiteral(organization.getLabel());
		description = valueFactory.createLiteral(organization.getDescription());
		extractedLink = valueFactory.createLiteral(organization.getExtractedLink());
		extractedDate = valueFactory.createLiteral(organization.getExtractedDate(), XMLSchema.DATE);
		headquarter = valueFactory.createLiteral(organization.getHeadquarter());

		modelBuilder.add(iri, RDF.TYPE, organizationType);
		modelBuilder.add(iri, labelOntology, label);
		modelBuilder.add(iri, descriptionOntology, description);
		modelBuilder.add(iri, extractedLinkOntology, extractedLink);
		modelBuilder.add(iri, extractedDateOntology, extractedDate);
		modelBuilder.add(iri, headquarterOntology, headquarter);

		return iri;
	}

	private IRI insertLocation(Location location) {
		IRI iri = valueFactory.createIRI(locationNamespace, location.getId());
		label = valueFactory.createLiteral(location.getLabel());
		description = valueFactory.createLiteral(location.getDescription());
		extractedLink = valueFactory.createLiteral(location.getExtractedLink());
		extractedDate = valueFactory.createLiteral(location.getExtractedDate(), XMLSchema.DATE);

		modelBuilder.add(iri, RDF.TYPE, locationType);
		modelBuilder.add(iri, labelOntology, label);
		modelBuilder.add(iri, descriptionOntology, description);
		modelBuilder.add(iri, extractedLinkOntology, extractedLink);
		modelBuilder.add(iri, extractedDateOntology, extractedDate);

		return iri;
	}

	private IRI insertCountry(Country country) {
		IRI iri = valueFactory.createIRI(countryNamespace, country.getId());
		label = valueFactory.createLiteral(country.getLabel());
		description = valueFactory.createLiteral(country.getDescription());
		extractedLink = valueFactory.createLiteral(country.getExtractedLink());
		extractedDate = valueFactory.createLiteral(country.getExtractedDate(), XMLSchema.DATE);

		modelBuilder.add(iri, RDF.TYPE, countryType);
		modelBuilder.add(iri, labelOntology, label);
		modelBuilder.add(iri, descriptionOntology, description);
		modelBuilder.add(iri, extractedLinkOntology, extractedLink);
		modelBuilder.add(iri, extractedDateOntology, extractedDate);

		return iri;
	}

	private IRI insertTime(Time time) {
		IRI iri = valueFactory.createIRI(timeNamespace, time.getId());
		label = valueFactory.createLiteral(time.getLabel());
		description = valueFactory.createLiteral(time.getDescription());
		extractedLink = valueFactory.createLiteral(time.getExtractedLink());
		extractedDate = valueFactory.createLiteral(time.getExtractedDate(), XMLSchema.DATE);

		modelBuilder.add(iri, RDF.TYPE, timeType);
		modelBuilder.add(iri, labelOntology, label);
		modelBuilder.add(iri, descriptionOntology, description);
		modelBuilder.add(iri, extractedLinkOntology, extractedLink);
		modelBuilder.add(iri, extractedDateOntology, extractedDate);

		return iri;
	}

	private IRI insertEvent(Event event) {
		IRI iri = valueFactory.createIRI(eventNamespace, event.getId());
		label = valueFactory.createLiteral(event.getLabel());
		description = valueFactory.createLiteral(event.getDescription());
		extractedLink = valueFactory.createLiteral(event.getExtractedLink());
		extractedDate = valueFactory.createLiteral(event.getExtractedDate(), XMLSchema.DATE);

		modelBuilder.add(iri, RDF.TYPE, eventType);
		modelBuilder.add(iri, labelOntology, label);
		modelBuilder.add(iri, descriptionOntology, description);
		modelBuilder.add(iri, extractedLinkOntology, extractedLink);
		modelBuilder.add(iri, extractedDateOntology, extractedDate);

		return iri;
	}

	public IRI insertEntity(Entity entity) {
		if (entity instanceof Person) {
			return insertPerson((Person) entity);
		} else if (entity instanceof Organization) {
			return insertOrganization((Organization) entity);
		} else if (entity instanceof Location) {
			return insertLocation((Location) entity);
		} else if (entity instanceof Country) {
			return insertCountry((Country) entity);
		} else if (entity instanceof Time) {
			return insertTime((Time) entity);
		} else if (entity instanceof Event) {
			return insertEvent((Event) entity);
		}
		return null;
	}
	
	public void pushIntoDatabase() {
		connection.add(modelBuilder.build());
		modelBuilder = new ModelBuilder();
	}
	
	public void setNamespace(String prefix, String name) {
		connection.setNamespace(prefix, name);
	}

	public IRI createRelationship(String relationshipDescription) {
		return valueFactory.createIRI(relationshipNamespace, relationshipDescription);
	}

	public void insertStatement(IRI entity1, IRI relationship, IRI entity2) {
		modelBuilder.add(entity1, relationship, entity2);
	}

	public static String getOntologyNamespace() {
		return ontologyNamespace;
	}

	public static String getPersonNamespace() {
		return personNamespace;
	}

	public static String getOrganizationNamespace() {
		return organizationNamespace;
	}

	public static String getLocationNamespace() {
		return locationNamespace;
	}

	public static String getCountryNamespace() {
		return countryNamespace;
	}

	public static String getTimeNamespace() {
		return timeNamespace;
	}

	public static String getEventNamespace() {
		return eventNamespace;
	}

	public static String getRelationshipNamespace() {
		return relationshipNamespace;
	}

	public IRI getLabelOntology() {
		return labelOntology;
	}

	public IRI getDescriptionOntology() {
		return descriptionOntology;
	}

	public IRI getExtractedLinkOntology() {
		return extractedLinkOntology;
	}

	public IRI getExtractedDateOntology() {
		return extractedDateOntology;
	}

	public IRI getAgeOntology() {
		return ageOntology;
	}

	public IRI getHeadquarterOntology() {
		return headquarterOntology;
	}

	public IRI getPersonType() {
		return personType;
	}

	public IRI getOrganizationType() {
		return organizationType;
	}

	public IRI getLocationType() {
		return locationType;
	}

	public IRI getCountryType() {
		return countryType;
	}

	public IRI getTimeType() {
		return timeType;
	}

	public IRI getEventType() {
		return eventType;
	}

}
