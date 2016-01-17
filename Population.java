import java.util.Scanner;
import java.text.DecimalFormat;

public class Population {
    public static void main(String[] args) {

        DecimalFormat df = new DecimalFormat("#0.000");
        Scanner keyb = new Scanner(System.in);

        int anneeInitiale = 2011;        // annee initiale
        double tauxCroissance = 1.2;     // taux de croissance, en %
        double populationInitiale = 7.0; // population initiale, en milliard d'humains

        double populationCourante = populationInitiale; // population mondiale pour l'annee courante
        int anneeCourante = anneeInitiale;              // annee de calcul

        System.out.println("====---- PARTIE 1 ----====");
        System.out.println("Population en " + anneeCourante + " : " + df.format(populationCourante));

        /*******************************************
         * Completez le programme a partir d'ici.
         *******************************************/
        int anneeFinale;
        double populationFinale=0;
        double populationCible=0;
        
        // ===== PARTIE 1 =====
		// utilisez cette instruction pour poser votre question :
		System.out.print("Quelle année (> " + anneeInitiale + ") ? ");
		anneeFinale = keyb.nextInt();
		while (anneeFinale<=anneeInitiale){
			System.out.print("Quelle année (> " + anneeInitiale + ") ? ");
			anneeFinale = keyb.nextInt();
		}
		populationFinale = populationInitiale * Math.exp((anneeFinale - anneeInitiale) * tauxCroissance / 100);
		System.out.println("Population en " + anneeFinale + " : " + df.format(populationFinale));

        // ===== PARTIE 2 =====
        System.out.println("\n====---- PARTIE 2 ----====");
        
		do {
			System.out.print("Combien de milliards (> " + populationInitiale + ") ? ");
			populationCible = keyb.nextDouble();
		} while (populationCible<=populationInitiale);
		
		anneeCourante = anneeInitiale;
		
		do {
			anneeCourante++;
			populationFinale = populationInitiale * Math.exp((anneeCourante - anneeInitiale) * tauxCroissance / 100);
			System.out.println("Population en " + anneeCourante + " : " + df.format(populationFinale));
			
		} while (populationFinale<populationCible);
        // ===== PARTIE 3 =====
        System.out.println("\n====---- PARTIE 3 ----====");
        
        anneeCourante = anneeInitiale;
		populationCourante = populationInitiale;
		populationFinale = 0;
		
		do {
			anneeCourante++;
			populationFinale = populationInitiale * Math.exp((anneeCourante - anneeInitiale) * tauxCroissance / 100);
			if (populationFinale>(populationCourante*2)){
				tauxCroissance/=2;
				populationInitiale = populationFinale;
				populationCourante = populationFinale;
				anneeInitiale = anneeCourante;
			}
			
			
			
			System.out.println("Population en " + anneeCourante + " : " + df.format(populationFinale) + " ; taux de croissance :" + tauxCroissance +"%");
			

			
		} while (populationFinale<populationCible);

         
		keyb.close();
        /*******************************************
         * Ne rien modifier apres cette ligne.
         *******************************************/
    }
}
