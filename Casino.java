import java.util.ArrayList;
import java.util.Arrays;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
abstract class Mise {
	private int nbJetons;
	
	public Mise(int nbJetons){
		this.nbJetons = nbJetons;
	}

	public int getMise() {
		return this.nbJetons;
	}

	public void setNbJetons(int nbJetons) {
		this.nbJetons = nbJetons;
	}
	
	abstract public int gain(int n);
	
}

class Pleine extends Mise {
	
	public static final int FACTEUR_GAIN = 35;
		
	private int numero;
	
	public Pleine(int nbJetons, int numero){
		super(nbJetons);
		this.numero = numero;
	}
	
	public int gain(int n){
		if (n == numero){
			return super.getMise()*FACTEUR_GAIN;
		} else {
			return 0;
		}
		
	}
}

class Rouges extends Mise {
	
	//private static final int[] LISTNUM = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
	private static final ArrayList<Integer> ROUGES = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36));
		
	public Rouges(int nbJetons){
		super(nbJetons);
	//	ROUGES = new ArrayList<Integer>();
	//	for ( int i : LISTNUM ) {
	//		ROUGES.add(i);
	//	}
	}
	
	public int gain(int n){
		if(ROUGES.contains(n)) {
		    return super.getMise(); 
		}else {
			return 0;
		}
			
	}
}

class Joueur {
	private String nom;
	private Mise strategie;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Joueur(String nom){
		this.nom = nom;
	}
	
	public void setStrategie(Mise mise){
		this.strategie = mise;
	}

	public int getMise() {
		if (strategie != null){
			return this.strategie.getMise();
		}else {
			return 0;
		}
	}
	
	public int gain(int n){
		if (strategie != null){
			return this.strategie.gain(n);
		}else {
			return 0;
		}
	}
	
	
}


abstract class Roulette {
	private ArrayList<Joueur> joueurs;
	private int gainMaison;
	private int numeroTire;
	

	public Roulette() {
		gainMaison=0;
		this.joueurs = new ArrayList<Joueur>();
	}
	
	public void participe(Joueur j){
			joueurs.add(j);
	}
	
	public int getTirage(){
		return numeroTire;
	}
	
	public int getGainMaison(){
		return gainMaison;
	}
	
	public int getParticipants(){
		return joueurs.size();
	}
	
	public void rienNeVaPlus(int n){
		
		this.numeroTire = n;
	}
	abstract public int perteMise(int miseDuJoueur);
	
	public void calculerGainMaison(){
		gainMaison=0;
		for (Joueur o : joueurs) {
			if (o.gain(numeroTire)>0){
				gainMaison -= o.gain(numeroTire);
			} else {
				gainMaison += perteMise(o.getMise());
			}
		}
	}

	@Override
	public String toString() {
		String chaine;
		chaine = "Croupier : le numéro du tirage est le " + numeroTire + "\n";
		for (Joueur o : joueurs) {
			chaine += "Le joueur " + o.getNom() + " mise " + o.getMise();
			if (o.gain(numeroTire)>0){
				chaine += " et gagne " + o.gain(numeroTire) + "\n";
			} else {
				chaine += " et perd" + "\n";
			}
		}
		calculerGainMaison();
		chaine += "Gain/perte du casino : " + gainMaison;
		return chaine;
	}
	
	
}

interface ControleJoueurs{
	boolean check();
}

class RouletteFrancaise extends Roulette{
	
	
	public int perteMise(int miseDuJoueur){
		return miseDuJoueur;
	}
	
}

class RouletteAnglaise extends Roulette implements ControleJoueurs{
	
		
		public void participe(Joueur j){
			if (check()){
				super.participe(j);
			}
			
		}
	
		public int perteMise(int miseDuJoueur){
			return miseDuJoueur/2;
		}
		
		public boolean check(){
			return (getParticipants()<10);		
		}
}


/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Casino {

    private static void simulerJeu(Roulette jeu) {
        for (int tirage : new int [] { 12, 1, 31 }) {
            jeu.rienNeVaPlus(tirage);
            jeu.calculerGainMaison();
            System.out.println(jeu);
            System.out.println("");
        }
    }

    public static void main(String[] args) {

        Joueur joueur1 = new Joueur("Dupond");
        joueur1.setStrategie(new Pleine(100,1)); // miser 100 jetons sur le 1

        Joueur joueur2 = new Joueur("Dupont");
        joueur2.setStrategie(new Rouges(30)); // miser 30 jetons sur les rouges

        Roulette jeu1 = new RouletteAnglaise();
        jeu1.participe(joueur1);
        jeu1.participe(joueur2);

        Roulette jeu2 = new RouletteFrancaise();
        jeu2.participe(joueur1);
        jeu2.participe(joueur2);

        System.out.println("Roulette anglaise :");
        simulerJeu(jeu1);
        System.out.println("Roulette française :");
        simulerJeu(jeu2);     
    }
}
