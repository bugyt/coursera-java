import java.util.ArrayList;
import java.util.Collections;


class Cellule {
    /*****************************************************
     * Compléter le code à partir d'ici
     *****************************************************/
	private String nom;
	private double taille;
	private int energie;
	private String couleur;
	
	public Cellule() {
		this.nom = "Pyrobacculum";
		this.taille = 10;
		this.energie = 5;
		this.couleur = "verte";
	}
	
	public Cellule(Cellule c) {
		this.nom = c.nom ;
		this.taille = c.taille;
		this.energie = c.energie;
		this.couleur = c.couleur;
	}
	
	public Cellule(String n, double t, int e, String c) {
		this.nom = n;
		this.taille = t;
		this.energie = e;
		this.couleur = c;
	}
	
	public void affiche() {
		System.out.printf("%s, taille = "+ taille +" microns, énergie = %d, couleur = %s",nom, energie, couleur);
		System.out.println();	
	}
	
	public Cellule division() {
		Cellule nvCellule  = new Cellule(this);
		
//		nvCellule.nom = this.nom;
//		nvCellule.taille = this.taille;
//		nvCellule.energie = this.energie;
		this.energie -= 1;
		
		switch (this.couleur) {
			case "verte" :
				nvCellule.couleur = "bleue";
				break;
			case "bleue" :
				nvCellule.couleur = "rouge";
				break;
			case "rouge" :
				nvCellule.couleur = "rose bonbon";
				break;
			case "violet" :
				nvCellule.couleur = "verte";
				break;
			default :
				nvCellule.couleur = this.couleur + " fluo";
				break;
		}
		
		return nvCellule;
		
	}
	
	public int getEnergie() {
		return this.energie;
	}
//    + ", taille = "
//    + " microns, énergie = "
//    + ", couleur = "

}

class Petri {
	ArrayList<Cellule> population = new ArrayList<Cellule>();
	
	public void ajouter(Cellule c) {
		population.add(c);
	}
	
	public void affiche() {
		for (Cellule c : population) {
			c.affiche();
		}
	}
	
	public void evolue() {
		
		int max = population.size();
		
		for(int i = 0 ; i < max; i++) {
			Cellule cellEnCours = population.get(i);
			population.add(cellEnCours.division());
		}
		
		max = population.size();
		
		for(int i = 0 ; i < population.size(); i++) {
			Cellule cellEnCours = population.get(i);
		
			if (cellEnCours.getEnergie()<=0) {
				int last = population.size()-1;
				Collections.swap(population, i, last);
				population.remove(last);
			}
		}
	}
	
}

/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/
class SimulationPetri {
    public static void main(String[] args) {
        Petri assiette = new Petri();

        assiette.ajouter(new Cellule());
        assiette.ajouter(new Cellule("PiliIV", 4, 1, "jaune"));
        System.out.println("Population avant évolution :");
        assiette.affiche();

        assiette.evolue();
        System.out.println("Population après évolution :");
        assiette.affiche();
    }
}

