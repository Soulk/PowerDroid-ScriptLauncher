package database;

import model.Script;
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
        String query = "select * from file_table where id = " + id;
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
            s.setManifest(getFile(rs.getString("filenamemanifest"), rs.getBytes("datamanifest")));
            s.setApkTest(getFile(rs.getString("filenameapktest"), rs.getBytes("dataapktest")));
            s.setManifestAndroid(getFile(rs.getString("filenamemanifestandroid"), rs.getBytes("datamanifestandroid")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    private static File getFile(String filename, byte[] data) throws IOException {
        File file = new File(filename);
        if(!filename.equals("")) {
            if (!file.exists())
                file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            return file;
        }
        return null;
    }

    public static void delete(ScriptFiles scriptFiles) {
        File file=null;
        if(scriptFiles.getApk() != null) {
            file = new File(scriptFiles.getApk().getName());
            if (file.exists())
                file.delete();
        }

        if(scriptFiles.getApkTest()!= null) {
            file = new File(scriptFiles.getApkTest().getName());
            if (file.exists())
                file.delete();
        }

        if(scriptFiles.getManifest() != null) {
            file = new File(scriptFiles.getManifest().getName());
            if (file.exists())
                file.delete();
        }
        if(!scriptFiles.getManifestAndroid().getName().equals("")) {
            file = new File(scriptFiles.getManifestAndroid().getName());
            if (file.exists())
                file.delete();
        }
    }
}
