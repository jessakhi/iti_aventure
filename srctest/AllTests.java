
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import fr.insarouen.asi.prog.asiaventure.elements.AllTestElements;
import fr.insarouen.asi.prog.asiaventure.elements.vivants.AllTestVivant;
import fr.insarouen.asi.prog.asiaventure.elements.structure.AllTestStructure;
@RunWith(Suite.class)
@Suite.SuiteClasses({
AllTestElements.class,
AllTestStructure.class,
AllTestVivant.class,
})
public class AllTests {}