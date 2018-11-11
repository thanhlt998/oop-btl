package databaseaccess;

import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGRepository;
import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGServer;

public class DataAccessObject {
	private static final String SERVER_URL = "http://localhost:10035";
	private static final String CATALOG_ID = "/";
	private static final String REPOSITORY_ID = "oop";
	private static final String USERNAME = "test";
	private static final String PASSWORD = "xyzzy";

	private AGRepositoryConnection connection = null;

	public DataAccessObject() {
		AGServer server = new AGServer(SERVER_URL, USERNAME, PASSWORD);
		AGCatalog catalog = server.getCatalog(CATALOG_ID);
		AGRepository myRepository = catalog.createRepository(REPOSITORY_ID);
		connection = myRepository.getConnection();
	}

	public AGRepositoryConnection getConnection() {
		return connection;
	}

	public void closeConnection() {
		if (connection != null) {
			connection.close();
		}
	}

	public void clearConnection() {
		if (connection != null) {
			connection.clear();
		}
	}

}
