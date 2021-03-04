package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.*;
import java.util.*;
public class LireFichier {
	
	/**
	 *  cette classe s occupe de lire le fichier bdcff , de recuperer chacune des maps et cave qui s y trouve et de les ajouter a la classe univers qui
	 *  contient chaque niveau du fichier
	 *  @see Univers
	 */
	private static Scanner sc;
	private static String fichier;
	
	
	
	static void aff(String p){
		System.out.println(p);
	}
	
	public static void lire(File bdcff,Univers listNiveau) {

	try{
		String ligne="";

		fichier=fichier+ligne+"\n";
		sc = new Scanner(bdcff);
		String name="";
		int caveTime=0;
		int diamondsRequire=0;
		int magicWallTime=0;
		int amoebaTime=0;
		while(sc.hasNextLine()){
			ligne=sc.nextLine();
			fichier=fichier+ligne+"\n";
			
			
			ArrayList<String> list=new ArrayList<String>();
			Cave y=null;
			Carte x=null;
			if(ligne.equals("[cave]")){
				int[] diamondValue=new int[2];
				while(sc.hasNextLine()){
					ligne=sc.nextLine();
					fichier=fichier+ligne+"\n";
					if(ligne.equals("[/cave]")){
						x=new Carte(list,y);
						listNiveau.ajouterNiveau(x)
						
						;
						break;
					}
					if(ligne.equals("[map]")){
						String s=""; 
						y=new Cave(name,caveTime,diamondsRequire,diamondValue,amoebaTime,magicWallTime);

						while(sc.hasNextLine()){
							ligne=sc.nextLine();
							fichier=fichier+ligne+"\n";
							if(ligne.equals("[/map]")){
								
								break;
							}
							list.add(ligne);
							s=s+ligne+"\n";
						}
					}

					
					
					if(ligne.length()>4 && ligne.substring(0, 4).equals("Name")){
						
						name=ligne.substring(5, ligne.length());
					}
					if(ligne.length()>8&& ligne.substring(0, 8).equals("CaveTime")){
						caveTime=Integer.parseInt(ligne.substring(9, ligne.length()));
					}
					if(ligne.length()>16 && ligne.substring(0,16).equals("DiamondsRequired")){
						diamondsRequire=Integer.parseInt(ligne.substring(17, ligne.length()));
					}
					if(ligne.length()>10 && ligne.substring(0, 10).equals("AmoebaTime")){
						amoebaTime=Integer.parseInt(ligne.substring(11, ligne.length()));
					}
					if(ligne.length()>13 && ligne.substring(0,13).equals("MagicWallTime")){
						magicWallTime=Integer.parseInt(ligne.substring(14,ligne.length()));
					}
					
					if(ligne.length()>12  && ligne.substring(0,12).equals("DiamondValue")){
						int v1,v2;
						String s=ligne.substring(13,ligne.length());
					
						int i1,i2;
						i1=i2=0;
						for(int i=1;i<=s.length()-1;i++){
							if(s.charAt(i)==' '){
								i1=i;
							}
						}
						for(int i=s.length()-1; i>=1; i--){
							if(s.charAt(i)==' '){
								i2=i+1;
							}
						}
						v1=Integer.parseInt(s.substring(0, i1));
						v2=Integer.parseInt(s.substring(i2,s.length()));
						diamondValue[0]=v1;diamondValue[1]=v2;
				
					}
				
					
					
					
				}
				System.out.println();
				
				
			}
		
		}
	
		
		
	}catch( FileNotFoundException e){
		e.printStackTrace();
	}
	}

	public static String getFichier() {
		return fichier;
	}

	public static void setFichier(String fichier) {
		LireFichier.fichier = fichier;
	}

	
	
	
	

}
