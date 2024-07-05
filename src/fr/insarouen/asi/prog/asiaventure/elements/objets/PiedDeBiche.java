package fr.insarouen.asi.prog.asiaventure.elements.objets;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class PiedDeBiche extends Objet{
  /** Contructeur de PiedDeBiche.
  * Possede 2 attributs :
  *  @param nom : nom du pied de biche créé
  *  @param monde : le monde auquel appartient le pied de biche
  * @throws NomDEntiteDejaUtiliseDansLeMondeException : lorsque le piedDeBiche que l'on veut créer est déjà dans le monde
    */  
    public PiedDeBiche(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    	super(nom,monde);
        
    }
    public boolean estDeplacable(){
        return true;
    }

}
