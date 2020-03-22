import java.util.Scanner;

public class Méthode {

	// Constantes
	private final static char VIDE = '.';
	private final static char JAUNE = 'X';
	private final static char ROUGE = 'O';

	/*******************************************
	 * JOUER 1V1
	 ********************************************
	 */

	/*
	 * Procédure d'une partie en 1v1
	 */

	public static void uncontreun(char[][] grille, char couleurJoueur) {
		boolean gagné;

		Méthode.initialise(grille);
		Méthode.affiche(grille);
		do {

			Méthode.demandeEtJoue(grille, couleurJoueur);
			Méthode.affiche(grille);

			gagné = Méthode.estCeGagne(grille, couleurJoueur);

			// On change de couleur
			if (!gagné) {
				if (couleurJoueur == JAUNE) {
					couleurJoueur = ROUGE;
				} else {
					couleurJoueur = JAUNE;
				}
			}

		} while (!gagné && !Méthode.pleine(grille));

		// Annonce du gagnant
		if (gagné == true && !Méthode.pleine(grille)) {
			if (couleurJoueur == JAUNE) {
				System.out.println("Le joueur X a gagné!");
			} else if (couleurJoueur == ROUGE) {
				System.out.println("Le joueur O A gagné! ");
			}
		}
		if (Méthode.pleine(grille) == true && gagné == false) {
			System.out.println("Match nul");
		}

	}

	/*
	 * Demande au joueur de choisir une colonne puis verifie si le joueur peut
	 * placer le pion et s'il peut il le place sinon il le redemande
	 */
	public static void demandeEtJoue(char[][] grille, char couleurJoueur) {
		Scanner sc = new Scanner(System.in);
		boolean valide;

		do {
			System.out.print("Joueur ");

			if (couleurJoueur == JAUNE) {
				System.out.print("X");
			} else {
				System.out.print("O");
			}
			System.out.println(" : entrez un numéro de colonne");

			int colonne = sc.nextInt();
			if (colonne <= 0) {
				do {
					System.out.println("Veuillez mettre un nombre correct");
					colonne = sc.nextInt();
				} while (colonne <= 0);
			} else {
				colonne--;
			}
			valide = joue(grille, colonne, couleurJoueur);

			if (!valide) {
				System.out.println("Ce coup n'est pas valide");
			}
		} while (!valide);
	}

	/*******************************************
	 * JOUER 1VIA
	 ********************************************
	 */

	/*
	 * Procédure pour jouez contre un bot (il est pas assez intelligent)
	 */

	public static void uncontreid(char[][] grille, char couleurJoueur) {
		boolean gagné;

		Méthode.initialise(grille);
		Méthode.affiche(grille);
		do {

			Méthode.demandeEtJoueIA(grille, couleurJoueur);
			Méthode.affiche(grille);

			gagné = Méthode.estCeGagne(grille, couleurJoueur);

			// On change de couleur
			if (!gagné) {
				if (couleurJoueur == JAUNE) {
					couleurJoueur = ROUGE;
				} else {
					couleurJoueur = JAUNE;
				}
			}

		} while (!gagné && !Méthode.pleine(grille));

		// Annonce du gagnant
		if (gagné == true && !Méthode.pleine(grille)) {
			if (couleurJoueur == JAUNE) {
				System.out.println("Le joueur X a gagné!");
			} else if (couleurJoueur == ROUGE) {
				System.out.println("Le joueur(bot) O A gagné! ");
			}
		}
		if (Méthode.pleine(grille) == true && gagné == false) {
			System.out.println("Match nul");
		}

	}

	/*
	 * Demande au joueur de choisir une colonne puis verifie si le joueur peut
	 * placer le pion et s'il peut il le place sinon il le redemande Le bot choisis
	 * une colonne parmis les 7
	 */

	public static void demandeEtJoueIA(char[][] grille, char couleurJoueur) {
		Scanner sc = new Scanner(System.in);
		boolean valide = true;
		int colonne = -1;

		do {

			if (couleurJoueur == JAUNE) {
				System.out.print("Joueur ");
				System.out.print("X");
				System.out.println(" : entrez un numéro de colonne");
				if (couleurJoueur == JAUNE) {
					colonne = sc.nextInt();
					if (colonne <= 0) {
						do {
							System.out.println("Veuillez mettre un nombre correct");
							colonne = sc.nextInt();
						} while (colonne <= 0);
					} else {
						colonne--;
					}
					valide = joue(grille, colonne, couleurJoueur);

					if (!valide) {
						System.out.println("Ce coup n'est pas valide");
					}

				}

			}

			// C'est le bot
			if (couleurJoueur == ROUGE) {
				colonne = (int) (Math.random() * 7);
				if (colonne <= 0) {
					do {
						colonne = (int) (Math.random() * 7);
					} while (colonne <= 0);
				} else {
					colonne--;
				}
				valide = joue(grille, colonne, couleurJoueur);

				if (!valide) {
					colonne = (int) (Math.random() * 7);
				}

			}
		} while (!valide);

	}

	/*******************************************
	 * Fonction de base
	 ********************************************
	 */

	/*
	 * Remet les cases du tableau a VIDES
	 */
	static void initialise(char[][] grille) {
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				grille[i][j] = VIDE;
			}
		}
	}

	/*
	 * Affiche le tableau
	 */
	static void affiche(char[][] grille) {

		// Affiche 'o' pour une case rouge et 'x' pour une case jaune
		for (int x = 0; x < grille.length; x++) {
			for (int y = 0; y < grille[x].length; y++) {
				System.out.print(" " + grille[x][y] + " ");
			}
			System.out.println();
		}
		for (int i = 1; i <= grille[0].length; ++i) {
			System.out.print(" " + i + " ");
		}
		System.out.println();

	}

	/*
	 * Fonction qui verifie si le tableau est plein Il verifie la ligne 0 pour voir
	 * si elle contient des pions ou pas
	 * 
	 */
	public static boolean pleine(char[][] grille) {
		for (int cellule : grille[0]) {
			if (cellule == VIDE) {
				// S'il y a un vide a la ligne 0 alors le tableau n'est pas vide
				return false;
			}
		}

		return true;
	}

	/*
	 * Fonction booléen qui retourne vrai s'il est possible de jouer
	 * 
	 */

	public static boolean joue(char[][] grille, int colonne, char couleur) {

		// on commence par parcourir par le bas et on remonte
		if (colonne >= grille[0].length) {
			return false;
		}
		int ligne = grille.length - 1;

		boolean pleine = false;

		while ((!pleine) && (grille[ligne][colonne] != VIDE)) {
			if (ligne == 0) {
				pleine = true;
			} else {
				ligne--;

			}
		}

		// si la colonne n'est pas pleine on place le pion

		if (!pleine) {
			grille[ligne][colonne] = couleur;
			return true;

		} else {
			return false;
		}
	}

	/*
	 * Fonction qui vérifie si un des deux joueurs a gagné
	 */
	public static boolean estCeGagne(char[][] grille, char couleurJoueur) {

		// on parcourt la grille
		for (int i = 0; i < grille.length; ++i) {
			for (int j = 0; j < grille[i].length; ++j) {

				// on verifie si la couleur de la case est la meme que le joueur
				if (grille[i][j] == couleurJoueur) {
					// on compte les pillons dans les 4 directions
					if (
					// diagonale haut droite
					(i >= 3 && j <= grille[i].length - 4 && compte(grille, i, j, -1, +1) >= 4) ||
					// horizontal
							(j <= grille[i].length - 4 && compte(grille, i, j, 0, +1) >= 4) ||
							// diago bas droite
							(i <= grille.length - 4 && j <= grille[i].length - 4 && compte(grille, i, j, +1, +1) >= 4)
							||
							// vertical
							(i <= grille.length - 4 && compte(grille, i, j, +1, 0) >= 4)) {
						return true;
					}
				}
			}
		}
		// si on a pas trouve 4 pions allignes, personne n'a gagne
		return false;

	}

	/*
	 * Fonction qui compte et avance selon la direction
	 * 
	 **/
	public static int compte(char[][] grille, int ligneDepart, int colonneDepart, int dirLigne, int dirColonne) {

		int compteur = 0;
		int ligne = ligneDepart;
		int colonne = colonneDepart;

		// verification que le pion est la bonne couleur, et qu'on sorte pas de la
		while ((ligne >= 0 && ligne < grille.length && colonne >= 0 && colonne < grille[ligneDepart].length
				&& grille[ligne][colonne] == grille[ligneDepart][colonneDepart])) {

			++compteur;
			ligne = ligne + dirLigne;
			colonne = colonne + dirColonne;
		}

		return compteur;

	}

}
