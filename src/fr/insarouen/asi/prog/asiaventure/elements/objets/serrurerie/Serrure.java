package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.elements.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;

/**
 * Classe implémentant les serrures elle hérite de Objet et implélente Activable
 * Une serrure est représentée par :
 * <ul>
 * <li>Son nom unique dans le monde</li>
 * <li>Son monde</li>
 * <li>Son Etat de la serrure soit :
 * <ul>
 * <li>Verrouille</li>
 * <li>Déverrouille</li>
 * </ul>
 * </li>
 * </ul>
 *
 * 
 */
public class Serrure extends Objet implements Activable {
	// Attributs
	/**
	 * Etat de la serrure soit :
	 * <ul>
	 * <li>Verrouille</li>
	 * <li>Déverrouille</li>
	 * </ul>
	 */
	private Etat etat = Etat.VERROUILLE;
	/**
	 * Designe si une clef a déjà été créé pour la serrure, PremiereCle est a true
	 * par défaut
	 */
	private boolean PremireCle = true;
	/**
	 * La clef de la serrure
	 */
	private Clef clef;

	/**
	 * Constructeur d'une serrure
	 *
	 * @param nom
	 * @param monde
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 */
	public Serrure(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
		super(nom, monde);
	}

	/**
	 * Constructeur de serrure avec nom automatique
	 *
	 * @param monde
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 */
	public Serrure(Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
		super("Serrure" + Serrure.creerNom(monde), monde);
	}

	/**
	 * Creer un nom aléatoire, unique dans le monde pour la serrure,
	 *
	 * @param monde monde auquel appartien la serrure
	 * @return une chaine de caractère désignant le nom
	 */
	private static String creerNom(Monde monde) {
		int nombre;
		String nom;
		do {
			nombre = (int) (Math.random() * 256);
			nom = Integer.toString(nombre);
		} while (monde.getEntite((String) nom)!=null);
		return nom;
	}

	/**
	 * Test si la serrure est activable avec l'objet en paramètre
	 *
	 * @param objet objet à tester
	 * @return boolean : true si la serrure correspond à la clé donnée
	 */
	@Override
	public boolean activableAvec(Objet objet) {
		return this.clef.equals(objet);
	}

	/**
	 * Ne peut pas être utilisé pour une serrure.
	 * Les serrures ne s'ouvre qu'avec une clé
	 *
	 * @throws ActivationException
	 */
	@Override
	public void activer() throws ActivationException {
		throw new ActivationException("Une serrure doit s'activer avec une cle");
	}

	/**
	 * Change l'etat de serrure en fonction de l'objet passé en paramètre
	 *
	 * @param objet l'objet avec lequel on test d'activer la serrure
	 * @throws ActivationException exception si la serrure n'est pas activable avec
	 *                             l'objet
	 */
	@Override
	public void activerAvec(Objet objet) throws ActivationImpossibleAvecObjetException {
		if (this.activableAvec(objet)) {
			if (this.etat.equals(Etat.VERROUILLE)) {
				this.etat = Etat.DEVERROUILLE;
			} else {
				this.etat = Etat.VERROUILLE;
			}
		} else {
			throw new ActivationImpossibleAvecObjetException(
					"L'objet donnée n'est pas la bonne clée pour ouvrir la porte");
		}
	}

	/**
	 * creer une clef unique pour la serrure
	 *
	 * @throws NomDEntiteDejaUtiliseDansLeMondeException
	 */
	public Clef creerClef() throws NomDEntiteDejaUtiliseDansLeMondeException {
		Clef res = null;
		if (this.PremireCle) {
			res = new Clef(this.getNom() + "cle", this.getMonde());
			PremireCle = false;
			this.clef = res;
		}
		return res;
	}

	/**
	 * renvoie si la serrure est deplacable
	 *
	 * @return false dans tout les cas
	 */
	@Override
	public boolean estDeplacable() {
		return false;
	}

	/**
	 * retourne l'etat de la serrure.
	 *
	 * @return l'etat de la serrure
	 */
	public Etat getEtat() {
		return this.etat;
	}

	public String toString() {
		return String.format("Serrure portant le nom %s dans le monde %s avec l'etat %s", this.getNom(),
				this.getMonde().toString(), this.getEtat().toString());
	}
}
