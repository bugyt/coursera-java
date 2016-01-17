// Utility.distance(x,y) retourne la distance entre x et y
class Utility {
    static int distance(int x, int y) {
        return Math.abs(x - y);
    }
}

class Creature {
    /*****************************************************
     * Compléter le code à partir d'ici
     *****************************************************/
     private final String nom;
     private int niveau;
     private int pointsDeVie;
     private int force;
     private int position;
     
     public Creature(String n, int niv, int pDV, int f, int p) {
        this.nom = n;
        this.setNiveau(niv);
        this.pointsDeVie = pDV;
        this.force = f;
        this.setPosition(p);
     }
     
     public Creature(String n, int niv, int pDV, int f) {
         this.nom = n;
         this.setNiveau(niv);
         this.pointsDeVie = pDV;
         this.force = f;
         this.setPosition(0);
      }
     
      public boolean vivant() {
        return (pointsDeVie>0);
      }
      
      public int pointsAttaque() {
        if (this.vivant()){
            return getNiveau()*force;
        } else {
            return 0;
        }
      }
      
      public void deplacer(int pas) {
    	  if (this.vivant()){
    		  this.setPosition(this.getPosition() + pas);
    	  }
        
      }
      
      public void adieux() {
        System.out.println(this.nom + " n'est plus!");
      }
     
     public void faiblir(int pts) {
        if (pointsDeVie-pts >0) {
            pointsDeVie-=pts;
        } else {
            pointsDeVie=0;
            this.adieux();
        }
     }
     
     public String toString() {
        return nom + ", niveau: " + getNiveau() + ", points de vie: " + pointsDeVie + ", force: " + force + ", points d'attaque: " + pointsAttaque() + ", position: " + getPosition();  
     }

	public int getPosition() {
		return position;
	}
	
	public int position() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
     

}

class Dragon extends Creature {
    
    private int porteeFlamme;
    
    public Dragon(String n, int niv, int pDV, int f, int pF, int p) {
        super(n, niv, pDV, f, p);
        porteeFlamme = pF;
    }
    
    public Dragon(String n, int niv, int pDV, int f, int pF) {
        super(n, niv, pDV, f);
        porteeFlamme = pF;
    }
    
    public void voler(int p) {
    	if (this.vivant()){
    		this.setPosition(p);
    	}  
    }
    
    public void souffleSur(Creature c) {
        int d = Utility.distance(this.getPosition(), c.getPosition()); 
        if (this.vivant() && c.vivant() && d<=this.porteeFlamme) {
            c.faiblir(this.pointsAttaque());
            this.faiblir(d);
            
            if (this.vivant() && !c.vivant()) {
                this.setNiveau(getNiveau()+1);
            }
        }
    }
}

class Hydre extends Creature{
    private int longueurCou;
    private int dosePoison;
    
    public Hydre(String n, int niv, int pDV, int f, int lC, int dP, int p) {
        super(n, niv, pDV, f, p);
        longueurCou = lC;
        dosePoison = dP;
    }
    
    public Hydre(String n, int niv, int pDV, int f, int lC, int dP) {
        super(n, niv, pDV, f);
        longueurCou = lC;
        dosePoison = dP;
    }
    
    public void empoisonne(Creature c) {
    	int d = Utility.distance(this.getPosition(), c.getPosition()); 
        if (this.vivant() && c.vivant() && d<=this.longueurCou) {
            c.faiblir(this.pointsAttaque()+dosePoison);
            if (this.vivant() && !c.vivant()) {
                this.setNiveau(getNiveau()+1);
            }
        }
    }
    
}

/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/
// ======================================================================
class Dragons {
    private static void combat(Dragon dragon, Hydre hydre) {
        hydre.empoisonne(dragon); // l'hydre a l'initiative (elle est plus rapide)
        dragon.souffleSur(hydre);
    }


    public static void main(String[] args) {
        Dragon dragon = new Dragon("Dragon rouge"   , 2, 10, 3, 20         );
        Hydre  hydre =  new Hydre ("Hydre maléfique", 2, 10, 1, 10, 1,  42 );

        System.out.println(dragon);
        System.out.println("se prépare au combat avec :");
        System.out.println(hydre);

        System.out.println();

        System.out.println("1er combat :");
        System.out.println("\t Les créatures ne sont pas à portée, donc ne peuvent pas s'attaquer.");
        combat(dragon, hydre);

        System.out.println();
        System.out.println("Après le combat :");
        System.out.println(dragon);
        System.out.println(hydre);

        System.out.println();

        System.out.println("Le dragon vole à proximité de l'hydre :");
        dragon.voler(hydre.position() - 1);
        System.out.println(dragon);

        System.out.println();

        System.out.println("L'hydre recule d'un pas :");
        hydre.deplacer(1);
        System.out.println(hydre);

        System.out.println();
        System.out.println("2e combat :");
        System.out.format("\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n",
                          "+ l'hydre inflige au dragon une attaque de 3 points",
                          " [ niveau (2) * force (1) + poison (1) = 3 ] ;",
                          "+  le dragon inflige à l'hydre une attaque de 6 points",
                          " [ niveau (2) * force (3) = 6 ] ;",
                          " + pendant son attaque, le dragon perd 2 points de vie supplémentaires",
                          "[ correspondant à la distance entre le dragon et l'hydre : 43 - 41 = 2 ]." );

        combat(dragon, hydre);

        System.out.println();
        System.out.println("Après le combat :");
        System.out.println(dragon);
        System.out.println(hydre);

        System.out.println();
        System.out.println("Le dragon avance d'un pas :");
        dragon.deplacer(1);
        System.out.println(dragon);

        System.out.println();
        System.out.println("3e combat :");
        System.out.format("\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n",

                          "+ l'hydre inflige au dragon une attaque de 3 points",
                          "[ niveau (2) * force (1) + poison (1) = 3 ] ;",
                          "+ le dragon inflige à l'hydre une attaque de 6 points",
                          "[ niveau (2) * force (3) = 6 ] ;",
                          "+ pendant son attaque, le dragon perd 1 point de vie supplémentaire",
                          "[ correspondant à la distance entre le dragon et l'hydre : 43 - 42 = 1 ] ;",
                          "+ l'hydre est vaincue et le dragon monte au niveau 3.");
        combat(dragon, hydre);

        System.out.println();
        System.out.println("Après le combat :");
        System.out.println(dragon);
        System.out.println(hydre);

        System.out.println();
        System.out.println("4e Combat :");
        System.out.println("\t quand une créature est vaincue, rien ne se passe.");
        combat(dragon, hydre);

        System.out.println();
        System.out.println("Après le combat :");
        System.out.println(dragon);
        System.out.println(hydre);
    }
}
