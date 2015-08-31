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

  public static Client find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM clients where id=:id";
    Client client = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
    return client;
    }
  }

}
