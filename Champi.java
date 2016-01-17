import java.util.Scanner;

class Champi{
	static boolean lectureClavier(String question, Scanner clavier) {
		boolean reponse = false;
		System.out.print(question);
		if (!clavier.hasNext()){clavier.nextBoolean();}
		while (!clavier.hasNextBoolean()){
		    	System.out.println("Réponse incorrect.");
	     	   	System.out.print(question);
	     		clavier.next();
		}
		reponse = clavier.nextBoolean();
        clavier.nextLine();
        return reponse;
	}
	public static void main(String[]args){
		
		String champi = "";
		Scanner clavier = new Scanner(System.in);
		boolean foret = false;
		boolean convexe = false;
		boolean lamelle = false;
		boolean anneau = false;
		
		System.out.println("Pensez a un champignon : amanite tue mouches, pied bleu, girolle,");
        System.out.println("cèpe de Bordeaux, coprin chevelu ou agaric jaunissant.");
        
        convexe = lectureClavier("Est-ce que votre champignon a un chapeau convexe (true : oui, false : non) ? ", clavier);
        anneau = lectureClavier("Est-ce que votre champignon a un anneau (true : oui, false : non) ? ", clavier);
        
       
        if (convexe){
        	if (anneau){
        		foret = lectureClavier("Est-ce que votre champignon vit en forêt (true : oui, false : non) ? ", clavier);
        		if (foret){
        			champi = "l'amanite tue-mouches";
        		} else {
        			champi = "l'agaric jaunissant";
        		}
        	} else {
        		champi = "le pied bleu";
        	}
    	   
       } else {
	       	if (anneau){
	       		champi = "le coprin chevelu";
	       	} else {
	       		lamelle = lectureClavier("Est-ce que votre champignon a des lamelles (true : oui, false : non) ? ", clavier);
	       		if (lamelle){
	       			champi = "la girolle";
        		} else {
        			champi = "le cèpe de Bordeaux";
        		}
	       	}
       }

        System.out.print("==> Le champignon auquel vous pensez est ");
        System.out.print(champi);
        
        clavier.close();
	}

}

