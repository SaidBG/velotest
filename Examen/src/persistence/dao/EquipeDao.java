package persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import business.entite.Equipe;

import persistence.exception.DaoException;
import persistence.manager.JDBCManagerSolo;

public class EquipeDao implements IDAO<Equipe> {

	public final static String _Select = "select * from equipe ";
	public final static String _SelectId = "select * from equipe where id = ?";
	public final static String _Insert = "insert into equipe (name,budget) values (?,?)";
	public final static String _Update = "update equipe set name = ?, budget = ? where id = ?";
	public final static String _Delete = "delete from equipe where id = ?";



	@Override
	public Equipe create(Equipe pT) throws DaoException {
		if (pT == null) {
			return null;
		}
		try {
			Connection cnx = JDBCManagerSolo.getInstance().openConection();
			PreparedStatement ps = cnx.prepareStatement(_Insert,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,pT.getNameEquipe());
			ps.setLong(2, pT.getBudget());			
			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			long newId = 0L;
			while(rs.next()) {
				newId = rs.getLong("GENERATED_KEY");

			}
			rs.close();
			ps.close();
			pT.setIdEquipe(newId);


		} catch (Exception e) {
			throw new DaoException(e);	
		}

		return pT;		
	}

	@Override
	public Equipe findById(long pId) throws DaoException {
		Equipe membre = null;
		Connection cnx;
		try {
			cnx = JDBCManagerSolo.getInstance().openConection();
			PreparedStatement pst2 = cnx.prepareStatement(_SelectId);
			pst2.setLong(1,pId);
			ResultSet rs2 = pst2.executeQuery();

			long idEquipe;
			String nameEquipe;
			long budget;
			while (rs2.next()) {
				idEquipe = rs2.getLong("id");
				nameEquipe = rs2.getString("name");
				budget = rs2.getLong("budget");
				membre = new Equipe(idEquipe,nameEquipe,budget);
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return membre;
	}

	@Override
	public List<Equipe> findList() throws DaoException {
		List<Equipe> listEquipe = new ArrayList<>();
		Equipe membre = null;
		Connection cnx;
		try {
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
				listEquipe.add(membre);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listEquipe;
	}

	@Override
	public Equipe updateById(Equipe pT) throws DaoException {
		if (pT == null ) {
			return null;
		}

		try {		

			Connection cnx1 = JDBCManagerSolo.getInstance().openConection();
			PreparedStatement ps1 = cnx1.prepareStatement(_Update);
			ps1.setString(1,pT.getNameEquipe());
			ps1.setLong(2, pT.getBudget());
			ps1.setLong(3, pT.getIdEquipe());
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
