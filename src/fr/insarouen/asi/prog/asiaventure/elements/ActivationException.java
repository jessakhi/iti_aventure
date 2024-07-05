package fr.insarouen.asi.prog.asiaventure.elements;

import fr.insarouen.asi.prog.asiaventure.ASIAventureException;

/**
 * Classe ActivationException héritant de ASIAventureException
 * 
 * @author Alexis IMBERT et Mehdi Saissi Hassani
 */
public class ActivationException extends ASIAventureException {
    /**
     * Constructeur sans paramètre de l'exception
     */
    public ActivationException() {
        super();
    }

    /**
     * Constructeur avec un message en paramètre
     * 
     * @param msg message levé par l'exception
     */
    public ActivationException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "ActivationException []";
    }
}