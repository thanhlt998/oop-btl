package application;

import org.eclipse.rdf4j.model.vocabulary.RDF;

import databaseaccess.DatabaseGeneration;

public class App {
	public static void main(String[] args) {
		int[] n = {100, 5000, 60000, 1000000, 15000000};
		int[] m = {200, 7000, 80000, 2000000, 17000000};
		long startTime = System.currentTimeMillis();
		DatabaseGeneration databaseGeneration = new DatabaseGeneration();
		databaseGeneration.clearStatements();
		long endTime = System.currentTimeMillis();
		System.out.println("Time to init DatabaseGeneration: " + (endTime - startTime));
		
		for(int i = 0; i < 2; i++) {
			databaseGeneration.generateDatabase(n[i], m[i]);
			long time = databaseGeneration.getDataAccessObject().queryStatementTime(null, RDF.TYPE, databaseGeneration.getDataAccessObject().getPersonType(), null);
			System.out.println("Time to query in " + n[i] + " entities and " + m[i] + " relationship is " + time);
		}
	}
}
