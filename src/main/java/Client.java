import java.util.List;
import org.sql2o.*;
import java.util.HashSet;
import java.util.ArrayList;

public class Client {

  private int id;
  private String name_client;
  private int stylist_id;

  public Client (String name_client, int stylist_id){
    this.name_client = name_client;
    this.stylist_id = stylist_id;
  }

  public String getNameClient(){
    return name_client;
  }

  public int getStylistId(){
    return stylist_id;
  }

  public int getId() {
    return id;
  }

  //add new client
  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO clients (name_client, stylist_id) VALUES (:name_client, :stylist_id)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name_client", name_client)
      .addParameter("stylist_id", stylist_id)
      .executeUpdate()
      .getKey();
      }
    }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  //find client by id
  public static Client find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM clients where id=:id";
    Client client = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
    return client;
    }
  }

  //remove client by id
  public static void removeClient(int clientId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id",clientId)
        .executeUpdate();
    }
  }

  //show stylist name
  public static String stylistName(int stylist_id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT name_stylist FROM stylists where id=:id";
    return con.createQuery(sql)
      .addParameter("id", stylist_id)
      .executeAndFetchFirst(String.class);
    }
  }

  //update client name
  public static void updateName(int id, String newClientName) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name_client = :name_client WHERE id=:id";
      con.createQuery(sql)
        .addParameter("id",id)
        .addParameter("name_client", newClientName)
        .executeUpdate();
    }
  }

  //update client's stylist
  public static void updateStylist(int id, int newStylistId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET stylist_id = :stylist_id WHERE id=:id";
      con.createQuery(sql)
        .addParameter("id",id)
        .addParameter("stylist_id", newStylistId)
        .executeUpdate();
    }
  }

  //find all clients that belong to one stylist
  public static List<Client> stylistsClients(int stylistId) {
    String sql = "SELECT * FROM clients WHERE stylist_id = :stylist_id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("stylist_id", stylistId)
      .executeAndFetch(Client.class);
    }
  }

}
