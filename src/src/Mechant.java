package src;

/**
 * 
 * @author malik
 * cette classe herite de la classe Personnage , elle servira de classe mere abstraite pour les classes Luciole , Libellule et Amibe .
 * Son role est de fournir les caracteristiques generales des personnages du jeu qui ne sont pas Rockford.
 * 
 * @see ObjetVivant 
 * @see Joueur
 * @see Luciole
 * @see Libellule
 * @see Amibe
 *
 */

public abstract class Mechant extends ObjetVivant {
	
	
	@Override
	public void seDeplacer(Carte map) throws DeplacementInvalideException {
		// TODO Auto-generated method stub
		
	}




	/**
	 * un enemie doit se deplacer dans une des quatres directions possible , ce mouvement est fixe par un des quatres  caractere possible 
	 * (e,f,d,s) et qui peut etre amene a change  si l'enemie rencontre un obstacle .Par exemple une classe derive pourra continuellement 
	 * faire le mouvement f jusqu'a ce qu'elle rencontre un obstacle puis a ce moment le mv sera change et prendre le caractere d et ainsi de suite .
	 * 
	 */
	private char mv ;
	
	/**
	 * si un enemie rencontre un obstacle il doit changer sa trajectoire
	 */
	//public abstract void changementDeCap();
	
	
	public Mechant(int absice, int ordonne, char mv) {
		super(absice, ordonne);
		this.mv=mv;
		// TODO Auto-generated constructor stub
	}
	
	
	public abstract void changementDeCap();



	
	public char getMv() {
		return mv;
	}




	public void setMv(char mv) {
		this.mv = mv;
	}
	
	


}