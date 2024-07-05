package fr.insarouen.asi.prog.asiaventure.elements.structure;

import fr.insarouen.asi.prog.asiaventure.elements.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie.Serrure;

/**
 * Classe Porte basé sur la classe abstraite ElementStructurel
 * 
 * @see ElementStructurel
 * 
 *
 */
public class Porte extends ElementStructurel implements Activable {
    // Attributs

    /**
     * Attribut de type Monde désignant le monde où se trouve la porte
     */
    private Monde monde;

    /**
     * Attribut de type Pièce désignant la pièce "A" où se trouve la porte
     */
    private Piece piece1;

    /**
     * Attribut de type Pièce désignant la pièce "B" où se trouve la porte
     */
    private Piece piece2;

    /**
     * la classe d'enumération de type Etat qui précise l'état possible d'un objet
     */
    private Etat etat = Etat.OUVERT;
    
    /**
     * La serrure de la porte si elle existe
     */
    private Serrure serrure = null;

    // Constructeur
    /**
     * Constructeur d'une porte sans serrure
     * 
     * @param nom    nom de la porte
     * @param monde  monde d'appartennance
     * @param pieceA pièce d'un coter de la porte
     * @param pieceB pièce de l'autre coter de la porte
     * @throws NomDEntiteDejaUtiliseDansLeMondeException exception si le nom donné
     *                                                   existe déjà dans le monde
     */
    public Porte(String nom, Monde monde, Piece piece1, Piece piece2) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
        this.piece1 = piece1;
        this.piece2 = piece2;
        piece1.addPorte(this);
        piece2.addPorte(this);
    }

    /**
     * Constructeur d'une porte avec serrure
     * 
     * @param nom     nom de la porte
     * @param monde   monde d'appartennance
     * @param serrure serrure de la porte
     * @param pieceA  pièce d'un coter de la porte
     * @param pieceB  pièce de l'autre coter de la porte
     * @throws NomDEntiteDejaUtiliseDansLeMondeException exception si le nom donné
     *                                                   existe déjà dans le monde
     */
    public Porte(String nom, Monde monde, Serrure serrure, Piece pieceA, Piece pieceB)
            throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
        this.piece1 = piece1;
        this.piece2 = piece2;
        this.serrure = serrure;
        pieceA.addPorte(this);
        pieceB.addPorte(this);
    }

    // Méthodes
    /**
     * retourne l'etat de la porte
     * 
     * @return Etat
     */
    public Etat getEtat() {
        return this.etat;
    }

    /**
     * active la porte, change l'etat de la porte en fonction de son Etat actuelle
     * 
     * @throws ActivationImpossibleException si l'activation n'est pas possible
     */
    public void activer() throws ActivationImpossibleException {

        switch (this.getEtat()) {
            case CASSE:
                throw new ActivationImpossibleException("La porte est Cassée");
            // Pas de break après un throw
            case VERROUILLE:
                throw new ActivationImpossibleException("La porte est vérrouillé");
            case OUVERT:
                this.etat = Etat.FERME;
                break;
            case FERME:
                this.etat = Etat.OUVERT;
                break;
            default:
                throw new ActivationImpossibleException("La porte n'est pas dans un etat logique : deverrouille");
        }
    }

    /**
     * active la porte avec un objet, c'est à dire change l'etat de la porte
     * 
     * @param objet l'objet avec lequel on active la porte
     * @throws ActivationImpossibleAvecObjetException
     * @throws ActivationImpossibleException
     */
    public void activerAvec(Objet objet) throws ActivationImpossibleAvecObjetException, ActivationImpossibleException {
        if (this.activableAvec(objet)) {
            if (objet instanceof Clef) {
                switch (this.etat) {
                    case CASSE:
                        throw new ActivationImpossibleException("La porte est cassé");
                    case FERME:
                        this.etat = Etat.VERROUILLE;
                        if (this.getSerrure() != null) {
                            this.getSerrure().activerAvec(objet);
                        }
                        break;
                    case OUVERT:
                        this.etat = Etat.VERROUILLE;
                        if (this.getSerrure() != null) {
                            this.getSerrure().activerAvec(objet);
                        }
                        break;
                    case VERROUILLE:
                        this.etat = Etat.OUVERT;
                        if (this.getSerrure() != null) {
                            this.getSerrure().activerAvec(objet);
                        }
                        break;
                    default:
                        throw new ActivationImpossibleException("La porte n'est pas dans un état cohérent");

                }
            } else {
                if (objet instanceof PiedDeBiche) {
                    if (this.etat == Etat.VERROUILLE | this.etat == Etat.FERME) {
                        this.etat = Etat.CASSE;
                    } else {
                        throw new ActivationImpossibleException("La porte n'est n'y verrouille ni ferme");
                    }
                }
            }
        } else {
            throw new ActivationImpossibleAvecObjetException("Vous ne pouvez pas activer la porte avec cet objet");
        }
    }

    /**
     * renvoie si la porte est activable avec un objet
     * 
     * @param objet l'objet testé
     */
    public boolean activableAvec(Objet objet) {
        return ((this.getSerrure() != null ? this.getSerrure().activableAvec(objet) : false)
                | (objet instanceof PiedDeBiche));
    }

    /**
     * retourne la serrure liee à la porte
     * 
     * @return Serrure
     */
    public Serrure getSerrure() {
        return this.serrure;
    }

    /**
     * retourne la pièce de l'autre coté d'une porte
     * 
     * @param piece pièce du premier coter de la porte
     * @return Piece
     */
    public Piece getPieceAutreCote(Piece piece) {
        if (piece.equals(this.piece1)) {
            return this.piece2;
        } else {
            return this.piece1;
        }
    }

    @Override
    public String toString() {
        return String.format("Porte %s du monde %s avec la serrure %s", this.getNom(), monde.toString(),
                this.serrure.toString());
    }

	public void setEtat(Etat etat) {
        this.etat = etat;
	}
}
