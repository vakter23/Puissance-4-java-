import java.util.Scanner;

public class Menu {

	private final static char VIDE = '.';
	private final static char JAUNE = 'X';
	private final static char ROUGE = 'O';

	public static void menu() {

		Scanner sc = new Scanner(System.in);

		// Initialise taille tableaux
		char[][] grille = new char[6][7];

		char couleurJoueur = JAUNE;

		// C'est le choix du joueur qui le permet de rejouer ou de quitter
		int choix;

		do {
			System.out.println(
					"Voulez vous jouez ? (Ecrivez pour choisir) \n1. En 1v1\n2. Contre l'IA\n3. Non je ne veux pas");
			choix = Integer.parseInt(sc.nextLine());

			Méthode.initialise(grille);

			switch (choix) {

			case 1:
				Méthode.uncontreun(grille, couleurJoueur);
				break;
			case 2:
				Méthode.uncontreid(grille, couleurJoueur);
				break;
			case 3:
				System.out.println("Aurevoir");
				break;
			}
		} while (choix != 3);
		sc.close();
	}
}
