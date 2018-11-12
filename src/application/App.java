package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.eclipse.rdf4j.model.vocabulary.RDF;

import databaseaccess.DataAccessObject;
import databaseaccess.DatabaseGeneration;
import databaseaccess.Query;

public class App {
	private static int[] n = { 100, 5000, 60000, 1000000, 15000000 };
	private static int[] m = { 200, 7000, 80000, 2000000, 17000000 };
	private static ArrayList<String> queryList;
	private static FileOutputStream outputStream;

	public static void main(String[] args) {
		
		DataAccessObject dataAccessObject = new DataAccessObject();
		DatabaseGeneration databaseGeneration = new DatabaseGeneration(dataAccessObject);
		Query query = new Query(dataAccessObject.getConnection());

		setQueryList("queries.txt");
		setOutputStream("result.txt");

		dataAccessObject.clearConnection();
		
		try {
			outputStream.write(new String("Begin at " + new Date().toString() + "\n").getBytes());
			outputStream
			.write(new String("Query 10 basic SPARQL query: \n")
					.getBytes());
			outputStream.write(new String("\t").getBytes());
			
			for (int i = 0; i < 2; i++) {
				databaseGeneration.generateDatabase(n[i], m[i]);

				outputStream.write(new String("(" + n[i] + ", " + m[i] + ")\t").getBytes());
				int j;
				for (j = 0; j < 10; j++) {
					long queryTime = query.querySPARQLTime(queryList.get(j));
					outputStream.write(new String(queryTime + "\t").getBytes());
				}
				
				for (; j < 20; j++) {
					long queryTime = query.queryPROLOGTime(queryList.get(j));
					outputStream.write(new String(queryTime + "\t").getBytes());
				}
				
				outputStream.write(new String("\n").getBytes());
			}
			outputStream.write(new String("End at " + new Date().toString()).getBytes());
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setQueryList(String fileName) {
		queryList = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(new File("queries.txt"));
			while (scanner.hasNextLine()) {
				queryList.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setOutputStream(String ouputFileName) {
		try {
			outputStream = new FileOutputStream(new File(ouputFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
