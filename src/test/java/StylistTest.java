import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {
  @Rule
  public DatabaseRule  database= new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void all_savesIntoDatabase_true() {
  Stylist newStylist = new Stylist("Shane");
  newStylist.save();
  assertEquals(Stylist.all().get(0).getNameStylist(), "Shane");
  }

  @Test
  public void stylists_instantiatesCorrectly_true() {
    Stylist test = new Stylist("Shane");
    assertEquals(true, test instanceof Stylist);
  }

  @Test
    public void find_findsStylistInDatabase_true() {
      Stylist newStylist = new Stylist("Kate");
      newStylist.save();
      Stylist savedStylist = Stylist.find(newStylist.getId());
      assertEquals(savedStylist.getNameStylist(), "Kate");
    }
}
