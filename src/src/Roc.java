package src;

public class Roc extends ObjetNonVivant {

	public Roc(int absice, int ordonne, Carte terrainObjet) {
		super(absice, ordonne, terrainObjet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tomber(Carte carte) {
		// TODO Auto-generated method stub
		
		if(this.isTombe()){
			
		}
		if(carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]==' '){
			
			this.setTombe(true);
		}
		
		
		
		if(this.isTombe()&&this.getAbsice()<=carte.getTerrain().length-3){
			//System.out.println("Je tombe");
			if(this.LibelluleExplose(carte)){
				carte.explosion(this.getAbsice()+1, this.getOrdonne(), true);
			}else if(this.EnemieExplose(carte)){
				carte.explosion(this.getAbsice()+1,this.getOrdonne(),false);
			}else if (carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]=='R'){
				
				carte.mortDuHero();
				carte.getTerrain()[this.getAbsice()][this.getOrdonne()]=' ';
						carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]='r';
			}else if(carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]==' '){
			
				carte.getTerrain()[this.getAbsice()+1][this.getOrdonne()]='r';
				carte.getTerrain()[this.getAbsice()][this.getOrdonne()]=' ';
				this.setDetruit(true);
			
				
			}
		
		
		
		
	}else{
		carte.getTerrain()[this.getAbsice()][this.getOrdonne()]='r';
		
		this.setDetruit(true);
	}
		
	if(this.getAbsice()+2<=carte.getTerrain().length-2){
		if(carte.getTerrain()[this.getAbsice()+2][this.getOrdonne()]=='r' || 
				carte.getTerrain()[this.getAbsice()+2][this.getOrdonne()]=='d' ||
				carte.getTerrain()[this.getAbsice()+2][this.getOrdonne()]=='.' && this.isTombe()){
				this.setTombe(false);
	}
	}
		
		
		
		
		
		if(this.isTombe()){
			
			carte.getTableauSauvegardeChuteObjet()[this.getAbsice()+2][this.getOrdonne()]='*';
		}
		
	}
	


}
