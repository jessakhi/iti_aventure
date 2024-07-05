package fr.insarouen.asi.prog.asiaventure.elements.structure;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import java.awt.Color;
import fr.insarouen.asi.prog.asiaventure.elements.Entite;
import fr.insarouen.asi.prog.asiaventure.elements.Monde;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.prog.asiaventure.elements.objets.Objet;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
public class TestPiece{

    public Entite entite1;
    public Entite entite2;
    public Monde monde;
    public VivantTest vivant;
    public VivantTest vivant2;
    public Piece piece;
    public ObjetTest objet;
    public ObjetTest objet2;
    public ObjetTest objet3;
    public ObjetTest objet4;
    public ObjetTest objet5;
    public ObjetTest objet6;

    private class ObjetTest extends Objet{
            public ObjetTest(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
                super(nom,monde);
            }
            public boolean estDeplacable(){
                return true;
            }
        }
    private class VivantTest extends Vivant{
        public VivantTest(String nomVivant, Monde monde, int pdv, int pdf, Piece piece, Objet ... objs ) throws NomDEntiteDejaUtiliseDansLeMondeException{
             super(nomVivant,monde,pdv,pdf,piece,objs);
        }
    }

    @Before
    public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException {
        monde = new Monde("mondepiece");
        piece = new Piece("piece",monde);
        
        entite1 = new Entite("entite1piece",monde){};
        entite2 = new Entite("entite2piece",monde){};
        
        
        objet = new ObjetTest("chosepiece",monde);
        objet2 = new ObjetTest("chose2piece",monde);
        objet3 = new ObjetTest("chose3piece",monde);
        objet4 = new ObjetTest("chose4piece",monde);
        objet5 = new ObjetTest("chose5piece",monde);
        objet6 = new ObjetTest("chose6piece",monde);
        /*Objet objet = new Objet("chosepiece",monde){
            public boolean estDeplacable(){
                return true;
            };
        };
        Objet objet2 = new Objet("chose2piece",monde){
            
        };
        Objet objet3 = new Objet("chose3piece",monde){
            public boolean estDeplacable(){
                return true;
            };
        };
        Objet objet4 = new Objet("chose4piece",monde){
            public boolean estDeplacable(){
                return true;
            };
        };*/

        // Objet[] listeObjets = new Objet [2];
        // listeObjets[0] = objet;
        // listeObjets[1] = objet2;
        vivant = new VivantTest("vivpiece",monde,10,10,piece,listeObjets){};
        vivant2 = new VivantTest("viv2piece",monde,10,10,piece,objet6){};
    }
    @Test
    public void test_getObjetsPiece() {
        // Objet[] objets = new Objet[2];
        // objets[0] = objet3; 
        // objets[1] = objet4;
        
        piece.deposer(objet3);
        piece.deposer(objet4);
        assertThat(piece.getObjets(), IsEqual.equalTo(objets));
    }

    @Test
    public void test_getVivantsPiece(){
        // Vivant[] vivants = new Vivant[2];
        // vivants[0]= vivant;
        // vivants[1]= vivant2;
        assertThat(piece.getVivants(), IsEqual.equalTo(vivants));
    }

    @Test
    public void test_contientObj(){
        
        System.out.println(piece);
        assertThat(piece.contientObjet(objet), is(false));
    }

    @Test
    public void test_contientVivant(){
       
        assertThat(piece.contientVivant(vivant), is(true));  
    }

    @Test
    public void test_deposerPiece(){
        
        assertThat(piece.contientObjet(objet3), is(false));
        piece.deposer(objet3); 
        assertThat(piece.contientObjet(objet3), is(true));
        System.out.println(piece.contientObjet(objet3));
        piece.deposer(objet);
        assertThat(piece.contientObjet(objet), is(true));
        }

    @Test
    public void test_entrerPiece()throws VivantAbsentDeLaPieceException{
        piece.sortir("vivpiece"); 
        assertThat(piece.contientVivant(vivant), is(false));
        piece.entrer(vivant); 
        assertThat(piece.contientVivant(vivant), is(true));
    }

    @Test
    public void test_retirerObj() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        piece.deposer(objet3);
        
        assertThat(piece.contientObjet(objet3), is(true));
        piece.retirer(objet3); 
        assertThat(piece.contientObjet(objet3), is(false));
        
    }
    @Test
    public void test_sortirPiece() throws VivantAbsentDeLaPieceException{
        assertThat(piece.contientVivant(vivant), is(true));
        piece.sortir("vivpiece"); 
        assertThat(piece.contientVivant(vivant), is(false));
    }

    @Test
    public void test_deposerObj() throws ObjetNonPossedeParLeVivantException,ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        piece.deposer(objet5);
        vivant.prendre(objet5);
        assertThat(vivant.possede(objet5), is(true));
        vivant.deposer(objet5);
        assertThat(vivant.possede(objet5), is(false));
    }

}
