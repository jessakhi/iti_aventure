package fr.insarouen.asi.prog.asiaventure.elements.objets;
import fr.insarouen.asi.prog.asiaventure.elements.Monde;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;




public class Coffre extends Objet implements Activable {
  private Etat etat = Etat.FERME;

/** Constructeur de Coffre
  * @param nom : nom du coffre
  * @param monde : monde dans lequel evolue le coffre
  */
  public Coffre(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom, monde);
  }


/** Methode permettant d'avoir l'état du coffre
  * @return Etat du coffre
  */
  public Etat getEtat(){
    return this.etat;
  }


/** Methode permettant de savoir si un objet permet d'activer le coffre
  * @return retourne faux
  */
  public boolean activableAvec(Objet obj){
    return false;
  }


/** Methode activant le coffre*/
  public void activer() throws ActivationException {
    if (this.getEtat()==Etat.FERME){
      this.etat=Etat.OUVERT;
    } else {
      this.etat=Etat.OUVERT;
    }
  }


/** Methode permettant d'activer le coffre avec un objet
  * @param obj l'objet permettant d'activer le coffre
  * @throws ActivationException retournée systématiquement car pas besoin d'objet pour activer un coffre
  */
  public void activerAvec(Objet obj) throws ActivationException{
    throw new ActivationException("Impossible d'activer un coffre avec un objet");
  }


/** Methode permettant de savoir si un coffre est deplacable
  * @return faux car coffre non déplacable
  */
  public boolean estDeplacable(){
    return false;
  }

  public String toString(){
    StringBuilder chaine = new StringBuilder("Ce coffre s'appelle "+ this.getNom() + ", il est dans le monde " + this.getMonde() + " et est dans l'état " + this.getEtat());
    return chaine.toString();
  }


}
