package src;

import java.util.Arrays;
/**
 * 
 * @author malik
 *cette case represente les caracteristiques du terrain
 */
public class Cave  {

	/**
	 * le nom du niveau
	 */
	private String name;
	
	/**
	 * le nombre de pas autorise avant que Rockford meurt
	 */
	private int caveTime;
	/**
	 * le nombre de diamond necessaire pour pouvoir activer la sortie
	 */
	private int diamondsRequire;
	
	/**
	 * la valeur des diamond
	 */
	private int[] diamondValue;
	
	/**
	 * le nombre de tour avant que l'amibe grandisse
	 */
	private int amoebaTime;
	
	/**
	 * nombre d utilisation possible d'un mur magique 
	 */
	private int magicWallTime;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCaveTime() {
		return caveTime;
	}
	public void setCaveTime(int caveTime) {
		this.caveTime = caveTime;
	}
	public int getDiamondsRequire() {
		return diamondsRequire;
	}
	public void setDiamondsRequire(int diamondsRequire) {
		this.diamondsRequire = diamondsRequire;
	}
	public int[] getDiamondValue() {
		return diamondValue;
	}
	public void setDiamondValue(int[] diamondValue) {
		this.diamondValue = diamondValue;
	}
	public int getAmoebaTime() {
		return amoebaTime;
	}
	public void setAmoebaTime(int amoebaTime) {
		this.amoebaTime = amoebaTime;
	}
	public int getMagicWallTIme() {
		return magicWallTime;
	}
	public void setMagicWallTIme(int magicWallTIme) {
		this.magicWallTime = magicWallTIme;
	}
	
	
	
	
	public Cave(String name, int caveTime, int diamondsRequire,int[] diamondValue, int amoebaTime, int magicWallTIme) {
		super();
		this.name = name;
		this.caveTime = caveTime;
		this.diamondsRequire = diamondsRequire;
		this.diamondValue = diamondValue;
		this.amoebaTime = amoebaTime;
		this.magicWallTime = magicWallTIme;
	
	}
	@Override
	public String toString() {
		return "name=" + name + "\ncaveTime=" + caveTime
				+ "\ndiamondsRequire=" + diamondsRequire + "\ndiamondValue="
				+ Arrays.toString(diamondValue) + "\namoebaTime=" + amoebaTime
				+ "\nmagicWallTime=" + magicWallTime;
	}
	
	
	
	
	
	public Cave copie(){
		Cave x=this;
		x.setAmoebaTime(this.amoebaTime);
		x.setCaveTime(this.caveTime);
		x.setDiamondsRequire(this.diamondsRequire);
		x.setDiamondValue(this.getDiamondValue());
		x.setMagicWallTIme(this.magicWallTime);
		x.setName(this.name);
		return x;
	}
	
	
	
	
	
	
	
	
}
