package application;

import databaseaccess.DatabaseGeneration;

public class App {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		DatabaseGeneration databaseGeneration = new DatabaseGeneration();
		long endTime = System.currentTimeMillis();
		System.out.println("Time to init DatabaseGeneration: " + (endTime - startTime));
	}
}
