import java.util.ArrayList;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.rules.ExternalResource;
import org.sql2o.*;


public class AppIntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule  database= new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("HairSalon");
  }

  @Test
  public void stylistIsAddedCorrectlyTest() {
    goTo("http://localhost:4567/");
    fill("#newStylistName").with("Halsey");
    submit("#newStylist");
    assertThat(pageSource()).contains("Halsey");
  }

  @Test
  public void clientIsAddedCorrectlyTest() {
    goTo("http://localhost:4567/");
    fill("#newStylistName").with("Halsey");
    submit("#newStylist");
    fill("#newClientName").with("Hurricane");
    submit("#newClient");
    assertThat(pageSource()).contains("Hurricane");
  }

  @Test
    public void stylistIsDeleted() {
      goTo("http://localhost:4567/");
      fill("#newStylistName").with("Shane");
      submit("#newStylist");
      click("a", withText("Delete"));
      assertThat(pageSource()).doesNotContain("Shane");
    }

  @Test
    public void clientIsDeleted() {
      goTo("http://localhost:4567/");
      fill("#newStylistName").with("Coeur de Pirate");
      submit("#newStylist");
      fill("#newClientName").with("Beatrice Martin");
      submit("#newClient");
      goTo("http://localhost:4567/");
      find("a", 1, withText("Delete")).click();
      assertThat(pageSource()).doesNotContain("Beatrice Martin");
    }

  @Test
    public void stylistIsUpdated() {
      goTo("http://localhost:4567/");
      fill("#newStylistName").with("Say Lou");
      submit("#newStylist");
      click("a", withText("Edit"));
      fill("#newStylistName").with("Say Lou Lou");
      submit("#newStylist");
      assertThat(pageSource()).contains("Say Lou Lou");
    }

  @Test
    public void clientIsUpdated() {
      goTo("http://localhost:4567/");
      fill("#newStylistName").with("Say Lou");
      submit("#newStylist");
      fill("#newClientName").with("Miranda Anna");
      submit("#newClient");
      goTo("http://localhost:4567/");
      find("a", 1, withText("Edit")).click();
      fill("#newClientName").with("Elektra June");
      submit("#newClient");
      assertThat(pageSource()).contains("Elektra June");
    }
}
