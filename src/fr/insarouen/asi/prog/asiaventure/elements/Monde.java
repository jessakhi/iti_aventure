package fr.insarouen.asi.prog.asiaventure.elements;
import fr.insarouen.asi.prog.asiaventure.ASIAventureException;
import fr.insarouen.asi.prog.asiaventure.EntiteDejaDansUnAutreMondeException;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.io.Serializable;

import java.util.stream.Collectors;
import java.util.Collection;
import fr.insarouen.asi.prog.asiaventure.elements.Executable;

/**
  * classe représentant un Monde dans lequel évoluent des Entites
  * Possède 2 attributs
  * nomDuMonde : représente le nom du monde
  * entites : contient les entites évoluant dans notre monde
  */
  
public class Monde{

    final private String nom; 
    private Map<String,Entite> entites;
    
  /** contructeur de Monde
  * @param nomDuMonde : nom du monde que l'on veut créer
  * @return Une instance du Monde que l'on vient de créer
  */
    public Monde(String nom){
        this.nom = nom;
        this.entites = new HashMap<String,Entite>();
    }
 /**Méthode retournant une entite parmis celles présentes
  * @param nomEntite : nom de l'Entite que l'on veut récupérer
  * @return l'Entite récupérée : cette entité vaut null si non présente dans le Monde
  */
    public Entite getEntite(String nomEntite){

        if(this.entites.containsKey(nomEntite)){
			return this.entites.get(nomEntite);
		}
		return null;
    }

    public String getNom(){
        return this.nom;
    }
    
      public Collection<Executable> getExecutables(){
    return this.entites.values().stream()
                       .filter(i->(i instanceof Executable))
                       .map(i -> (Executable)i)
                       .collect(Collectors.toList());

  }
    
    
    
  /** Méthode permettant d'ajouter une Entite à notre monde
  * @param entite : Entite à ajouter
  * @throws NomDEntiteDejaUtiliseDansLeMondeException : Lorsque l'entite est deja crée dans ce monde
  * @throws EntiteDejaDansUnAutreMondeException : Lorsque l'entite est presente dans un autre monde
  */
  
    public void ajouter(Entite e) throws EntiteDejaDansUnAutreMondeException, NomDEntiteDejaUtiliseDansLeMondeException{
        for(String nom : this.entites.keySet()){
				if(nom.equals(e.getNom())){
					throw new NomDEntiteDejaUtiliseDansLeMondeException("Le nom "+e.getNom()+ " est déjà utilise");
				}
		}

			if(!e.getMonde().equals(this)){
				throw new EntiteDejaDansUnAutreMondeException("L'entite"+e.getNom()+ "déjà dans un autre monde ");
			}

			this.entites.put(e.getNom(),e);
        
    }
    
  /** Méthode permettant d'avoir une description de notre Monde
  * @return une instance de String contenant la description de notre Monde
  */
    public String toString(){
        StringBuilder str = new StringBuilder();
		str.append(this.nom+"\n");
		for(String nom : this.entites.keySet()){
			str.append(nom).append("\n");
		}
		return str.toString();
    }
    
    /*public Collection<Executable> getExecutables(){
      public String toString(){
        String nomEntites = " ";
	    for(int i=0; i< this.entites.length; i++){
	        nomEntites = nomEntites + "\n" + this.entites[i];
	    }
	    return "nom du monde : " + this.nom + " nom de ses entites"+ nomEntites;
    }
    }*/

}
