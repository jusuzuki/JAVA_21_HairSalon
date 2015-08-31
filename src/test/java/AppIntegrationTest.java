import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppIntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

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


}
