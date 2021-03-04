package src;

/**
 * 
 * @author malik
 * il s agit de la classe Libellule herite de mechant , elle represente une instance d objet enemie qui peut se deplacer sur 
 * le terrain .
 *
 *
 *
 *
 *@see ObjetVivant 
 *@see Mechant
 *@see ElementTerrrain
 */




public class Libellule extends Mechant {

	@Override
	public void seDeplacer(Carte map) throws DeplacementInvalideException{
		// TODO Auto-generated method stub
		char x=this.getMv();
		int ab=this.getAbsice();
		int ord=this.getOrdonne();
		
		char[][]tab=map.getTerrain();
		if(x=='e'){
			if(ab==1){
				this.changementDeCap();
				//seDeplacer(map);
			}else {
				if(tab[ab-1][ord]!=' ' && tab[ab-1][ord]!='R'){
					this.changementDeCap();
					//seDeplacer(map);
					
				}else{
					map.deplacerPersonnage(ab, ord, x, this);
				}
				
			}
		}else if(x=='f'){
			
			if(ord==tab[0].length-2){
				this.changementDeCap();
				//seDeplacer(map);
			}else {
				if(tab[ab][ord+1]!=' ' && tab[ab][ord+1]!='R' ){
					this.changementDeCap();
					//seDeplacer(map);
				}else{
					map.deplacerPersonnage(ab, ord, x, this);
				}
			}
			
		}else if (x=='d'){
			if(ab==tab.length-2){
				this.changementDeCap();
			//	seDeplacer(map);
			}else{
				if(tab[ab+1][ord]!=' ' && tab[ab+1][ord]!='R'){
					this.changementDeCap();
				//	seDeplacer(map);
				}else{
					map.deplacerPersonnage(ab, ord,x,this);
				}
			}
		}else if(x=='s'){
			if(ord==1){
				this.changementDeCap();
				//seDeplacer(map);
			}else{
				if(tab[ab][ord-1]!=' '&& tab[ab][ord-1]!='R'){
					this.changementDeCap();
					//seDeplacer(map);
				}else{
					map.deplacerPersonnage(ab, ord, x, this);
				}
			}
		}else {
			throw new DeplacementInvalideException();
		}
		

	}


	public Libellule(int absice, int ordonne) {
		super(absice, ordonne,'f');
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * quand la libellule rencontre un obstacle , elle change de trajectoire dans le sens inverse des aiguilles d une montre
	 */
	
	public void changementDeCap(){
		char x=this.getMv();
		if(x=='e'){
			this.setMv('f');
		}else if (x=='f'){
			this.setMv('d');
		}else if(x=='d'){
			this.setMv('s');
		}else if(x=='s'){
			this.setMv('e');
		}
		
	}

}