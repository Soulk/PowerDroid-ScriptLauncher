package database;

import model.ScriptFiles;

import java.io.*;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Geoffrey on 27/01/2017.
 */
public class FileDatabase {

    public static ScriptFiles select(int id){
        ScriptFiles scriptFiles = null;
        DatabaseMetaData md = null;
        Statement stmt = null;
        String query = "select * from script where idscript = " + id;
        try {
            stmt = Connexion.getDbconnect().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                scriptFiles = formatResult(rs);
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
        return scriptFiles;
    }

    private static ScriptFiles formatResult(ResultSet rs) {
        ScriptFiles s = new ScriptFiles();
        try {
            s.setId(rs.getInt("id"));

            s.setApk(getFile(rs.getString("filenameapk"), rs.getBytes("dataapk")));
            s.setApk(getFile(rs.getString("filenamemanifest"), rs.getBytes("datamanifest")));
            s.setApk(getFile(rs.getString("filenameapktest"), rs.getBytes("dataapktest")));
            s.setApk(getFile(rs.getString("filenamemanifestandroid"), rs.getBytes("datamanifestandroid")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    private static File getFile(String filename, byte[] data) throws IOException {
        File file = new File(filename);
        if(!file.exists()) file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.close();
        return file;
    }
}
