package fr.insarouen.asi.prog.asiaventure.elements.structure;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.Monde;

public abstract class ElementStructurel extends Entite {
    private String nom;
    private Monde monde;
    public ElementStructurel(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
    }

}