package src;

/**
 * 
 * @author malik
 *cette interface definie les methodes a implementer pour les objets qui se deplace de maniere autonome sur le terrain par opposition au objet
 *qui ne peuvent que tomber ou rouler .  Il s agit de tous les classes non abstraites heritiere de la classe Personnage .
 *@see ObjetVivant 
 *@see Mechant
 *
 */
public interface Deplacement {
	
	
	/**
	 * 
	 * @param map la carte sur laquelle se deplacera l objet dont la classe implementera cette interface 
	 * @throws DeplacementInvalideException il se peut que le mouvement ne soit pas correcte auquel cas cette exception sera declencher
	 * 
	 * @see Carte
	 * @see DeplacementInvalideException 
	 *
	 */
	public void seDeplacer(Carte map)throws DeplacementInvalideException;
}
