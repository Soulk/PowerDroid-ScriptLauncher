import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by David on 27/01/2017.
 */
public class Connexion {
    private String url, dbname, password;
    public static Connection dbconnect;
    public Connexion(String url, String dbname, String password) {
        this.url = url;
        this.dbname = dbname;
        this.password = password;
        getConnection();
    }

    public void getConnection() {
        try {
            if(dbconnect == null || dbconnect.isClosed()) {
                Connection c = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    c = DriverManager
                            .getConnection(url, dbname, password);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
                System.out.println("Opened database successfully");
                dbconnect = c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getDbconnect() {
        return dbconnect;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
