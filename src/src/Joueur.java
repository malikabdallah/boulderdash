package src;


import java.util.*;




public class Joueur extends ObjetVivant {

	
	static Scanner lire=new Scanner(System.in);
	



	public void seDeplacer(Carte map, char mv){
		
		if(mv!=' '){
		
		}else{
			
		}
		map.deplacerPersonnage(this.getAbsice(), this.getOrdonne(), mv, this);
		
		
		
		
	}



	
	
	
	
	@Override
	public void seDeplacer(Carte map) throws DeplacementInvalideException {
		char c=' ';
		try{
			c=lire.nextLine().charAt(0);
			this.setMv(c);
		}catch(StringIndexOutOfBoundsException x){
			//this.setMv(' ');
			Cave cave=map.getCave();
			cave.setCaveTime(cave.getCaveTime()+1);
			//seDeplacer(map);
		}
		if(c=='e'|| c=='E'){
			if(this.getAbsice()==1){
				throw new DeplacementInvalideException("Deplacement non valide");
			}else{
				map.deplacerPersonnage(this.getAbsice(), this.getOrdonne(), c,this);
			}
		}
		if(c=='s'||c=='S'){
			if(this.getOrdonne()==1){
				throw new DeplacementInvalideException("Deplacement non valide");
			}else {
				map.deplacerPersonnage(this.getAbsice(), this.getOrdonne(),c, this);
			}
		}
		if(c=='f'|| c=='F'){
			if(this.getOrdonne()==map.getTerrain()[0].length-2){
				throw new DeplacementInvalideException("Deplacement non valide");
			}else{
				map.deplacerPersonnage(this.getAbsice(), this.getOrdonne(), c,this);
			}
		}
		if(c=='D'|| c=='d'){
			if(this.getAbsice()==map.getTerrain().length-2){
				throw new DeplacementInvalideException("Deplacement non valide");
			}else{
				map.deplacerPersonnage(this.getAbsice(), this.getOrdonne(), c, this);
			}
		}
		
	}

	
	
	
	

	public Joueur(int absice, int ordonne) {
		super(absice, ordonne);
		// TODO Auto-generated constructor stub
	}

}