package src;

/**

 * @author Malik
 * 
 * cette classe implemente l algorithme genetique pour chercher une solution gagnante dans le jeu
 * 
 * <p>
 * 		  elle est compose 
 * 
 *			<ul>	<li> une List<Character> listeCoup : qui est la liste des coups jouer par l ia</li>
 *					<li> une List<Character> listeRetenueCoup : qui va sauvegarder les coups jouers car la premiere liste se vide
 *					petit a petit</li>
 *
 *			 </ul>
 * </p>
 * 
 * 
 * 
 * 
 */


import java.util.*;
public class Genetique implements Ia{
	
	
	private List<Character> listeCoup;
	private List<Character> listeRetenueCoup;
	
	
	
	
	
	@Override
	/**
	 * renvoie le coup jouer par l ia 
	 */
	public char prochainCoup() {
		char mouvement='e';
		if(!listeCoup.isEmpty()){
			 mouvement=listeCoup.get(0);
		}else{
			
		}
		listeCoup.remove(0);
		return mouvement;
	}



	
	
	/**
	 * cette methode genere la premiere generation a partir de laquelle l algorithme va se mettre en place
	 * on va donc generer un nombre definit d individu qui auront comme particularite de pouvoir simuler une partie
	 * avec un attribut implementant Ia qu ils ont et de scorer cette partie .
	 * @see Individu
	 * @param nbCase le nombre d individu a initialiser
	 * @param carte 
	 * @return la liste des individu creer
	 */
	public List<Individu> initiationPopulation(int nbCase, Carte carte ){
		List<Individu>list=new ArrayList<Individu>();
		for(int i=1;i<=nbCase;i++){
			list.add(new Individu(new Simplet(carte)));
		}
		
		
		return list;
	}
	
	
	
	/**
	 * cette methode va permettre d evaluer un individu fourni en parametre en faisant appele a la methode
	 * qui simule une partie avec une ia dans la classe jeu .
	 * suivant le resultat de cette partie alors l individu aura un score plus ou moins eleve .
	 * 
	 * S il gagne alors il aura 1000 points additionner au nombre de diamant obtenue obtenue multipliez par 50 soustrait du temps restant
	 * s il reste en vie mais perd alors il aura 300 point additionner au nombre de diamant obtenue multipliez par 20 soustrait du temps restant
	 * s il meurt alors il aura 50 point additionner au nombre de diamant obtenue multipliez par 3 soustrait du temps restant
	 * 
	 * 
	 * 
	 * @param carte sur laquelle va se derouler l evaluation
	 * @param jeu qui va simuler l evaluation 
	 * @param individu qui va etre evalue
	 * @return
	 */
	public boolean evaluationDesIndividus(Carte carte , Jeu jeu, Individu individu){
		Cave cave=new Cave(carte.getCave().getName(),carte.getCave().getCaveTime(),carte.getCave().getDiamondsRequire(),carte.getCave().getDiamondValue(),carte.getCave().getAmoebaTime(),carte.getCave().getMagicWallTIme());
		char [][]terrain=new char[carte.getTerrain().length][carte.getTerrain()[0].length];
		char [][]tableauSauvegarde=new char[carte.getTerrain().length][carte.getTerrain()[0].length];
		
		for(int i=0;i<=terrain.length-1;i++){
			for(int j=0;j<=terrain[0].length-1;j++){
				terrain[i][j]=carte.getTerrain()[i][j];
				tableauSauvegarde[i][j]=' ';
			}
		}
		Carte carteIndi=new Carte(cave,terrain,tableauSauvegarde,jeu);
		carteIndi.setAbSortie(carte.getAbSortie());
		carteIndi.setOrdSortie(carte.getOrdSortie());
		boolean b=jeu.simulerPartieAvecIa(carteIndi, individu.getSimplet(),true);
		if(b){
			individu.setScore(1000+carteIndi.getNbDiamant()*50-carteIndi.getCave().getCaveTime());
			return true;
		}else{
			if(jeu.heroEncoreDansListe(carteIndi.getPersonnageSurLeTerrain())){
				individu.setScore(300+carteIndi.getNbDiamant()*20-carteIndi.getCave().getCaveTime());
				
			}else{
				individu.setScore(50+carteIndi.getNbDiamant()*3-carteIndi.getCave().getCaveTime());
			}
		return false;
		}
	}
	
	
	
	
	
	
	
	/**
	 * cette methode renvoie les meilleurs individues (50 % du total )
	 * 
	 * 
	 * @param list des individues total
	 * @return list des individes apres selection de la meilleur moitie
	 */
	public List<Individu> selection (List<Individu> list){
		Object[]tableau=list.toArray();
		Arrays.sort(tableau);
		List<Individu>select=new ArrayList<Individu>();
		for(int i=0; i<=(tableau.length-1)/2; i++){
			select.add((Individu)tableau[i]);
		}
		
		return select;
		
		
		
	}
	
	
	
	/**
	 * 
	 * @param carte sur laquelle l algorithme genetique va etre applquer
	 * @param jeu pour simuler une partie avec des ia
	 * 
	 * Une population intiale de 100 individues va etre creer puis evaluer . Les meilleurs elements elements vont etre selectionner
	 * ils vont subir des croissement et mutation pour donner des enfants , qui seront evaluer a leur tour et cette nouvelle
	 * generation sera evaluer .
	 * On procedera da la sorte jusqu'a obtenir un individu qui gagner ou que le seuil fatidique des 1000 generations eu ete atteint.
	 * 
	 * 
	 * 
	 */
	public Genetique (Carte carte ,Jeu jeu ){
		boolean partieGagner=false;
		List<Individu>list=new ArrayList<Individu>();
		list=initiationPopulation(100,carte);
		this.listeRetenueCoup=new ArrayList<Character>();
		int cpt=1;
		for(Individu indi: list){
			partieGagner=evaluationDesIndividus(carte,jeu,indi);
			this.listeRetenueCoup=indi.getSimplet().getListeCoup();
			if(partieGagner){
				break;
			}
		}
		cpt=1;
		this.listeCoup=list.get(0).getSimplet().getListeCoup();
		int nb=50;
		if(!partieGagner){
			while(cpt<=1000 && !partieGagner){
				list=selection(list);
				cpt++;
				
				
				List<Individu>listeEnfantCree=new ArrayList<Individu>();
				for(int i=1;i<=nb;i++){
					int rand1=(int)(Math.random()*list.size()-1)+0;
					int rand2=(int)(Math.random()*list.size()-1)+0;
					while(rand1==rand2){
						 rand1=(int)(Math.random()*list.size()-1)+0;
						 rand2=(int)(Math.random()*list.size()-1)+0;
					}
					Individu x=croisement(list.get(rand1),list.get(rand2));
					if( i<=nb*.3){
						x=mutation(x,carte);
					}
					partieGagner=evaluationDesIndividus(carte,jeu,x);
					listeEnfantCree.add(x);
					if(partieGagner){
						break;
					}
					
				}
				for(Individu indi:listeEnfantCree){
					list.add(indi);
				}
			}
		}
		list=selection(list);	
		this.listeCoup=list.get(0).getSimplet().getListeCoup();
		this.listeRetenueCoup=new ArrayList<Character>();
		for(int i=0;i<=list.get(0).getSimplet().getListeCoup().size()-1; i++){
			listeRetenueCoup.add(list.get(0).getSimplet().getListeCoup().get(i));
		}

		
	}
	
	
	
	
	
	
	
	@Override
	public String listeCoup() {
		
		String listeC="";
		for(int i=0;i<=this.listeRetenueCoup.size()-1;i++){
			listeC=listeC+this.listeRetenueCoup.get(i);
		}
		return listeC;
	}


	@Override
	public String toString() {
		return "Genetique ";
	}


	
	
	/**
	 * 
	 * cette methode va faire muter l individu passer en parametre c est a dire que son ensemble de solution va etre
	 * totalement changer et remplacer par celle d'une nouvelle ia .
	 * 
	 * 
	 * @param mutable l individu qui va muter
	 * @param carte la carte a partir duquel une nouelle liste de coup va etre evaluer
	 * @return l individu passer en parametre qui a mute
	 */
	public Individu mutation(Individu mutable , Carte carte){
		mutable.setSimplet(new Simplet(carte));
		return mutable;
		
	}
	
	
	
	
	
	/**
	 * 
	 * 
	 * cette methode va croiser les deux individus fourni en parametre pour founir un individu dont les caracteristiques seraient
	 * un melange entre les deux parents 
	 * 
	 * @param parent1 un parent de l individu 
	 * @param parent2 un autre parent de l individu
	 * @return un individu enfant issue d un melange des deux parents fourni en parametre
	 * 
	 */
	public Individu croisement (Individu parent1, Individu parent2){
		
		Object[]ListeCoupParent1=parent1.getSimplet().getListeCoup().toArray();
		
		Object[]ListeCoupParent2=parent2.getSimplet().getListeCoup().toArray();
		int rand=(int)(Math.random()*10)+1;
		double rest=rand*.1;
		
		Object[] listeCoupEnfant=new Object[ListeCoupParent1.length];
		
		List<Character>listeCoupEnf=new ArrayList<Character>();
		for(int i=0; i<=listeCoupEnfant.length-1; i++){
			if(i<=(listeCoupEnfant.length-1)*rest){
				listeCoupEnfant[i]=ListeCoupParent1[i];
				listeCoupEnf.add((Character)ListeCoupParent1[i]);
			}else{
				listeCoupEnfant[i]=ListeCoupParent2[i];
				listeCoupEnf.add((Character)ListeCoupParent2[i]);
			}
		}
		;
		
		Simplet enfant1=new Simplet(listeCoupEnf);
		Individu enfant=new Individu(enfant1);
		return enfant;
	}
	
	
	
	
	
	
	
	
}

