package metier;

public class Plateau {
	
	private Case[][] plat;
	private final int longueur;
	private final int largeur;
	private int nbrBat3;
	private int nbrBat4;
	private int nbrBat5;
	private boolean PlacementReussi = false;
		
	public boolean isPlacementReussi() {
		return PlacementReussi;
	}


	public void setPlacementReussi(boolean placementReussi) {
		PlacementReussi = placementReussi;
	}


	public Plateau(int longueur, int largeur, int nb3, int nb4, int nb5) {
		this.plat = new Case[longueur][largeur]; 
		for (int i = 0; i < plat.length; i++) {
			for (int j = 0; j < plat.length; j++) {
				this.plat[i][j] = new Case();
			}
		}
		this.longueur = longueur;
		this.largeur = largeur;
		this.nbrBat3=nb3;
		this.nbrBat4=nb4;
		this.nbrBat5=nb5;
	}
	

	public boolean aEteTouch�(int x, int y){
		if (this.plat[x][y].getDejaTir�() && this.plat[x][y].getEstoccup�()) {
			return true;
		} else {
			return false;
		}
	}
	public void d�crementer(int tailleBat){
		if(tailleBat==3)
		{
			nbrBat3--;
		}
		if(tailleBat==4){
			nbrBat4--;
			
		}
		if(tailleBat==5)
		{
			nbrBat5--;
		}
	}
	public int EncoreUnBateau(int tailleBat){
		if(tailleBat==3)
		{
			return nbrBat3;
		}
		if(tailleBat==4){
			return nbrBat4;
			
		}
		else
		{
			return nbrBat5;
		}
	}
	
	
	public int getlongueur() {
		return longueur;
	}

	
	public int getLargeur() {
		return largeur;
	}


	public boolean emplcmtDisp(int x, int y){
		if(plat[x][y].getEstoccup�()){
			
			return false;
		} 
		else{
			return true;
		}
		
	}
	

	public boolean aDejaEteTire(int x, int y){
		if(plat[x][y].getDejaTir�()){
			
			return true;
		} 
		else{
			return false;
		}
		
	}
	

	public boolean PlateauSuffisant(){
		int taillePlateau = (this.largeur*this.longueur)/2;
		int tailleTousBateaux = nbrBat3*3+nbrBat4*4+nbrBat5*5;
		if(taillePlateau>=tailleTousBateaux){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public void positionner(Bateau bat, int x, int y){

		boolean peutPosit = false;
		PlacementReussi=false;
		System.out.println(bat.isEstVertical()+" "+ "vert");
		//if pour le nombre de bateau
		if(EncoreUnBateau(bat.getTaille())!=0)
		{
			if (bat.isEstVertical()) {				
					peutPosit = true;
	
						// vérifie qu'il n'y a pas de bateau à l'endroit ou l'on veut le poser 
						for (int i = 0; i < bat.getTaille(); i++) {
							if (this.plat[x + i][y].getEstoccup�()) {
								peutPosit = false;
								System.out.println("La case est occupé");
							}
						}			
				if(peutPosit==true){	
					PlacementReussi=true;
				// Positionne le bateau case à case
				for (int i = 0; i < bat.getTaille(); i++) {
					this.plat[x+i][y].setEstoccup�();
					this.plat[x+i][y].setOccupePar(bat);
					System.out.println(x+" "+y);
				}
				d�crementer(bat.getTaille());
			}
			
				
			}else {				
					peutPosit = true;
						// vérifie qu'il n'y a pas de bateau à l'endroit ou l'on veut le poser 
						for (int i = 0; i < bat.getTaille(); i++) {
							if (this.plat[x][y + i].getEstoccup�()) {
								peutPosit = false;
								System.out.println("il y a déjà un bateau ");
							}
						}	
				if(peutPosit==true){
				PlacementReussi=true;
				//Positionne le bateau case à case
					for (int i = 0; i < bat.getTaille(); i++) {
						this.plat[x][y+i].setEstoccup�();		
						this.plat[x][y+i].setOccupePar(bat);
						System.out.println("test");
					}
				d�crementer(bat.getTaille());
				}
			}
		}
		else{
			System.out.println("VOus n'avez plus de Bateau de taille "+ bat.getTaille());
		}
	}
	
	public int getNbrBat3() {
		return nbrBat3;
	}


	public void setNbrBat3(int nbrBat3) {
		this.nbrBat3 = nbrBat3;
	}


	public int getNbrBat4() {
		return nbrBat4;
	}


	public void setNbrBat4(int nbrBat4) {
		this.nbrBat4 = nbrBat4;
	}


	public int getNbrBat5() {
		return nbrBat5;
	}


	public void setNbrBat5(int nbrBat5) {
		this.nbrBat5 = nbrBat5;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * 
	 * tirer v�rifie que l'on peut tirer sur cette case et si touche bateau v�rifie s'il est coul� 
	 */

	public int tirer( int x , int y){
		

		if(this.plat[x][y].getDejaTir�()) {
			System.out.println("Vous avez déjà tiré sur cette case");
			return 0;
		} 	
		else{
		// Fait que le joueur ne puisse plus tirer sur cette case
		this.plat[x][y].setDejaTir�();
		
		//Test si un bateau est touch�
		if (this.plat[x][y].getEstoccup�()) {
			
			System.out.println("Touch� !");
			//enl�ve un point de vie au bateau 

			this.plat[x][y].getOccupePar().setPtDeVie(this.plat[x][y].getOccupePar().getPtDeVie() - 1);
			System.out.println(this.plat[x][y].getOccupePar().getPtDeVie());
			

			//v�rifie si le bateau est coul�
			if (this.plat[x][y].getOccupePar().getPtDeVie() == 0) {

				System.out.println("Coul� !");
				this.plat[x][y].getOccupePar().setestCoul�();
				System.out.println("Coulé !");
				this.plat[x][y].getOccupePar().setestCoul�();
				return 3;
			}
			return 1;
		}
		else{
			System.out.println("A l'eau");
			return 2;
		}
		}
				
	}

}
