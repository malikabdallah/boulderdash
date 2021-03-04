package src;

/**
 * 
 * @author malik
 * 
 * 
 * 
 * cette classe definit les individus a partir dequels l algorithme genetique va etre execute
 * 
 * 
 * @see Genetique
 * 
 *
 */
public class Individu implements Comparable<Individu> {
	
	
	
	
	
	
	
	
	@Override
	public int compareTo(Individu arg0) {
		//int reste=this.score-arg0.getScore();
		int reste=arg0.getScore()-this.score;
				
		if(reste==0){
			reste=this.numero-arg0.getNumero();
		}
		return reste;
	}

	public int getNumero() {
		return numero;
	}

	private static int cptInstance=0;
	private int numero;
	private Simplet simplet;
	private int score;
	private Directif directif;
	
	
	
	
	
	
	
	public Directif getDirectif() {
		return directif;
	}

	public void setDirectif(Directif directif) {
		this.directif = directif;
	}

	public Simplet getSimplet() {
		return simplet;
	}

	public void setSimplet(Simplet simplet) {
		this.simplet = simplet;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Individu(Simplet simplet){
		cptInstance++;
		this.numero=cptInstance;
		this.simplet = simplet;
		this.score =0;
	}
	
	
	public Individu(Directif directif) {
		super();
		cptInstance++;
		this.numero=cptInstance;
		this.directif = directif;
		this.score =0;
	}

	
	
	
	

	@Override
	public String toString() {
		return this.score+"score ";
	} 
	
	
}