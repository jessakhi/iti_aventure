package fr.insarouen.asi.prog.asiaventure.elements;

import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;

public interface Activable{

  public abstract boolean activableAvec(Objet obj);
  public abstract void activer() throws ActivationException;
  public abstract void activerAvec(Objet obj) throws ActivationException;
  
}
