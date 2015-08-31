import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Iterator;
import java.time.LocalDateTime;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    //homepage
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      //show all stylists
      // List<Stylist> stylists = Stylist.all();
      // model.put("stylists", stylists);

      //show all clients
      // List<Client> clients = Client.all();
      // model.put("clients", clients);

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


 }
}
