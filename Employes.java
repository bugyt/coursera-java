/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.text.NumberFormat;
import java.util.*;

class Employe{
	private final String nom;
	private double revenuMensuel;
	private int tauxOccupation;
	private String type;
	private double montantPrime = 0;
	
	public String retourneType(){
		return "";
	}
	
	public Employe(String nom, double revenuMensuel, int tauxOccupation) {
		this.nom = nom;
		this.revenuMensuel = revenuMensuel;
		if (tauxOccupation <10){
			this.tauxOccupation=10;
		} else{
			if (tauxOccupation >100){
				this.tauxOccupation=100;
			} else{
				this.tauxOccupation = tauxOccupation;
			}
		}
		
		
		System.out.println("Nous avons un nouvel employé : " + nom + "," + retourneType());
	}
	
	public Employe(String nom, double revenuMensuel) {
		this(nom, revenuMensuel, 100);	
	}
	
	
	public double getRevenuMensuel() {
		return revenuMensuel;
	}
	public void setRevenuMensuel(int revenuMensuel) {
		this.revenuMensuel = revenuMensuel;
	}
	public double getTauxOcupation() {
		return tauxOccupation;
	}
	public void setTauxOcupation(int tauxOcupation) {
		this.tauxOccupation = tauxOcupation;
	}
	public String getNom() {
		return nom;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public double revenuAnnuel(){
		return (12*revenuMensuel*tauxOccupation/100) + montantPrime;
	}

	@Override
	public String toString() {
		String txtPrime="";
		
		if (montantPrime!=0){
			txtPrime = ", Prime : " + montantPrime;
		} 
		
		return nom + " :\n Taux d'occupation : " + tauxOccupation + "%. Salaire annuel : " + revenuAnnuel() + " francs" + txtPrime + ".\n";
	}
	
	public void demandePrime(){
		double primeDemandee=0;
		boolean success = true;
		Number number;
		int nbEssais = 0;
		String saisie;
		Scanner clavier = new Scanner(System.in);
		NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
				
		do{
		    try
		    { 
		    	System.out.println("Montant de la prime souhaitée par " + nom + " ?");
		    	success = true;
		    	nbEssais++;
		    	saisie = clavier.next();
		    	number = format.parse(saisie);
		    	primeDemandee = number.doubleValue();
		    	//primeDemandee = clavier.nextLine();
		    	
		    } 

		    catch ( Exception ex )
		    { 
		    	success = false;
		    	System.out.println("Vous devez introduire un nombre!" );
		    }  

		    if (success && (primeDemandee > (revenuAnnuel()*2/100))){
		    	System.out.println("Trop cher!" );
		    } else {
		    	if (success){montantPrime = primeDemandee;}
		    }
		    
		}
		while((!success || primeDemandee > (revenuAnnuel()*2/100)) && (nbEssais < 5));
		//clavier.next();
		//clavier.close();
		

	}
	
}

class Manager extends Employe{
	private int nbJoursVoyages;
	private int nbNouveauClients;
	public final static int FACTEUR_GAIN_CLIENT = 500;
	public final static int FACTEUR_GAIN_VOYAGE = 100;
	
	public String retourneType(){
		return  " c'est un manager.";
	}
	
	public int getNbJoursVoyages() {
		return nbJoursVoyages;
	}
	public void setNbJoursVoyages(int nbJoursVoyages) {
		this.nbJoursVoyages = nbJoursVoyages;
	}
	public int getNbNouveauClients() {
		return nbNouveauClients;
	}
	public void setNbNouveauClients(int nbNouveauClients) {
		this.nbNouveauClients = nbNouveauClients;
	}
	public Manager(String nom, double revenuMensuel, int nbJoursVoyages, int nbNouveauClients, int tauxOcupation) {
		
		super(nom, revenuMensuel, tauxOcupation);
		this.nbJoursVoyages = nbJoursVoyages;
		this.nbNouveauClients = nbNouveauClients;
		
		// TODO Auto-generated constructor stub
	}
	
	public Manager(String nom, double revenuMensuel, int nbJoursVoyages, int nbNouveauClients) {
		this(nom, revenuMensuel,nbJoursVoyages, nbNouveauClients,100);
	}
	public double revenuAnnuel(){
		return super.revenuAnnuel() + nbNouveauClients*FACTEUR_GAIN_CLIENT + nbJoursVoyages*FACTEUR_GAIN_VOYAGE;
	}
	
	public String toString() {
		return super.toString() + " A voyagé " + nbJoursVoyages + " jours et apporté " + nbNouveauClients + " nouveaux clients.";

	}
	
}

class Programmeur extends Employe{
	private int nbProjetsAcheves;
	public final static int FACTEUR_GAIN_PROJETS = 200;
	
	public String retourneType(){
		return " c'est un programmeur.";
	}

	public int getNbProjetsAcheves() {
		return nbProjetsAcheves;
	}

	public void setNbProjetsAcheves(int nbProjetsAcheves) {
		this.nbProjetsAcheves = nbProjetsAcheves;
	}

	public Programmeur(String nom, double revenuMensuel, int nbProjetsAcheves, int tauxOcupation) {
		super(nom, revenuMensuel, tauxOcupation);
		// TODO Auto-generated constructor stub
		this.nbProjetsAcheves = nbProjetsAcheves;
	}
	public Programmeur(String nom, double revenuMensuel, int nbProjetsAcheves) {
		this(nom, revenuMensuel, nbProjetsAcheves, 100);
	}	
	
	public double revenuAnnuel(){
		return super.revenuAnnuel() + nbProjetsAcheves*FACTEUR_GAIN_PROJETS;
	}
	
	public String toString() {
		String proj;
		if(nbProjetsAcheves>1){
			proj = " projets";
		} else {proj = " projet";}
		return super.toString() + " A mené à bien " + nbProjetsAcheves + proj;

	}
	
}

class Testeur extends Employe{
	private int nbErreursCorrigees;
	public final static int FACTEUR_GAIN_ERREURS = 10;
	
	public String retourneType(){
		return " c'est un testeur.";
	}

	public int getNbErreursCorrigees() {
		return nbErreursCorrigees;
	}

	public void setNbErreursCorrigees(int nbErreursCorrigees) {
		this.nbErreursCorrigees = nbErreursCorrigees;
	}

	public Testeur(String nom, double revenuMensuel, int nbErreursCorrigees, int tauxOcupation) {
		super(nom, revenuMensuel, tauxOcupation);
		this.nbErreursCorrigees = nbErreursCorrigees;
	}
	public Testeur(String nom, double revenuMensuel, int nbErreursCorrigees) {
		this(nom, revenuMensuel, nbErreursCorrigees,100);
	}
	
	public double revenuAnnuel(){
		return super.revenuAnnuel() + nbErreursCorrigees*FACTEUR_GAIN_ERREURS;
	}
	
	public String toString() {
		return super.toString() + " A corrigé " + nbErreursCorrigees + " erreurs.";

	}
	
	
}

/*"Montant de la prime souhaitée par "



/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Employes {
    public static void main(String[] args) {

        List<Employe> staff = new ArrayList<Employe>();

        // TEST PARTIE 1:

        System.out.println("Test partie 1 : ");
        staff.add(new Manager("Serge Legrand", 7456, 30, 4 ));
        staff.add(new Programmeur("Paul Lepetit" , 6456, 3, 75 ));
        staff.add(new Testeur("Pierre Lelong", 5456, 124, 50 ));

        System.out.println("Affichage des employés : ");
        for (Employe modele : staff) {
            System.out.println(modele);
        }
        // FIN TEST PARTIE 1
        // TEST PARTIE 2
        System.out.println("Test partie 2 : ");

        staff.get(0).demandePrime();
        staff.get(0).demandePrime();
        staff.get(0).demandePrime();

        System.out.println("Affichage après demande de prime : ");
        System.out.println(staff.get(0));

        // FIN TEST PARTIE 2
    }
}

