/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
abstract class Navire {
	private int x;
    private int y;
    private int drapeau;
    private boolean coule;
    
    public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDrapeau() {
		return drapeau;
	}

	public void setDrapeau(int drapeau) {
		this.drapeau = drapeau;
	}
  
    
    public Navire(int x, int y, int drapeau){
    	initCoord( x,  y);
        this.drapeau = drapeau;
        this.coule = false;
    }
    
    public boolean estDetruit(){
        return coule;
    }
    
    public double distance(Navire autreNavire){
    	 return Math.sqrt((this.x - autreNavire.x)*(this.x - autreNavire.x) + (this.y - autreNavire.y)*(this.y - autreNavire.y));
    }
    
    public void avance(int unitsX, int unitsY) {
    	initCoord( this.x+unitsX,  this.y+unitsY);
    }
    
    private void initCoord(int x, int y){
    	this.x=x;
    	if (this.x<0){this.x = 0;}else if (this.x>Piraterie.MAX_X){this.x=Piraterie.MAX_X;}
    	this.y=y;
    	if (this.y<0){this.y = 0;}else if (this.y>Piraterie.MAX_Y){this.y=Piraterie.MAX_Y;}
    }
    
    public void coule(){
    	this.coule = true;
    }
    

	public String getNom(){
		return "Bateau";
	}
	public String getEtat(){
		if (coule){
			return "détruit";
		} else {
			return "intact";
		}
	}
	
	public String toString(){
		return getNom() + " avec drapeau " + getDrapeau() + " en (" + getX() + "," + getY() + ") -> " + getEtat();
	}
	
    public void rencontre(Navire autreNavire){
    	if (this.drapeau!=autreNavire.getDrapeau() && this.distance(autreNavire)<Piraterie.RAYON_RENCONTRE){
  
    			this.combat(autreNavire);


    		
    	}
    }
	public abstract void combat(Navire autreNavire);
	
	public abstract void recoitBoulet();
	
	public boolean estPacifique(){
		return false;
	}
}
class Pirate extends Navire {
	private boolean endommage;
	public Pirate(int x, int y, int drapeau, boolean endommage){
		super(x, y , drapeau);
		this.endommage = endommage;
	}
	
	public String getNom(){
		return "Bateau pirate";
	}
	
	public String getEtat(){
		if (estDetruit()){
			return "détruit";
		} else {
			if (estEndommage()) {
				return "ayant subi des dommages";
			} else {
				return "intact";
			}
			
		}
	}
	
	public boolean estEndommage(){
		return endommage;
	}
	
	public void combat(Navire autreNavire){
		autreNavire.recoitBoulet();
		if (!autreNavire.estPacifique()){
			this.recoitBoulet();
		}
	}
	public void recoitBoulet() {
		if (this.estEndommage()){
			this.coule();
		} else {
			this.endommage=true;
		}
	}
	
	public String toString(){
		return getNom() + " avec drapeau " + getDrapeau() + " en (" + getX() + "," + getY() + ") -> " + getEtat();
	}
}
class Marchand extends Navire {
	public Marchand(int x, int y, int drapeau){
		super(x, y , drapeau);
	}
	public String getNom(){
		return "Bateau marchand";
	}
	public void recoitBoulet() {
		this.coule();
	}
	public void combat(Navire autreNavire){
		if (!this.estPacifique()){
			autreNavire.recoitBoulet();
		}
		if (!autreNavire.estPacifique()){
		this.recoitBoulet();
		}
	}
	
	public boolean estPacifique(){
		return true;
	}
	
	public String toString(){
		return getNom() + " avec drapeau " + getDrapeau() + " en (" + getX() + "," + getY() + ") -> " + getEtat();
	}
}

/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
class Piraterie {

    static public final int MAX_X = 500;
    static public final int MAX_Y = 500;
    static public final double RAYON_RENCONTRE = 10;

    static public void main(String[] args) {
        // Test de la partie 1
        System.out.println("***Test de la partie 1***");
        System.out.println();
        // un bateau pirate 0,0 avec le drapeau 1 et avec dommages
        Navire ship1 = new Pirate(0, 0, 1, true);
        // un bateau marchand en 25,0 avec le drapeau 2
        Navire ship2 = new Marchand(25, 0, 2);
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println("Distance: " + ship1.distance(ship2));
        System.out.println("Quelques déplacements horizontaux et verticaux");
        // se deplace de 75 unites a droite et 100 en haut
        ship1.avance(75, 100);
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println("Un déplacement en bas:");
        ship1.avance(0, -5);
        System.out.println(ship1);
        ship1.coule();
        ship2.coule();
        System.out.println("Après destruction:");
        System.out.println(ship1);
        System.out.println(ship2);

        // Test de la partie 2
        System.out.println();
        System.out.println("***Test de la partie 2***");
        System.out.println();

        // deux vaisseaux sont enemis s'ils ont des drapeaux differents

        System.out.println("Bateau pirate et marchand ennemis (trop loin):");
        // bateau pirate intact
        ship1 = new Pirate(0, 0, 1, false);
        ship2 = new Marchand(0, 25, 2);
        System.out.println(ship1);
        System.out.println(ship2);
        ship1.rencontre(ship2);
        System.out.println("Après la rencontre:");
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println();

        System.out.println("Bateau pirate et marchand ennemis (proches):");
        // bateau pirate intact
        ship1 = new Pirate(0, 0, 1, false);
        ship2 = new Marchand(2, 0, 2);
        System.out.println(ship1);
        System.out.println(ship2);
        ship1.rencontre(ship2);
        System.out.println("Après la rencontre:");
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println();

        System.out.println("Bateau pirate et marchand amis (proches):");
        // bateau pirate intact
        ship1 = new Pirate(0, 0, 1, false);
        ship2 = new Marchand(2, 0, 1);
        System.out.println(ship1);
        System.out.println(ship2);
        ship1.rencontre(ship2);
        System.out.println("Après la rencontre:");
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println();

        System.out.println("Deux bateaux pirates ennemis intacts (proches):");
        // bateaux pirates intacts
        ship1 = new Pirate(0, 0, 1, false);
        ship2 = new Pirate(2, 0, 2, false);
        System.out.println(ship1);
        System.out.println(ship2);
        ship1.rencontre(ship2);
        System.out.println("Après la rencontre:");
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println();

        System.out.println("Un bateau pirate intact et un avec dommages, ennemis:");
        // bateau pirate intact
        Navire ship3 = new Pirate(0, 2, 3, false);
        System.out.println(ship1);
        System.out.println(ship3);
        ship3.rencontre(ship1);
        System.out.println("Après la rencontre:");
        System.out.println(ship1);
        System.out.println(ship3);
        System.out.println();

        System.out.println("Deux bateaux pirates ennemis avec dommages:");
        System.out.println(ship2);
        System.out.println(ship3);
        ship3.rencontre(ship2);
        System.out.println("Après la rencontre:");
        System.out.println(ship2);
        System.out.println(ship3);
        System.out.println();
    }
}
