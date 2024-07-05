package fr.insarouen.asi.prog.asiaventure.elements.vivants;
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
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.asi.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;


public class TestVivant{

    public Entite entite1;
    public Entite entite2;
    public Monde monde;
    public VivantTest vivant;
    public Piece piece;
    public ObjetTest objet;
    public ObjetTest objet2;
    public ObjetTest objet3;
    public ObjetTest[] listeObjets;

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
        
        
        monde = new Monde("mondevivant");
        piece = new Piece("piecevivant",monde);
        //entite1 = new Entite("entite1vivant",monde){};
        //entite2 = new Entite("entite2vivant",monde){};
        
        objet = new ObjetTest("chosev",monde);
        objet2 = new ObjetTest("chose2v",monde);
        objet3 = new ObjetTest("chose3v",monde);
        
        
        listeObjets = new ObjetTest [2];
        listeObjets[0] = objet;
        listeObjets[1] = objet2;
        vivant = new VivantTest("vivvivant",monde,10,10,piece,listeObjets){};

        piece.deposer(objet3);
    }

    @Test
    public void test_getObjets(){

        assertThat(vivant.getObjets(), IsEqual.equalTo(listeObjets));  
    }

    @Test
    public void test_getPiece(){
        assertThat(vivant.getPiece(), IsEqual.equalTo(piece));  
    }

    @Test
    public void test_getPointDeVie(){
        assertThat(vivant.getPointDeVie(), IsEqual.equalTo(10));  
    }

    @Test
    public void test_getPointDeForce(){
        assertThat(vivant.getPointDeForce(), IsEqual.equalTo(10));
    }

    @Test
    public void test_possedeObj(){
        assertThat(vivant.possede(objet), is(true));
    }
    @Test
    public void test_prendreObj() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        assertThat(vivant.possede(objet3), is(false));
        vivant.prendre(objet3);
        assertThat(vivant.possede(objet3), is(true));
    }

    @Test
    public void test_deposerObj() throws ObjetNonPossedeParLeVivantException, ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        vivant.prendre(objet3);
        assertThat(vivant.possede(objet3), is(true));
        vivant.deposer(objet3);
        assertThat(vivant.possede(objet3), is(false));
    }

}
