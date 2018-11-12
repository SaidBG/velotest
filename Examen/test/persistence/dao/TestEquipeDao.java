package persistence.dao;

import java.util.ArrayList;
import java.util.List;

import business.entite.Equipe;
import exception.SqlInserterException;
import persistence.exception.DaoException;
import persistence.pere.TU_Pere;

public class TestEquipeDao extends TU_Pere {




	int realNb = 0;
	EquipeDao daoEquipe = new EquipeDao();
	Equipe testEquipe = null;




	public void setUp() throws Exception {			
		super.setUp();

		realNb = getInserter().select("select count(id) from equipe").getDataAsInt();
	}


	public void testList() throws DaoException {
		List<Equipe> listEquipe = daoEquipe.findList();	
		assertEquals(realNb,listEquipe.size());
	}

	public void testFindById() throws DaoException, SqlInserterException {
		testEquipe = daoEquipe.findById(2);
		String name = getInserter().select("select equipe.name from equipe where id = 2").getDataAsString();
		assertEquals(name,testEquipe.getNameEquipe());	
	}


	public void testDeleteById() throws DaoException {
		testEquipe = new Equipe  (0, "Gerard", 5000);
		daoEquipe.create(testEquipe);
		long newId = testEquipe.getIdEquipe();
		assertEquals(realNb+1, daoEquipe.findList().size());

		daoEquipe.deleteById(newId);		
		assertNull(daoEquipe.findById(newId));	
	}



	public void testCreate () throws DaoException {
		List<Equipe> listEquipe  = daoEquipe.findList();
		String name ="";

		try {

			testEquipe= new Equipe  (0, "Gerard", 5000);
			daoEquipe.create(testEquipe);	
			long newId = testEquipe.getIdEquipe();
			name = getInserter().select("select equipe.name from equipe where id =" + newId).getDataAsString();
			listEquipe.add(testEquipe);	
			assertEquals(name,testEquipe.getNameEquipe());				
			assertEquals(realNb+1, listEquipe.size());
		
		} catch (SqlInserterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Equipe nulos = daoEquipe.findById(testEquipe.getIdEquipe());
		assertEquals(nulos.getNameEquipe(),testEquipe.getNameEquipe());
		nulos = daoEquipe.create(null);
		assertNull(nulos);
	}

	public void testUpdate() throws DaoException, SqlInserterException {
		List<Equipe> listEquipe = daoEquipe.findList();	
		testEquipe = daoEquipe.findById(1);
		assertNotNull(testEquipe);

		testEquipe = new Equipe  (1, "Equipe Gerard", 50000);
		testEquipe = daoEquipe.updateById(testEquipe);
		String name =  getInserter().select("select equipe.name from equipe where id = 1").getDataAsString();
		assertEquals(testEquipe.getNameEquipe(), name);
		Equipe a = daoEquipe.updateById(null);
		assertNull(a);
	}





}
