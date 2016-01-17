import java.util.Scanner;

public class Velo {

    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        System.out.print("Donnez l'heure de début de la location (un entier) : ");
        int debut = clavier.nextInt();
        System.out.print("Donnez l'heure de fin de la location (un entier) : ");
        int fin = clavier.nextInt();

        /*******************************************
         * Completez le programme a partir d'ici.
         *******************************************/
        if (debut<0 || fin<0 || debut>24 || fin>24 ){
        	System.out.println("Les heures doivent être comprises entre 0 et 24 !");
        	
        } else {
            if (debut == fin) {
            	System.out.println("Bizarre, vous n'avez pas loué votre vélo bien longtemps !");
            } else {
                if (fin<debut) {
                	System.out.println("Bizarre, le début de la location est après la fin ...");
                } else {
                	int tarifNormal=0;
                	int tarifNuit=0;
                	double total=0;
                	for (int i = debut+1; i <= fin; ++i) {
                		if (i>=8 && i <=17){
                			tarifNormal++;
                			
                		} else {
                			tarifNuit++;
                		}
                	}
                	total = tarifNormal * 2 + tarifNuit * 1;
                	 System.out.println("Vous avez loué votre vélo pendant");
                	 if (tarifNuit>0){
                		 System.out.println(tarifNuit + " heure(s) au tarif horaire de 1.0 francs"); 
                	 }
                	 if (tarifNormal>0){
                		 System.out.println(tarifNormal + " heure(s) au tarif horaire de 2.0 francs");
                	 }
                	 
                     System.out.print("Le montant total à payer est de ");
                     System.out.println(total + " francs.");
                }
            }
        }


        
       

        /*******************************************
         * Ne rien modifier apres cette ligne.
         *******************************************/

        clavier.close();
    }
}
