package fr.insarouen.asi.prog.asiaventure.elements.objets.serrurerie;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Monde;


/** Classe clef héritant de Objet
*/
public final class Clef extends Objet {

/**Constructeur de Clef
* @param nom : le nom de la Clef
* @param monde : le monde dans lequel évolue la Clef
*/
  Clef(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom, monde);
  }

/** Permet de savoir si la clé est deplacable
* @return la clé est Deplacable
*/
  public boolean estDeplacable(){
    return true;
  }


  public String toString(){
    return this.getNom();
  }

}
