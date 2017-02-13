package database;

import model.Script;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geoffrey on 27/01/2017.
 */
public class ScriptDatabase {

    public static List<Script> all() {

        List<Script> lScript = new ArrayList<Script>();

        Statement stmt = null;
        String query = "select * from script";
        try {
            stmt = Connexion.getDbconnect().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                lScript.add(formatResult(rs));
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
        return lScript;
    }
    private static Script formatResult(ResultSet rs) {
        Script s = new Script();
        try {
            s.setId(rs.getInt("idscript"));
            s.setIdFile(rs.getInt("idFile"));
            s.setMethod(rs.getString("method"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void delete(Script script){
        Statement stmt = null;
        String query = "DELETE from script where idscript=" + script.getId();

        try {
            stmt = Connexion.getDbconnect().createStatement();
            stmt.executeUpdate(query);
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
}
