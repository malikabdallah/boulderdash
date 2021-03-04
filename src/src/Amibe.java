package src;

/**
	 * la classe amibe represente les enemies de types amibe , il s agit d un enemie qui agit comme un sangsue qui s aggrandit d une case 
	 *  bout d un certain nombre de temps .
	 *  @see Mechant
	 *  @see ObjetVivant
	 *  @see Deplacement
	 *  
	 */
	
public class Amibe extends Mechant {

	
	
	/**
	 
	 * cette methode permet de selectionner la case sur laquelle l'amibe va s aggrandir , elle est choisit aleatoirement parmi le nombre de case
	 * possible 
	 */
	 
	@Override
	public void seDeplacer(Carte map) throws DeplacementInvalideException {
		// TODO Auto-generated method stub
		throw new DeplacementInvalideException("deplacement inccorect ! ");
	}

	
	
	public Amibe(int absice, int ordonne) {
		super(absice, ordonne ,'e');
		// TODO Auto-generated constructor stub
	}



	/**
	 * cette methode de changer le mv qui determine quel case sera prise par l amibe ( e pour celle au dessus de celle ou se trouve l amibe etc) .
	 */
	@Override
	public void changementDeCap() {
		// TODO Auto-generated method stub
		if(this.getMv()=='e'){
			this.setMv('d');
		}else if(this.getMv()=='d'){
			this.setMv('e');
		}else if(this.getMv()=='f'){
			this.setMv('s');
		}else {
			this.setMv('f');
		}
		
	}

}