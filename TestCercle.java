class Cercle {
	/**
	 * Members
	 */
	private double x;
	private double y;
	private double rayon;

	/**
	 * Getters & Setters
	 */
	public double getRayon() {
		return rayon;
	}

	public void setRayon(double rayon) {
		this.rayon = rayon;
	}

	public void setCentre(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructors
	 */

	/**
	 * Methods
	 */
	public double surface() {
		return Math.PI * (rayon * rayon);
	}

	public boolean estInterieur(double unX, double unY) {
		return (((unX - x) * (unX - x) + (unY - y) * (unY - y)) <= rayon * rayon);
	}
}

class TestCercle {
	public static void main(String[] args) {
		Cercle c1 = new Cercle();
		Cercle c2 = new Cercle();
		Cercle c3 = new Cercle();

		c1.setCentre(1.0, 2.0);
		c1.setRayon(Math.sqrt(5.0)); // passe par (0, 0)

		c2.setCentre(-2.0, 1.0);
		c2.setRayon(2.25); // 2.25 > sqrt(5) => inclus le point (0, 0)

		c3.setCentre(-2.0, -5.0);
		c3.setRayon(1.0);

		System.out.println("Surface de c1 : " + c1.surface());
		System.out.println("Surface de c2 : " + c2.surface());
		System.out.println("Surface de c3 : " + c3.surface());

		afficherPosition("c1", c1, 0.0, 0.0);
		afficherPosition("c2", c2, 0.0, 0.0);
		afficherPosition("c3", c3, 0.0, 0.0);
	}

	static void afficherPosition(String nom, Cercle c, double x, double y) {
		System.out.print("Position du point (" + x + ", " + y + ") : ");

		if (c.estInterieur(x, y)) {
			System.out.print("dans ");
		} else {
			System.out.print("hors de ");
		}
		System.out.println(nom);
	}

}
