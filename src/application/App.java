package application;

import org.eclipse.rdf4j.model.vocabulary.RDF;

import databaseaccess.DataAccessObject;
import databaseaccess.DatabaseGeneration;
import databaseaccess.Query;

public class App {
	public static void main(String[] args) {
		int[] n = {100, 5000, 60000, 1000000, 15000000};
		int[] m = {200, 7000, 80000, 2000000, 17000000};
		
		DataAccessObject dataAccessObject = new DataAccessObject();
		DatabaseGeneration databaseGeneration = new DatabaseGeneration(dataAccessObject);
		Query query = new Query(dataAccessObject.getConnection());
		
	}
}
