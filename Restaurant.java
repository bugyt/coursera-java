import java.util.ArrayList;

/*****************************************************
 * Compléter le code à partir d'ici
 *****************************************************/
class Produit {
	private String nom;
	private String unite;
	/**
	 * Getters & Setters
	 */
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getUnite() {
		return unite;
	}
	public void setUnite(String unite) {
		this.unite = unite;
	}
	/**
	 * Constructors
	 */
	public Produit(String nom, String unite){
		this.nom = nom;
		this.unite = unite;
	}
	public Produit(String nom){
		this.nom = nom;
		this.unite = "";
	}
	public Produit(Produit p){
		this.nom = p.nom;
		this.unite = p.unite;
	}
	/**
	 * Methods
	 */
	public Produit adapter(double n){
		return this;
	}
	public double quantiteTotale(String nomProduit){
		if (this.nom == nomProduit) {
			return 1;
		} else {
			return 0;
		}
	}

	public String toString(){
		return this.nom;
	}
}

class Ingredient {
	private Produit produit;
	private double quantite;
	
	/**
	 * Getters & Setters
	 */
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public double getQuantite() {
		return quantite;
	}
	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}
	/**
	 * Constructors
	 */
	public Ingredient(Produit produit, double quantite) {
		this.produit = produit;
		this.quantite = quantite;
	}
	public Ingredient(Ingredient i) {
		this.produit = i.produit;
		this.quantite = i.quantite;
	}
	/**
	 * Methods
	 */
	public double quantiteTotale(String nomProduit){
		return quantite*produit.quantiteTotale(nomProduit);
	}

	public String toString(){
		Produit nvProduit = produit.adapter(quantite);
		return quantite + " " + produit.getUnite() + " de "+nvProduit;
	}
}

class Recette {
	private ArrayList<Ingredient> ingredients;
	private String nom;
	private double nbFois;
	/**
	 * Getters & Setters
	 */
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getNbFois() {
		return nbFois;
	}
	public void setNbFois(double nbFois) {
		this.nbFois = nbFois;
	}
	/**
	 * Constructors
	 */
	public Recette(String nom, double nbFois) {
		this.ingredients=new ArrayList<Ingredient>();
		this.nom = nom;
		if (nbFois<=0) {
			this.nbFois = 1;
		} else {
			this.nbFois = nbFois;
		}
	}
	public Recette(String nom) {
		this.ingredients=new ArrayList<Ingredient>();
		this.nom = nom;
		this.nbFois = 1;
	}
	public Recette(Recette r) {
		this.ingredients=new ArrayList<Ingredient>(r.ingredients);
		this.nom = r.nom;
		this.nbFois = r.nbFois;
	}
	/**
	 * Methods
	 */
	public void ajouter(Produit p, double quantite){
		if (p!=null){
			Ingredient ing = new Ingredient(p, quantite * nbFois);
			ingredients.add(ing);
		}
	}
	public Recette adapter(double n){
		Recette nvRecette = new Recette(this.nom, n * this.nbFois);
		for (Ingredient i : this.ingredients){
			nvRecette.ajouter(i.getProduit(), i.getQuantite() / this.nbFois);
		}
		return nvRecette;
	}
	public double quantiteTotale(String nomProduit){
		double total = 0;
		for (Ingredient i : ingredients) {
			total += i.quantiteTotale(nomProduit);
		}
		return total;
		
	}
	
	public String toString(){
		String description;
		
		description = "  Recette \"" + this.nom + "\" x "+ this.nbFois + ":" + "\n";
		int cpt=0;
		for(Ingredient i : ingredients){
			cpt++;
			description+="  "+cpt + ". " + i; 
			if (cpt<ingredients.size()){
				description+= "\n";
			}
		}
		return description;
	}
}

class ProduitCuisine extends Produit{
	
	private Recette recettePdtCuisine;
	
	/**
	 * Constructors
	 */
	public ProduitCuisine(String nom) {
		super(nom, "portion(s)");
		recettePdtCuisine = new Recette(nom);
		
	}
	public ProduitCuisine(ProduitCuisine p) {
		super(p.getNom(), p.getUnite());
		recettePdtCuisine = new Recette(p.recettePdtCuisine);
	}
	/**
	 * Methods
	 */
	public void ajouterARecette(Produit produit, double quantite){
		if (recettePdtCuisine != null){
			recettePdtCuisine.ajouter(produit, quantite);
		}
	}
	public ProduitCuisine adapter(double n){
		ProduitCuisine nvProduitCuisine = new ProduitCuisine(this);
		nvProduitCuisine.recettePdtCuisine = nvProduitCuisine.recettePdtCuisine.adapter(n);
		return nvProduitCuisine;
	}
	public double quantiteTotale(String nomProduit){
		if (this.getNom() == nomProduit) {
			return 1;
		} else {
			return recettePdtCuisine.quantiteTotale(nomProduit);
		}
	}

	public String toString(){ 
		String description;
		description=super.toString() + "\n";
		description+=recettePdtCuisine;
		return description;
	}
}



/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/

class Restaurant {
    public static void main(String[] args) {

        // quelques produits de base
        Produit oeufs          = new Produit("oeufs");
        Produit farine         = new Produit("farine", "grammes");
        Produit beurre         = new Produit("beurre", "grammes");
        Produit sucreGlace     = new Produit("sucre glacé", "grammes");
        Produit chocolatNoir   = new Produit("chocolat noir", "grammes");
        Produit amandesMoulues = new Produit("amandes moulues", "grammes");
        Produit extraitAmandes = new Produit("extrait d'amandes", "gouttes");

        ProduitCuisine glacage = new ProduitCuisine("glaçage au chocolat");
        // recette pour une portion de glaçage :
        glacage.ajouterARecette(chocolatNoir, 200);
        glacage.ajouterARecette(beurre,        25);
        glacage.ajouterARecette(sucreGlace,   100);

        System.out.println(glacage);
        System.out.println();

        ProduitCuisine glacageParfume = new ProduitCuisine("glaçage au chocolat parfumé");
        // besoin de 1 portions de glaçage au chocolat et de 2 gouttes
        // d'extrait d'amandes pour 1 portion de glaçage parfumé
        glacageParfume.ajouterARecette(extraitAmandes, 2);
        glacageParfume.ajouterARecette(glacage,        1);

        System.out.println(glacageParfume);
        System.out.println();

        Recette recette = new Recette("tourte glacée au chocolat");
        // recette pour une portion de tourte glacée :
        recette.ajouter(oeufs,           5);
        recette.ajouter(farine,        150);
        recette.ajouter(beurre,        100);
        recette.ajouter(amandesMoulues, 50);
        recette.ajouter(glacageParfume,  2);

        System.out.println("===  Recette finale  ======\n");
        System.out.println(recette);
        System.out.println();

        afficherQuantiteTotale(recette, beurre);
        System.out.println();

        Recette doubleRecette = recette.adapter(2);
        System.out.println("===  Recette finale x 2 ===\n");
        System.out.println(doubleRecette);
        System.out.println();

        afficherQuantiteTotale(doubleRecette, beurre);
        afficherQuantiteTotale(doubleRecette, oeufs);
        afficherQuantiteTotale(doubleRecette, extraitAmandes);
        afficherQuantiteTotale(doubleRecette, glacage);
        System.out.println();

        System.out.println("===========================\n");
        System.out.println("Vérification que le glaçage n'a pas été modifié :\n");
        System.out.println(glacage);
    }

    private static void afficherQuantiteTotale(Recette recette, Produit produit) {
        String nom = produit.getNom();
        System.out.println("Cette recette contient " +
                           recette.quantiteTotale(nom) + " " + produit.getUnite() + " de " + nom);
    }
}
