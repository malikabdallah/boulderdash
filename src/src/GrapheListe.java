package src;

import java.util.ArrayList;
/**
 * @author Malik
 * cette classe sert a representer une liste d adjacence
 */

import java.util.*;
public class GrapheListe extends Graphe{


	private Vector <LinkedList<Arrete>>L;
	private List<Sommet> S;
	private Carte carte;
	
	
	public GrapheListe(Carte carte){
		this.carte=carte;
		int n=carte.getTerrain().length*carte.getTerrain()[0].length;
		L=new Vector(n);
		L.setSize(n);
		creationSommet(carte);
		creationArrete(carte);
		
		

	}
	
	
	private void creationArrete(Carte carte){
		for(int i=0;i<=carte.getTerrain().length-1;i++){
			for(int j=0;j<=carte.getTerrain()[0].length-1;j++){
				Sommet x=null;
				Sommet dessus,dessous,agauche,adroite;
				dessus=null;
				dessous=null;
				agauche=null;
				adroite=null;
				for(Sommet sommet: S){
					if(sommet.getAbsice()==i && sommet.getOrdonne()==j){
						x=sommet;
						
					}
					if(sommet.getAbsice()==i-1 && sommet.getOrdonne()==j){
						dessus=sommet;
					}
					if(sommet.getAbsice()==i+1 && sommet.getOrdonne()==j){
						dessous=sommet;
					}
					if(sommet.getAbsice()==i&& sommet.getOrdonne()==j-1){
						agauche=sommet;
					}
					if(sommet.getAbsice()==i && sommet.getOrdonne()==j+1){
						adroite=sommet;
					}
				}
				this.ajouterArrete(x, agauche);
				
				this.ajouterArrete(x, adroite);
				this.ajouterArrete(x, dessous);
				this.ajouterArrete(x, dessus);
				
			
				
			}
		}
	}
	
	
	
	@Override
	public String toString() {
		return L.toString();
	}


	private void creationSommet(Carte carte){
		int cpt=0;S=new ArrayList<Sommet>();
		for(int i=0;i<=carte.getTerrain().length-1;i++){
			for(int j=0;j<=carte.getTerrain()[0].length-1;j++){
				Sommet x=new Sommet(i,j);
				S.add(x);
				ajouterSommet(x);
				cpt++;
			
			}
		}
		
	}
	

	

	
	public Vector<LinkedList<Arrete>> getL() {
		return L;
	}


	public List<Sommet> getS() {
		return S;
	}


	public Carte getCarte() {
		return carte;
	}


	@Override
	public int taille() {
		// TODO Auto-generated method stub
		return L.size();
	}


	@Override
	public void ajouterSommet(Sommet s) {
		// TODO Auto-generated method stub
		L.set(s.getNumero(), new LinkedList<Arrete>());
		
	}


	
	
	@Override
	public void ajouterArrete(Sommet s, Sommet t) {
		// TODO Auto-generated method stub
		if(t!=null){
			LinkedList<Arrete> l=L.get(s.getNumero());
			l.add(new Arrete(s,t));
		}
		
	}








}
