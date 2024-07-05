package fr.insarouen.asi.prog.asiaventure.elements;

/**
 * Classe ActivationImpossibleAvecObjetException héritant de
 * ActivationImpossibleException
 * 
 * @author Alexis IMBERT et Mehdi Saissi Hassani
 */
public class ActivationImpossibleAvecObjetException extends ActivationImpossibleException {
    /**
     * Constructeur sans paramètre de l'exception
     */
    public ActivationImpossibleAvecObjetException() {
        super();
    }

    /**
     * Constructeur avec un message en paramètre
     * 
     * @param msg message levé par l'exception
     */
    public ActivationImpossibleAvecObjetException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "ActivationImpossibleAvecObjetException []";
    }
}