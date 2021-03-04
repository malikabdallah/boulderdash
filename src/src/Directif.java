package src;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author malik
 * Cette Ia applique des algorihmes de plus courts chemin sur des cibles choisis
 * <p>	
 * 		elle est compose de plusieur attributs 
 * 		<ul> 
 * 				<li> 	private GrapheListe listeAdjacence : qui est le graphe representant la liste d 'adjacence </li>
 * 				<li>    private List<Character> listeCoup: qui est l'ensemble des coup qui vont etre jouer par lia </li>
 * 				<li>    private List<Character>listeCoupRetenue : qui est une liste pour retenir l ensemble des coup jouer car
 * 				la listeCoup va se vider petit a petit </li>
 *              <li>   	private Carte carte: qui est la carte a partir de laquelle le graphe va etre construit et le parcour en largeur va etre 
 *              	appliquer </li>
		
	
				<li>private Sommet destinationFinal: qui represente a chaque application de l algorithme de parcour en largeur
				  la cible chercher </li>
 * 		</ul>
 * 
 * 
 * 
 * 
 * </p>
 * 
 *
 */

public class Directif implements Ia {
	
	
	/**
	 * cette methode renvoie le prochain coup jouer par cette ia .
	 */
	public char prochainCoup() {
		
		while(listeCoup==null || listeCoup.size()==0){
			listeCoup=calculeStrategie(carte);
		}
		if(listeCoup==null || listeCoup.size()==0){
			listeCoup=new Simplet(carte).getListeCoup();
		}
			char mv=listeCoup.get(0);
			listeCoup.remove(0);
			listeCoupRetenue.add(mv);
			return mv;
		
	}

	
	
	
	public List<Character> getListeCoupRetenue() {
		return listeCoupRetenue;
	}

	public void setListeCoupRetenue(List<Character> listeCoupRetenue) {
		this.listeCoupRetenue = listeCoupRetenue;
	}

	public List<Character> getListeCoup() {
		return listeCoup;
	}



	public void setListeCoup(List<Character> listeCoup) {
		this.listeCoup = listeCoup;
	}
	
	
	
	/**
	 * Le graphe sur lequel s applique le parcour en largeur
	 */
	private GrapheListe listeAdjacence;
	
	/**
	 * la liste des coup jouer par l ia
	 */
	private List<Character> listeCoup=new ArrayList<Character>();
	
	/**
	 * la sauvegarde des mouvement jouer par l ia 
	 */
	private List<Character>listeCoupRetenue=new ArrayList<Character>();
	
	/**
	 * la carte sur laquelle  l ia va s applique
	 */
	private Carte carte;
	
	/**
	 * private Sommet destinationFinal
	 */
	private Sommet destinationFinal; 
	
	
	
	public Directif(Carte carte){
		this.carte=carte;
		if(carte!=null){
			listeAdjacence=new GrapheListe(carte);
		}
		System.out.println(listeAdjacence.toString());
		
	}
	
	
	public Directif(List<Character> list){
		this.listeCoup=new ArrayList<Character>(list);
		this.listeCoupRetenue=new ArrayList<Character>();
		listeCoupRetenue.addAll(list);
		this.listeAdjacence=new GrapheListe(this.carte);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param carte
	 * @return le chemin obtenue vers une cible designer
	 * 
	 * 
	 * Les sommets du graphes sont initialise , si la sortie est affiché alors on lance un parcourt en largeur vers la sortie
	 * sinon on lance un parcours en largeur vers un diamant  . Si aucune de ces cibles n'est atteignable alors on genere 
	 * un chemin aleatoirement avec l ia simplet .
	 * 
	 * 
	 * 
	 */
	private List<Character> calculeStrategie(Carte carte){
		Sommet s=null;
		Joueur joueur=(Joueur)carte.getJoueur();
		Sommet.reinitialisation();
		this.listeAdjacence=new GrapheListe(carte);
		List<Character>list=new ArrayList<Character>();
		Sommet.reinitialisation();
		for(Sommet sommet: listeAdjacence.getS()){
			if(joueur!=null){
				if(sommet.getAbsice()==joueur.getAbsice()&&sommet.getOrdonne()==joueur.getOrdonne()){
					s=sommet;
					break;
			}
			}
		}
	
		
		if(carte.getTerrain()[carte.getAbSortie()][carte.getOrdSortie()]!='X'){
			algorithmeParcourLargeur(listeAdjacence,s,'d');
			list=plusCourtChemin();
			if(this.destinationFinal==null){
				
				Simplet x=new Simplet(carte);
				
				list=x.getListeCoup();
			}
		
		}else{
			
			for(Sommet sommet: this.listeAdjacence.getS()){
				if(sommet.getAbsice()==carte.getAbSortie() && sommet.getOrdonne()==carte.getOrdSortie()){
					
					destinationFinal=sommet;
				}
			}
			algorithmeParcourLargeur(listeAdjacence,s,'X');
			list=plusCourtChemin();
			if(this.destinationFinal.getParent()==null){
						
						Simplet x=new Simplet(carte);
						
						list=x.getListeCoup();
			}
		}
		
		return list;
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @return la liste du plus court chemin obtenue
	 * 
	 * on se position sur le sommet destination final et on remonte ses parents au fur et a mesure pour recuperer l ensemble
	 * des mouvement effectuer pour aller du sommet d origine jusqu'a celui de la cible .
	 */
	public List<Character> plusCourtChemin(){
		List<Character> list=new ArrayList<Character>();
	
		Sommet x=this.destinationFinal;
		
		
		while(x!=null && x.getParent()!=null){
			
			Sommet pere=x.getParent();
			if(pere.getAbsice()>x.getAbsice()){
				list.add('e');
			}
			if(pere.getAbsice()<x.getAbsice()){
				
				list.add('d');
				
				
			}
			if(pere.getOrdonne()<x.getOrdonne()){
				list.add('f');
			}
			if(pere.getOrdonne()>x.getOrdonne()){
				
				list.add('s');
				
				
			}
			
			x=x.getParent();
			
		}
	
		
		Object[]tab=list.toArray();
		for(int i=tab.length-1; i>=0;i--){
			this.listeCoup.add((char)tab[i]);
		}
		return listeCoup;
	}
	
	
	
	
	
	
	/**
	 * 
	 * @param a sommet don on va verifie l accessibilite
	 * @return boolean verifiiant l accessibilite du sommet passer en parametre
	 * 
	 * si le sommet en question est sur une case innacessible alors on renvoie false sinon vrai , 
	 * cela servira a eviter les chemins impossible lors du calcule de plus court chemin 
	 */
	public boolean caseAccessible(Sommet a){
		return carte.getTerrain()[a.getAbsice()][a.getOrdonne()]!='w'&&
				carte.getTerrain()[a.getAbsice()][a.getOrdonne()]!='W' &&
						carte.getTerrain()[a.getAbsice()][a.getOrdonne()]!='M' &&
						carte.getTerrain()[a.getAbsice()][a.getOrdonne()]!='r';
	}
	
	
	/**
	 * touts les sommet sont initialise telle que prevu par l algorithme de parcours en largeur 
	 */
	private void initialisation(){
		for(Sommet sommet: this.listeAdjacence.getS()){
			sommet.setParent(null);
		}
	}
	
	
	/**
	 * applique l algorithme de parcours en largeur a la recherche d un sommet dont les coordonnes sur le tableau
	 * donne le caractere c ( la cible ) passer en parametre 
	 * @param listeAdjacence  le graphe sur lequel on va effectuer le parcours en largeur
	 * @param s le sommet d origine
	 * @param c la cible 
	 */
	private void algorithmeParcourLargeur(GrapheListe listeAdjacence,Sommet s,char c){
		initialisation();
		s.setMarque("A.T");
		
		List<Sommet>list=new ArrayList<Sommet>();
		list.add(s);
		boolean objectifAtteint=false;
		while(!list.isEmpty()&& objectifAtteint==false){
			Sommet p=list.get(0);
			list.remove(0);
			for(Arrete arrete:listeAdjacence.getL().get(p.getNumero())){
				
				if(arrete.getSommetA().getMarque()!="A.T" && caseAccessible(arrete.getSommetA())){
					arrete.getSommetA().setMarque("A.T");
					if(carte.getTerrain()[arrete.getSommetA().getAbsice()][arrete.getSommetA().getOrdonne()]==c){
						objectifAtteint=true;
						this.destinationFinal=arrete.getSommetA();
						this.destinationFinal.setParent(arrete.getSommetD());
												
						
						break;
					}
					
					list.add(arrete.getSommetA());
					arrete.getSommetA().setParent(p);
					
				}
			}
			
		}
		
		
		
		
	}

	@Override
	public String listeCoup() {
		String listeC="";
		for(int i=0;i<=this.listeCoupRetenue.size()-1;i++){
			listeC=listeC+this.listeCoupRetenue.get(i);
		}
		return listeC;
	}

	@Override
	public String toString() {
		return "Directif";
				
	}
	
	
	
	
	

}
