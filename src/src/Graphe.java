package src;

import java.util.Collection;


/** cette classe abstraite sert de super classe a la classe grapheListe 
 * 
 */



public abstract class Graphe{

	/**
	 * 
	 * @return la taille du graphe
	 */
	public abstract int taille();
	
	/**
	 * 
	 * @param s sommet a ajouter au graphe
	 */
	public abstract void ajouterSommet(Sommet s);
	
	/**
	 * creer une arrete {s,t} a ajouter au graphe
	 * @param s
	 * @param t
	 */
	public abstract void ajouterArrete(Sommet s,Sommet t);
	


}
