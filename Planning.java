import java.util.ArrayList;

class Time
/* Représente le jour et l'heure d'un évènement.
 * Les heures sont représentées en double depuis minuit.
 * Par exemple 14:30 est représenté 14.5.
 */
{
    private final String day_;
    private final double hour_;

    // Constructeur à partir du jour et de l'heure
    public Time(String jour, double heure) {
        day_ = jour;
        hour_ = heure;
    }

    // Affichage
    public void print() {
        System.out.print(day_ + " à ");
        Time.printTime(hour_);
    }

    // Pour connaître le jour
    public String day() {
        return day_;
    }

    // Pour connaître l'heure
    public double hour()  {
        return hour_;
    }

    // Affiche un double en format heures:minutes
    public static void printTime(double hour) {
        int hh = (int)hour;
        int mm = (int)(60. * (hour - hh));
        System.out.format("%02d:%02d", hh, mm);
    }
}


/*******************************************
 * Complétez le programme à partir d'ici.
 *******************************************/

/*************************************************************************************************
 * Activity
 **************************************************************************************************/

class Activity {
	private final String lieu;
	private final double duree;
	private final Time horaire;
	
	public Activity(String l, String j, double h, double d) {
		this.lieu = l;
		this.duree = d;
		this.horaire = new Time(j,h);
	}
	
	public String getLocation() {
		return lieu; 
	}
	public double getDuration() { 
		return duree; 
	}
	public Time getTime() { 
		return horaire; 
	}
	
	public boolean conflicts(Activity a){
		double hDeb1=this.horaire.hour();
		double hFin1=this.horaire.hour()+this.getDuration();
		double hDeb2=a.horaire.hour();
		double hFin2=a.horaire.hour()+a.getDuration();
		
		return (this.horaire.day() == a.horaire.day()) && !((hDeb2<hDeb1 && hFin2<=hDeb1) || (hDeb2>=hFin1 && hFin2>hFin1));

	}
	
	public void print() {
		System.out.print("le ");
		this.horaire.print();
		System.out.print(" en " + this.lieu + ", durée ");
		Time.printTime(this.duree);
		//System.out.print(")");
	}
}

/*************************************************************************************************
 * Course
 **************************************************************************************************/
class Course {
	final private String id;
	final private String nom;
	final private Activity cours;
	final private Activity exercice;
	final private int credit;
	
	public Course(String i, String n, Activity c, Activity e, int cr){
		this.id = i;
		this.nom = n;
		this.cours = c;
		this.exercice = e;
		this.credit = cr;
		
		System.out.println("Nouveau cours : " + i);
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return nom;
	}
	
	public int getCredits() {
		return credit;
	}
	
	public double workload() {
		return this.cours.getDuration() + this.exercice.getDuration();
	}
	
	public boolean conflicts(Activity a) {
		return (this.cours.conflicts(a) || this.exercice.conflicts(a));
		
	}
	
	public boolean conflicts(Course c) {
		return (cours.conflicts(c.cours) || exercice.conflicts(c.exercice) || cours.conflicts(c.exercice) || exercice.conflicts(c.cours));
	}
	
	public void print() {
		System.out.print(this.id + ": " + this.nom + " - cours ");
		this.cours.print();
		System.out.print(", exercices ");
		this.exercice.print();
		System.out.print(". crédits : " + this.credit + "");
	}
	
}
/*************************************************************************************************
 * StudyPlan
 **************************************************************************************************/
class StudyPlan{
	
	private ArrayList<Course> courses;
	
	public StudyPlan() {
		courses = new ArrayList<Course>();
	}
	public StudyPlan(StudyPlan s) {
		courses = new ArrayList<Course>(s.courses);
	}
	
	public void addCourse(Course c) {
			courses.add(c);
	}
	public boolean conflicts(String idCourse, ArrayList<String> listIdCourses) {
		Course course2 = findCourse(idCourse);
		if (coursExiste(idCourse)) {
			for (String id : listIdCourses) {
				Course course1 = findCourse(id);
				if (course1.conflicts(course2)) {
					return true;
				}	
			}
		}
		
		return false;
	}
	
	public boolean coursExiste(String idCourse) {
		for (Course c : courses) {
			if (c.getId().contains(idCourse)){
				return true;
			}
		}
		return false;
	}
	
	private Course findCourse(String id){
		for(Course c : courses){
	        if(c.getId() != null && c.getId().contains(id)) {
	        	return c;
	        }
	    }
		return null;
	}
	
	public void print(String id) {
		Course c = findCourse(id);
		if (c != null) {
			c.print();
		}
	}
	
	public int credits(String id) {
		Course c = findCourse(id);
		if (c != null) {
			return c.getCredits();
		} else {
			return 0;
		}
	}
	
	public double workload(String id) {
		Course c = findCourse(id);
		if (c != null) {
			return c.workload();
		} else {
			return 0;
		}
	}
	
	public void printCourseSuggestions(ArrayList<String> listIdCourses){
		boolean trouve = false;
		for(Course c: courses){
		    if (!listIdCourses.contains(c.getId()) && !this.conflicts(c.getId(), listIdCourses)){
		         trouve = true;
		    	 c.print();
		         System.out.println();
		    }
		}
		if (!trouve) {
			System.out.println("Aucun cours n'est compatible avec la sélection de cours.");
		}
	}
	
}
/*************************************************************************************************
 * Schedule
 **************************************************************************************************/
class Schedule {
	
	private ArrayList<String> listIdCourses= new ArrayList<String>();
	private StudyPlan plan = new StudyPlan();
	
	
	public Schedule(StudyPlan s) {
		this.plan = new StudyPlan(s);
	}
	
	public Schedule(Schedule s) {
		this(s.plan);
		listIdCourses = new ArrayList<String>(s.listIdCourses);
	}
	
	public void print() {
		for (String localid : listIdCourses){
	        plan.print(localid);
	        System.out.println();
	    }
		System.out.println("Total de crédits   : " + this.computeTotalCredits());
		System.out.print("Charge journalière : ");
		Time.printTime(this.computeDailyWorkload());
		System.out.println();
		System.out.println("Suggestions :");
		plan.printCourseSuggestions(listIdCourses);
		System.out.println();
		System.out.println();
			
		
	}
	public boolean addCourse(String id) {
		boolean trouve = false;
		trouve=(listIdCourses.contains(id) || plan.conflicts(id, listIdCourses));
		if (trouve) {
			return false;
		} else {
			if (plan.coursExiste(id)) {
				listIdCourses.add(id);
				return true;
			} else {
				return false;
			}
			
		}
	}
	
	public double computeDailyWorkload() {
		double total = 0;
		for (String localid : listIdCourses){
	        total += plan.workload(localid);
	    }
		return total/5;
	}

	public int computeTotalCredits() {
		int total = 0;
		for (String localid : listIdCourses){
	        total += plan.credits(localid);
	    }
		return total;
	}	
	
	
}



/*******************************************
 * Ne rien modifier après cette ligne.
 *******************************************/
class Planning {
    public static void main(String[] args) {
        // Quelques activités
        Activity physicsLecture  = new Activity("Central Hall", "lundi",  9.25, 1.75);
        Activity physicsExercises = new Activity("Central 101" , "lundi", 14.00, 2.00);

        Activity historyLecture  = new Activity("North Hall", "lundi", 10.25, 1.75);
        Activity historyExercises = new Activity("East 201"  , "mardi",  9.00, 2.00);

        Activity financeLecture  = new Activity("South Hall",  "vendredi", 14.00, 2.00);
        Activity financeExercises = new Activity("Central 105", "vendredi", 16.00, 1.00);

        // On affiche quelques informations sur ces activités
        System.out.print("L'activité physicsLecture a lieu ");
        physicsLecture.print();
        System.out.println(".");

        System.out.print("L'activité historyLecture a lieu ");
        historyLecture.print();
        System.out.println(".");

        if (physicsLecture.conflicts(historyLecture)) {
            System.out.println("physicsLecture est en conflit avec historyLecture.");
        } else {
            System.out.println("physicsLecture n'est pas en conflit avec historyLecture.");
        }
        System.out.println();

        // Création d'un plan d'étude
        StudyPlan studyPlan = new StudyPlan();
        Course physics = new Course("PHY-101", "Physique", physicsLecture, physicsExercises, 4);
        studyPlan.addCourse(physics);
        Course history = new Course("HIS-101", "Histoire", historyLecture, historyExercises, 4);
        studyPlan.addCourse(history);
        Course finance = new Course("ECN-214", "Finance" , financeLecture, financeExercises, 3);
        studyPlan.addCourse(finance);

        // Première tentative d'emploi du temps
        Schedule schedule1 = new Schedule(studyPlan);
        schedule1.addCourse(finance.getId());
        System.out.println("Emploi du temps 1 :");
        schedule1.print();
        /* On ne sait pas encore très bien quoi faire : on essaye donc
         * sur une copie de l'emploi du temps précédent.
         */
        Schedule schedule2 = new Schedule(schedule1);
        schedule2.addCourse(history.getId());
        System.out.println("Emploi du temps 2 :");
        schedule2.print();

        // Un troisième essai
        Schedule schedule3 = new Schedule(studyPlan);
        schedule3.addCourse(physics.getId());
        System.out.println("Emploi du temps 3 :");
        schedule3.print();
    }
}

