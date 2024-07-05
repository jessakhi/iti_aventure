package fr.insarouen.asi.prog.asiaventure;
import fr.insarouen.asi.prog.asiaventure.elements.*;
import fr.insarouen.asi.prog.asiaventure.elements.objets.*;
import fr.insarouen.asi.prog.asiaventure.elements.structure.*;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.*;

public class ClassMainTestEntite{
    public static void main(String[] args){

        /*
        
        //test 
        Monde monde = new Monde("monde1");
        Monde monde2 = new Monde("monde2");
        
        
        
        
        //tests monde
	System.out.println("**************************\n");
        System.out.println("TEST ENTITE \n");
        System.out.println("**************************\n");
        Entite entite = new Entite("nom",monde2){};

        System.out.println(entite);
        Entite entite2 = new Entite("nom2",monde2){};
        Entite entite3 = new Entite("nom",monde){};
        Entite entite4 = new Entite("nom4",monde){};
        Entite entite5 = new Entite("nom5",monde){};
        
        System.out.println(entite + "," + entite.equals(entite3) + " -hashcode :"+ entite.hashCode() + " ---"+entite3.hashCode());
        System.out.println(entite + "," + entite.equals(entite2));
        System.out.println("fin test entite");
        
	System.out.println("**************************\n");
        System.out.println("TEST OBJET \n");
        System.out.println("**************************\n");
        
        Objet objet = new Objet("chose",monde){
            public boolean estDeplacable(){
                return false;
            };
        };
         Objet objet2 = new Objet("chose2",monde){
            public boolean estDeplacable(){
                return false;
            };
        };

        System.out.println(objet.estDeplacable());
        ElementStructurel element = new ElementStructurel("elem",monde){};
        PiedDeBiche pdb = new PiedDeBiche("pdb1",monde){};
        //System.out.println(element);
        
	System.out.println("**************************\n");
        System.out.println("TEST PIECE \n");
        System.out.println("**************************\n");
        System.out.println("\n test piece \n");
        
        Piece piece1 = new Piece("piece",monde);
        System.out.println(piece1);
        System.out.println("test deposer \n");
        piece1.deposer(pdb);
        piece1.deposer(objet);
        piece1.deposer(objet2);
        System.out.println(piece1);
        System.out.println(piece1.contientObjet(objet));
        System.out.println(piece1.contientObjet(objet2));
        System.out.println("test retirer \n");
        piece1.retirer("chose2");
	piece1.retirer("pdb1");
	System.out.println(piece1.contientObjet(pdb));
        System.out.println(piece1.contientObjet(objet));
        System.out.println(piece1.contientObjet(objet2));
        System.out.println(piece1);
        
        System.out.println("**************************\n");
        System.out.println("TEST VIVANT \n");
        System.out.println("**************************\n");
        piece1.deposer(pdb);
        piece1.deposer(objet);
        piece1.deposer(objet2);
        System.out.println("test constructeur \n");
        Objet[] listeObjets = new Objet [1];
        listeObjets[0] = objet;
        Vivant vivant = new Vivant ("vivant1",monde,50,50,piece1,listeObjets){};
        
        System.out.println(piece1.contientVivant(vivant));
        System.out.println(piece1);
        System.out.println("le vivant va prendre un objet\n");
        vivant.prendre(pdb);
        System.out.println("est ce que le vivant possede l'objet qu'il a pris ?\n");
        System.out.println(vivant.possede(pdb));
        System.out.println("est ce que la piece possede l'objet pris ?\n");
        System.out.println(piece1.contientObjet(pdb));
        System.out.println("le vivant va déposer cet objet\n");
        vivant.deposer(pdb);
        System.out.println("est ce que le vivant possede toujours cet objet? \n");
        System.out.println(vivant.possede(pdb));
        System.out.println("est ce que la piece possede l'objet déposé ?\n");
        System.out.println(piece1.contientObjet(pdb));


        System.out.println("**************************\n");
        System.out.println("TEST EXCEPTION nom monde \n");
        System.out.println("**************************\n");

        */

        Monde monde = new Monde("monde1");
        Monde monde2 = new Monde("monde2");
        //Objet[] listeObjets = new Objet [0];
        
        try{
            Entite entitet1 = new Entite("nomtest",monde){};
            Entite entitet2 = new Entite("nomt",monde){};
            Objet objet = new Objet("chose",monde){
                public boolean estDeplacable(){
                    return false;
                };
            };
             Objet objet2 = new Objet("chose2",monde){
                public boolean estDeplacable(){
                    return false;
                };
            };
            Piece piece1 = new Piece("piece",monde);
            Piece piece2 = new Piece("piece2",monde);
            piece1.deposer(objet);
            piece1.deposer(objet2);
            Vivant vivant = new Vivant ("vivant1",monde,50,50,piece1){};
            //vivant.prendre(objet);
            System.out.println("vivant :" + vivant);
            //vivant.prendre(objet2);
            //piece2.sortir(vivant);
            vivant.deposer(objet2);
        }
        catch(NomDEntiteDejaUtiliseDansLeMondeException e){
            e.printStackTrace();
        }
        //catch(ObjetAbsentDeLaPieceException e){
            //e.printStackTrace();
        //}
        //catch(VivantAbsentDeLaPieceException e){
            //e.printStackTrace();
        //} 
        //catch(ObjetNonDeplacableException e){
           // e.printStackTrace();
        //} 
        catch(ObjetNonPossedeParLeVivantException e){
            e.printStackTrace();
        }




    }


}


