package src;

import java.util.*;
/**
 * 
 * @author malik
 *cette classe contient la liste des carte sur lequels se deroule une partie 
 *@see Carte
 */
public class Univers {

	ArrayList<Carte> listeMap;
	
	
	public Univers(){
		this.listeMap=new ArrayList<Carte>();
	}
	
	
	
	
	public void ajouterNiveau(Carte map){
		listeMap.add(map);
	}
	
}
