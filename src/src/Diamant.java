package src;

/**
 * 
 * @author malik
 * cette classe diamant represente un type d 'objet qui peut tomber et generer des explosions
 * 
 * @see ObjetNonVivant
 * 
 *
 */
public class Diamant extends ObjetNonVivant {

	
	/**
	 * 
	 * @param absice l'absice de l objet sur le terrain
	 * @param ordonne l'ordonne de l objet sur le terrain 
	 * @param terrainObjet le terrain sur lequel se situe l objet
	 */
	public Diamant(int absice, int ordonne, Carte terrainObjet) {
		super(absice, ordonne, terrainObjet);
		// TODO Auto-generated constructor stubf
		
	}

	
	
	
	@Override
	/**
	 * cette methode va faire tomber le diamant d'une case dans la carte , on va verifier si l objet tombe ( a chaque tour ou il est sus
	 * pendu dans le vide l objet tombe ) .S'il tombe sur un objetVivant alors cela genera une explosion s'il ne s agit pas
	 * de l instance joueur sinon le diamant est captur.
	 */
	public void tomber(Carte carte) {
		// TODO Auto-gene58rated method stub
		if(carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]==' '){
			
			this.setTombe(true);
		}
		if(this.isTombe()&&this.getAbsice()<=carte.getTerrain().length-3 && carte.getTerrain()[this.getAbsice()][this.getOrdonne()]=='d'){
			if(this.LibelluleExplose(carte)){
				carte.explosion(this.getAbsice()+1, this.getOrdonne(), true);
			}else if(this.EnemieExplose(carte)){
				carte.explosion(this.getAbsice()+1,this.getOrdonne(),false);
			}else if (carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]=='R'){
				carte.captureDiamant();
				carte.getTerrain()[this.getAbsice()][this.getOrdonne()]=' ';
			
				this.setDetruit(true);
				
				
			
			
			
			}else if(carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]==' '){
				carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]='d';
				carte.getTerrain()[this.getAbsice()][this.getOrdonne()]=' ';
				
				this.setAbsice(this.getAbsice()+1);
				
			}
		
		
		
		
	}
	
	}
	
	
	

}
