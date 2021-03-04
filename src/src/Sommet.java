package src;

/**
 * 
 * @author Malik
 * 
 * 
 * cette classe represente les sommets qui composent un graphe 
 * 
 * 
 * ils sont representer par 
 * 
 * <ul>
 * 		<li> un entier absice qui correspond a l absice ou se trouve le sommet </li>
 * 		<li> un entier ordonne qui correspond a l ordonne ou se trouve le sommet </li>
		 * 		<li> un string qui indique si le sommet a deja ete visiter </li>

		 * 		<li>un entier static nbInstance qui compte le nombre d instance sommet creer </li>
		
		 * 		<li> le numero du sommet </li>

		 * 		<li> un sommet parent qui indiqiue comment on est arrivé sur ce sommet </li>

 * 
 * 
 * </ul>
 *
 */


public class Sommet{


	private int absice;
	private int ordonne;
	private String marque;
	private static int nbInstance=0;
	private int numero;
	private Sommet parent;

	public int getNumero(){
		return numero;

	}


	
	
	public static void reinitialisation(){
		nbInstance=0;
	}
	
	public Sommet(int absice,int ordonne){
		
		this.numero=nbInstance;
		nbInstance++;
		this.absice=absice;
		this.ordonne=ordonne;
		this.marque="N.A";
	}


	


	public int getAbsice(){
		return this.absice;
	}

	public int getOrdonne(){
		return this.ordonne;
	}

	public String getMarque(){
		return this.marque;
	}


	public void setAbsice(int absice){
		this.absice=absice;
	}

	public void setOrdonne(int ordonne){
		this.ordonne=ordonne;
	}

	public void setMarque(String marque){
		this.marque=marque;
	}


	


	public Sommet (Sommet s){
		this.absice=s.getAbsice();
		this.ordonne=s.getOrdonne();
		this.marque="N.A";
	}


	public String valeurMarque(){
		return marque;
	}

	public void modifierMarque(String marque){
		this.marque=marque;
	}

	public boolean equals(Object o){
		Sommet autre=(Sommet) o;
		return this.absice==autre.getAbsice()
		&& this.ordonne==autre.getOrdonne()
		&& this.marque.equals(autre.getMarque());

	}

	
	public String toString(){
		return "   Numero :"+this.numero+" ; abscie:"+this.absice+" ; ordonne:"+this.ordonne+" ; marque:"+this.marque;

	}


	public Sommet getParent() {
		return parent;
	}


	public void setParent(Sommet parent) {
		this.parent = parent;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}
	








}
