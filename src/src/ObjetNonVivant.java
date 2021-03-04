package src;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Malik
 * 
 * cette classe represente les instances d objet non vivant a savoir les rocs et diamants .
 * Ils ont la particularite de pouvoir tomber .
 *
 */
public abstract class ObjetNonVivant extends ElementTerrain  {
	




	public ObjetNonVivant(int absice, int ordonne,Carte terrainObjet) {
		super(absice, ordonne);

		if(terrainObjet.getTableauSauvegardeChuteObjet()[this.getAbsice()+1][this.getOrdonne()]!='*'){
				if(absice<=terrainObjet.getTerrain().length-3){
					if(terrainObjet.getTerrain()[absice+1][ordonne]==' '){
				
						tombe=true;
					}else{
						
						tombe=false;
					}
			
			
					
				}
		
		}else{
			this.tombe=true;
			terrainObjet.getTableauSauvegardeChuteObjet()[this.getAbsice()+1][this.getOrdonne()]=' ';
			
		}
	}
	
	
	
	
	
	/**
	 *verifie si un objetVivant(joueur , luciole etc) se trouve en dessous de l objet qui tombe auquel cas
	 *on retourne vrai sinon on retourn faux
	 * @param carte
	 * @return
	 */
	public boolean ObjetVivantEnDessous(Carte carte){
		if(carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]=='a' || 
				carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]=='C' ||
				carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]=='Q'	||
				carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]=='R'){
				
				return true;
			}
		return false;
	}
	
	
	
	
	
	/**
	 * cette methode verifie si une libellule va etre prise dans l explosion 
	 * @param carte
	 * @return un boolean pour indiquer si une libellule va exploser
	 */
	public boolean LibelluleExplose (Carte carte){
		char [][]terrain=carte.getTerrain();
		int ab=this.getAbsice();int ord=this.getOrdonne();
		if(carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]=='C' || 
			(ObjetVivantVaEtrePrisParLaChuteRocher(carte,ab+1,ord-1)&&(terrain[ab+1][ord-1]=='C')) ||
			(ObjetVivantVaEtrePrisParLaChuteRocher(carte,ab+2,ord)&&(terrain[ab+2][ord]=='C'))  ||
			(ObjetVivantVaEtrePrisParLaChuteRocher(carte,ab+1,ord+1)&&(terrain[ab+1][ord+1]=='C'))		){
			return true;
		}else{
			return false;
		}
		
		
		
	}
	
	
	public boolean EnemieExplose (Carte carte){
		char [][]terrain=carte.getTerrain();
		int ab=this.getAbsice();int ord=this.getOrdonne();
		if((carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]=='Q') || 
			(ObjetVivantVaEtrePrisParLaChuteRocher(carte,ab+1,ord-1)&&(terrain[ab+1][ord-1]=='Q')) ||
			(ObjetVivantVaEtrePrisParLaChuteRocher(carte,ab+2,ord)&&(terrain[ab+2][ord]=='Q'))  ||
			(ObjetVivantVaEtrePrisParLaChuteRocher(carte,ab+1,ord+1)&&(terrain[ab+1][ord+1]=='Q'))
			 ||(terrain[ab+1][ord]=='a')	){
			return true;
		}else{
			return false;
		}
		
		
		
	}
	
	
	private boolean detruit=false;
	
	
	
	
	
	
	public boolean isDetruit() {
		return detruit;
	}





	public void setDetruit(boolean detruit) {
		this.detruit = detruit;
	}





	/**
	 * verifie si un objetvivant va faire un deplacement qui va le placer sur la trajectoire de l objet qui tombe
	 * @param carte
	 * @param abEnemie
	 * @param ordEnemie
	 * @return
	 */
	public boolean ObjetVivantVaEtrePrisParLaChuteRocher(Carte carte,int abEnemie,int ordEnemie){
		char [][] terrain=carte.getTerrain();
		int ab=0;int ord=0;
		if(terrain[abEnemie][ordEnemie]=='a' || 
				terrain[abEnemie][ordEnemie]=='C' ||
				terrain[abEnemie][ordEnemie]=='Q' ||
				terrain[abEnemie][ordEnemie]=='R'){
			Iterator<ObjetVivant>it=carte.getPersonnageSurLeTerrain().iterator();
			while(it.hasNext()){
				ObjetVivant x=it.next();
				if(x.getAbsice()==abEnemie && x.getOrdonne()==ordEnemie){
					switch(x.getMv()){
					case 's':ab=x.getAbsice(); ord=x.getOrdonne()-1;break;
					case 'S':ab=x.getAbsice(); ord=x.getOrdonne()-1;break;
					case 'e':ab=x.getAbsice()-1; ord=x.getOrdonne();break;
					case 'E':ab=x.getAbsice()-1; ord=x.getOrdonne();break;
					case 'f':ab=x.getAbsice(); ord=x.getOrdonne()+1;break;
					case 'F':ab=x.getAbsice(); ord=x.getOrdonne()+1;break;
					}
				}
			}
		}
		
		return ab==this.getAbsice()-1&&ord==this.getOrdonne();
		
	}
	
	
	

	
	public abstract void tomber(Carte carte);
	


	private boolean tombe;
	
	
	
	
	public boolean isTombe() {
		return tombe;
	}







	public void setTombe(boolean tombe) {
		this.tombe = tombe;
	}
	
	

}
