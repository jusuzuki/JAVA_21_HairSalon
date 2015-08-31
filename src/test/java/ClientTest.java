import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {
  @Rule
  public DatabaseRule  database= new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void all_savesIntoDatabase_true() {
  Client myClient = new Client("Carmen", 1);
  myClient.save();
  assertEquals(Client.all().get(0).getNameClient(), "Carmen");
  }

  @Test
  public void clients_instantiatesCorrectly_true() {
    Client test = new Client("Carmen", 1);
    assertEquals(true, test instanceof Client);
  }

  @Test
    public void find_findsClientInDatabase_true() {
      Client myClient = new Client("Kate", 1);
      myClient.save();
      Client savedClient = Client.find(myClient.getId());
      assertEquals(savedClient.getNameClient(), "Kate");
    }
}
