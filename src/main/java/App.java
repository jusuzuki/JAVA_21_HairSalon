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

    //HOMEPAGE
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      //show all stylists
      List<Stylist> stylists = Stylist.all();
      model.put("stylists", stylists);

      //show all clients
      List<Client> clients = Client.all();
      model.put("clients", clients);

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    //ADD NEW STYLIST
    post("/stylists/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      //add new stylist
      String newStylistName = request.queryParams("newStylistName");
      Stylist newStylist = new Stylist(newStylistName);
      newStylist.save();

      //show all stylists
      List<Stylist> stylists = Stylist.all();
      model.put("stylists", stylists);

      //show all clients
      List<Client> clients = Client.all();
      model.put("clients", clients);

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    //ADD NEW CLIENT
    post("/clients/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      //add new client
      String newClientName = request.queryParams("newClientName");
      int clientStylistId = Integer.parseInt(request.queryParams("clientStylist"));
      Client newClient = new Client(newClientName, clientStylistId);
      newClient.save();

      //show all stylists
      List<Stylist> stylists = Stylist.all();
      model.put("stylists", stylists);

      //show all clients
      List<Client> clients = Client.all();
      model.put("clients", clients);

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //REMOVE STYLIST
    get("/stylists/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      //remove stylist
      int stylistId = Integer.parseInt(request.params(":id"));
      Stylist.removeStylist(stylistId);
      Stylist.removeStylistsClients(stylistId);

      //show all stylists
      List<Stylist> stylists = Stylist.all();
      model.put("stylists", stylists);

      //show all clients
      List<Client> clients = Client.all();
      model.put("clients", clients);

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //REMOVE CLIENT
    get("/clients/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      //remove stylist
      int clientId = Integer.parseInt(request.params(":id"));
      Client.removeClient(clientId);

      //show all stylists
      List<Stylist> stylists = Stylist.all();
      model.put("stylists", stylists);

      //show all clients
      List<Client> clients = Client.all();
      model.put("clients", clients);

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //UPDATE STYLIST OPEN FORM:
    get("/stylists/:id/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      //update stylist
      int stylistId = Integer.parseInt(request.params(":id"));;
      Stylist stylist = Stylist.find(stylistId);
      model.put("stylist", stylist);


      model.put("template", "templates/editstylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //UPDATE STYLIST POST FORM:
    post("/stylists/:id/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      //update stylist
      int stylistId = Integer.parseInt(request.params(":id"));
      String newStylistName = request.queryParams("newStylistName");
      Stylist.update(newStylistName, stylistId);

      //show all stylists
      List<Stylist> stylists = Stylist.all();
      model.put("stylists", stylists);

      //show all clients
      List<Client> clients = Client.all();
      model.put("clients", clients);

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    //UPDATE CLIENT OPEN FORM:
    get("/clients/:id/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      //update stylist
      int clientId = Integer.parseInt(request.params(":id"));;
      Client client = Client.find(clientId);
      model.put("client", client);

      //show all stylists
      List<Stylist> stylists = Stylist.all();
      model.put("stylists", stylists);

      model.put("template", "templates/editclient.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //UPDATE CLIENT POST FORM:
    post("/clients/:id/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      //update stylist
      int clientId = Integer.parseInt(request.params(":id"));
      String newClientName = request.queryParams("newClientName");
      int stylistId = Integer.parseInt(request.queryParams("clientStylist"));
      Client.updateName(clientId, newClientName);
      Client.updateStylist(clientId, stylistId);

      //show all stylists
      List<Stylist> stylists = Stylist.all();
      model.put("stylists", stylists);

      //show all clients
      List<Client> clients = Client.all();
      model.put("clients", clients);

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



 }
}
