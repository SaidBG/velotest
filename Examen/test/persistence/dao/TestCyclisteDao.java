package persistence.dao;

import java.util.List;


import business.entite.Cycliste;
import business.entite.Equipe;
import exception.SqlInserterException;
import persistence.exception.DaoException;
import persistence.pere.TU_Pere;

public class TestCyclisteDao extends TU_Pere {


	int realNb = 0;
	EquipeDao daoEquipe = new EquipeDao();
	Equipe testEquipe = null;
	CyclisteDao daoCycliste = new CyclisteDao();
	Cycliste testCycliste = null;




	public void setUp() throws Exception {			
		super.setUp();

		realNb = getInserter().select("select count(id) from cycliste").getDataAsInt();
	}


	public void testList() throws DaoException {
		List<Cycliste> listCycliste = daoCycliste.findList();	
		assertEquals(realNb,listCycliste.size());
	}

	public void testFindById() throws DaoException, SqlInserterException {
		testCycliste = daoCycliste.findById(2);
		String name = getInserter().select("select cycliste.name from cycliste where id = 2").getDataAsString();
		assertEquals(name,testCycliste.getNameCycliste());	
	}


	public void testDeleteById() throws DaoException {
		testEquipe = daoEquipe.findById(1);
		assertNotNull(testEquipe);
		testCycliste = new Cycliste  (5,"Gerard",testEquipe, 5);
		daoCycliste.create(testCycliste);
		assertNotNull(testCycliste);
		long newId = testCycliste.getIdCycliste();
		assertEquals(realNb+1, daoCycliste.findList().size());

		daoCycliste.deleteById(newId);		
		assertNull(daoCycliste.findById(newId));			
	}

	public void testCreate () throws DaoException {
		List<Cycliste> listCycliste  = daoCycliste.findList();
		String name ="";

		try {
			testEquipe = daoEquipe.findById(1);
			assertNotNull(testEquipe);
			testCycliste = new Cycliste  (0, "Gerard",testEquipe, 5);
			
			daoCycliste.create(testCycliste);
			assertNotNull(testCycliste);
			
			long newId = testCycliste.getIdCycliste();
			name = getInserter().select("select cycliste.name from cycliste where id =" + newId).getDataAsString();
			listCycliste.add(testCycliste);	
			
			assertEquals(name,testCycliste.getNameCycliste());				
			assertEquals(realNb+1, listCycliste.size());
		
		} catch (SqlInserterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Cycliste nulos = daoCycliste.findById(testCycliste.getIdCycliste());
		assertEquals(nulos.getNameCycliste(),testCycliste.getNameCycliste());
		nulos = daoCycliste.create(null);
		assertNull(nulos);
	}

	public void testUpdate() throws DaoException, SqlInserterException {
		List<Cycliste> listCyliste = daoCycliste.findList();	
		
		testCycliste = daoCycliste.findById(1);
		assertNotNull(testCycliste);
		
		testEquipe = daoEquipe.findById(1);
		assertNotNull(testEquipe);
		
		testCycliste = new Cycliste  (1, "Equipe Said",testEquipe, 5);
		testCycliste = daoCycliste.updateById(testCycliste);
		
		String name =  getInserter().select("select cycliste.name from cycliste where cycliste.id = 1").getDataAsString();
		assertEquals(testCycliste.getNameCycliste(), name);
		Equipe a = daoEquipe.updateById(null);
		assertNull(a);
	}

	
	
	
}
