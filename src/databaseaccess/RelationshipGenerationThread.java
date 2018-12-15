package databaseaccess;

import java.util.List;
import java.util.Random;

import org.eclipse.rdf4j.model.IRI;

import relationship.RelationshipGeneration;

public class RelationshipGenerationThread extends Thread{
	private static final Random RANDOM = new Random();
	
	private DataInsertion dataInsertion;
	private RelationshipGeneration relationshipGeneration;
	private List<IRI> entityIRIList;
	private int noEntity;
	private int noRelationship;
	
	private IRI entity1;
	private IRI entity2;
	private IRI relationship;
	
	
	public RelationshipGenerationThread(DataInsertion dataInsertion, RelationshipGeneration relationshipGeneration,
			List<IRI> entityIRIList, int noEntity, int noRelationship) {
		this.dataInsertion = dataInsertion;
		this.relationshipGeneration = relationshipGeneration;
		this.entityIRIList = entityIRIList;
		this.noEntity = noEntity;
		this.noRelationship = noRelationship;
	}
	
	@Override
	public void run() {
		/*IRI entity1 = null;
		IRI entity2 = null;
		IRI relationship = null;*/

		for (int i = 0; i < noRelationship; i++) {
			entity1 = entityIRIList.get(RANDOM.nextInt(noEntity));
			entity2 = entityIRIList.get(RANDOM.nextInt(noEntity));
			relationship = dataInsertion.createRelationship(
					relationshipGeneration.generateRandomRelationshipDescription(entity1, entity2));
			dataInsertion.insertStatement(entity1, relationship, entity2);
		}
	}
}
