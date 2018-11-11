package databaseaccess;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.RepositoryResult;

import com.franz.agraph.repository.AGRepositoryConnection;
import com.franz.agraph.repository.AGTupleQuery;

public class Query {
	private AGRepositoryConnection connection;
	
	public Query(AGRepositoryConnection connection) {
		this.connection = connection;
	}
	
	public long querySPARQLTime(String queryString) {
		AGTupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
		long startTime = System.currentTimeMillis();

		TupleQueryResult result = tupleQuery.evaluate();

		long endTime = System.currentTimeMillis();
		result.close();
		return endTime - startTime;
	}

	public long queryStatementTime(IRI subject, IRI predicate, IRI object, Resource context) {
		long startTime = System.currentTimeMillis();
		RepositoryResult<Statement> statements = connection.getStatements(subject, predicate, object, context);
		long endTime = System.currentTimeMillis();
		statements.close();
		return endTime - startTime;
	}
}
