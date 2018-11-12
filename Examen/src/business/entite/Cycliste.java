package business.entite;

public class Cycliste {

	private long idCycliste;
	private String nameCycliste;
	private Equipe equipeId;
	private int nbVelo;
	public long getIdCycliste() {
		return idCycliste;
	}
	public void setIdCycliste(long idCycliste) {
		this.idCycliste = idCycliste;
	}
	public String getNameCycliste() {
		return nameCycliste;
	}
	public void setNameCycliste(String nameCycliste) {
		this.nameCycliste = nameCycliste;
	}
	public Equipe getEquipeId() {
		return equipeId;
	}
	public void setEquipeId(Equipe equipeId) {
		this.equipeId = equipeId;
	}
	public int getNbVelo() {
		return nbVelo;
	}
	public void setNbVelo(int nbVelo) {
		this.nbVelo = nbVelo;
	}
	public Cycliste(long idCycliste, String nameCycliste, Equipe equipeId, int nbVelo) {
		super();
		this.idCycliste = idCycliste;
		this.nameCycliste = nameCycliste;
		this.equipeId = equipeId;
		this.nbVelo = nbVelo;
	}
	
	public Cycliste() {
		
	}
	
	
	
}
