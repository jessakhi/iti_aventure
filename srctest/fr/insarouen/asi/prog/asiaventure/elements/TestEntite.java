package fr.insarouen.asi.prog.asiaventure.elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import java.awt.Color;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import fr.insarouen.asi.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class TestEntite{
    public class EntiteTest extends Entite {
           public EntiteTest(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
                super(nom,monde);
           }
    }
    public EntiteTest entite1;
    public EntiteTest entite2;
    public Monde monde;
    @Before
    public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException {
        monde = new Monde("mondeentite");
        entite1 = new EntiteTest("entitee1entite",monde){};
        entite2 = new EntiteTest("entite2entite",monde){};
    }

    @Test
    public void test_GetNom(){
        assertThat(entite1.getNom(), IsEqual.equalTo("entitee1entite"));
        
    }

    @Test
    public void test_GetMonde(){
        assertThat(entite1.getMonde(), IsEqual.equalTo(monde));
    }

    @Test
    public void test_equals(){
        assertThat(entite1.equals(entite2), is(false));
    }

    @Test
    public void test_hashCode(){
        assertThat(entite1.hashCode(), IsEqual.equalTo(entite1.hashCode()));
    }

}
