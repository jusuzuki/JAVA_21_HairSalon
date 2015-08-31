import java.util.List;
import org.sql2o.*;
import java.util.HashSet;
import java.util.ArrayList;

public class Stylist {
  private int id;
  private String name_stylist;



  public Stylist(String name_stylist) {
    this.name_stylist = name_stylist;
  }

  public String getNameStylist() {
    return name_stylist;
  }

  public int getId() {
    return id;
  }

  public static List<Stylist> all() {
    String sql = "SELECT * FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO stylists (name_stylist) VALUES (:name_stylist)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name_stylist",name_stylist)
      .executeUpdate()
      .getKey();
    }
  }

  public static Stylist find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM stylists where id=:id";
    Stylist stylist = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Stylist.class);
    return stylist;
    }
  }

  public static void removeStylist(int stylistId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id",stylistId)
        .executeUpdate();
    }
  }

}