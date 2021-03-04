package src;


import java.util.*;
/**
 * 
 * @author Malik
 * cette classe symbolise le terrain sur lequel se deplace les personnages (le joueur , les monstres).
 * Elle est composse
 * -d un tableau de char a deux dimenssions ou chaque coefficient i,j de la matrice represente une case du terrain sur lequel se 
 * deroule le jeu
 * -d un ArrayList qui contient la liste des personnages present sur le terrain (joueur principal , monstres etc)
 * -d un ArrayList qui contient la liste des objet present sur le terrain (Diamant, roc )
 * -un attribue Cave qui sert a connaitre les details de cette map (caveDelay ,diamondsValues etc) .
 * -Deux int (abSortie , ordSortie ) qui contiennent les coordonnees de la case ou se trouve la sortie du niveau qui s affiche si le
 *   nombre de diamant requis est atteint 
 * -un int nbDIamant qui correspond au nombre  de diamant obtenu lors de la partie en cours
 *@see Cave
 *@see ElementTerrain
 *@see ObjetNonVivant
 *@see ObjetVivant
 * 
 *
 */


public class Carte {
		
	
	
		/**
		 * cette attribut correspond au nombre de diamant collecte au cours d une partie et est initialis� a zero 
		 */
		private int nbDiamant=0;
		
		
		
		/**
		 *cette attribut correspond a l'absice de la case ou se trouve la sortie vers le niveau suivant
		 */
		private int abSortie;
		
		
		
		/**
		 * cette attribut correspond à l ordonne de la case ou se trouve la sortie vers le niveau suivant
		 */
		private int ordSortie;

		
		
       /**
        * cette attribut correspond a un tableau a deux dimensions de caracteres sur lequel la partie va se deroule
        */
        private char [][]terrain  ;

        
        
        
        /**
         * cette attribut correspond a une liste des personnages present sur le terrain 
         * On utilise le polymorphisme est on cree une liste du type abstrait Personnage dont herite touts les elements se deplacant se deplacant 
         * avec autonomie sur le terrain(RockFord ainsi que les ennemies ) .
         * @see ObjetVivant 
         * 
         */
        private ArrayList<ObjetVivant>personnageSurLeTerrain=new ArrayList<ObjetVivant>();
        
        
        
        
        
        /**
         * liste objetNonVivant sur le terrain (roc ,diamant,mur magique)
         */
        private ArrayList<ObjetNonVivant>ObjetSurLeTerrain=new ArrayList<ObjetNonVivant>();

        
        
        
        
		/**
         * Il s agit d'un attribut correspond aux données sur le terrain comme par exemple le nombre de tour  avant que le joueur perde .
         * @see Cave
         */
        private Cave cave;
        
        
        
        
        /**
         * Ce tabeau sauvegarde l ensemble des objets tombants 
         * 
         */
        private char[][] tableauSauvegardeChuteObjet;

        
        
        
        
        /**
         * constructeur qui recoit une list de chaine qui correspond a chaque ligne de la map lu a travers un File dans la classe LireFichier .
         * Donc le premiers element de la list represente la premiere ligne de la map.
         * Les elements de la list vont remplir le tableau terrain sur lequel les perssonages vont se deplacer.
         * Une fois cette tache accomplie , la list des personnages sur le terrain va etre cree grace a la methode initialisation personnage .
         * @param tab cave
         * @see LireFichier
         * @see Carte#initialisationPersonnage()
         */
        public Carte(List<String> tab,Cave cave){
        		this.cave=cave;
                int colonne=tab.get(0).length();
                int ligne=tab.size();
                terrain=new char[ligne][colonne];
                this.tableauSauvegardeChuteObjet=new char[ligne][colonne];
                int cpt=0;
                Iterator<String>it=tab.iterator();
                while(it.hasNext()){
                        String s=it.next();
                        for(int i=0;i<=s.length()-1;i++){
                                terrain[cpt][i]=s.charAt(i);
                               
                        }
                        cpt++;
                }


                this.initialisationPersonnage();

        }

        
        
        
        
        
        
        
        
        
        
        /**
         * permet d obtenir la liste des rocs , diamants et mur magique present sur le terrain 
         * @return la liste des objets non Vivants du terrrain 
         */
        public ArrayList<ObjetNonVivant> getObjetSurLeTerrain() {
			return ObjetSurLeTerrain;
		}


        
        /**
         * retourne l'attribut Cave de la carte
         * @return les donnees du terrain 
         * @see Cave
         */
        public Cave getCave(){
        	return cave;
        }

        
        
        
        public char[][] getTableauSauvegardeChuteObjet() {
			return tableauSauvegardeChuteObjet;
		}











		public void setTableauSauvegardeChuteObjet(char[][] tableauSauvegardeChuteObjet) {
			this.tableauSauvegardeChuteObjet = tableauSauvegardeChuteObjet;
		}











		/**
         * 
         * @return le nombre de diamant collect� au cours d une partie
         */
		public int getNbDiamant() {
			return nbDiamant;
		}

        
        
        

        /**
         * 
         * @return l absice de la sortie du niveau 
         */
        public int getAbSortie() {
		return abSortie;
	}



        /**
         * 
         * @return l ordonne de la sortie du niveau 
         */
	public int getOrdSortie() {
		return ordSortie;
	}


		/**
         * 
         * @return le terrain sur lequel se deplace les joueurs
         */
        public char[][] getTerrain() {
                return terrain;
        }


        
        /**
         * 
         * @return la liste des personnages presents sur le terrain 
         */
        public ArrayList<ObjetVivant> getPersonnageSurLeTerrain() {
                return personnageSurLeTerrain;
        }

        
        
        

        
        
        
        
        
        

       /**
        * cette methode parcourt chaque case du terrain et initie les personnages quand elle en trouve .Donc si en arrivant sur une case elle
        * voit que cette case contient la lette P on saura que c'est a cet endroit que le joueur principale doit se trouver et un objet Joueur sera instancier
        * avec les coordonnes de la case en question .
        * Donc c'est a partir de cette methode que la liste personnageSurLeTerrain qui est l arrayList des personnages sur le terrain va se remplir .
        */
        public void initialisationPersonnage(){
                for(int i=0;i<=terrain.length-1;i++){
                        for(int j=0;j<=terrain[0].length-1;j++){
                        	
                        	
                        		//enregistre la sortie du niveau
                        if(terrain[i][j]=='X'){
                        			terrain[i][j]='W';
                        			this.abSortie=i;
                        			this.ordSortie=j;
                        		
                        		//enregistre la position de depart du Joueur
                        }else if(terrain[i][j]=='P' || terrain[i][j]=='R'){
                                        Joueur x=new Joueur(i,j);
                                        terrain[i][j]='R';
                                        personnageSurLeTerrain.add(x);
                                
                                
                                
                                //enregistre le s libellules presente sur le terrain
                		}else if(terrain[i][j]=='b' || terrain[i][j]=='B' || terrain[i][j]=='c' || terrain[i][j]=='C'){
                                	
                                	terrain[i][j]='C';
                                	Libellule x=new Libellule(i,j);
                                	personnageSurLeTerrain.add(x);
                                
                                
                                //enregistre les lucioles presente sur le terrain
                		}else if(terrain[i][j]=='F' || terrain[i][j]=='Q' || terrain[i][j]=='o' || terrain[i][j]=='O' || terrain[i][j]=='q'){
                                	terrain[i][j]='Q';
                                	Luciole x=new Luciole(i,j);
                                	personnageSurLeTerrain.add(x);
                                
                                
                                //enregistre les rochers present sur le niveau
                		}else if(terrain[i][j]=='r'){
                                	Roc x=new Roc(i,j,this);
                                	this.ObjetSurLeTerrain.add(x);
                                
                                
                                
                                //enregistre les diamants present sur le niveau
                		}else if(terrain[i][j]=='d'){
                                	Diamant x=new Diamant(i,j,this);
                                	this.ObjetSurLeTerrain.add(x);
                                
                               
                                
                        }else if (terrain[i][j]!='w'&&terrain[i][j]!='M'&& terrain[i][j]!='a'&&terrain[i][j]!='W'&& terrain[i][j]!='.'&&terrain[i][j]!=' '){
                        	terrain[i][j]='d';
                        	Diamant x=new Diamant(i,j,this);
                        	this.ObjetSurLeTerrain.add(x);
                        }
                        }
                }
        }
                
              
        




        
        
        
        
        /**
         * affiche le terrain sur lequel se deroule la partie
         */
        public void afficherMap(){
        	System.out.println("Time :"+this.getCave().getCaveTime());
                for(int i=0;i<=terrain.length-1;i++){
                		
                        for(int j=0;j<=terrain[0].length-1;j++){
                        	//si nombre de diamant requis pas atteint alors cacher la sortie et afficher un mur de titane
                        	
                        		System.out.print(terrain[i][j]);
                        	
                        		 
                        
                        			}
                        
                        System.out.println();
                }
                if((cave.getDiamondsRequire()-nbDiamant)>=0){
                	System.out.println("Diamants restant a capturer : " + (cave.getDiamondsRequire()-nbDiamant)+" ");
                }else {
                	System.out.println("Diamants restant a capturer : 0");
                }
                }
        


        
        
        
 
        
        /**
         * 
         * @param absice  l absice ou se trouve le rocher a deplacer
         * @param ordonne l ordonne ou se trouve le rocher a deplace
         * @param mouve le mouvement effectuer
         * @param element le joueur principale
         * cette methode deplace un rocher suivant le mouvement effectuer , donc si on lance la methode avec le charactere f alors le rocher
         * sera deplacer d'une case sur la droite et Rockord aura la place anciennement attribue au rocher 
         */
        private void deplacementRocher(int absice,int ordonne, char mouve, ObjetVivant element){
        	
        	//si on deplace un rocher vers le haut
        	
        	if(mouve=='e'||mouve=='E'){
                 if(absice>1){
                         if(terrain[absice-1][ordonne]==' '){
                        	 /*
                                 terrain[absice-1][ordonne]='r';
                                 terrain[absice][ordonne]=terrain[element.getAbsice()][element.getOrdonne()];
                                 terrain[element.getAbsice()][element.getOrdonne()]=' ';
                                 element.setAbsice(absice);
                                 */

                         }
                 }
                 // si on deplace rocher vers la gauche
        	 }else if(mouve=='s' || mouve=='S'){
                 if(ordonne>1){
                         if(terrain[absice][ordonne-1]==' '){
                                 terrain[absice][ordonne-1]='r';
                                 terrain[absice][ordonne]='R';
                                 terrain[element.getAbsice()][element.getOrdonne()]=' ';
                                 element.setOrdonne(ordonne);
                                 changerCoordonnesRocher(absice,ordonne,absice,ordonne-1);
                         }
                 }
                 // si on deplace rocher vers la droite
        	 }else if(mouve=='f'|| mouve=='F'){
        		 if(ordonne<terrain[0].length-2){
        			 if(terrain[absice][ordonne+1]==' '){
        				 terrain[absice][ordonne+1]='r';
        				 terrain[absice][ordonne]='R';
        				 terrain[element.getAbsice()][element.getOrdonne()]=' ';
        				 element.setOrdonne(ordonne);
        				 changerCoordonnesRocher(absice,ordonne,absice,ordonne+1);
         		}
         	}
        		 // si on deplace un rocher vers le bas
        		 // au tours suivant si aucun obstacle en bas du diamant alors il tombera ou roulera 
        	 }else if(mouve=='d'||mouve=='D'){
        		 if(absice<terrain.length-2){
        			 if(terrain[absice+1][ordonne]==' '){
        				 terrain[absice+1][ordonne]='r';
        				 terrain[absice][ordonne]='R';
        				 terrain[element.getAbsice()][element.getOrdonne()]=' ';
        				 element.setAbsice(absice);
        				 changerCoordonnesRocher(absice,ordonne,absice+1,ordonne);
         		}
         	}
        }
        }
        
        
        
        
        private void changerCoordonnesRocher(int abOr,int ordOr,int abFin,int ordFin){
        	Iterator<ObjetNonVivant>it=this.ObjetSurLeTerrain.iterator();
        	while(it.hasNext()){
        		ObjetNonVivant x=it.next();
        		if(x.getAbsice()==abOr && x.getOrdonne()==ordOr){
        			x.setAbsice(abFin);
        			x.setOrdonne(ordFin);
        		}
        	}
        }
        
        
        
        
        
        /**
         * cette methode fait mourrir le hero principale dans le cas ou un enemie se deplace sur lui ou un objet (diamant ou rochers )lui tombe dessus
         * 
         */
        public void mortDuHero (){
        	Iterator<ObjetVivant>it=this.personnageSurLeTerrain.iterator();
        	while(it.hasNext()){
        		ObjetVivant x=it.next();
        		if(x instanceof Joueur){
        			System.out.print("Le hero meurttttttt");
        			x.meurt();break;
        		}
        	}
        }
        
        
        
      
    
        /**
         * @param absice absice ou le personnage doit etre deplacer
         * @param ordonne ordonne ou le personnage doit etre deplacer
         * @param mouve le mouvement effectuer 
         * @param element le personnage a deplacer
         * 
         * 
         */
        private void caseSuivante(int absice , int ordonne ,char mouve, ObjetVivant element){
        	 
        	
        	// si un joueur enemie se deplace sur une case ou se trouve le joueur principale
        	if(terrain[absice][ordonne]=='R'){
        			  terrain[absice][ordonne]=terrain[element.getAbsice()][element.getOrdonne()];
                      terrain[element.getAbsice()][element.getOrdonne()]=' ';
                      element.setAbsice(absice);element.setOrdonne(ordonne);
                      System.out.println(" PERDU !!!");
                      mortDuHero();
        		}
        	
        		// si poussieure  alors celle ci est capturé 
                if(terrain[absice][ordonne]=='.' && element instanceof Joueur){
                		//System.out.println("Je suis pass� en dessous d un objet qui peut tomber");
                        terrain[absice][ordonne]=terrain[element.getAbsice()][element.getOrdonne()];
                        terrain[element.getAbsice()][element.getOrdonne()]=' ';
                        element.setAbsice(absice);element.setOrdonne(ordonne);
                      
  
                       // si aucun obstacle alors tout le monde peut s y deplacer 
                }else if(terrain[absice][ordonne]==' '){
                        terrain[absice][ordonne]=terrain[element.getAbsice()][element.getOrdonne()];
                        terrain[element.getAbsice()][element.getOrdonne()]=' ';
                        element.setAbsice(absice);element.setOrdonne(ordonne);
                      
                        
                        
                        // s'il y a un rocher et qu il s agit du joueur principale alors il peut deplacer le rocher
                }else if(terrain[absice][ordonne]=='r' && element instanceof Joueur){
                		
                		deplacementRocher(absice,ordonne,mouve,element);
                		
               

                        
                       // quand sortie atteint 
                }else if (terrain[absice][ordonne]=='X' && element instanceof Joueur){
                	System.out.println("SORTIE ###########################################################");
                	 terrain[this.abSortie][this.ordSortie]='R';
                     terrain[element.getAbsice()][element.getOrdonne()]=' ';
                    
                    // element.setAbsice(absice);element.setOrdonne(ordonne);
                    

                	
                	//capture diamant 
                }else if (terrain[absice][ordonne]=='d'&& element instanceof Joueur){
                	 
                	  this.detruireElement(absice, ordonne);
                	  terrain[absice][ordonne]='R';//terrain[element.getAbsice()][element.getOrdonne()];
                      terrain[element.getAbsice()][element.getOrdonne()]=' ';
                      element.setAbsice(absice);element.setOrdonne(ordonne);
                     
                      this.captureDiamant();
                      if(terrain[absice+1][ordonne]=='d' && element.getMv()=='e' || element.getMv()=='E'){
                    	this.detruireElement(absice+1,ordonne);
                    	terrain[absice+1][ordonne]=' ';
                    	this.captureDiamant();
                    }
                      
                      //Quand le joueur principale se deplace accidentellement sur une case ou se trouve un enemie
                }else if(terrain[absice][ordonne]=='C' || terrain[absice][ordonne]=='Q' && element instanceof Joueur){
                
                	terrain[element.getAbsice()][element.getOrdonne()]=' ';
                	mortDuHero();
                }

        }




        /**
         * cette methode fait se deplacer un personnage sur le terrain
         * @param absice l absice du personnage qui se deplace
         * @param ordonne l ordonne du personnage qui se deplace
         * @param mouvement quel mouvement est effectué ( e ,s,f,d)
         * @param element le personnage qui se deplace
         *
         * le personnage est bougé sur la map suivant le mouvement choisi .
         */
        public void deplacerPersonnage(int absice, int ordonne, char mouvement,ObjetVivant element){
                if(mouvement=='e' || mouvement=='E'){
                        caseSuivante(absice-1,ordonne,mouvement,element);
                }
                if(mouvement=='s'|| mouvement=='S'){
                        caseSuivante(absice,ordonne-1,mouvement,element);
                }
                if(mouvement=='f'||mouvement=='F'){
                        caseSuivante(absice,ordonne+1,mouvement,element);
                }
                if(mouvement=='D'||mouvement=='d'){
                        caseSuivante(absice+1,ordonne,mouvement,element);
                }
        }


        
	

		/**
		 * incremente le nombre de diamant quand le joueur prncipale se deplace sur une case ou il y a un diamant 
		 */
		public void captureDiamant(){
			this.nbDiamant++;
			
			if(this.terrain[this.getAbSortie()][this.getOrdSortie()]!='X' && this.nbDiamant>=this.getCave().getDiamondsRequire()){
				
				if(terrain[this.getAbSortie()][this.getOrdSortie()]!='R'){
					terrain[this.getAbSortie()][this.getOrdSortie()]='X';
				}
			}
		}
		
		
		
		
		
			/**
			 * cette methode detruit l element situe a la case[ab][ord] qu il soit un objet vivant ou non 
			 * @param ab
			 * @param ord
			 */
		  private void detruireElement(int ab,int ord){
	        	if(ab>=1 && ab<=this.terrain.length-2 && ord>=1 && ord<=this.terrain[0].length-2){
	        		if(terrain[ab][ord]!='W' && terrain[ab][ord]!='X'){
	        			if(terrain[ab][ord]=='R' || terrain[ab][ord]=='C' || terrain[ab][ord]=='Q' || terrain[ab][ord]=='a'){
	        				this.supprimerEnnemie(ab,ord);
	        			}
	        			if( terrain[ab][ord]=='r' || terrain[ab][ord]=='d'/* || terrain[ab][ord]=='w'*/){
	        				this.supprimerObjetNoVivant(ab,ord);
	        				
	        			}
	        			if(terrain[ab][ord]=='w'){
	        				terrain[ab][ord]=' ';
	        			}
	        			
	        		}
	        		
	        		
	        	}
		  }

		  
		  
		  
		  /**
		   * cree une matrice carree 3*3 dont le centre est situ� aux coordonnes ab , ord
		   * @param ab
		   * @param ord
		   */
		   private void creationMatriceDiamant(int ab,int ord){
		          for(int x=ab-1; x<=ab+1; x++){
		        	  for(int y=ord-1; y<=ord+1;y++){
		        		  if(x>=1 && x<=this.terrain.length-2 && y>=1 && y<=this.terrain[0].length-2){
		        			  Diamant a=new Diamant(x,y,this);
		        			  //this.getObjetSurLeTerrain().add(a);
		        			  this.terrain[x][y]='d';
		        	}
		        	  }
		          }
		        }
		   
		   
		   
		   
		   
		   /**
		    * creation matrice c aree 3*3  vide dont le centre est situ� aux coordonnees ab ord
		    * @param ab
		    * @param ord
		    */
		   private void creationMatriceVide(int ab,int ord){
			   for(int x=ab-1; x<=ab+1; x++){
		        	  for(int y=ord-1; y<=ord+1;y++){
		        		  if(x>=1 && x<=this.terrain.length-2 && y>=1 && y<=this.terrain[0].length-2){
		        			  this.terrain[x][y]=' ';
		        		  }
		        	  }
			   }
			   
			   
			   
		   }
		        
		
		
		
		
		
		
		/**
		 * detruit les 9 elements ou se trouvera la matrice diamant a cree puis lance la methode creationmatricediamant
		 * @param ab
		 * @param ord
		 */
		private void matriceDiamant(int ab,int ord){
			System.out.println("Diamant");
			detruireElement(ab-1,ord-1);//terrain[ab-1][ord-1]='d';
			detruireElement(ab-1,ord);//terrain[ab-1][ord]='d';
			detruireElement(ab-1,ord+1);//terrain[ab-1][ord+1]='d';
			detruireElement(ab,ord-1);//terrain[ab][ord-1]='d';
			detruireElement(ab,ord);//terrain[ab][ord]='d';
			detruireElement(ab,ord+1);//terrain[ab][ord+1]='d';
			detruireElement(ab+1,ord-1);//terrain[ab+1][ord-1]='d';
			detruireElement(ab+1,ord);//terrain[ab+1][ord]='d';
			detruireElement(ab+1,ord+1);//terrain[ab+1][ord+1]='d';
			creationMatriceDiamant(ab,ord);
			
			
			
		}
		
		
		
		/**
		 * 		 * detruit les 9 elements ou se trouvera la matrice vide a cree puis lance la methode creationmatricediamant

		 * @param ab
		 * @param ord
		 */
		private void matriceVide(int ab,int ord){
			System.out.println("Vide");
			detruireElement(ab-1,ord-1);//terrain[ab-1][ord-1]='d';
			detruireElement(ab-1,ord);//terrain[ab-1][ord]='d';
			detruireElement(ab-1,ord+1);//terrain[ab-1][ord+1]='d';
			detruireElement(ab,ord-1);//terrain[ab][ord-1]='d';
			detruireElement(ab,ord);//terrain[ab][ord]='d';
			detruireElement(ab,ord+1);//terrain[ab][ord+1]='d';
			detruireElement(ab+1,ord-1);//terrain[ab+1][ord-1]='d';
			detruireElement(ab+1,ord);//terrain[ab+1][ord]='d';
			detruireElement(ab+1,ord+1);//terrain[ab+1][ord+1]='d';
			creationMatriceVide(ab,ord);
		}
		
		
		
		
		/**
		 * reinitialise les objet a chaque tour au cas ou il y a eu une explosion 
		 * quand une explosion a lieu du a la chute d un objet , on ne peut rajouter potienlement des objets cree
		 * par l explosion a la liste des objet en cour car on la parcour actuellement pour faire tomber les objets
		 * sinon on provoque un concurent modification , cette methode s assure une fois que tous les objets a faire tomber
		 * le sont de rajouter les nouveaux objets potienlement cree par l explosion .
		 * @return
		 */
		public List<ObjetNonVivant> reinitialisationObjet(){
			List<ObjetNonVivant> list=new ArrayList<ObjetNonVivant>();
			
			
			for(int i=0;i<=this.getTerrain().length-1;i++){
				for(int j=0;j<=this.getTerrain()[0].length-1;j++){
					if(terrain[i][j]=='d'){
						Diamant x=new Diamant(i,j,this);
						
						if(!this.ObjetSurLeTerrain.contains(x)){
							
							list.add(x);
						}
					}
					if(terrain[i][j]=='r'){
						Roc x=new Roc(i,j,this);
						
						//this.ObjetSurLeTerrain.add(x);
						
						if(!this.ObjetSurLeTerrain.contains(x)){
							
							list.add(x);
						}
						
					}
				}
			}
			return list;
		}
		
		
		
		
		
		/**
		 * lance l explosion qui va cree une matrice diamant ou vide
		 * @param ab
		 * @param ord
		 * @param diam
		 */
		public void explosion( int ab,int ord,boolean diam){
			if(diam){
				System.out.println("BOUM");
				matriceDiamant(ab,ord);
			}else{
				matriceVide(ab,ord);
			}
			
		}
		
		
		
		/**
		 * cette methode tue l element qui se trouve sur la case correspondant aux coordonnes passé en parametre 
		 */
		public void supprimerEnnemie(int ab,int ord){
			Iterator<ObjetVivant>it=this.personnageSurLeTerrain.iterator();
			while(it.hasNext()){
				ObjetVivant x=it.next();
				if(x.getAbsice()==ab && x.getOrdonne()==ord){
					x.meurt();
					break;
				}
			}
			
		}
		
		
		
		
		public void supprimerObjetNoVivant(int ab,int ord){
			Iterator<ObjetNonVivant>it=this.ObjetSurLeTerrain.iterator();
			while(it.hasNext()){
				ObjetNonVivant x=it.next();
				
				if(x.getAbsice()==ab && x.getOrdonne()==ord){
					if ( x instanceof Diamant){
						
					}
					x.setDetruit(true);
					
					break;
				}
			}
		}
		
		
		private Jeu jeu;
		
		/*
		public void copie(Carte copie){
			copie.abSortie=this.abSortie;
			copie.ordSortie=this.ordSortie;
			copie.cave=copie.getCave();
			copie.jeu=this.jeu;
			copie.nbDiamant=this.nbDiamant;
			copie.personnageSurLeTerrain=this.personnageSurLeTerrain;
			copie.ObjetSurLeTerrain=this.ObjetSurLeTerrain;
			copie.tableauSauvegardeChuteObjet=this.tableauSauvegardeChuteObjet;
			copie.terrain=this.terrain;
			
		}*/
		
		
	
		
		public Jeu getJeu() {
			return jeu;
		}











		public void setJeu(Jeu jeu) {
			this.jeu = jeu;
		}











		public boolean simulationIa(Ia ia){
			return jeu.simulerPartieAvecIa(this, ia,true);
		
		}


		
		private char[][] copieTabeau (char[][]tableauACopier){
			char[][] tableauARetourner=new char[tableauACopier.length][tableauACopier[0].length];
			for(int i=0;i<=tableauACopier.length-1;i++){
				for(int j=0;j<=tableauACopier[0].length-1;j++){
					tableauARetourner[i][j]=tableauACopier[i][j];
				}
			}
			return tableauARetourner;
		}
		





		
		public void copieTableau(char[][]tableauOriginal,char[][]tableauACopier){
			for(int i=0;i<=tableauOriginal.length-1;i++){
				for(int j=0;j<=tableauOriginal[0].length-1;j++){
					tableauACopier[i][j]=tableauOriginal[i][j];
				}
			}
		}
		
		
		
		public Carte(Carte carte){
			
			this.cave=(Cave)carte.getCave().copie();
			
			this.terrain=new char[carte.terrain.length][carte.terrain[0].length];
			this.tableauSauvegardeChuteObjet=new char[carte.getTableauSauvegardeChuteObjet().length][carte.getTableauSauvegardeChuteObjet()[0].length];
			copieTableau(carte.getTerrain(),this.terrain);
			copieTableau(carte.getTableauSauvegardeChuteObjet(),this.getTableauSauvegardeChuteObjet());
			this.initialisationPersonnage();
			System.out.println("Nouvelle carte !!!!!!!!!!!!!!!!!!!");
			this.afficherMap();
			
			
			
			
		}











		public void setNbDiamant(int nbDiamant) {
			this.nbDiamant = nbDiamant;
		}











		public void setAbSortie(int abSortie) {
			this.abSortie = abSortie;
		}











		public void setOrdSortie(int ordSortie) {
			this.ordSortie = ordSortie;
		}











		public void setTerrain(char[][] terrain) {
			this.terrain = terrain;
		}











		public void setPersonnageSurLeTerrain(ArrayList<ObjetVivant> personnageSurLeTerrain) {
			this.personnageSurLeTerrain = personnageSurLeTerrain;
		}











		public void setObjetSurLeTerrain(ArrayList<ObjetNonVivant> objetSurLeTerrain) {
			ObjetSurLeTerrain = objetSurLeTerrain;
		}








		public ObjetVivant getJoueur(){
			
			
			for(ObjetVivant perso:this.personnageSurLeTerrain){
				if(perso instanceof Joueur){
					return perso;
				}
			}
			return null;
		}


		public void setCave(Cave cave) {
			this.cave = cave;
		}
		
		
		public Carte (Cave cave , char[][]terrain, char[][]tableauChute,Jeu jeu){
			this.cave=cave;
			this.terrain=terrain;
			this.tableauSauvegardeChuteObjet=tableauChute;
			this.nbDiamant=0;
			System.out.println("Initialisation des personnages du terrain !!!!!!!!!!!!!!!!!!!");
			this.initialisationPersonnage();
			this.jeu=jeu;
			
			
			
		}
	
		
		
		public Carte (char[][]tableau,Jeu jeu,Cave cave){
			System.out.println("######## Creation CARTE #########");
			this.terrain=tableau;
			this.nbDiamant=0;
			this.tableauSauvegardeChuteObjet=new char[tableau.length-1][tableau[0].length-1];
			this.cave=cave.copie();
			//System.out.println("Initialisation des personnages du terrain !!!!!!!!!!!!!!!!!!!");
			this.initialisationPersonnage();

			this.jeu=jeu;
			
		
		
		}


} 