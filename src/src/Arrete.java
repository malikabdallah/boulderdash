package src;

/**
 * La classe Arrete represente les arretes d'un graphe.
 * <p>
 * 	Elle est compose de deux Sommet
 * 	<ul>
 * 		<li>Le premier sommet represente la zone de depart    </li>
 * 		<li>le deuxieme sommet represente la zone d arrivee </li>
 * 
 * 	
 * 	</ul>
 * 	Dans un graphe non oriente ce qui est le cas ici on pouura trouver respectiement les arretes {u,v} et {v,u}
 * 
 * 
 * </p>
 * 
 *
 *
 *
 *@see Sommet
 *@see GrapheListe
 *@see Graphe 
 */

public class Arrete {


	
	/**
	 * Il s'agit la des deux sommet qui compose une arrete
	 */
	private Sommet d,a;
	

	/**
	 * on instancie une arrete en lui fournissant les deux sommets en parametre
	 * @param d le premier sommet de l'arrete
	 * @param a le deuxieme sommet de l arrete 
	 */
	public Arrete(Sommet d,Sommet a){
		this.d=d;
		this.a=a;

	}

	/**
	 * 
	 * @param a creation d une arrete a partir d une arrete fourni en parametre
	 */
	public Arrete(Arrete a){
		this.a=a.getSommetA();
		this.d=a.getSommetD();

	}

	
	
	/**
	 * 
	 * @return le sommet d arrive de l arrete
	 */
	public Sommet getSommetA(){
		return this.a;
	}
	
	
	/**
	 * 
	 * @return le sommet de depart de l arrete
	 */
	public Sommet getSommetD(){
		return this.d;
	}

	
	/**
	 * 
	 * @param a attribut au sommet d arrive le sommet fourni en parametre
	 */
	public void setSommetA(Sommet a){
		this.a=a;
	}

	
	
	/**
	 * 
	 * @param d attribut au sommet de depart le sommet fourni en parametre
	 */
	public void setSommetD(Sommet d){
		this.d=d;
	}

	
	/**
	 * 
	 */
	public boolean equals(Object aa){
		Arrete a=(Arrete)aa;
		return a.equals(a.getSommetA())&&d.equals(a.getSommetD());
	}

	
	/**
	 * 
	 */
	public String toString(){
		return this.a.toString();
	}
}









