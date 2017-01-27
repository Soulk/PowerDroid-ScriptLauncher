import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 27/01/2017.
 */
public class ScriptLauncher {
    List<Script> l_script;
    Connexion con;
    String dbname;

    public ScriptLauncher(String url, String dbname, String password) {
        this.con = new Connexion(url, dbname, password);
        this.dbname= dbname;
        l_script = new ArrayList<Script>();
    }
    public void run() {
        getAllScript();
        while(true) {

        }
    }

    public void getAllScript() {
        DatabaseMetaData md = null;
        Statement stmt = null;
        String query = "select * from script";
        try {
            stmt = con.getDbconnect().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                l_script.add(formatResult(rs));
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
    }
    private Script formatResult(ResultSet rs) {
        Script s = new Script();
        try {
            s.setId(rs.getInt("idscript"));
            s.setIdUser(rs.getInt("idUser"));
            s.setMethod(rs.getString("method"));
           /* s.setFilenameapk(rs.getString("filenameapk"));
            s.setFilenameapktest(rs.getString("filenameapktest"));
            s.setFilenamemanifest(rs.getString("filenamemanifest"));
            s.setFilenamemanifestandroid(rs.getString("filenamemanifestandroid"));*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
}
