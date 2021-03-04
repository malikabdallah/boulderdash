package src;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Malik
 * 
 * il s agit de l ia simplet qui genere ses mouvements aleatoirements 
 * 
 * <p>
 * 
 * 		elle possede deux attributs une List<Character>listCoup qui contient les coups effectuer a chaque tour et listeCoupRetenue
 * 		qui servira a conserver ses coups . 
 * 
 * 
 * </p>
 *
 */
public class Simplet implements Ia{
	
	
	
	
	
	@Override
	public String toString() {
		return "Simplet ";
	}






	public Simplet (Carte carte){
		int x=carte.getCave().getCaveTime()*2 ;
		List<Character> list=new ArrayList<Character>();
		this.RetenuListeCoup=new ArrayList<Character>();
		for(int i=0;i<=x; i++){
			int rand=(int)(Math.random()*5)+0;
			switch(rand){
			case 0: list.add(' ');this.RetenuListeCoup.add(' ');break;
			case 1: list.add('e');this.RetenuListeCoup.add('e');break;
			case 2: list.add('f');this.RetenuListeCoup.add('f');break;
			case 3: list.add('d');this.RetenuListeCoup.add('d');break;
			case 4: list.add('s');this.RetenuListeCoup.add('s');break;
			}
		}
		this.listeCoup=list;
		
		
		
	}
	
	public Simplet(List<Character> listeCoup){
		this.listeCoup=listeCoup;
		this.RetenuListeCoup=new ArrayList<Character>();
		for(Character cara : listeCoup){
			RetenuListeCoup.add(cara);
		}
	}
	
	
	
	
	
	
	public List<Character> getListeCoup() {
		return RetenuListeCoup;
	}


	
	public String listeCoup(){
		String listeC="";
		for(int i=0;i<=this.RetenuListeCoup.size()-1;i++){
			listeC=listeC+this.RetenuListeCoup.get(i);
		}
		return listeC;
	}




	@Override
	public char prochainCoup() {
		// TODO Auto-generated method stub
		char mouvement='e';
		if(!listeCoup.isEmpty()){
			 mouvement=listeCoup.get(0);
		}else{
			
		}
		listeCoup.remove(0);
		return mouvement;
		
	}
	
	
	

	private List<Character> listeCoup;
	private List<Character> RetenuListeCoup;
	
	
	
	
	
}