import java.util.ArrayList;
import java.util.Random;

/*******************************************
 * Completez le programme à partir d'ici.
 *******************************************/
class Postulant {
	private String nom;
	private int nombreElecteurs;
	
	public Postulant(String nom, int nombreElecteurs) {
		this.nom = nom;
		this.nombreElecteurs = nombreElecteurs;
	}
	
	public Postulant(String nom) {
		this.nom = nom;
		this.nombreElecteurs = 0;
	}
	
	public Postulant(Postulant autrePostulant) {
		this(autrePostulant.nom, autrePostulant.nombreElecteurs);
	}
	
    public void init(){
    	this.nombreElecteurs = 0;
    }
    
    public void elect(){
    	this.nombreElecteurs ++;
    }
    
    public int getVotes(){
    	return this.nombreElecteurs;
    }
    
    public String getNom(){
    	return this.nom;
    }
    
}

class Scrutin {
	private ArrayList<Postulant> postulants;
	private ArrayList<Vote> votes;
	private int nombreMembres;
	private int jour;
	
	public Scrutin(ArrayList<Postulant> postulants, int nombreMembres, int jour, boolean init) {
		
		this.postulants = new ArrayList<Postulant>();
		if (postulants!=null){
			for (Postulant p: postulants){
				this.postulants.add(new Postulant(p));
			}
		}
		
		this.nombreMembres = nombreMembres;
		this.jour = jour;
		if (init){
			for(Postulant p : this.postulants){
				p.init();
			}
		}
		this.votes = new ArrayList<Vote>();
	}
	
	public Scrutin(ArrayList<Postulant> postulants, int nombreMembres, int jour, boolean init,ArrayList<Vote> votes) {
		this.postulants = new ArrayList<Postulant>();
		if (postulants!=null){
			for (Postulant p: postulants){
				this.postulants.add(new Postulant(p));
			}
		}
		this.nombreMembres = nombreMembres;
		this.jour = jour;
		if (init){
			for(Postulant p : this.postulants){
				p.init();
			}
		}
		this.votes = new ArrayList<Vote>(votes);
	}
	
	public Scrutin(ArrayList<Postulant> postulants, int nombreMembres, int jour) {
		this.postulants = new ArrayList<Postulant>();
		if (postulants!=null){
			for (Postulant p: postulants){
				this.postulants.add(new Postulant(p));
			}
		}
		this.nombreMembres = nombreMembres;
		this.jour = jour;
		for(Postulant p : this.postulants){
			p.init();
		}
		this.votes = new ArrayList<Vote>();
	}
	
	public Scrutin(ArrayList<Postulant> postulants, int nombreMembres, int jour,ArrayList<Vote> votes) {
		this.postulants = new ArrayList<Postulant>();
		if (postulants!=null){
			for (Postulant p: postulants){
				this.postulants.add(new Postulant(p));
			}
		}
		this.nombreMembres = nombreMembres;
		this.jour = jour;
		for(Postulant p : this.postulants){
			p.init();
		}
		this.votes = new ArrayList<Vote>(votes);
	}
	
	public Scrutin(Scrutin autreScrutin){
		this(autreScrutin.postulants, autreScrutin.nombreMembres, autreScrutin.jour, autreScrutin.votes);
	}
	
	public int calculerVotants(){
		int nombreVotants=0;
		for(Postulant p : this.postulants){
			nombreVotants+=p.getVotes();
		}
		return nombreVotants;
	}
	
	public String gagnant(){
		String nom="";
		int nombreVotants=0;
		for(Postulant p : this.postulants){
			if (p.getVotes()>=nombreVotants){
				nombreVotants=p.getVotes();
				nom = p.getNom();
			}
		}
		return nom;
	}
	
	private String ratio(int base, int total){
		double calcul;
		if (total!=0){
			calcul = (double)base / (double)total * 100;
		} else {
			calcul = 0;
		}
		return  String.format("%.1f", calcul);
	}
	
	public void resultats(){
		String chaine;
		if (calculerVotants()>0){
			chaine = "Taux de participation -> " + ratio(calculerVotants(), nombreMembres) + " pour cent\n";
			chaine += "Nombre effectif de votants -> " + calculerVotants() + "\n";
			chaine += "Le chef choisi est -> " + gagnant() + "\n\n";
			chaine += "Répartition des electeurs\n";
			for(Postulant p : this.postulants){
				chaine += p.getNom() + " -> " + ratio(p.getVotes(), calculerVotants()) + " pour cent des électeurs\n";
			}		
		} else {
			chaine = "Scrutin annulé, pas de votants\n";
		}
		System.out.println(chaine);
	}
	
	public void simuler(double tauxParticipation, int jourDeVote){
		int nombreVotants = (int)(nombreMembres*tauxParticipation);
		int candNum;

		for(int i=0; i<nombreVotants;++i){
			candNum = Utils.randomInt(postulants.size());
			
			if (i%3 == 0){
				votes.add(new BulletinElectronique(postulants.get(candNum).getNom(),jourDeVote,jour));
			}
			if (i%3 == 1){
				votes.add(new BulletinPapier(postulants.get(candNum).getNom(),jourDeVote,jour, (i%2!=0)));
			}
			if (i%3 == 2){
				votes.add(new BulletinCourrier(postulants.get(candNum).getNom(),jourDeVote,jour, (i%2!=0)));
			}
			System.out.println(votes.get(votes.size()-1));
		}
	}
	
	public void compterVotes(){
		 for(Vote v : votes){
			 if(!v.estInvalide()){
				 for(Postulant p : postulants){
					 if (p.getNom() == v.getNomPostulant()){
							p.elect();
						}
				 }
			 }
		 }
	}
	
	
}

abstract class Vote {
	private String nomPostulant;
	private int date;
	private int dateLimite;
	
	public String getPostulant() {
		return nomPostulant;
	}
	
	public String getNomPostulant() {
		return nomPostulant;
	}

	public void setNomPostulant(String nomPostulant) {
		this.nomPostulant = nomPostulant;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getDateLimite() {
		return dateLimite;
	}

	public void setDateLimite(int dateLimite) {
		this.dateLimite = dateLimite;
	}

	public Vote(String nomPostulant, int date, int dateLimite) {
		this.nomPostulant = nomPostulant;
		this.date = date;
		this.dateLimite = dateLimite;
	}
	
	public Vote(Vote v){
		this(v.nomPostulant, v.date, v.dateLimite);
	}

	abstract boolean estInvalide();	
		
	public String lib(){
		return "";
	}
	public String toString(){
		String chaine;
		chaine = lib() + " pour " + getNomPostulant() + " -> ";
		if (estInvalide()){
			chaine += "invalide";
		} else {
			chaine += "valide";
		}
		
		return chaine;
	}
	
	
}

interface CheckBulletin{
	boolean checkDate();
}

class BulletinPapier extends Vote{
	
	private boolean signe;
	
	public boolean getSigne(){
		return signe;
	}
	
	public BulletinPapier(String nomPostulant, int dateEffective, int dateLimite, boolean signe) {
		super(nomPostulant, dateEffective, dateLimite);
		this.signe = signe;
	}
	
	public BulletinPapier(BulletinPapier b){
		super(b.getPostulant(), b.getDate(), b.getDateLimite());
		this.signe = b.signe;
	}

	public boolean estInvalide(){
		return !this.signe;
	}
	
	public String lib(){
		return "vote par bulletin papier";
	}
	
	public String toString(){
		return super.toString();
	}
	
}

class BulletinCourrier extends BulletinPapier implements CheckBulletin{

	public BulletinCourrier(String nomPostulant, int dateEffective, int dateLimite, boolean signe) {
		super(nomPostulant, dateEffective, dateLimite, signe);
		// TODO Auto-generated constructor stub
	}
	
	public BulletinCourrier(BulletinCourrier b){
		super(b.getPostulant(), b.getDate(), b.getDateLimite(), b.getSigne());
	} 
	public boolean estInvalide(){
		return !getSigne() || !(checkDate());
	}
	
	public boolean checkDate(){
		return !(getDate() > getDateLimite());
	}
	
	public String lib(){
		return "envoi par courrier d'un vote par bulletin papier";
	}
	
	public String toString(){
		return super.toString();
	}
	
}
class BulletinElectronique extends Vote implements CheckBulletin{
	
	
	public BulletinElectronique(String nomPostulant, int dateEffective, int dateLimite) {
		super(nomPostulant, dateEffective, dateLimite);
		// TODO Auto-generated constructor stub
	}
	
	public BulletinElectronique(BulletinElectronique b){
		super(b.getPostulant(), b.getDate(), b.getDateLimite());
	} 

	public boolean estInvalide(){
		return !checkDate();
	}
	
	public boolean checkDate(){
		return !(getDate() > (getDateLimite()-2));
	}
	
	public String lib(){
		return "vote electronique";
	}
	
	public String toString(){
		return super.toString();
	}
	
}



/*
"vote électronique"
System.out.println("Scrutin annulé, pas de votants");
"Répartition des électeurs "
" -> %.1f pour cent des électeurs\n"
*/
/*******************************************
 * Ne pas modifier les parties fournies
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

class Utils {

    private static final Random RANDOM = new Random();

    // NE PAS UTILISER CETTE METHODE DANS LES PARTIES A COMPLETER
    public static void setSeed(long seed) {
        RANDOM.setSeed(seed);
    }

    // génère un entier entre 0 et max (max non compris)
    public static int randomInt(int max) {
        return RANDOM.nextInt(max);
    }
}

/**
 * Classe pour tester la simulation
 */

class Votation {

    public static void main(String args[]) {
        Utils.setSeed(20000);
        // TEST 1
        System.out.println("Test partie I:");
        System.out.println("--------------");

        ArrayList<Postulant> postulants = new ArrayList<Postulant>();
        postulants.add(new Postulant("Tarek Oxlama", 2));
        postulants.add(new Postulant("Nicolai Tarcozi", 3));
        postulants.add(new Postulant("Vlad Imirboutine", 2));
        postulants.add(new Postulant("Angel Anerckjel", 4));

        // 30 -> nombre maximal de votants
        // 15 jour du scrutin
        Scrutin scrutin = new Scrutin(postulants, 30, 15, false);

        scrutin.resultats();

        // FIN TEST 1

        // TEST 2
        System.out.println("Test partie II:");
        System.out.println("---------------");

        scrutin = new Scrutin(postulants, 20, 15);
        // tous les bulletins passent le check de la date
        // les parametres de simuler sont dans l'ordre:
        // le pourcentage de votants et le jour du vote
        scrutin.simuler(0.75, 12);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // seuls les bulletins papier non courrier passent
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // les bulletins electroniques ne passent pas
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();
        //FIN TEST 2

    }
}
