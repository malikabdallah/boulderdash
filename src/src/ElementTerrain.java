package src;

import java.util.*;
/**
 * 
 * @author malik
 * cette classe represente les elements presents sur le terrain , elle sert de super classe a tous les elements du terrains qu'ils 
 * s agissent d objet vivant ou non .
 * <p>
 * 		elle possede un 
 * 		<ul>
 * 			<li> absice </li>
 * 			<li> ordonne </li>
 * 		</ul>
 * 		qui aide a la placer sur la carte terrain 
 * 
 * </p>
 *
 */
public abstract class ElementTerrain {
	
	
	
	static Scanner lire=new Scanner(System.in);
	
	
	public int getAbsice() {
		return absice;
	}

	public void setAbsice(int absice) {
		this.absice = absice;
	}

	public int getOrdonne() {
		return ordonne;
	}

	public void setOrdonne(int ordonne) {
		this.ordonne = ordonne;
	}

	private int absice;
	
	private int ordonne;


	public ElementTerrain(int absice, int ordonne) {
		super();
		this.absice = absice;
		this.ordonne = ordonne;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + absice;
		result = prime * result + ordonne;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementTerrain other = (ElementTerrain) obj;
		if (absice != other.absice)
			return false;
		if (ordonne != other.ordonne)
			return false;
		return true;
	}
	
	
	
	

}
