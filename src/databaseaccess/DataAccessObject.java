package databaseaccess;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.XMLSchema;

import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGRepository;
import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGServer;

import entity.Country;
import entity.Entity;
import entity.Event;
import entity.Location;
import entity.Organization;
import entity.Person;
import entity.Time;

public class DataAccessObject {
	private static final String SERVER_URL = "http://localhost:10035";
	private static final String CATALOG_ID = "/";
	private static final String REPOSITORY_ID = "oop";
	private static final String USERNAME = "test";
	private static final String PASSWORD = "xyzzy";
	
	private static String ontologyNamespace = "http://www.randomlink.org/ontology/";
	
	private static String personNamespace = "http://www.randomlink.org/person/";
	private static String organizationNamespace = "http://www.randomlink.org/organization/";
	private static String locationNamespace = "http://www.randomlink.org/location/";
	private static String countryNamespace = "http://www.randomlink.org/country/";
	private static String timeNamespace = "http://www.randomlink.org/time/";
	private static String eventNamespace = "http://www.randomlink.org/event/";
	
	private AGRepositoryConnection connection = null;
	private ValueFactory valueFactory = null;
	
	private IRI labelOntology;
	private IRI descriptionOntology;
//	private IRI identifyOntology;
	private IRI extractedLinkOntology;
	private IRI extractedDateOntology;
	private IRI ageOntology;
	private IRI headquarterOntology;
	
	public DataAccessObject() {
		AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
		AGCatalog catalog = server.getCatalog(CATALOG_ID);
		AGRepository myRepository = catalog.createRepository(REPOSITORY_ID);
		connection = myRepository.getConnection();
		
		valueFactory =  connection.getValueFactory();
		labelOntology = valueFactory.createIRI(ontologyNamespace, "label");
		descriptionOntology = valueFactory.createIRI(ontologyNamespace, "description");
//		identifyOntology = valueFactory.createIRI(ontologyNamespace, "identify");
		extractedLinkOntology = valueFactory.createIRI(ontologyNamespace, "extracted-link");
		extractedDateOntology = valueFactory.createIRI(ontologyNamespace, "extracted-date");
		ageOntology = valueFactory.createIRI(ontologyNamespace, "age");
		headquarterOntology = valueFactory.createIRI(ontologyNamespace, "headquarter");
	}
	
	public AGRepositoryConnection getConnection() {
		return connection;
	}
	
	public void closeConnection() {
		if(connection != null) {
			connection.close();
		}
	}
	
	private void insertPerson(Person person) {
		IRI iri = valueFactory.createIRI(personNamespace, person.getId());
		Literal label = valueFactory.createLiteral(person.getLabel());
		Literal description = valueFactory.createLiteral(person.getDescription());
		Literal extractedLink = valueFactory.createLiteral(person.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(person.getExtractedDate(), XMLSchema.DATE);
		Literal age = valueFactory.createLiteral(person.getAge());
		
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, extractedLinkOntology, extractedLink);
		connection.add(iri, extractedDateOntology, extractedDate);
		connection.add(iri, ageOntology, age);
	}
	
	private void insertOrganization(Organization organization) {
		IRI iri = valueFactory.createIRI(organizationNamespace, organization.getId());
		Literal label = valueFactory.createLiteral(organization.getLabel());
		Literal description = valueFactory.createLiteral(organization.getDescription());
		Literal extractedLink = valueFactory.createLiteral(organization.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(organization.getExtractedDate(), XMLSchema.DATE);
		Literal headquarter = valueFactory.createLiteral(organization.getHeadquarter());
		
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, extractedLinkOntology, extractedLink);
		connection.add(iri, extractedDateOntology, extractedDate);
		connection.add(iri, headquarterOntology, headquarter);
	}
	
	private void insertLocation(Location location) {
		IRI iri = valueFactory.createIRI(locationNamespace, location.getId());
		Literal label = valueFactory.createLiteral(location.getLabel());
		Literal description = valueFactory.createLiteral(location.getDescription());
		Literal extractedLink = valueFactory.createLiteral(location.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(location.getExtractedDate(), XMLSchema.DATE);
		
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, extractedLinkOntology, extractedLink);
		connection.add(iri, extractedDateOntology, extractedDate);
	}
	
	private void insertCountry(Country country) {
		IRI iri = valueFactory.createIRI(countryNamespace, country.getId());
		Literal label = valueFactory.createLiteral(country.getLabel());
		Literal description = valueFactory.createLiteral(country.getDescription());
		Literal extractedLink = valueFactory.createLiteral(country.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(country.getExtractedDate(), XMLSchema.DATE);
		
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, extractedLinkOntology, extractedLink);
		connection.add(iri, extractedDateOntology, extractedDate);
	}
	
	private void insertTime(Time time) {
		IRI iri = valueFactory.createIRI(timeNamespace, time.getId());
		Literal label = valueFactory.createLiteral(time.getLabel());
		Literal description = valueFactory.createLiteral(time.getDescription());
		Literal extractedLink = valueFactory.createLiteral(time.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(time.getExtractedDate(), XMLSchema.DATE);
		
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, extractedLinkOntology, extractedLink);
		connection.add(iri, extractedDateOntology, extractedDate);
	}
	
	private void insertEvent(Event event) {
		IRI iri = valueFactory.createIRI(eventNamespace, event.getId());
		Literal label = valueFactory.createLiteral(event.getLabel());
		Literal description = valueFactory.createLiteral(event.getDescription());
		Literal extractedLink = valueFactory.createLiteral(event.getExtractedLink());
		Literal extractedDate = valueFactory.createLiteral(event.getExtractedDate(), XMLSchema.DATE);
		
		connection.add(iri, labelOntology, label);
		connection.add(iri, descriptionOntology, description);
		connection.add(iri, extractedLinkOntology, extractedLink);
		connection.add(iri, extractedDateOntology, extractedDate);
	}
	
	public void insertEntity(Entity entity) {
		if(entity instanceof Person) {
			insertPerson((Person) entity); 
		}
		else if(entity instanceof Organization) {
			insertOrganization((Organization) entity);
		}
		else if(entity instanceof Location) {
			insertLocation((Location) entity);
		}
		else if(entity instanceof Country) {
			insertCountry((Country) entity);
		}
		else if(entity instanceof Time) {
			insertTime((Time) entity);
		}
		else if(entity instanceof Event) {
			insertEvent((Event) entity);
		}
	}
}
