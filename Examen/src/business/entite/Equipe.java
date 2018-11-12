package business.entite;

public class Equipe {
	private long idEquipe;
	private String nameEquipe;
	private long budget;
	private String couleur;
	
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public long getIdEquipe() {
		return idEquipe;
	}
	public void setIdEquipe(long idEquipe) {
		this.idEquipe = idEquipe;
	}
	public String getNameEquipe() {
		return nameEquipe;
	}
	public void setNameEquipe(String nameEquipe) {
		this.nameEquipe = nameEquipe;
	}
	public long getBudget() {
		return budget;
	}
	public void setBudget(long budget) {
		this.budget = budget;
	}
	public Equipe(long idEquipe, String nameEquipe, long budget) {
		super();
		this.idEquipe = idEquipe;
		this.nameEquipe = nameEquipe;
		this.budget = budget;
	}
	public Equipe(long idEquipe, String nameEquipe, long budget, String couleur) {
		super();
		this.idEquipe = idEquipe;
		this.nameEquipe = nameEquipe;
		this.budget = budget;
		this.couleur = couleur;
	}
	
	
	
}	
