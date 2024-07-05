package fr.insarouen.asi.prog.asiaventure.elements.objets;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

public abstract class Objet extends Entite{
    private String nom;
    private Monde monde;

    public Objet(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom,monde);
    }

    public abstract boolean estDeplacable();
    
}