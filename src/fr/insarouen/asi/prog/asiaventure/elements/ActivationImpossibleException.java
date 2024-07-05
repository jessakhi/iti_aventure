package fr.insarouen.asi.prog.asiaventure.elements;

/**
 * Classe ActivationImpossibleException héritant de
 * ActivationException
 * 
 * @author Alexis IMBERT et Mehdi Saissi Hassani
 */
public class ActivationImpossibleException extends ActivationException {
    /**
     * Constructeur sans paramètre de l'exception
     */
    public ActivationImpossibleException() {
        super();
    }

    /**
     * Constructeur avec un message en paramètre
     * 
     * @param msg message levé par l'exception
     */
    public ActivationImpossibleException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "ActivationImpossibleException []";
    }
}