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

public class TestMonde{

    public Entite entite1;
    public Entite entite2;
    public Monde monde;
    @Before
    public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException {
        monde = new Monde("mondemonde");
        entite1 = new Entite("entite1monde",monde){};
        entite2 = new Entite("entite2monde",monde){};
    }

    @Test
    public void test_getEntite(){
        assertThat(monde.getEntite("entite1monde"), IsEqual.equalTo(entite1));  
    }

    @Test
    public void test_getNom(){
        assertThat(monde.getNom(), IsEqual.equalTo("mondemonde"));  
    }
}
