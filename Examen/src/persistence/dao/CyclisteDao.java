package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import business.entite.Cycliste;
import business.entite.Equipe;
import persistence.exception.DaoException;
import persistence.manager.JDBCManagerSolo;

public class CyclisteDao implements IDAO<Cycliste> {
	
	public final static String _Select = "select * from cycliste "
			+ "inner join equipe on equipe.id = cycliste.equipe_id";
	public final static String _SelectId = "select * from cycliste "
			+ "inner join equipe on equipe.id = cycliste.equipe_id where cycliste.id = ?";
	public final static String _Insert = "insert into cycliste (name,equipe_id,nombre_velos) values (?,?,?)";
	public final static String _Update = "update cycliste set name = ?, equipe_id = ?, nombre_velos = ? where id = ?";
	public final static String _Delete = "delete from cycliste where id = ?";
	
	
	

	@Override
	public Cycliste create(Cycliste pT) throws DaoException {
		if (pT == null) {
			return null;
		}
		try {
			Connection cnx = JDBCManagerSolo.getInstance().openConection();
			PreparedStatement ps = cnx.prepareStatement(_Insert,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,pT.getNameCycliste());
			ps.setLong(2, pT.getEquipeId().getIdEquipe());	
			ps.setInt(3, pT.getNbVelo());
			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			long newId = 0L;
			while(rs.next()) {
				newId = rs.getLong("GENERATED_KEY");

			}
			rs.close();
			ps.close();
			pT.setIdCycliste(newId);


		} catch (Exception e) {
			throw new DaoException(e);	
		}

		return pT;	
	}

	@Override
	public Cycliste findById(long pId) throws DaoException {
		Cycliste cyclo = null;
		Equipe membre = null;
		Connection cnx;
		try {
			cnx = JDBCManagerSolo.getInstance().openConection();
			PreparedStatement pst1 = cnx.prepareStatement(_SelectId);
			pst1.setLong(1,pId);
			ResultSet rs1 = pst1.executeQuery();
			long idCycliste;
			String nameCycliste;			
			int nbVelo;
			while (rs1.next()) {
				idCycliste = rs1.getLong("cycliste.id");
				nameCycliste = rs1.getString("cycliste.name");			
				nbVelo = rs1.getInt("cycliste.nombre_velos");
				cyclo = new Cycliste(idCycliste,nameCycliste,null,nbVelo);
				
				
				cnx = JDBCManagerSolo.getInstance().openConection();
				PreparedStatement pst2 = cnx.prepareStatement(_SelectId);
				pst2.setLong(1,pId);
				ResultSet rs2 = pst2.executeQuery();				
				
				long idEquipe;
				String nameEquipe;
				long budget;
				while (rs2.next()) {
					idEquipe = rs2.getLong("equipe.id");
					nameEquipe = rs2.getString("equipe.name");
					budget = rs2.getLong("equipe.budget");
					membre = new Equipe(idEquipe,nameEquipe,budget);
					
				}
					
			cyclo = new Cycliste(idCycliste,nameCycliste,membre,nbVelo);		
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cyclo;
	}

	@Override
	public List<Cycliste> findList() throws DaoException {
		List<Cycliste> listCycliste = new ArrayList<>();
		Cycliste cyclo = null;
		Equipe membre = null;
		Connection cnx;
		try {
			cnx = JDBCManagerSolo.getInstance().openConection();
			PreparedStatement pst1 = cnx.prepareStatement(_Select);		
			ResultSet rs1 = pst1.executeQuery();

		
			long idCycliste;
			String nameCycliste;
			int nbVelo;
			while (rs1.next()) {
				idCycliste = rs1.getLong("id");
				nameCycliste = rs1.getString("name");				
				nbVelo = rs1.getInt("nombre_velos");
				cyclo = new Cycliste(idCycliste,nameCycliste,null,nbVelo);		
			
				
				cnx = JDBCManagerSolo.getInstance().openConection();
				PreparedStatement pst2 = cnx.prepareStatement(_Select);		
				ResultSet rs2 = pst2.executeQuery();				
				
				long idEquipe;
				String nameEquipe;
				long budget;
				while (rs2.next()) {
					idEquipe = rs2.getLong("id");
					nameEquipe = rs2.getString("name");
					budget = rs2.getLong("budget");
					membre = new Equipe(idEquipe,nameEquipe,budget);
					cyclo = new Cycliste(idCycliste,nameCycliste,membre,nbVelo);
					
				}
					listCycliste.add(cyclo);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCycliste;
	}

	@Override
	public Cycliste updateById(Cycliste pT) throws DaoException {
		if (pT == null ) {
			return null;
		}

		try {		

			Connection cnx1 = JDBCManagerSolo.getInstance().openConection();
			PreparedStatement ps1 = cnx1.prepareStatement(_Update);
			ps1.setString(1,pT.getNameCycliste());
			ps1.setLong(2, pT.getEquipeId().getIdEquipe());
			ps1.setLong(3, pT.getNbVelo());
			ps1.setLong(4, pT.getIdCycliste());
			ps1.execute();


		} catch (Exception e) {
			try {
				JDBCManagerSolo.getInstance().closeConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DaoException(e);
		}


		return pT;
	}

	@Override
	public void deleteById(long pId) throws DaoException {
		try {
			Connection cnx = JDBCManagerSolo.getInstance().openConection();
			PreparedStatement ps = cnx.prepareStatement(_Delete);
			ps.setLong(1, pId);
			ps.execute();

		} catch (Exception e) {
			throw new DaoException(e);
		}

		
	}

}
