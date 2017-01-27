import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 27/01/2017.
 */
public class ScriptLauncher {
    String adresse;
    List<Script> l_script;
    Connexion con;
    String dbname;

    public ScriptLauncher(String url, String dbname, String password) {
        this.con = new Connexion(url, dbname, password);
        this.dbname= dbname;
        this.adresse = adresse;
        l_script = new ArrayList<Script>();
    }

    public Script getAllScript() {
        DatabaseMetaData md = null;
        Statement stmt = null;
        String query = "select * from script";
        try {
            stmt = con.getDbconnect().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getInt("idscript"));
            }
        } catch (SQLException e ) {
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
