package fr.insarouen.asi.prog.asiaventure.elements;
import fr.insarouen.asi.prog.asiaventure.EntiteDejaDansUnAutreMondeException;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.Monde;
import java.io.Serializable;


/** Classe définissant une entité quelconque de notre jeu.
  * Possede 2 attributs
  * nom : Defini le nom de notre entitesTemp
  * monde : Defini le monde dans lequel se trouve cette entite
  */
  
public abstract class Entite implements Serializable {

    /** La taille maximum de caractere du nom d'une entite. */
	public static int MAXIMUMNOMALEATOIRE;
	/**Le nom d'une entite */
	private final String nom;
	/**Le monde auquel appartient une entite */
	private final Monde monde;
 /**
  * Constructeur d'une Entite.
  * @param nom : représente le nom de l'entite que l'on veut créer
  * @param monde : représente le monde dans lequel se situe l'entite
  * @throws NomDEntiteDejaUtiliseDansLeMondeException : lorsque l'entite est deja presente dans ce monde
  */
    
    public Entite(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        this.nom = nom;
        this.monde = monde;
        try{
            monde.ajouter(this);
        }
        catch(EntiteDejaDansUnAutreMondeException e){
            System.out.println("Entite Deja Dans Un Autre Monde ");
            e.printStackTrace();
        }   
    }
/** Retourne le nom de l'entite
  * @return le nom de l'Entite sous forme de String.
  */
    public String getNom(){
        return this.nom;
    }
 /** Retourne le monde de l'entite
  * @return une instance de Monde représentant le monde de l'Entite
  */
    public Monde getMonde(){
        return this.monde;
    }
    
    public String toString(){
        return String.format("%s",getNom());
    }
/** Permet de savoir si 2 Entites sont égales
  * @param o : Deuxième entite à comparer
  * @return un Boolean qui vaut vrai si les deux objets sont égaux, faux sinon
  */
    public boolean equals(Object o){
        return !( (o.getClass() != this.getClass()) || (((Entite)o).getNom() != this.nom) || (((Entite)o).getMonde() != this.monde) || (o==null));
    }
/** Méthode de hashage d'une Entite
  * @return l'entier correspondant au hashage d'Entite
  */
    public int hashCode(){
        return 13*monde.hashCode() + 5*nom.hashCode();
    }

}
