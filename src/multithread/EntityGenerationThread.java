package multithread;

import java.util.List;

import org.eclipse.rdf4j.model.IRI;

import databaseaccess.DataInsertion;
import entity.Entity;
import entitygeneration.RandomEntityGeneration;

public class EntityGenerationThread extends Thread{
	
	private RandomEntityGeneration randomEntityGeneration;
	private DataInsertion dataInsertion;
	private List<IRI> entityIRIList;
	private int noEntity;
	
	public EntityGenerationThread(RandomEntityGeneration randomEntityGeneration, DataInsertion dataInsertion,
			List<IRI> entityIRIList, int noEntity) {
		super();
		this.randomEntityGeneration = randomEntityGeneration;
		this.dataInsertion = dataInsertion;
		this.entityIRIList = entityIRIList;
		this.noEntity = noEntity;
	}
	
	@Override
	public void run() {
		Entity entity = null;
		for (int i = 0; i < noEntity; i++) {
			entity = randomEntityGeneration.generateRandomEntity();
			entityIRIList.add(dataInsertion.insertEntity(entity));
		}
	}
	
}
