package fr.insarouen.asi.prog.asiaventure.elements.vivants;
import fr.insarouen.asi.prog.asiaventure.elements.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Activable;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.prog.asiaventure.elements.ActivationException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.Etat;
import java.util.Map;
import java.util.HashMap;

/** Classe representant un vivant.
  * Possède 4 attributs :
  * - pointVie : Entier représentant la vie du vivant
  * - pointForce : Entier représentant la force du vivant
  * - piece : Piece représentant la piece dans laquelle se trouve le vivant
  * - objets : Objet[] representant les objets transportes par le vivant
  */
  
public abstract class Vivant extends Entite{
    private int pointDeVie;
    private int pointDeForce;
    private Piece piece;
    private Map<String, Objet> lesObjetsDuVivant;
/** Contructeur de la classe Vivant
  * @param nom : représente le nom du vivant
  * @param monde : le monde dans lequel evolue le Vivant
  * @param pointVie : la vie du Vivant
  * @param pointForce : la force du vivant
  * @param piece : la piece dans laquelle evolue le vivant
  * @param Objet... : les objets que possede le vivant
  * @throws NomDEntiteDejaUtiliseDansLeMondeException : lorsque l'entite est deja presente dans ce monde
  */
    public Vivant(String nomVivant, Monde mondeDuVivant,int pdvDuVivant,int pdfDuVivant, Piece pieceDuVivant, Objet ... objetsDuVivant ) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nomVivant,mondeDuVivant);
        this.pointDeVie = pdvDuVivant;
        this.pointDeForce = pdfDuVivant;
        this.piece = pieceDuVivant;
        piece.entrer(this);
        this.lesObjetsDuVivant = new HashMap<String,Objet>();

        for (Objet obj : objetsDuVivant){
            this.lesObjetsDuVivant.put(obj.getNom(), obj);
        }
    }

  /** Methode permettant d'activer un Activable
    * @param activable : l'activable qu'on souhaite activer
    * @throws ActivationException : Si une erreur se produit lors de l'activation
    */
  public void activerActivable(Activable activable) throws ActivationException{
    activable.activer();
  }

    public Map<String, Objet> getObjets(){
        return this.lesObjetsDuVivant;
    }

    public Piece getPiece(){
        return this.piece;
    }

    /**
     * @return le nombre de point de vie que possède le vivant
     */
    public int getPointVie() {
        return pointDeVie;
    }

    public void setPointVie(int pointVie) {
        this.pointDeVie = pointVie;
    }
    public int getPointDeForce(){
        return this.pointDeForce;
    }
    
        /* * Le vivant dépose un objet qu'il a en sa possesion (implique le retrait de
     * l'objet dans sa liste d'objet)
     * 
     * @param objet objet à déposer
     * @throws ObjetNonPossedeParLeVivantException l'objet n'est pas possédé par le
     *                                             vivant
     */
     
    public void deposer(Objet objet) throws ObjetNonPossedeParLeVivantException {
        deposer(objet.getNom());
    }

    /**
     * Dépose un objet en possesion du vivant grace à la chaine de caractère le
     * désignant (implique le retrait de l'objet dans la liste des objet possédé par
     * le vivant)
     * 
     * @param nomObj chaine de caratère désignant l'objet que l'on souhaite retirer
     * @throws ObjetNonPossedeParLeVivantException
     */
    public void deposer(String nomObj) throws ObjetNonPossedeParLeVivantException {
        if (!this.lesObjetsDuVivant.containsKey(nomObj)) {
            throw new ObjetNonPossedeParLeVivantException(String.format("Le vivant ne peut pas déposer l'objet %s car il ne l'a pas", nomObj));
        }
        Objet tmp = lesObjetsDuVivant.get(nomObj);
        lesObjetsDuVivant.remove(nomObj);
        this.getPiece().deposer(tmp);
    }

    /**
     * Franchissement de la porte en paramètre
     * 
     * @param porte porte à frnchir
     * @throws PorteFermeException                  exception si la porte est fermé
     * @throws PorteInexistanteDansLaPieceException exception si la porte n'est pas
     *                                              dans la pièce
     * @throws VivantAbsentDeLaPieceException
     */
    public void franchir(Porte porte)
            throws PorteFermeException, PorteInexistanteDansLaPieceException, VivantAbsentDeLaPieceException {
        franchir(porte.getNom());
    }

    /**
     * Franchissement de la porte dont le nom est en paramètre
     * 
     * @param nomPorte
     * @throws PorteFermeException                  exception si la porte est fermé
     * @throws PorteInexistanteDansLaPieceException exception si la porte n'est pas
     *                                              dans la pièce
     * @throws VivantAbsentDeLaPieceException
     */
    public void franchir(String nomPorte)
            throws PorteFermeException, PorteInexistanteDansLaPieceException, VivantAbsentDeLaPieceException {
        if (!this.getPiece().aLaPorte(nomPorte)) {
            throw new PorteInexistanteDansLaPieceException("La Porte n'existe pas.");
        }
        Etat etatPorte = this.getPiece().getPorte(nomPorte).getEtat();
        if (etatPorte.equals(Etat.FERME) || etatPorte.equals(Etat.VERROUILLE) || etatPorte.equals(Etat.CASSE)) {
            throw new PorteFermeException("La Porte est fermee, verrouille ou casse ");
        }
        Piece newPiece = this.getPiece().getPorte(nomPorte).getPieceAutreCote(this.getPiece());
        newPiece.entrer(this.getPiece().sortir(this));
        this.piece = newPiece;
    }
    
    /**
     * Vérifie la présence d'un objet sur le vivant
     * 
     * @param objet objet dont on souhaite vérifier la présence
     * @return boolean : true l'objet est présent false sinon
     */
    public boolean possede(Objet objet) {
        return possede(objet.getNom());
    }

    /**
     * Vérifie que le vivant possède un objet. Cet objet est désigné par son nom de
     * type chaine de caractère.
     * 
     * @param nomObjet chaine de caractère désignant le nom de l'objet
     * @return boolean : true l'objet est présent false sinon
     */
    public boolean possede(String nomObjet) {
        return this.lesObjetsDuVivant.containsKey(nomObjet);
    }

   /*public boolean possede(Objet objet){
        return this.possede(objet.getNom());
    }

    public boolean possede(String nomObjet){
        if(this.lesObjetsDuVivant.containsKey(nomObjet)){
            return true;
        }
        return false;
    }

    public void prendre(Objet obj) throws ObjetAbsentDeLaPieceException,ObjetNonDeplacableException{
        if(!this.piece.contientObjet(obj.getNom())){
			throw new ObjetAbsentDeLaPieceException("L'objet : "+obj.getNom()+" n'est pas dans la pièce");
		}

        if(!obj.estDeplacable()){
			throw new ObjetNonDeplacableException("L'objet : "+ obj.getNom()+"n'est pas déplacable");
		}

       
        this.lesObjetsDuVivant.put(obj.getNom(),obj);
        this.piece.retirer(obj);
     
    }
    public void prendre(String nomObj) throws ObjetAbsentDeLaPieceException,ObjetNonDeplacableException{

        if(!this.piece.contientObjet(nomObj)){
			throw new ObjetAbsentDeLaPieceException("L'objet : "+nomObj+" n'est pas dans la pièce");
		}
        Objet obj = (Objet)this.getMonde().getEntite(nomObj);
        if(!obj.estDeplacable()){
			throw new ObjetNonDeplacableException("L'objet : "+ nomObj+"n'est pas déplacable");
		}

        
        this.prendre(obj);
    }*/

	/*public void deposer(Objet obj) throws ObjetNonPossedeParLeVivantException{
         
        if(!this.possede(obj)){
            throw new ObjetNonPossedeParLeVivantException("Vous ne posseder pas l'objet");
        }
        this.lesObjetsDuVivant.remove(obj.getNom(), obj);
        this.piece.deposer(obj);
    }
    public void deposer(String nomObj) throws ObjetNonPossedeParLeVivantException{


        Objet obj = (Objet)this.getMonde().getEntite(nomObj);
        this.deposer(obj);
    }*/
    
    /**
     * Le vivant prend un objet de la pièce où il se trouve (ajoute l'objet dans la
     * liste des objets présent dans l'inventaire du vivant et donc le retire de la
     * liste des objet présent dans la pièce)
     * 
     * @param objet objet en question
     */
    public void prendre(Objet objet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        prendre(objet.getNom());
    }

    public void prendre(String nomObj) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        Objet objet = this.getPiece().retirer(nomObj);
        this.lesObjetsDuVivant.put(objet.getNom(), objet);
    }
    
     public boolean estMort() {
        return (this.getPointVie() <= 0);
    }

    public String toString(){
		StringBuilder str=new StringBuilder();
		str.append(this.getNom());
		str.append("\n");
		str.append("PDV : "+this.pointDeVie);
		str.append("\n");
		str.append("PDF : "+this.pointDeForce);
		str.append("\n");
		str.append("INVENTAIRE : \n");
		for (String objnom : this.lesObjetsDuVivant.keySet()){
			str.append(objnom);
		}
		return str.toString();
	}
}
