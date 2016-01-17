
import java.util.Scanner;

public class LesVariables {
	public static void main(String[] args) {
		// Different types of variable
		int entier = 4;
		double decimal = 1.0;
		char caractere = 'A';
		
		// Constants :
		final int CONSTANTE = 5;
		// Declare several variables at the same time (not abuse)
		int p = 1, q = 0;
		double x = 0.1, y;
		
		
		y=0.2;

		System.out.println("entier : "+entier);
		System.out.println("decimal : "+decimal);
		System.out.println("caractere : "+caractere);
		System.out.println("p : "+p);
		System.out.println("q : "+q);
		System.out.println("x : "+x);
		System.out.println("y : "+y);
		System.out.println("CONSTANTE : "+CONSTANTE);
		
		// Lecture du clavier
		@SuppressWarnings("resource")
		Scanner keyb = new Scanner(System.in);
		System.out.println("Entrez une valeur pour un entier n");
		int n = keyb.nextInt();
		int nCarre = n * n;
		
		System.out.println("La variable n contient " + n);
		System.out.println("Le carre de " + n + " est " + nCarre + ".");
		System.out.println("Le double de " + n + " est " + 2 * n + ".");
		
		System.out.println("Entrez une valeur pour un décimal d");
		double d = keyb.nextDouble();
		double dCarre = d * d;
		
		System.out.println("La variable d contient " + d);
		System.out.println("Le carre de " + d + " est " + dCarre + ".");
		System.out.println("Le double de " + d + " est " + 2 * d + ".");
		
		// Lecture d'une ligne complète
		System.out.println("Saisissez une ligne de texte :");
		keyb.nextLine(); // Pour que ça fonctionne
		String s  = keyb.nextLine();
		System.out.println("Vous avez saisi " + s);
		
		// Attention au division sur les int
		int ope1;
		int ope2;
		double res;
		double ope3;
		double ope4;
		
		ope1 = 5;
		ope2 = 2;
		
		res= ope1 / ope2;
		System.out.println("La division de " + ope1 + " par " + ope2 + " = "+ res);
		
		ope3 = 5;
		ope4 = 2;
		
		res= ope3 / ope4;
		System.out.println("La division de " + ope3 + " par " + ope4 + " = "+ res);
		
		// Fonctions Mathématiques
		double angle = 10 * Math.PI / 180;
		double si = Math.sin(angle);
		System.out.println("Angle : " + angle + " | si : " + si);
		
		
		
		
	}
}
