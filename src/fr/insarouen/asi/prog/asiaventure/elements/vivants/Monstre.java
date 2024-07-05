package fr.insarouen.asi.prog.asiaventure.elements.vivants;

import fr.insarouen.asi.prog.asiaventure.elements.Executable;
import java.lang.Throwable;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;






import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public class Monstre extends Vivant implements Executable {

/** Constructeur de monstre
  * @param nom nom du monstre
  * @param monde monde dans lequel évolue le mondstre
  * @param pointVie les points de vie du monstre
  * @param pointForce les points de force du monstre
  * @param piece la piece initiale du monstre
  */
  public Monstre (String nom, Monde monde, int pointVie, int pointForce, Piece piece) throws NomDEntiteDejaUtiliseDansLeMondeException{
    super(nom, monde, pointVie, pointForce, piece);
  }

/** Méthode définissant l'action du monstre
*/
  public void executer() throws Throwable{
    if (!this.estMort()){
      this.setPointVie(this.getPointVie()-1);
      Collection<Porte> portes = this.getPiece().getPortes();
      List<Porte> lPorte = new ArrayList<>(portes);
      Collections.shuffle(lPorte);

      franchirPorteAleatoire(lPorte);

      activiteMonstre();
    }
  }

  private void franchirPorteAleatoire(List<Porte> portes) throws PorteFermeException, ActivationImpossibleException, PorteInexistanteDansLaPieceException, VivantAbsentDeLaPieceException{
    boolean ok = false;
    int compteur = 0;
    while ((!ok) && compteur < portes.size()) {
      Porte laPorte = portes.get(compteur);
      compteur++;
      if ((laPorte.getEtat()==Etat.OUVERT) || (laPorte.getEtat()==Etat.CASSE)) {
        franchissement(laPorte);
        ok = true;
      }
      else if (laPorte.getEtat()==Etat.FERME){
        if (laPorte.getSerrure()==null){
          laPorte.activer();
          franchissement(laPorte);
          ok = true;
        } else if (laPorte.getSerrure().getEtat()==Etat.DEVERROUILLE){
            laPorte.activer();
            franchissement(laPorte);
            ok = true;
          }
      }
    }
  }


  private void franchissement(Porte laPorte) throws PorteFermeException, VivantAbsentDeLaPieceException, PorteInexistanteDansLaPieceException{
    Piece pieceDepart = this.getPiece();
    this.franchir(laPorte);
    pieceDepart.sortir(this);
    this.getPiece().entrer(this);
  }

  private void activiteMonstre() throws ObjetNonDeplacableException, ObjetNonPossedeParLeVivantException, ObjetAbsentDeLaPieceException{
    Piece pieceAutreCote = this.getPiece();
    Collection<Objet> objPiece = pieceAutreCote.getObjets();
    List<Objet> lObjPiece = new ArrayList<>(objPiece);

    Map<String, Objet> objMonstre = this.getObjets();
    List<Objet> lObjMonstre = new ArrayList<>(objMonstre.values());

    for (Iterator i = lObjMonstre.iterator(); i.hasNext(); ){
      this.deposer((Objet)i.next());

    }

    for (Iterator i = lObjPiece.iterator(); i.hasNext(); ){
      Objet obj = (Objet)i.next();
      if (obj.estDeplacable()){
        this.prendre(obj);
      }
    }
  }




}
