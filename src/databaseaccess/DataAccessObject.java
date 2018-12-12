package databaseaccess;

import com.franz.agraph.repository.AGCatalog;
import com.franz.agraph.repository.AGRepository;
import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGServer;

public class DataAccessObject {
	private String serverUrl;
	private String catalogId;
	private String repositoryId;
	private String username;
	private String password;

	private AGRepositoryConnection connection = null;

	public DataAccessObject(String serverUrl, String username, String password, String catalogId, String repositoryId) {
		this.serverUrl = serverUrl;
		this.username = username;
		this.password = password;
		this.catalogId = catalogId;
		this.repositoryId = repositoryId;
		
		AGServer server = new AGServer(serverUrl, username, password);
		AGCatalog catalog = server.getCatalog(catalogId);
		AGRepository myRepository = catalog.createRepository(repositoryId);
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

	public String getServerUrl() {
		return serverUrl;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public String getRepositoryId() {
		return repositoryId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
