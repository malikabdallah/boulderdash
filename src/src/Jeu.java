package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
public class Jeu {

	
	
	static Scanner sc=new Scanner(System.in);
	
	
	/**
	 * 
	 * 
	 * cette classe represente le jeu Boulder dash c'est a dire que c'est dans cette classe que sera organise le deroule d une partie de Boulder
	 * dash .Elle possede donc un attribut Univers qui est correspond a la liste des Cartes disponible pour le jeu .
	 * Un attribut int qui correspond au niveau en cours lors d une partie et terrainDeLaPartie qui correspond a la carte du niveau associe .
	 * Cette classe s assure du bon deroulement du jeu , elle fait joueur les personnages au tour a tour , met a jour leur status (s il sont
	 * toujours en vie ou s'il vienne de mourrir) et elle s occupe aussi des chutes d objets et explosion .
	 *
	 */



		
		/**
		 * le niveau en cour 
		 */
		private int niveau ;
		
		/**
		 * l'ensemble des niveau 
		 * @Univers
		 */
		private Univers EnsembleNiveau=new Univers(); 
		
		/**
		 * la carte du niveau en cour
		 * @see Carte
		 */
		private Carte terrainDeLaPartie; 
		
		
		static Scanner lire=new Scanner(System.in);
		
		
		private void intro(){

			System.out.println("************************************************");
			System.out.println("                  BOULDER DASH                  ");
			System.out.println("************************************************");
			System.out.println("1) Visualiser les niveaux ");
			System.out.println("2) Jouer un niveau ");
			System.out.println("3) Jouer un niveau avec une Ia ");
			
			System.out.println("4) Notice utilisateur ");
			System.out.println("5) Quitter");
			System.out.println();
			System.out.println("Quel mode ? ");
			
			
			
		}
		
		
	
		
		
		public void calculeStrategie(int niveau,Ia ia  ){
	        System.out.println(new File(".").getAbsolutePath());

			File bdcff=new File("src\\BD01plus.bdcff");
			if(EnsembleNiveau!=null)
				LireFichier.lire(bdcff,EnsembleNiveau);
			
			this.niveau=niveau;
			this.terrainDeLaPartie=this.EnsembleNiveau.listeMap.get(niveau-1);
			System.out.println("STRATEGIE CALCULER ");
			this.simulerPartieAvecIa(terrainDeLaPartie, ia,true);
			
		}
		
		
		
		
	
		
		
		
		
		
		
		
		public void notice(){
			System.out.println();
			System.out.println("Mouvement : {e,E}=monter ;{d,D}=descendre;{s,S}=aller a gauche;{d,D}=aller a droite"
					+ "{' '}=rester sur place");
			System.out.println(" Vous avez la possibilité de jouer un niveau vous même en lancer le mode 2");
			System.out.println("Vous la possibilite de jouer un niveau avec une Ia parmi les trois Ia qui ont èté implementer");
			System.out.println("          Simplet : les mouvement du joueur seront definie aleatoirement 3");
			
			System.out.println("          Genetique: Plusieurs partie sont lancer et suite a des arrangements la partie optimal est selectionné (!Attention cette ia peut mettre du temps a fournir une solution "
					+ "donc il est recommandé de l employer sur des niveaux relativement cour ");
			System.out.println("          Directif: Fixe le plus court chemin vers des objectifs definit ou aleatoirement");
			
			System.out.println(" Le mode 4 vous permet de comparer l'efficacite de deux ia sur un meme niveau ");
			
			
		}
		
		
		
		/**
		 * creer l ensemble des niveaux ï¿½ partir du fichier bdcff et lance la partie apres avoir demande a l utilisateur d indiquer le niveau
		 * souhaite
		 * @throws FileNotFoundException 
		 */
		
		
		
		/**
		 * 	System.out.println("1) Visualiser les niveaux ");
			System.out.println("2) Jouer un niveau ");
			System.out.println("3) Jouer un niveau avec une Ia ");
			System.out.println("4) Comparer deux Ia sur un meme niveau ");
			System.out.println("5) Notice utilisateur ");
			System.out.println("6) Quitter");
			System
		 * @param niveau
		 */
		public Jeu (){
			boolean partieEnCour=true;
			while(partieEnCour){
			intro();
			int choix=lire.nextInt();
			File f=new File("src\\BD01plus.bdcff");
			EnsembleNiveau=new Univers();
			LireFichier.lire(f, EnsembleNiveau);
			switch(choix){
				case 1: f = new File ("src\\BD01plus.bdcff");LireFichier.lire(f,EnsembleNiveau);System.out.println(LireFichier.getFichier());break;
				case 2: f = new File ("src\\BD01plus.bdcff");LireFichier.lire(f,EnsembleNiveau); this.partieEnCour(0);break;
				case 3: f = new File("src\\BD01plus.bdcff");LireFichier.lire(f, EnsembleNiveau); quelleIa(); break;
				case 4: notice(); break;
				case 5:partieEnCour=false; break;
				
			}
		
		}
			
			
			System.out.println("Fin de l application , nous esperons vous revoir bientôt");
		}	
		
		
		
		
		public void initialisation(){
			File f=new File("BD01plus.bdcff");
			EnsembleNiveau=new Univers();
			
			LireFichier.lire(f, EnsembleNiveau);
		}
		
		
		public void comparerDeuxIa(){
			initialisation();
			
			int niveau;
			System.out.println("Quelle niveau ? ");
			niveau=lire.nextInt();
			this.terrainDeLaPartie=EnsembleNiveau.listeMap.get(niveau-1);
			
			
			
			Ia ia1,ia2;
			System.out.println("choix premiere Ia ");
			quelleChoix();
			int choix=lire.nextInt();
			switch(choix){
			case 1:initialisation();ia1=new Simplet(terrainDeLaPartie);break;
			case 2:initialisation();ia1=new Genetique(terrainDeLaPartie,this);break;
			case 3:initialisation();ia1=new Directif(terrainDeLaPartie);break;
			default : initialisation(); ia1=new Simplet(terrainDeLaPartie);break;

			}
			System.out.println("choix deuxieme Ia");
			quelleChoix();
		    choix=lire.nextInt();
			switch(choix){
			case 1:initialisation();ia2=new Simplet(terrainDeLaPartie);break;
			case 2:initialisation();ia2=new Genetique(terrainDeLaPartie,this);break;
			case 3:initialisation();ia2=new Directif(terrainDeLaPartie);break;
			default : initialisation(); ia2=new Simplet(terrainDeLaPartie);break;
			}
			System.out.println("Combien de partie voulez vous simuler avec chaque ia ? ");
			int nbPartie=lire.nextInt();
			int nbPartieIa1=nbPartie;
			int nbPartieIa2=nbPartie;
			int nbMouvementIa1=0;
			int nbVictoireIa1=0;
			int nbVictoireIa2=0;
			int nbMouvementIa2=0;
			while(nbPartieIa1>0){
				nbPartieIa1--;
				boolean victoire;
				victoire=this.simulerPartieAvecIa(terrainDeLaPartie, ia1,true);
				if(victoire){
					nbVictoireIa1++;
				}
				nbMouvementIa1=this.terrainDeLaPartie.getCave().getCaveTime()+nbMouvementIa1;
				initialisation();
				File f = new File ("BD01plus.bdcff");LireFichier.lire(f,EnsembleNiveau);
				this.terrainDeLaPartie=EnsembleNiveau.listeMap.get(niveau-1);
				if(ia1 instanceof Simplet){
					ia1=new Simplet(terrainDeLaPartie);
				}else if(ia1 instanceof Genetique){
					ia1=new Genetique(terrainDeLaPartie, this);
				}else{
					ia1=new Directif(terrainDeLaPartie);
				}
			}
			while(nbPartieIa2>0){
				nbPartieIa2--;
				boolean victoire;
				victoire=this.simulerPartieAvecIa(terrainDeLaPartie, ia2,true);
				if(victoire){
					nbVictoireIa2++;
				}
				nbMouvementIa2=this.terrainDeLaPartie.getCave().getCaveTime()+nbMouvementIa1;
				initialisation();
				File f = new File ("BD01plus.bdcff");LireFichier.lire(f,EnsembleNiveau);
				this.terrainDeLaPartie=EnsembleNiveau.listeMap.get(niveau-1);
				if(ia1 instanceof Simplet){
					ia1=new Simplet(terrainDeLaPartie);
				}else if(ia1 instanceof Genetique){
					ia1=new Genetique(terrainDeLaPartie, this);
				}else{
					ia1=new Directif(terrainDeLaPartie);
				}
			}
			
			System.out.println("########################################################");
			System.out.println("Comparaison IA :"+ia1.toString()+" VS "+ia2.toString());
			System.out.println("Nombre de partie jouer :"+nbPartie);
			System.out.println("Nombre de victoire: "+nbVictoireIa1+" : "+nbVictoireIa2);
			System.out.println("mouvements requis: "+(nbMouvementIa1/nbPartie)+" : "+(nbMouvementIa2/nbPartie));
			System.out.println("#########################################################");
			
			
			
			
			
		}
			
		
	
		
		public void quelleChoix(){
			System.out.println("1:Simplet");
			System.out.println("2:Genetique (prend beaucoup de temps )");
			System.out.println("3:Directif ");
			System.out.println("Quelle Ia ? ");
		}
		
		
		
		
		
		
		
		
		/**
		 * cette methode permet de lancer une partie avec une ia , pour cela l'utilisateur choisit le niveau qui l'interesse
		 * puis il choisit parmis les trois ia implementé enfin il lance un appel a la methode simulerPartieAvecIa avec l ia 
		 * selectionner .
		 * 
		 * 
		 * @see Jeu#simulerPartieAvecIa(Carte, Ia)
		 */
		public void quelleIa(){
			quelleChoix();
			int choixIa=lire.nextInt();
			System.out.println("Quelle niveau ? ");
			int niveau=lire.nextInt(); 
			this.niveau=niveau;
			this.terrainDeLaPartie=this.EnsembleNiveau.listeMap.get(niveau-1);
			Ia ia;
			switch(choixIa){
				case 1:ia=new Simplet(terrainDeLaPartie);break;
				case 2:ia=new Genetique(terrainDeLaPartie,this);break;
				case 3:ia=new Directif(terrainDeLaPartie);break;
				default:ia=new Simplet(terrainDeLaPartie);break;
			}
			this.simulerPartieAvecIa(terrainDeLaPartie, ia,true);
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		/**
		 * retourne faux si la partie est termine du a une victoire du joueur c'est a dire si le joueur principale est arrive sur la sortie
		 * @return un boolean indiquant si le joueur principale est arrive sur la sortie
		 */
		private boolean sortieNonAtteint(){
			//return this.terrainDeLaPartie.getTerrain()[this.terrainDeLaPartie.getAbSortie()][this.terrainDeLaPartie.getOrdSortie()]!='R'   ;			
			if(this.terrainDeLaPartie.getTerrain()[terrainDeLaPartie.getAbSortie()][terrainDeLaPartie.getOrdSortie()]=='R'){
				System.out.println("###################################### SORTIE #################################");
			}
			return this.terrainDeLaPartie.getTerrain()[terrainDeLaPartie.getAbSortie()][terrainDeLaPartie.getOrdSortie()]!='R';
		}
		
		
		private boolean tempsNonEcoule(){
			return this.terrainDeLaPartie.getCave().getCaveTime()>=0;
			//return carte.getCave().getCaveTime()>=0;
		}
		
		
		
		private boolean sortieNonAtteint(Carte carte){
			return carte.getTerrain()[carte.getAbSortie()][carte.getOrdSortie()]!='R';
		}
		
		private boolean tempsNonEcoule(Carte carte){
			return carte.getCave().getCaveTime()>=0;		
			}
		
		
		
		
		
		public void partieEnCourAvecIa(Carte carte,Ia ia){
			if(simulerPartieAvecIa(carte,ia,true)){
				System.out.println("Vous avez gagner ");
			}else{
				System.out.println("Vous avez perdu");
			}
			
			
		}
		
		
		
		
		public boolean simulerPartieAvecIa( Carte TerrainJeu,Ia ia,Boolean afficher){
			if(afficher){
					System.out.println(/*this.*/TerrainJeu.getCave());
					/*this.*/TerrainJeu.afficherMap();
			}
			int i=1;
			boolean heroEnVie=true;
			//PrintWriter out; 
          //  File p = new File ("Strategie :"+ia.toString()+" niveau :"+this.niveau+" .DASH");
           
           
			
			
			while(sortieNonAtteint(TerrainJeu)&& tempsNonEcoule(TerrainJeu) && heroEnVie==true){
			
				
				List<ObjetVivant>list=TerrainJeu.getPersonnageSurLeTerrain();
				Iterator<ObjetVivant>it=list.iterator();
				List<ObjetNonVivant>list2=TerrainJeu.getObjetSurLeTerrain();
				Iterator<ObjetNonVivant>it2=list2.iterator();
				
				List <ObjetNonVivant> listBis=new ArrayList<ObjetNonVivant>();
				
				while(it.hasNext() ){
					ObjetVivant j=it.next();
					
					try{
						if(j instanceof Joueur){
						
							char mv=ia.prochainCoup();
							((Joueur) j).seDeplacer(TerrainJeu, mv);
							
						}else{
							if(j.estEnVie()){
								j.seDeplacer(TerrainJeu);
							}
						}
						
					}catch(DeplacementInvalideException e){
						
					}
					
					
				}
				while(it2.hasNext()){
					ObjetNonVivant x=it2.next();
					x.tomber(TerrainJeu);
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				quiEstMort(TerrainJeu);
				quiEstDetruit(TerrainJeu);
				
				/*this.*/TerrainJeu.getObjetSurLeTerrain().addAll(/*this.*/TerrainJeu.reinitialisationObjet());
				
				/*this.*/TerrainJeu.afficherMap();
				
				Cave a=/*this.*/TerrainJeu.getCave();
				a.setCaveTime(a.getCaveTime()-1);
				
				//System.out.println(/*this.*/TerrainJeu.getPersonnageSurLeTerrain().size()-1+" enemie");
				//System.out.println(/*this.*/TerrainJeu.getObjetSurLeTerrain().size()-1+" objet");
				
				heroEnVie=heroEncoreDansListe(/*this.*/TerrainJeu.getPersonnageSurLeTerrain());
			//	System.out.println("Sortie Non atteint :"+sortieNonAtteint(TerrainJeu));
				//System.out.println("temps non ecoule: "+tempsNonEcoule(TerrainJeu));
				//System.out.println("Hero en vie :"+heroEnVie);

			}
			
			
            
			
	     
			
			
			if(heroEnVie && !sortieNonAtteint(TerrainJeu)){
				
				return true;
			}else {
				
				return false;
			}
			
			
		}
		
		

		private void quiEstDetruit(Carte terrainPartie){
			List<ObjetNonVivant>list=terrainPartie.getObjetSurLeTerrain();		
			Iterator<ObjetNonVivant>it=list.iterator();
			boolean f=false;
			while(it.hasNext()){
				ObjetNonVivant j=it.next();
				if(j.isDetruit()){
					list.remove(j);
					terrainPartie.getObjetSurLeTerrain().remove(j);
					f=true;
					break;
				}
			}
			if(f){
				quiEstDetruit();
			}
			
			
			}
		
		
		
		
		
		
		/**
		 * cette methode s assure de retirer de la liste des personnageSurLeTerrain les personnages qui viennent de mourir lors d un tour de jeu 
		 * de cette faï¿½on au tour suivant il n auront pas de coup a jouer .
		 */
		private void quiEstMort(Carte terrainPartie){
			List<ObjetVivant>list=terrainPartie.getPersonnageSurLeTerrain();
			Iterator<ObjetVivant>it=list.iterator();
			boolean f=false;
			while(it.hasNext()){
				ObjetVivant j=it.next();
				if(!j.estEnVie()){
				
					f=true;
					list.remove(j);
					break;
				}
			}
			if(f){
				quiEstMort();
			}
		}
		
		
		
	

		
			
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/**
		 * simule une partie en cours , affiche le terrain et fait jouer les perssonage du terrain au tour a tour 
		 * @see Carte
		
		 */
		public void partieEnCour(int niveau){
			
			if(niveau<=0){
				System.out.println("Quel niveau ?");
				niveau=lire.nextInt();
			}
			this.niveau=niveau;
			this.terrainDeLaPartie=this.EnsembleNiveau.listeMap.get(niveau-1);
			System.out.println(terrainDeLaPartie.getCave().toString());
			this.terrainDeLaPartie.afficherMap();
			boolean heroEnVie=true;
			while(sortieNonAtteint()&& tempsNonEcoule() && heroEnVie==true){
				
				
				for(ObjetVivant eleViv:terrainDeLaPartie.getPersonnageSurLeTerrain()){
					try{
						if(eleViv.estEnVie()){
							eleViv.seDeplacer(terrainDeLaPartie);
						}
					}catch(DeplacementInvalideException e){
						
					}
					
				}
				for(ObjetNonVivant eleNonViv:terrainDeLaPartie.getObjetSurLeTerrain()){
					eleNonViv.tomber(terrainDeLaPartie);
				}
				
				
					
				quiEstMort();
				quiEstDetruit();
				this.terrainDeLaPartie.getObjetSurLeTerrain().addAll(this.terrainDeLaPartie.reinitialisationObjet());
				this.terrainDeLaPartie.afficherMap();
				Cave a=this.terrainDeLaPartie.getCave();
				a.setCaveTime(a.getCaveTime()-1);			
				System.out.println(this.terrainDeLaPartie.getPersonnageSurLeTerrain().size()-1+" enemie");
				System.out.println(this.terrainDeLaPartie.getObjetSurLeTerrain().size()-1+" objet");
				
				heroEnVie=heroEncoreDansListe(this.terrainDeLaPartie.getPersonnageSurLeTerrain());

			}
			
			
			if(heroEnVie && !sortieNonAtteint()){
				this.terrainDeLaPartie=this.EnsembleNiveau.listeMap.get(niveau);
				partieEnCour(niveau+1);
			}else {
				System.out.println("Vous avez perdu ! Voulez vous rejouer ?");
				System.out.println("1:rejouer");
				System.out.println("2:quitter ");
				int rep=lire.nextInt();
				if(rep==1){
					File f=new File("BD01plus.bdcff");
					EnsembleNiveau=new Univers();
					LireFichier.lire(f, EnsembleNiveau);
					this.partieEnCour(niveau);
				}
			}
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		public boolean heroEncoreDansListe(List<ObjetVivant>liste){
			Iterator<ObjetVivant>it=liste.iterator();
			while(it.hasNext()){
				ObjetVivant x=it.next();
				if( x instanceof Joueur)
					return true;
			}
			return false;
			
			
			
		}
		
		
		
		private void quiEstDetruit(){
			List<ObjetNonVivant>list=terrainDeLaPartie.getObjetSurLeTerrain();		
			Iterator<ObjetNonVivant>it=list.iterator();
			boolean f=false;
			while(it.hasNext()){
				ObjetNonVivant j=it.next();
				if(j.isDetruit()){
					list.remove(j);
					terrainDeLaPartie.getObjetSurLeTerrain().remove(j);
					f=true;
					break;
				}
			}
			if(f){
				quiEstDetruit();
			}
			
			
			}
		
		
		
		
		
		
		/**
		 * cette methode s assure de retirer de la liste des personnageSurLeTerrain les personnages qui viennent de mourir lors d un tour de jeu 
		 * de cette faï¿½on au tour suivant il n auront pas de coup a jouer .
		 */
		
		
		private void quiEstMort(){
			List<ObjetVivant>list=terrainDeLaPartie.getPersonnageSurLeTerrain();
			Iterator<ObjetVivant>it=list.iterator();
			boolean f=false;
			while(it.hasNext()){
				ObjetVivant j=it.next();
				if(!j.estEnVie()){
				
					f=true;
					list.remove(j);
					break;
				}
			}
			if(f){
				quiEstMort();
			}
		}
		
		
		
	}


	
	
	
	
	

