package fr.insarouen.asi.prog.asiaventure.elements.structure;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ElementStructurel;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

/** Class piece permettant de representer une Piece
  Possede 2 attributs :
    - contenuPiece : Objet[] representant les objets contenus dans la piece
    - vivants : Vivant[] representant les Vivant présents dans la piece
    */
public class Piece extends ElementStructurel {
   	/** les objets contenus dans la pièce*/
    private Map<String,Objet> lesObjets;
    private Map<String, Objet> contenuPiece = new HashMap<>();
    /** les Vivants qui se trouvent dans une pièce**/
    private Map<String,Vivant> lesVivants;
    //private Map<String, Vivant> vivants = new HashMap<>();
    private Map<String, Porte> portes = new HashMap<>();
    
    /** Contructeur de Piece
  * @param nom : le nom donné à la piece
  * @param monde : le monde auquel appartient la piece
  * @throws NomDEntiteDejaUtiliseDansLeMondeException : Lorsque la piece que l'on veut créé est deja présente dans le monde
  */
    public Piece(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
        this.lesObjets= new HashMap<String,Objet>();
        this.lesVivants= new HashMap<String,Vivant>();
    }

/** Methode permettant d'ajouter une porte à la piece
  * @param porte : la porte à ajouter
  */
    protected void addPorte(Porte porte){
    this.portes.put(porte.getNom(), porte);
  }
  
 /** Methode permettant de savoir si la piece contient la porte dont le nom est donné
  * @param nom : le nom de la porte à vérifier
  * @return vrai ou faux
  */
    public boolean aLaPorte(String nom){
        return this.portes.containsKey(nom);
  }


/** Methode permettant de savoir si la piece contient la porte donnée en paramètre
  * @param porte : la porte à vérifier
  * @return vrai ou faux
  */
    public boolean aLaPorte(Porte porte){
        return this.aLaPorte(porte.getNom());
  }


/** Methode permettant d'obtenir la porte dont le nom est passé en paramètre
  * @param nomPorte le nom de la porte que l'on veut
  * @return la porte souhaitée
  */
    public Porte getPorte(String nomPorte){
        return this.portes.get(nomPorte);
  } 
  
  /** Methode permettant de savoir si une piece possede un objet en particulier
  * @param nom : le nom de l'objet a tester
  */
    public boolean contientObjet(String nom){
        return this.contenuPiece.containsKey(nom);
  }


/** Methode permettant de savoir si une piece possede un objet en particulier
  * @param obj : l'objet a tester
  */
    public boolean contientObjet(Objet obj){
       return contientObjet(obj.getNom());
  }

/** Methode retournant toutes les portes de la piece
  * @return les portes de la piece
  */
    public Collection<Porte> getPortes(){
       return this.portes.values();
       //return Collection.unmodifiableMap(objets);
  }


/** Methode retournant les objets de la piece
  * @return les objets de la piece
  */
    public Collection<Objet> getObjets(){
       return this.contenuPiece.values();
  }




/** Methode permettant de deposer un objet dans la piece
  * @param obj : l'objet a deposer
  */
   public void deposer (Objet obj){
     this.contenuPiece.put(obj.getNom(), obj);
  }
  
  
     /*public boolean contientObjet(Objet obj){
       return contientObjet(obj.getNom());
    }
    public boolean contientObjet(String nomObjet){
         if(this.lesObjets.containsKey(nomObjet)){
            return true;
        }
        return false;
       
    }*/
    
  public boolean contientVivant(String nomVivant){
    return this.lesVivants.containsKey(nomVivant);
  }


/** Methode permettant de savoir si un vivant est present dans la piece
  * @param vivant : Le vivant qu'on souhaite verifier
  */
  public boolean contientVivant(Vivant vivant){
    return contientVivant(vivant.getNom());
  }
    
    
    //public void entrer(Vivant vivant){

			//this.lesVivants.put(vivant.getNom(),vivant);
	//}
     //public Map<String, Objet> getObjets(){
       // return this.lesObjets;
    //}
    
    public Map<String, Vivant> getVivants(){
        return this.lesVivants;
    }
    

   /*public Objet retirer(Objet objet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
	
        return retirer(objet.getNom());

    }
    
    public Objet retirer(String nomObjet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{

		if(!this.contientObjet(nomObjet)){
			throw new ObjetAbsentDeLaPieceException("L'objet : "+nomObjet+" n'est pas dans la pièce");
		}

		if(! ((Objet)this.getMonde().getEntite(nomObjet)).estDeplacable()){
			throw new ObjetNonDeplacableException("L'objet : "+ nomObjet+"n'est pas déplacable");
		}


        Objet aRetirer = this.lesObjets.get(nomObjet);
        this.lesObjets.remove(nomObjet);
        return aRetirer;
    }


    public Vivant sortir(String nomVivant) throws VivantAbsentDeLaPieceException{
		if(!this.contientVivant(nomVivant)){
			
			throw new VivantAbsentDeLaPieceException("Le vivant : "+nomVivant+" n'est pas dans la pièce");
		}

	    Vivant aRetirer = this.lesVivants.get(nomVivant);
        this.lesVivants.remove(nomVivant);
        return aRetirer;
    }
     public Vivant sortir(Vivant vivant) throws VivantAbsentDeLaPieceException{
		

		return sortir(vivant.getNom());
	}
    */

/** Methode permettant d'enlever un objet de la piece
  * @param nom : nom de l'objet a retirer
  * @throws ObjetNonDeplacableException : Si l'objet que l'on souhaite prendre n'est pas deplacable
  * @throws ObjetAbsentDeLaPieceException : Si l'objet que l'on souhaite prendre n'est pas dans la piece
  */
  public Objet retirer (String nom) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
    if(!(this.contientObjet(nom))) {
      throw new ObjetAbsentDeLaPieceException("L'objet " + nom + " n'est pas present dans la piece");
    }
    if (!(((Objet)(this.getMonde().getEntite(nom))).estDeplacable())){
      throw new ObjetNonDeplacableException("L'objet " + nom + " n'est pas deplacable");
    }
    return this.contenuPiece.remove(nom);

  }


/** Methode permettant d'enlever un objet de la piece
  * @param obj : l'objet a retirer
  * @throws ObjetNonDeplacableException : Si l'objet que l'on souhaite prendre n'est pas deplacable
  * @throws ObjetAbsentDeLaPieceException : Si l'objet que l'on souhaite prendre n'est pas dans la piece
  */
  public Objet retirer(Objet obj) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
    return retirer(obj.getNom());
  }


/** Methode faisant entrer un vivant dans la piece
  * @param vivant : Vivant a faire entrer dans la piece
  */
  public void entrer (Vivant vivant){
    this.lesVivants.put(vivant.getNom(), vivant);
  }


/** Methode faisant sortir un vivant de la piece
  * @param nomVivant : nom du Vivant a faire sortir
  * @throws VivantAbsentDeLaPieceException : lorsque le Vivant que l'on veut faire sortir de la piece ne se trouve pas dans la piece
  */
 /* public Vivant sortir (String nomVivant) throws VivantAbsentDeLaPieceException {
    if (!(this.contientVivant(nomVivant))){
      throw new VivantAbsentDeLaPieceException(nomVivant + " n'est pas present dans la piece");
    }
    this.lesVivants.remove(nomVivant);
  }


/** Methode faisant sortir un vivant de la piece
  * @param vivant : Vivant a faire sortir de la piece
  * @throws VivantAbsentDeLaPieceException : lorsque le Vivant que l'on veut faire sortir de la piece ne se trouve pas dans la piece
  */
 /* public Vivant sortir(Vivant vivant)throws VivantAbsentDeLaPieceException {
    this.sortir(vivant.getNom());
  }
*/



/** Methode permettant de savoir si un vivant est dans la piece
  * @param nomVivant : nom du vivant qu'on souhaite verifier
  */
 
public Vivant sortir(Vivant vivant) throws VivantAbsentDeLaPieceException {
        return sortir(vivant.getNom());
    }

    /**
     * Sort un vivant de la pièce (le retire de la pièce) grace à la chaine de
     * caractère le désignant
     * 
     * @param nomVivant chaine de caractère désignant le vivant
     * @return le vivant en question
     */
    public Vivant sortir(String nomVivant) throws VivantAbsentDeLaPieceException {
        if (!this.lesVivants.containsKey(nomVivant)) {
            throw new VivantAbsentDeLaPieceException();
        }
        Vivant tmp = this.lesVivants.get(nomVivant);
        this.lesVivants.remove(nomVivant);
        return tmp;
    }

    @Override
    public String toString(){
        StringBuilder str=new StringBuilder();
        str.append(this.getNom()+"\n");
        str.append("Objets dans la piece : \n");
		for(String nomObj : this.lesObjets.keySet()){
			str.append(nomObj).append("\n");
		}

         str.append("Vivant dans la piece : \n");
		for(String nomVivant : this.lesVivants.keySet()){
			str.append(nomVivant).append("\n");
		}
		return str.toString();
    }


}
