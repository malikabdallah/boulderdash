package src;

import java.util.*;
public abstract class ObjetVivant extends ElementTerrain implements Deplacement {
	
	


	private boolean enVie;
	
	static Scanner lire=new Scanner(System.in);
	
	@Override
	public abstract void seDeplacer(Carte map) throws DeplacementInvalideException ;


	
	private char mv=' ' ;
	
	
	public char getMv() {
		return mv;
	}




	public void setMv(char mv) {
		this.mv = mv;
	}
	
	
	

	public ObjetVivant(int absice, int ordonne) {
		super(absice, ordonne);
		this.enVie = true;
	}



	public boolean estEnVie() {
		return enVie;
	}


	public void meurt(){
		this.enVie=false;
	}






	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (enVie ? 1231 : 1237);
		return result;
	}






	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjetVivant other = (ObjetVivant) obj;
		if (enVie != other.enVie)
			return false;
		return true;
	}


	
	

	
	
	
	

}
